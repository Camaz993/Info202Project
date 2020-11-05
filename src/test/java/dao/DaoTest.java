/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mazca993
 */
public class DaoTest {

    //ProductData dao = new ProductList();
    ProductData dao = new daoManager("jdbc:h2:tcp://localhost:9092/project-testing");
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;

    public DaoTest() {
    }

    @Before
    public void setUp() {
        this.prodOne = new Product(1, "name1", "desc1", new BigDecimal("11.00"), new Integer("22"), "cat1");
        this.prodTwo = new Product(2, "name2", "desc2", new BigDecimal("33.00"), new Integer("44"), "cat2");
        this.prodThree = new Product(3, "name3", "desc3", new BigDecimal("55.00"), new Integer("66"), "cat3");

// save the products
        dao.add(prodOne);
        dao.add(prodTwo);
// Note: Intentionally not saving prodThree
    }

    @After
    public void tearDown() {
        dao.delete(prodOne);
        dao.delete(prodTwo);
        dao.delete(prodThree);
    }

    @Test
    public void testDaoSave() {
        // save the product using DAO
        dao.add(prodThree);
        // retrieve the same product via DAO
        Product retrieved = dao.searchID(3);
        // ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same",
                prodThree, retrieved);
    }

    @Test
    public void testDaoEdit() {
        // save the product using DAO
        prodOne.setName("newName");
        // retrieve the same product via DAO
        dao.add(prodOne);

        Product retrieved = dao.searchID(1);

        assertEquals("Retrieved product should reflect name change", retrieved.getName(), "newName");
        assertEquals(prodOne.getName(), "newName");

    }

    @Test
    public void testDaoDelete() {
        // delete the product via the DAO
        dao.delete(prodOne);

        // try to retrieve the deleted product
        Product retrieved = dao.searchID(1);
        // ensure that the student was not retrieved (should be null)
        assertNull("Product should no longer exist", retrieved);
    }

    @Test
    public void testDaoGetAll() {
        Collection<Product> products = dao.getProductList();
        // ensure the result includes the two saved products
        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));
        // ensure the result ONLY includes the two saved products
        assertEquals("Only 2 products in result", 2, products.size());
        // find prodOne - result is not a map, so we have to scan for it
        for (Product p : products) {
            if (p.equals(prodOne)) {
                // ensure that all of the details were correctly retrieved
                assertEquals(prodOne.getProduct_Id(), p.getProduct_Id());
                assertEquals(prodOne.getName(), p.getName());
                assertEquals(prodOne.getDescription(), p.getDescription());
                assertEquals(prodOne.getCategories(), p.getCategories());
                assertEquals(prodOne.getList_price(), p.getList_price());
                assertEquals(prodOne.getStock(), p.getStock());
            }
        }
    }

    @Test
    public void testDaoSearchByID() {
        Product p = dao.searchID(prodOne.getProduct_Id());

        // Check that the Product recieved by ProdOne's ID is in fact prodOne
        assertEquals(prodOne, p);
        if (p.equals(prodOne)) {
            // ensure that all of the details were correctly retrieved
            assertEquals(prodOne.getProduct_Id(), p.getProduct_Id());
            assertEquals(prodOne.getName(), p.getName());
            assertEquals(prodOne.getDescription(), p.getDescription());
            assertEquals(prodOne.getCategories(), p.getCategories());
            assertEquals(prodOne.getList_price(), p.getList_price());
            assertEquals(prodOne.getStock(), p.getStock());
        }
        //call getByID using a non-existent ID
        Product np = dao.searchID(0);

        //assert that the result is null
        assertEquals(np, null);
    }

    @Test
    public void testDaoSearchByCategory() {
        // Search for prodOne by category value
        Collection<Product> c = dao.filterCategory(prodOne.getCategories());

        //Make sure the returned Collection contains the Product searched for
        assertEquals((c.contains(prodOne)), true);

        //Make sure the returned Collection does not contain those we didn't search for
        assertTrue("Products should contain prodOne", (c.contains(prodOne)));
        assertEquals("Only 1 product in result", 1, c.size());

        Product p = c.iterator().next();
        if (p.equals(prodOne)) {
            // ensure that all of the details were correctly retrieved
            assertEquals(prodOne.getProduct_Id(), p.getProduct_Id());
            assertEquals(prodOne.getName(), p.getName());
            assertEquals(prodOne.getDescription(), p.getDescription());
            assertEquals(prodOne.getCategories(), p.getCategories());
            assertEquals(prodOne.getList_price(), p.getList_price());
            assertEquals(prodOne.getStock(), p.getStock());
        }
        
        assertTrue(dao.filterCategory("no such category").isEmpty());

    }

    @Test
    public void getCategories() {
        //Create collection of categories
        Collection c = dao.getCategories();

        //Make sure that the collection contains each category for saved products
        assertEquals((c.contains(prodOne.getCategories())), true);
        assertEquals((c.contains(prodTwo.getCategories())), true);

        //We didn't save prodThree in setup method, make sure it's not there
        assertEquals((c.contains(prodThree.getCategories())), false);
    }
}
