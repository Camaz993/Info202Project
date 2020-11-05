package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author mazca993
 */
public interface ProductData {

	//  public Product searchCAT(String category){
	//     if(category == null){
	//         return null;
	//     } else return categories.add(category);
	// }
	void add(Product newProduct);

	void delete(Product newProduct);

	Collection<String> getCategories();

	Collection<Product> getProductList();

	Product searchID(Integer productID);

   Collection<Product> filterCategory(String category);
                
	void setCategories(String category);
}
