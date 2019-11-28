package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class WarBird extends Force{
    private boolean hasCloak;
    
    public WarBird(String forceRef, String forceName, int battleStr,
            boolean hasCloak, int activationFee){
        super(forceRef, forceName, battleStr, activationFee);
        this.hasCloak = hasCloak; 
    }
    
    /**
     * Determine whether the force has a cloak
     * @return true if the force has a cloak, false otherwise.
     */
    private boolean getCloak(){
        return hasCloak;
    }
    
    public String toString(){
        String s =  super.toString() +
                    "\nCloaking Abilty: " + String.valueOf(hasCloak);
        return s;
    }
}
