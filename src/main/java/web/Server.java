/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

//import dao.CustomerCollectionsDAO;
import dao.CustomerDao;
import dao.CustomerDatabaseDAO;
import dao.SaleDAO;
import dao.SaleJdbcDAO;
import dao.daoManager;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author mazca993
 */
public class Server extends Jooby {

    private final daoManager dao = new daoManager();
    private final CustomerDao custDao = new CustomerDatabaseDAO();
    private final SaleDAO saleDao = new SaleJdbcDAO();

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        Server server = new Server();
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });
        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }

    public Server() {
        port(8080);
        get("/api/products/:id", (req) -> { // had commented
            int id = req.param("id").intValue();// had commented
            return dao.searchID(id);// had commented
        });// had commented
        use(new ProductModule(dao));
        use(new CustomerModule(custDao));
        use(new SaleModule(saleDao));
        use(new Gzon());
        use(new AssetModule());
    }
}
