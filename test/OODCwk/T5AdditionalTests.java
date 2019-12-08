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
 * @author Aston Turner
 * @author Jason Hitching
 * 
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
        String expected = "The Active Star Fleet is empty.";
        String actual = game.getASFleet();
        assertEquals(expected, actual);
    }
      
    /* Force Activation Tests */
    
    /**
     * 
     */
    public void activateDestroyedForce(){
        int expected = 4;
        game.activateForce("IW1");
        game.doBattle(2);
        int actual = game.activateForce("IW1");
        assertEquals(expected, actual);
    }
    
    /**
     * Attempt to activate a force when the player doesn't have enough funds.
     * 
     */
    public void notEnoughBitcoins(){
        int expected = 2;
        int warchest = game.getWarchest();
        warchest -= 999;
        int actual = game.activateForce("IW1");
        assertEquals(expected, actual);  
    }
    
    /* Battle Tests */
    
    /**
     * No a suitable force for desired battle in the ASF
     */
    public void noSuitableForces(){
        int expected = 1;
        game.activateForce("IW1");
        int actual = game.doBattle(1);
        assertEquals(expected, actual);
    }
    
    /**
     * No such battle in the battle list
     */
    public void battleDoesntExist() {
        String expected = "\nThat battle does not exist.";
        String actual = game.getBattle(10);
        assertEquals(expected, actual);
    }
    
    /**
     * Check that the admiral gets defeated
     */
    public void admiralDefeated(){
        boolean expected = true;
        int warchest = game.getWarchest();
        warchest -= 999;
        boolean actual = game.isDefeated();
        assertEquals(expected, actual);
    }
  
}
