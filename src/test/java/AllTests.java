/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
 
/**
 *
 * @author Caleb Mazure
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({dao.DaoTest.class,
    gui.ProductEditorTest.class})
   
public class AllTests {
 
    @Before
    public void setUp() throws Exception {
    }
 
    @After
    public void tearDown() throws Exception {
    }
   
}