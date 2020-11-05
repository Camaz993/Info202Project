package dao;

import dao.DAOException;
import dao.SaleDAO;
import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleJdbcDAO implements SaleDAO {

	private static final String url = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";

	@Override
	public void save(Sale sale) {

            sale.setStatus("new");
            
		Connection con = JdbcConnection.getConnection(url);
		try {
			try (
					PreparedStatement insertSaleStmt = con.prepareStatement(
							"insert into Sale (date, person_Id, status) values (?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					PreparedStatement insertSaleItemStmt = con.prepareStatement(
							"insert into SaleItem (sale_Id, product_Id, quantity_purchased, sale_price) values (?,?,?,?)");

					PreparedStatement updateProductStmt = con.prepareStatement(
							"update product set stock = stock -(?) where id = (?)"); ){

				// Since saving and sale involves multiple statements across
				// multiple tables we need to control the transaction ourselves
				// to ensure our DB remains consistent.
				//
				// Turn off auto-commit which effectively starts a new transaction.
				con.setAutoCommit(false);

				Customer customer = sale.getBoughtBy();
                                

				// #### save the sale ### //

				// add a date to the sale if one doesn't already exist
				if(sale.getDate() == null) {
					sale.setDate(new Date());
				}

				// convert sale date into to java.sql.Timestamp
				Date date = sale.getDate();
				Timestamp timestamp = new Timestamp(date.getTime());

				// ****
				// write code here that saves the timestamp and personID in the
				// sale table using the insertSaleStmt statement.
                                insertSaleStmt.setTimestamp(1, timestamp);
                                insertSaleStmt.setString(3, sale.getStatus());
                                insertSaleStmt.setInt(2, customer.getPerson_Id());
                                
                                insertSaleStmt.executeUpdate();
				// ****


				// get the auto-generated sale ID from the database
				ResultSet rs = insertSaleStmt.getGeneratedKeys();

				Integer saleId = null;

				if (rs.next()) {
					saleId = rs.getInt(1);
				} else {
					throw new DAOException("Problem getting generated Sale ID");
				}


				Collection<SaleItem> items = sale.getSaleItemList();
                                

				for (SaleItem item : items) {

					Product product = item.getProduct();

					// ****
					// write code here that saves the sale item
					// using the insertSaleItemStmt statement.
                                        insertSaleItemStmt.setInt(1, saleId);
                                        insertSaleItemStmt.setInt(2, item.getProduct().getProduct_Id());
                                        insertSaleItemStmt.setInt(3, item.getQuantity_Purchased());
                                        insertSaleItemStmt.setBigDecimal(4, item.getSale_Price());
                                        
                                        insertSaleItemStmt.executeUpdate();
					// ****


					// ****
					// write code here that updates the product quantity using
					// the updateProductStmt statement.
                                        
                                        updateProductStmt.setInt(1, item.getQuantity_Purchased());
                                        updateProductStmt.setInt(2, item.getProduct().getProduct_Id());
                                        updateProductStmt.executeUpdate();
					// ****

				}

				// commit the transaction
				con.setAutoCommit(true);
			}
		} catch (SQLException ex) {

			Logger.getLogger(SaleJdbcDAO.class.getName()).log(Level.SEVERE, null, ex);

			try {
				// something went wrong so rollback
				con.rollback();

				// turn auto-commit back on
				con.setAutoCommit(true);

				// and throw an exception to tell the user something bad happened
				throw new DAOException(ex.getMessage(), ex);
			} catch (SQLException ex1) {
				throw new DAOException(ex1.getMessage(), ex1);
			}

		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				Logger.getLogger(SaleJdbcDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}