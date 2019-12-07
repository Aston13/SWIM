package OODCwk;

import java.util.ArrayList;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class WarBird extends Force{
    private boolean hasCloak;
    public static ArrayList<BattleType> listType;
    public static ArrayList<BattleType> cloakList; 
    
    public WarBird(String forceName, int battleStrength,
            int activationFee, boolean hasCloak){
        super(forceName, battleStrength, activationFee);
        
        this.listType = new ArrayList<BattleType>();
        this.cloakList = new ArrayList<BattleType>();
        
        this.hasCloak = hasCloak; 
        this.listType.add(BattleType.FIGHT);
        this.cloakList.add(BattleType.FIGHT);
        this.cloakList.add(BattleType.AMBUSH);

    }
    
    /**
     * Determine whether the force has a cloak
     * @return true if the force has a cloak, false otherwise.
     */
    public boolean getCloak(){
        return this.hasCloak;
    }
    
    public ArrayList<BattleType> getBattleType(){ 
        if(this.hasCloak){
            return this.cloakList;
        }
        return this.listType;
    }
    
    public String toString(){
        String s =  super.toString() +
                    "\nCloaking Abilty: " + String.valueOf(this.hasCloak);
        return s;
    }
}
