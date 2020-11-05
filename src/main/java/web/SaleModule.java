/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import domain.Sale;
import domain.SaleItem;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jooby.Jooby;
import org.jooby.Status;

/**
 *
 * @author mazca993
 */
public class SaleModule extends Jooby {

    public SaleModule(SaleDAO dao) {

        post("/api/sales", (req, rsp) -> {
            Sale sale = req.body().to(Sale.class);
            System.out.println(sale);
            dao.save(sale);
            
            ArrayList<String> itemStrings = new ArrayList();
            
            for(SaleItem item: sale.getSaleItemList()){
                String productName = item.getProduct().getName();
                String quantity = item.getQuantity_Purchased().toString();
                String price = item.getSale_Price().toString();
                itemStrings.add("Name; " + productName + ", Price:" + price  + ", Quantity:" + quantity);
            }
            CompletableFuture.runAsync(() -> {
                try {
                    Email email = new SimpleEmail();
                    email.setHostName("localhost");
                    email.setSmtpPort(2525);
                    //email.setSSLOnConnect(true);
                    email.setFrom("SalesInfoShop@gmail.com");
                    email.setSubject("Sale Information");
                    email.setMsg(
                            "Dear " + sale.getBoughtBy().getFirst_Name() + " " + sale.getBoughtBy().getSurname() + "," + "\n" +
                                    "Address Shipped To; " + sale.getBoughtBy().getEmailAddress() + "\n" +
                                            "Items; " + itemStrings.toString() + "\n" +
                                                    "Total Price Of Order; " + sale.getTotal().toString() + "\n"
                    );
                    email.addTo(sale.getBoughtBy().getEmailAddress());
                    email.send();
                } catch (EmailException ex) {
                    Logger.getLogger(SaleModule.class.getName()).log(Level.SEVERE, null, ex);
                }
               });
            rsp.status(Status.CREATED);
        });
    }
}
