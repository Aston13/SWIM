package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */

// add child classes/polymorphism/inheritance


public class Force {
    private String forceRef;
    private String forceName;
    private int actiFee;
    private int battleStr;
    
    /**
     * Force constructor
     * @param forceRef Unique force reference
     * @param forceName Forces name
     * @param battleStr Forces battle strength
     * @param actiFee Activation cost of the force
     */
    public Force(String forceRef, String forceName, int battleStr, int actiFee){
        this.forceRef = forceRef;
        this.forceName = forceName;
        this.actiFee = actiFee;
        this.battleStr = battleStr;
    }
    
    /**
     * Retrieve the forces name
     * @return forces name
     */
    private String getName(){
        return forceName;
    }
    
    /**
     * Retrieve the forces unique reference
     * @return forceRef
     */
    private String getReference(){
        return forceRef;
    }
    
    /**
     * Retrieve the activate cost of force
     * @return actiFee
     */
    public int getFee(){
        return actiFee;
    }
    
    /**
     * Retrieve the forces battle strength
     * @return battleStr
     */
    private int getStrength(){
        return battleStr;
    }
    
    
    public String toString(){ 
        String s =  "Reference: " + forceRef + 
                    "\nName: " + forceName + 
                    "\nActivation Cost: " + String.valueOf(actiFee) + 
                    "\nStrength: " + String.valueOf(battleStr);
        return s;
    }
}
