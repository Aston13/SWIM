package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class Battle {
    
    private String battleType;
    private String enemyType;
    private int enemyStrength;
    private int losses;
    private int gains;
    
    
    public Battle(String battleType, String enemyType,
                  int enemyStrength, int losses, int gains) {
        
        this.battleType = battleType;
        this.enemyType = enemyType;
        this.enemyStrength = enemyStrength;
        this.losses = losses;
        this.gains = gains;         
    }
    
    public int getLosses(){
        return losses;
    }
    
    public int getGains(){
        return gains;
    }
    
    public String toString() {
        String s =  "\nBattle Type: " + battleType + 
                    "\nEnemy Type: " + enemyType + 
                    "\nEnemy Strength: " + String.valueOf(enemyStrength) +
                    "\nLosses: " + String.valueOf(losses) +
                    "\nGains: " + String.valueOf(gains);
        return s;
    }
}
