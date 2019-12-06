package OODCwk;

import java.util.ArrayList;

/**
 *
 * @author Aston Turner & Jason Hitching
 */

// add child classes/polymorphism/inheritance


public abstract class Force {
    private String forceName;
    private int activationFee;
    private int battleStrength;
    private ForceState state;
    
    /**
     * Force constructor
     * @param forceName Forces name
     * @param battleStrength Forces battle strength
     * @param activationFee Activation cost of the force
     */
    public Force(String forceName, int battleStrength, int activationFee){
        this.state = state.DOCKED;
        this.forceName = forceName;
        this.battleStrength = battleStrength;
        this.activationFee = activationFee;
    }
    
    /**
     * Retrieve the activate cost of force
     * @return actiFee
     */
    public int getFee(){
        return activationFee;
    }
    
    public abstract ArrayList<BattleType> getBattleType();
    
    public void setDocked(){
        this.state = state.DOCKED;
    }
    
    public void setActive(){
        this.state = state.ACTIVE;
    }

    public void setDestroyed(){
        this.state = state.DESTROYED;
    }
    
    public int getStrength(){
        return this.battleStrength;
    }
    
    public ForceState getState(){
        return state;   
    }
    
    public boolean isDestroyed(){
        if(this.state.equals(state.DESTROYED)) {
            return true;
        }
        return false;
    }
    
    public String toString(){ 
        String s =  "\nName: " + forceName + 
                    "\nActivation Cost: " + String.valueOf(activationFee) + 
                    "\nStrength: " + String.valueOf(battleStrength) +
                    "\nState:" + state;
        return s;
    }
}
