package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class Battle {
    private int battleNum;
    private String battleType;
    private String enemyType;
    private int strength;
    // increase/decrease warchest
    
    
    public Battle(int battleNum, String battleType, String enemyType, int strength){
        this.battleNum = battleNum;
        this.battleType = battleType;
        this.enemyType = enemyType;
        this.strength = strength;
               
    }
    
    public String toString(){
        return "";
    }
}
