package OODCwk; 
import java.io.*;
/**
 * Enumeration class BattleType. Used by the Force and Battle classes to
 * enforce set battle types that can be used in battle methods to find 
 * compatible Force/Battle engagements. 
 * 
 * @author A.Marczyk
 * @author Aston Turner
 * @author Jason Hitching
 * 
 * @version 07/12/2019
 */
public enum BattleType implements Serializable
{
    SKIRMISH ("Skirmish"), AMBUSH("Ambush"), FIGHT("Fight") ;
    private final String type;
    
    private BattleType(String type) {
        this.type = type;
    }
    
    /**
     * Used to retrieve the BattleType as a String.
     * 
     * @return the battle type variable
     */ 
    @Override
    public String toString() {
        return type;
    }
}
