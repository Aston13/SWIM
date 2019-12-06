package OODCwk;

import java.util.ArrayList;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class Wing extends Force{
    private int strikers;
    ArrayList<BattleType> compatibleBattles = new ArrayList<BattleType>();
    
    
    
    /**
     * Wing constructor
     * @param forceRef Unique force reference
     * @param forceName Forces name
     * @param strikers Number of strikers on the force */
    public Wing(String forceName, int battleStrength, int activationFee, 
            int strikers){
        super(forceName, battleStrength, activationFee);
        this.strikers = strikers;
        
        //Types of battle wings can engage in
        compatibleBattles.add(BattleType.SKIRMISH);
        compatibleBattles.add(BattleType.AMBUSH);
    }
    
//    public boolean checkBattleType(){
//        if(compatibleBattles.contains(type)){
//            return true;
//        }
//        return false;
//    }
    
    
    
    /**
     * Retrieve a formatted string with details of the Wing class
     * @return return a formatted string
     */
    public String toString(){
        String s =  super.toString() +
                    "\nStrikers: " + String.valueOf(this.strikers);
        return s;
    }
}
