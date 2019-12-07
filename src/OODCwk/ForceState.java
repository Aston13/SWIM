package OODCwk; 
import java.io.*;

/**
 * Enumeration class ForceState. Used by the Force class to
 * enforce a state that denotes whether the force is docked, active or 
 * destroyed.
 * 
 * @author A.Marczyk
 * @author Aston Turner
 * @author Jason Hitching
 * 
 * @version 07/12/2019
 */
public enum ForceState implements Serializable {
    DOCKED("In Dock"), ACTIVE("Active"), DESTROYED ("Destroyed");
    private final String state;
    
    private ForceState(String st) {
        state = st;
    }
    
    /**
     * Used to retrieve the ForceState as a String.
     * 
     * @return the state variable
     */ 
    @Override
    public String toString() {
        return state;
    }
}
