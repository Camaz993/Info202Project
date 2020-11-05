/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProductData;
import org.jooby.Jooby;

/**
 *
 * @author mazca993
 */
public class ProductModule extends Jooby {

    public ProductModule(ProductData dao) {
        get("/api/products", () -> dao.getProductList());
        get("/api/products/:id", (req) -> {
            int id = req.param("id").intValue();
            return dao.searchID(id);
        });
        get("/api/categories", () -> dao.getCategories());
        get("api/categories/:category", (req) -> {
            String category = req.param("category").value();
            return dao.filterCategory(category);
    });

}
}
