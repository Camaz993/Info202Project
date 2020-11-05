package dao;

//import java.net.URL;

import domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 *
 * @author mazca993
 */
public class daoManager implements ProductData {
    
        private String url = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";
  
	public daoManager(String url){
		this.url = url;
	}

	public daoManager() {           
	}

	@Override
	public void add(Product newProduct) { //save
            
            String sql="merge into product (id, name, description, categories, price, stock) values (?,?,?,?,?,?)";
            
              try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);

                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);
            ) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setInt(1, newProduct.getProduct_Id());
                stmt.setString(2, newProduct.getName());
                stmt.setString(3, newProduct.getDescription());
                stmt.setString(4, newProduct.getCategories());
                stmt.setBigDecimal(5, newProduct.getList_price());
                stmt.setInt(6, newProduct.getStock());

                stmt.executeUpdate();  // execute the statement

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
	}

	@Override
	public void delete(Product newProduct) {
		String sql = "delete from Product where id =?";
                
              try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);

                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);
            ) {
                // copy the data from the student domain object into the SQL parameters
                stmt.setInt(1, newProduct.getProduct_Id());

                stmt.executeUpdate();  // execute the statement

            } catch (SQLException ex) {  // we are forced to catch SQLException
                // don't let the SQLException leak from our DAO encapsulation
                throw new DAOException(ex.getMessage(), ex);
            }
	}

	@Override
	public Collection<String> getCategories() {
		String sql = "select distinct categories from product order by categories";

                try (
                    // get a connection to the database
                    Connection dbCon = JdbcConnection.getConnection(url);

                    // create the statement
                    PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
                    // execute the query
                    ResultSet rs = stmt.executeQuery();

                    // Using a List to preserve the order in which the data was returned from the query.
                    List<String> categories = new ArrayList<>();

                    // iterate through the query results
                    while (rs.next()) {

                        // get the data out of the query
                        String product_Category = rs.getString("categories");
                        // use the data to create a student object
                        
                        // and put it in the collection
                        categories.add(product_Category);
                    }

                    return categories;

                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }

	@Override
	public Collection<Product> getProductList() {
		String sql = "select * from product order by id";

                try (
                    // get a connection to the database
                    Connection dbCon = JdbcConnection.getConnection(url);

                    // create the statement
                    PreparedStatement stmt = dbCon.prepareStatement(sql);
                ) {
                    // execute the query
                    ResultSet rs = stmt.executeQuery();

                    // Using a List to preserve the order in which the data was returned from the query.
                    List<Product> products = new ArrayList<>();

                    // iterate through the query results
                    while (rs.next()) {

                        // get the data out of the query
                        Integer id = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        String categories = rs.getString("categories");
                        BigDecimal price = rs.getBigDecimal("price");
                        Integer stock = rs.getInt("stock");
                        // use the data to create a student object
                        Product p = new Product(id, name, description, price, stock, categories);

                        // and put it in the collection
                        products.add(p);
                    }

                    return products;

                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }


	@Override
	public Product searchID(Integer productID) {
		 String sql = "select * from product where id = ?";

                try (
                    // get connection to database
                    Connection connection = JdbcConnection.getConnection(url);

                    // create the statement
                    PreparedStatement stmt = connection.prepareStatement(sql);
                ) {
                    // set the parameter
                    stmt.setInt(1, productID);

                    // execute the query
                    ResultSet rs = stmt.executeQuery();

                    // query only returns a single result, so use 'if' instead of 'while'
                    if (rs.next()) {
                        Integer id = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        String categories = rs.getString("categories");
                        BigDecimal price = rs.getBigDecimal("price");
                        Integer stock = rs.getInt("stock");

                        return new Product(id, name, description, price, stock, categories);

                    } else {
                        // no student matches given ID so return null
                        return null;
                    }

                } catch (SQLException ex) {  // we are forced to catch SQLException
                    // don't let the SQLException leak from our DAO encapsulation
                    throw new DAOException(ex.getMessage(), ex);
                }
            }

	@Override
	public void setCategories(String category) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
        
        @Override
	public Collection<Product> filterCategory(String category) {
		String sql = "select * from product where categories = ?";

                try (
                    // get connection to database
                    Connection connection = JdbcConnection.getConnection(url);

                    // create the statement
                    PreparedStatement stmt = connection.prepareStatement(sql);
                ) {
                    // set the parameter
                    stmt.setString(1, category);
                    Collection<Product> products = new HashSet();
                    // execute the query
                    ResultSet rs = stmt.executeQuery();

                    // query only returns a single result, so use 'if' instead of 'while'
                    while (rs.next()) {
                        Integer id = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        String categories = rs.getString("categories");
                        BigDecimal price = rs.getBigDecimal("price");
                        Integer stock = rs.getInt("stock");

                        Product p = new Product(id, name, description, price, stock, categories);

                        products.add(p);
                        
                        // no student matches given ID so return null                       
                    }
                    return products;
                } catch (SQLException ex) {  // we are forced to catch SQLException
                    // don't let the SQLException leak from our DAO encapsulation
                    throw new DAOException(ex.getMessage(), ex);
                }
                }
}