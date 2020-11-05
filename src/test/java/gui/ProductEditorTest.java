package gui;

import dao.ProductData;
import domain.Product;
import gui.ProductEditor;
import gui.ProductReport;
import gui.helpers.SimpleListModel;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductEditorTest {

	private ProductData dao;
        private ProductReport vP;
	private DialogFixture fixture;
	private Robot robot;
        private Product prodOne;
        private Product prodTwo;

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();

		// Slow down the robot a little bit - default is 30 (milliseconds).
		// Do NOT make it less than 5 or you will have thread-race problems.
		robot.settings().delayBetweenEvents(75);

		// add some majors for testing with
		Collection<String> categories = new TreeSet<>();
		categories.add("cat1");
		categories.add("cat3");
                
                
                prodOne = new Product(1, "name1", "desc1", new BigDecimal("11.00"), new Integer("22"), "cat1");
                prodTwo = new Product(1, "name3", "desc3", new BigDecimal("13.00"), new Integer("23"), "cat3");
                
                Collection<Product> products = new ArrayList<>();
		products.add(prodOne);
		products.add(prodTwo);

		// create a mock for the DAO
		dao = mock(ProductData.class);

		// stub the getMajors method to return the test majors
		when(dao.getProductList()).thenReturn(products);
                
                when(dao.getCategories()).thenReturn(categories);
	}

	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}
        
	@Test
	public void testEdit() {
		// a student to edit
		prodOne = new Product(1, "name1", "desc1", new BigDecimal("11.00"), new Integer("22"), "cat1");

		// create dialog passing in student and mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, prodOne, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();

		// verify that the UI componenents contains the student's details
		fixture.textBox("txtID").requireText("1");
		fixture.textBox("txtName").requireText("name1");
		fixture.textBox("txtDescription").requireText("desc1");
                fixture.textBox("txtPrice").requireText("11.00");
                fixture.textBox("txtStock").requireText("22");
                fixture.comboBox("txtCategory").requireSelection("cat1");
		// edit the name and major
                
                fixture.textBox("txtName").selectAll().deleteText().enterText("name2");
                fixture.textBox("txtDescription").selectAll().deleteText().enterText("desc2");
		fixture.textBox("txtPrice").click().doubleClick().pressAndReleaseKeys(VK_BACK_SPACE).enterText("10");
                fixture.textBox("txtStock").click().doubleClick().pressAndReleaseKeys(VK_BACK_SPACE).enterText("5");
		fixture.comboBox("txtCategory").selectItem("cat3");

		// click the save button
		fixture.button("saveButton").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).add(argument.capture());

		// retrieve the passed student from the captor
		Product editedProduct = argument.getValue();

		// check that the changes were saved
                assertEquals("Ensure the name was changed", "name2", editedProduct.getName());
                assertEquals("Ensure the Description was changed", "desc2", editedProduct.getDescription());
                assertEquals("Ensure the Price was changed", new BigDecimal(10.00), editedProduct.getList_price());
                assertEquals("Ensure the Stock was changed", new Integer(5), editedProduct.getStock());
		assertEquals("Ensure the category was changed", "cat3", editedProduct.getCategories());
	}
        
	@Test
	public void testSave() {
		// create the dialog passing in the mocked DAO
		ProductEditor dialog = new ProductEditor(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		// enter some details into the UI components
		fixture.textBox("txtID").enterText("1234");
		fixture.textBox("txtName").enterText("Jack");
                fixture.textBox("txtDescription").enterText("EXAMPLe");
                fixture.textBox("txtPrice").enterText("10");
                fixture.textBox("txtStock").enterText("5");
                fixture.comboBox("txtCategory").enterText("Knitting");



		// click the save button
		fixture.button("saveButton").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).add(argument.capture());

		// retrieve the passed student from the captor
		Product savedProduct= argument.getValue();

		// test that the products details were properly saved
		assertEquals("Ensure the ID was saved", new Integer(1234), savedProduct.getProduct_Id());
		assertEquals("Ensure the name was saved", "Jack", savedProduct.getName());
		assertEquals("Ensure the description was saved", "EXAMPLe", savedProduct.getDescription());
                assertEquals("Ensure the price was saved", new BigDecimal(10), savedProduct.getList_price());
                assertEquals("Ensure the quantity was saved", new Integer(5), savedProduct.getStock());
                assertEquals("Ensure the Category was saved", "Knitting", savedProduct.getCategories());
	}
        
        
        @Test
	public void testView() {
		// a student to edit

		// create dialog passing in student and mocked DAO
		ProductReport dialog = new ProductReport(null, true, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();
                
                verify(dao).getProductList();
                
                SimpleListModel model = (SimpleListModel) fixture.list("productList").target().getModel();
                
                
                assertTrue("List contains the expected product", model.contains(prodOne));
		assertEquals("List contains the correct number of products", 2, model.getSize());

		// verify that the UI componenents contains the student's details
		fixture.list("productList").selectItem(prodOne.toString());

		// click the save button
		fixture.button("edit").click();

                DialogFixture editDialog = fixture.dialog("productView");
		// retrieve the passed student from the captor
		editDialog.textBox("txtID").requireText(prodOne.getProduct_Id().toString());

		// check that the changes were saved
	}
}
