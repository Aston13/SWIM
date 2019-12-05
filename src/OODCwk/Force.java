package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */

// add child classes/polymorphism/inheritance


public abstract class Force {
    private String forceName;
    private int activationFee;
    private int battleStrength;
    
    /**
     * Force constructor
     * @param forceName Forces name
     * @param battleStrength Forces battle strength
     * @param activationFee Activation cost of the force
     */
    public Force(String forceName, int battleStrength, int activationFee){
        this.forceName = forceName;
        this.battleStrength = battleStrength;
        this.activationFee = activationFee;
    }
    
    /**
     * Retrieve the forces name
     * @return forces name
     */
    private String getName(){
        return forceName;
    }
    
    /**
     * Retrieve the activate cost of force
     * @return actiFee
     */
    public int getFee(){
        return activationFee;
    }
    
    /**
     * Retrieve the forces battle strength
     * @return battleStr
     */
    private int getStrength(){
        return battleStrength;
    }
    
    
    public String toString(){ 
        String s =  "\nName: " + forceName + 
                    "\nActivation Cost: " + String.valueOf(activationFee) + 
                    "\nStrength: " + String.valueOf(battleStrength);
        return s;
    }
}
