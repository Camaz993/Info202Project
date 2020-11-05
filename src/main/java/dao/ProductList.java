/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 *
 * @author mazca993
 */
public class ProductList implements ProductData {
     public static Collection<Product> productList = new HashSet<>();
     public static Collection<String> categories = new HashSet<>();
     public static final Map<Integer, Product> p = new HashMap<>();
     public static final Multimap<String, Product> mmCatProd = HashMultimap.create();

	  @Override
    public Collection<Product> getProductList() {
        return productList;
    }

	  @Override
    public Collection<String> getCategories() {
        return categories;
    }

	  @Override
    public void setCategories(String category) {
        categories.add(category);
    }
    
	  @Override
    public void delete(Product newProduct){
        productList.remove(newProduct);
        p.remove(newProduct.getProduct_Id());
    }
    
	  @Override
    public Product searchID(Integer productID){
        if(productID == null){
            return null;
        } else return p.get(productID);
    }
    
    @Override
   public Collection<Product> filterCategory(String category){
      if(category == null){
          return null;
        } else return mmCatProd.get(category);
   }
     
     @Override
    public void add(Product newProduct){
         productList.add(newProduct);
         p.put(newProduct.getProduct_Id(),newProduct);
         if(categories.contains(newProduct.getCategories()) == false){
             categories.add(newProduct.getCategories()); 
         
         }
        System.out.println(categories);
    }
          
}
   

   

    
    
   
