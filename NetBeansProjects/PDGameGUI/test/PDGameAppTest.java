

import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PDGameAppTest {
    
    public PDGameAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class PDGameApp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PDGameApp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAndShowGUI method, of class PDGameApp.
     */
    @Test
    public void testCreateAndShowGUI() {
        System.out.println("createAndShowGUI");
        PDGameApp.createAndShowGUI();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addListeners method, of class PDGameApp.
     */
    @Test
    public void testAddListeners() {
        System.out.println("addListeners");
        PDGameApp instance = new PDGameApp();
        instance.addListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueChanged method, of class PDGameApp.
     */
    @Test
    public void testValueChanged() {
        System.out.println("valueChanged");
        ListSelectionEvent l = null;
        PDGameApp instance = new PDGameApp();
        instance.valueChanged(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class PDGameApp.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent e = null;
        PDGameApp instance = new PDGameApp();
        instance.actionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinal method, of class PDGameApp.
     */
    @Test
    public void testGetFinal() {
        System.out.println("getFinal");
        PDGameApp instance = new PDGameApp();
        instance.getFinal();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
