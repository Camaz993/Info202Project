/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author mazca993
 */
public class SaleItem {
    private Integer quantity_Purchased;
    private ArrayList<Product> ProductList;
    private BigDecimal sale_Price;
    private Sale ownedBy; //Relationship
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public SaleItem(Integer quantity_Purchased, ArrayList<Product> ProductList, BigDecimal sale_Price, Sale ownedBy) {
        this.quantity_Purchased = quantity_Purchased;
        this.ProductList = ProductList;
        this.sale_Price = sale_Price;
        this.ownedBy = ownedBy;
    }

    public Integer getQuantity_Purchased() {
        return quantity_Purchased;
    }

    public void setQuantity_Purchased(Integer quantity_Purchased) {
        this.quantity_Purchased = quantity_Purchased;
    }

    public ArrayList<Product> getProductList() {
        return ProductList;
    }

    public void setProductList(ArrayList<Product> ProductList) {
        this.ProductList = ProductList;
    }

    public BigDecimal getSale_Price() {
        return sale_Price;
    }

    public void setSale_Price(BigDecimal sale_Price) {
        this.sale_Price = sale_Price;
    }

    public Sale getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Sale ownedBy) {
        this.ownedBy = ownedBy;
    }

    public BigDecimal getItemTotal(){
        return sale_Price.multiply(new BigDecimal(quantity_Purchased));
    }
}
