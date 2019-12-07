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
 * @author aam
 * @author Aston Turner
 * @author Jason Hitching
 * 
 */
public class T1SetupTest {
    SWIM game;
    
    public T1SetupTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new SpaceWars("David");
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
    
    /* We (Aston & Jason) added a new test file. 'T5AdditionalTests.java' and 
     * have placed our designed test methods in there.
     */
   
    @Test
    public void gameCorrectlyInitialised() {
        String result = game.toString();
        String[] details = {"David", "1000", ""};
        boolean actual = containsText(result, details);
        assertTrue(actual);
    }
    
    @Test
    public void warChestWhenNoBattle() {
        int expected = 1000;
        int actual = game.getWarchest();
        assertEquals(expected, actual);
    }  
    
    @Test
    public void battleAtSetup() {
        boolean result = game.isBattle(3);
        assertTrue(result);
    }
    
    @Test
    public void allBattleLoadedAtSetup() {
        boolean result = true;
        for (int i = 1; i < 8; i++) {
            result = result && game.isBattle(i);
        }
        assertTrue(result);
    }
    
    @Test
    public void forceInUFFAtSetup() {
        boolean result = game.isInUFFleet("IW1");
        assertTrue(result);
    }
    
    @Test
    public void UFFleetLoadedAtTheBeginning() {
        boolean result = true;
        List<String> forces = new ArrayList<>(Arrays.asList("IW1","WB3","SS2",
                "IW4","WB5","SS6","SS7","WB9","IW10"));
        for (String force : forces) {
            result = result && game.isInUFFleet(force);
        }
        assertTrue(result);
    }
    
    
    @Test
    public void ASFleetEmptyAtTheBeginning() {
        boolean result = true;
        List<String> forces = new ArrayList<>(Arrays.asList("IW1","WB3","SS2",
                "IW4","WB5","SS6","SS7","WB9","IW10"));
        for (String force : forces) {
            result = result && !game.isInASFleet(force);
        }
        assertTrue(result);
    }
    
    
    
    @Test
    public void detailsOfWB3() {
        String str = game.getForce("WB3");
        String[] target = {"WB3","Droop","100","300","In dock","false"};
        boolean result = containsText(str, target);
        assertTrue(result);
    }
    
    @Test
    public void detailsOfSS2() {
        String str = game.getForce("SS2");
        String[] target = {"SS2","Enterprise","200","300","In dock","20","10"};
        boolean result = containsText(str, target);
        assertTrue(result);
    }
    
    @Test
    public void detailsOfIW1() {
        String str = game.getForce("IW1");
        String[] target = {"IW1","Twisters","200","200","In dock","10"};
        boolean result = containsText(str, target);
        assertTrue(result);
    }
    
    @Test
    public void detailsOfNonExistantForce() {
        String str = game.getForce("XX3");        
        String[] target = {"No such force"};
        boolean result = containsText(str, target);
        assertTrue(result);
    }
    
    @Test
    public void detailsOfBattle() {
        String str = game.getBattle(2);        
        String[] details = {"2", "Skirmish", "Kardassians", "700", "200", "120"};
        boolean result = containsText(str, details);
        assertTrue(result);
    }
}
