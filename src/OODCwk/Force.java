package OODCwk;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aston Turner
 * @author Jason Hitching
 * 
 */
public abstract class Force implements Serializable {
    private final String forceName;
    private final int activationFee;
    private final int battleStrength;
    private ForceState state;
    
    /**
     * Constructs a new Force.
     * 
     * @param forceName the name
     * @param battleStrength the strength value
     * @param activationFee the fee charged on activation
     */
    public Force(String forceName, int battleStrength, int activationFee) {
        this.state = state.DOCKED;
        this.forceName = forceName;
        this.battleStrength = battleStrength;
        this.activationFee = activationFee;
    }
    
    /**
     * Used to get the fee of activating a force.
     * 
     * @return an integer that represents the activation fee
     */
    public int getFee() {
        return activationFee;
    }
    
    public abstract ArrayList<BattleType> getBattleType();
    
    /**
     * This method sets the state of a force to docked.
     */
    public void setDocked() {
        this.state = state.DOCKED;
    }
    
    /**
     * This method sets the state of a force to active.
     */
    public void setActive() {
        this.state = state.ACTIVE;
    }

    /**
     * This method sets the state of a force to destroyed.
     */
    public void setDestroyed() {
        this.state = state.DESTROYED;
    }
    
    /**
     * Used to get the strength value of the force.
     * 
     * @return an integer representing the strength
     */
    public int getStrength() {
        return this.battleStrength;
    }
    
    /**
     * Used to get the state of the force.
     * 
     * @return state of the force (enumerated type)
     */
    public ForceState getState() {
        return state;   
    }
    
    /**
     * Used to check if a force is destroyed.
     * 
     * @return true if the force is destroyed. Otherwise false is returned
     */
    public boolean isDestroyed() {
        if(this.state.equals(state.DESTROYED)) {
            return true;
        }
        return false;
    }
    
    /**
     * Formats a string with forceName, activationFee, battleStrength and
     * state.
     * 
     * @return a formatted String
     */
    @Override
    public String toString() { 
        String s = "";
        
        return s.format("%-10s | %-15s | %-8s |"
                + " %-11s |", forceName, String.valueOf(activationFee),
                String.valueOf(battleStrength), state);
    }
}
