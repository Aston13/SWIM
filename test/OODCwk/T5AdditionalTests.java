package OODCwk;

import OODCwk.SpaceWars;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import OODCwk.SWIM;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class T5AdditionalTests {
    SWIM game;
    
    public T5AdditionalTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new SpaceWars("Jaston");
    }
    
    @After
    public void tearDown() {
    }

    private boolean containsText(String text, String[] str) {
        boolean result = true;
        for (String temp : str) {
            result = result && (text.toLowerCase()).contains(temp.toLowerCase());
        }
        return result;
    }
    
    /* Our designed test methods are listed below. */

    /* Setup Tests */
    @Test
    public void correctlyInitialised() {
        String result = game.toString();
        String[] details = {"Jaston", "1000", ""};
        boolean actual = containsText(result, details);
        assertTrue(actual);
    }
    
    @Test
    public void emptyASFAtSetup() {
        String expected = "ASF is empty";
        String actual = game.getASFleet();
        assertEquals(expected, actual);
    }
      
    /* Force Activation Tests */
    public void activateDestroyedForce(){
        
    }
    
    /* Battle Tests */
    public void battleWithDestroyedForceNotAllowed(){
        
    }
  
}
