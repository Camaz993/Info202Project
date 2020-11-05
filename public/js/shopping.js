/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";
class SaleItem {

    constructor(product, stock) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantity_Purchased = stock;
            this.sale_Price = product.list_price;
        }
    }

    getItemTotal() {
        return this.sale_Price * this.quantity_Purchased;
    }

}

class ShoppingCart {

    constructor() {
        this.saleItemList = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.saleItemList) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.saleItemList;
    }

    addItem(item) {
        this.saleItemList.push(item);
    }

    setCustomer(customer) {
        this.boughtBy = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.saleItemList) {
            total += item.getItemTotal();
        }
        return total;
    }

}

// create a new module, and load the other pluggable modules
var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

module.factory('productDAO', function ($resource) {
    return $resource('/api/products/:id');
});

module.controller('ProductController', function (productDAO, categoryDAO) {
    // load the products
    this.products = productDAO.query();
    this.categories = categoryDAO.query();
// click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
    };
    this.selectAll = function () {
        this.products = productDAO.query();
    };
});

module.factory('categoryDAO', function ($resource) {
    return $resource('/api/categories/:cat');
});

module.controller('CustomerController', function (registerDAO, signInDAO, $sessionStorage, $window) {
    // load the products
    this.registerCustomer = function (customer) {
        registerDAO.save(null, customer);
        console.log(customer);
        this.signInMessage = "Please sign in to continue.";
    };

// alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.signIn = function (username, password) {
// get customer from web service
        signInDAO.get({'username': username},
// success
                function (customer) {
// also store the retrieved customer
                    $sessionStorage.customer = customer;
// redirect to home
                    $window.location.href = '.';
                },
// fail
                function () {
                    ctrl.signInMessage = 'Sign in failed. Please try again.';
                }
        );
    };

    this.checkSignIn = function () {
        if ($sessionStorage.customer) {
            this.signedIn = true;
            this.welcome = "Welcome " + $sessionStorage.customer.first_Name;
        } else {
            this.signedIn = false;
        }     
    };
    
    this.signOut = function(){
        delete $sessionStorage.customer;
        $window.location.href = 'index.html';   
    };

});

module.factory('registerDAO', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.factory('saleDAO', function ($resource) {
    return $resource('/api/sales');
});

module.controller('CartController', function (cart, $sessionStorage, $window, saleDAO) {
    // load the products
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;

    this.storeProduct = function (product) {
        $sessionStorage.selectedProduct = product;
        $window.location.href = "./quantity_to_purchase.html";
    };

    this.addtoCart = function (stock){
        let saleItem = new SaleItem(this.selectedProduct, stock);
        cart.addItem(saleItem);
        $sessionStorage.cart = cart;
        $window.location.href = "./cart.html";
    };
    
        this.checkout = function(){
            this.customer = $sessionStorage.customer;
            cart.setCustomer(this.customer);
            saleDAO.save(cart);
            delete $sessionStorage.cart;
            $window.location.href = "./thankyou.html";
            
    };

});

