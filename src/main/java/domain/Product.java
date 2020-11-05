/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author mazca993
 */
public class Product {
    @NotNull(message = "id must be provided.")
    @NotNegative(message = "id must be zero or greater.")
    private Integer product_Id;
    @NotNull(message = "Name must be provided.")
    @NotBlank(message = "Name must be provided.")
    @Length(min=2, message="Name must contain at least two characters.")
    //@MaxLength(value=50, message = "Name must not exceed 50 characters")
    private String name;
    //Dont need anything for description
    //@MaxLength(value=50, message = "Name must not exceed 50 characters")
    private String description;
    @NotNull(message = "Price must be provided.")
    @NotNegative(message = "Price must be zero or greater.")
    private BigDecimal list_price;
    @NotNull(message = "Stock must be provided.")
    @NotNegative(message = "Stock must be zero or greater.")
    private Integer stock;
    @NotNull(message = "Category must be provided.")
    private String categories;

    public Product(Integer product_Id, String name, String description, BigDecimal list_price, Integer stock, String categories) {
        this.product_Id = product_Id;
        this.name = name;
        this.description = description;
        this.list_price = list_price;
        this.stock = stock;
        this.categories = categories;
    }

    public Product() {
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.product_Id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.product_Id, other.product_Id)) {
            return false;
        }
        return true;
    }
    

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(Integer product_Id) {
        this.product_Id = product_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return  "product_Id=" + product_Id;
    }  
    
}

