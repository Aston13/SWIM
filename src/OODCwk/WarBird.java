package OODCwk;

import java.util.ArrayList;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class WarBird extends Force{
    private boolean hasCloak;
    private ArrayList<BattleType> listType = new ArrayList<BattleType>();
    
    public WarBird(String forceName, int battleStrength,
            int activationFee, boolean hasCloak){
        super(forceName, battleStrength, activationFee);
        this.hasCloak = hasCloak; 
        this.listType.add(BattleType.FIGHT);
        
        if(hasCloak){
            this.listType.add(BattleType.AMBUSH);
        }
    }
    
    /**
     * Determine whether the force has a cloak
     * @return true if the force has a cloak, false otherwise.
     */
    public boolean getCloak(){
        return hasCloak;
    }
    
    public ArrayList<BattleType> getBattleType(){
        return listType;
    }
    
    public String toString(){
        String s =  super.toString() +
                    "\nCloaking Abilty: " + String.valueOf(hasCloak);
        return s;
    }
}
