/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import domain.Product;
import domain.Sale;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mazca993
 */
public class CustomerDatabaseDAO implements CustomerDao {

    private String url = "jdbc:h2:tcp://localhost:9092/project;IFEXISTS=TRUE";

    public CustomerDatabaseDAO(String url) {
        this.url = url;
    }

    public CustomerDatabaseDAO() {
    }

@Override
        public void save(Customer customer) {
                String sql = "insert into customer (USERNAME,FIRST_NAME,SURNAME,PASSWORD,EMAILADDRESS,SHIPPINGADDRESS,CreditCardDetails,SALELIST ) values (?,?,?,?,?,?,?,?)";

        try (
                // get connection to database
                Connection dbCon = JdbcConnection.getConnection(url);
                // create the statement
                PreparedStatement stmt = dbCon.prepareStatement(sql);) {
            // copy the data from the student domain object into the SQL parameters
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getFirst_Name());
            stmt.setString(3, customer.getSurname());
            stmt.setString(4, customer.getPassword());
            stmt.setString(5, customer.getEmailAddress());
            stmt.setString(6, customer.getShippingAddress());
            stmt.setString(7, customer.getCreditCardDetails());
            stmt.setObject(8, customer.getSaleList());

            stmt.executeUpdate();  // execute the statement

        } catch (SQLException ex) {  // we are forced to catch SQLException
            // don't let the SQLException leak from our DAO encapsulation
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
        public Customer getCustomer(String username) {
        String sql = "select * from customer where username = ?";

                try (
                    // get connection to database
                    Connection connection = JdbcConnection.getConnection(url);

                    // create the statement
                    PreparedStatement stmt = connection.prepareStatement(sql);
                ) {
                    // set the parameter
                    stmt.setString(1, username);

                    // execute the query
                    ResultSet rs = stmt.executeQuery();

                    // query only returns a single result, so use 'if' instead of 'while'
                    if (rs.next()) {
                        Integer id = rs.getInt("Person_Id");
                        String user = rs.getString("username");
                        String firstname = rs.getString("first_Name");
                        String surname = rs.getString("surname");
                        String password = rs.getString("password");
                        String email = rs.getString("emailAddress");
                        String shipping = rs.getString("shippingAddress");
                        String creditCardDetails = rs.getString("CreditCardDetails");
                        ArrayList<Sale> saleList = (ArrayList<Sale>) rs.getObject("saleList");

                        return new Customer(id, user, firstname, surname, password, email, shipping, creditCardDetails,saleList);
                       

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
        public Boolean validateCredentials(String username, String password) {
        return true;
    }

}
