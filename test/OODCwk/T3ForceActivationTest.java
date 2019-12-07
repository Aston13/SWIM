/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODCwk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aam
 * @amended by Aston Turner
 */
public class T3ForceActivationTest {
    SWIM game;
    
    public T3ForceActivationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new SpaceWars("Olenka");
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
        
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
        
    /* We (Aston & Jason) added a new test file. 'T5AdditionalTests.java' and 
     * have placed our designed test methods in there.
     */
    
    @Test
    public void forceActivationReturn() {
        int expected = 0;
        String forceRef = "IW1";
        int actual = game.activateForce(forceRef);
        assertEquals(actual, expected);
    }
    
    @Test
    public void forceActivationWorks() {
        String forceRef = "IW1";
        game.activateForce(forceRef);
        boolean actual = game.isInASFleet(forceRef);
        assertTrue(actual);
    }
    @Test
    public void warchestAfterForceActivated() {
        int expected = 800;
        game.activateForce("IW1");
        int actual = game.getWarchest();
        assertEquals(expected, actual);
    }
    
    @Test
    public void statusActiveForActivatedForce() {
        String expected = "active";
        game.activateForce("IW1");
        String actual = game.getForce("IW1");
        boolean result = actual.toLowerCase().contains(expected);
        assertTrue(result);
    }
    
    @Test
    public void notEnoughCreditInWarchest() {
        int expected = 200;
        game.activateForce("IW1");
        game.activateForce("SS2");
        game.activateForce("WB3");
        game.activateForce("WB5");
        assertEquals(expected, game.getWarchest());
    }
    
    @Test
    public void returnValueForNotEnoughInWarchest() {
        int expected = 2;
        game.activateForce("IW1");
        game.activateForce("SS2");
        game.activateForce("WB3");
        int actual = game.activateForce("WB5");
        assertEquals(expected, actual);
    }
    
    @Test
    public void returnValueForNotEnoughInWarchestOnNonExistentForce() {
        int expected = 3;
        game.activateForce("IW1");
        game.activateForce("SS2");
        game.activateForce("WB3");
        game.activateForce("WB5");
        int actual = game.activateForce("XX3");
        assertEquals(expected, actual);
    }
    
    @Test
    public void reactivateExistingForceHasNoEffectOnWarchest() {
        int expected = 200;
        game.activateForce("IW1");
        game.activateForce("SS2");
        game.activateForce("WB3");
        game.activateForce("IW1");
        int actual = game.getWarchest();
        assertEquals(expected, actual);
    }
    
    @Test
    public void activatedForceNotInASFleetList() {
        game.activateForce("IW10");
        game.activateForce("SS2");
        game.activateForce("WB3");
        boolean result = true;
        String[] notOnList = {"IW10","SS2","WB3"};
        String actual = game.getUFFleet();
        for (String force : notOnList) {
            result = result && !actual.contains(force);
        }
        assertTrue(result);
    }
    
    @Test
    public void forceRecallUpdatesWarchestForActivatedForce() {
        int expected = 800;
        game.activateForce("WB5");
        game.recallForce("WB5");
        int actual = game.getWarchest();
        assertEquals(expected, actual);
    }
    
    @Test
    public void forceRecallDoesNotUpdateWarchestForNonActiveForce() {
        int expected = 800;
        game.activateForce("WB5");
        game.recallForce("WB5");
        game.recallForce("IW1"); // not activated
        int actual = game.getWarchest();
        assertEquals(expected, actual);
    }
    
    @Test
    public void forceRecallDoesNotUpdateWarchestForNonExistantForce() {
        int expected = 800;
        game.activateForce("WB5");
        game.recallForce("WB5");
        game.recallForce("XX3");
        int actual = game.getWarchest();
        assertEquals(expected, actual);
    }
}
