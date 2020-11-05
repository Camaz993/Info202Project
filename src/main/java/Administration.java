
import dao.ProductData;
import dao.ProductList;
import dao.daoManager;
import gui.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mazca993
 */
public class Administration {
//    private static ProductData dao = new daoManager();
    private static ProductData dao = new daoManager();
    public static void main(String[] args) {
       // create the frame instance
        MainMenu frame = new MainMenu(dao);

        // centre the frame on the screen
        frame.setLocationRelativeTo(null);

        // show the frame
        frame.setVisible(true);
    }
}
