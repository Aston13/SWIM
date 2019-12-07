package OODCwk;

import java.util.ArrayList;

/**
 * @author Aston Turner
 * @author Jason Hitching
 * 
 */
public class WarBird extends Force {
    private final boolean hasCloak;
    public static ArrayList<BattleType> listType;
    public static ArrayList<BattleType> cloakList; 
    
    /**
     * Constructs a new WarBird.
     * 
     * @param forceName the name
     * @param battleStrength the strength value
     * @param activationFee the fee charged on activation
     * @param hasCloak whether or not the WarBird has a cloaking ability
     */
    public WarBird(String forceName, int battleStrength,
            int activationFee, boolean hasCloak) {
        super(forceName, battleStrength, activationFee);
        
        WarBird.listType = new ArrayList<>();
        WarBird.cloakList = new ArrayList<>();
        
        this.hasCloak = hasCloak; 
        WarBird.listType.add(BattleType.FIGHT);
        WarBird.cloakList.add(BattleType.FIGHT);
        WarBird.cloakList.add(BattleType.AMBUSH);

    }
    
    /**
     * This method is used to check if a WarBird has the cloaking ability.
     * 
     * @return true if the WarBird has a cloaking ability. False is returned 
     * otherwise
     */
    public boolean getCloak() {
        return this.hasCloak;
    }
    
    /**
     * Used to check what battle types a WarBird is capable of engaging in.
     * 
     * @return an ArrayList containing enumerated BattleTypes. The list 
     * returned depends on the 'hasCloak' variable
     */
    @Override
    public ArrayList<BattleType> getBattleType() { 
        if(this.hasCloak) {
            return WarBird.cloakList;
        }
        return WarBird.listType;
    }
    
    /**
     * This method calls the super class method and concatenates
     * the WarBird specific variable 'hasCloak', and then returns the String.
     * 
     * @return a formatted String
     */
    @Override
    public String toString() {
        String s =  super.toString() +
                    "\nCloaking Abilty: " + String.valueOf(this.hasCloak);
        return s;
    }
}
