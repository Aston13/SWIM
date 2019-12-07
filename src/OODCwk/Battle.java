package OODCwk;

/**
 * @author Aston Turner
 * @author Jason Hitching
 * 
 */
public class Battle {
    private final String battleType;
    private final String enemyType;
    private final int enemyStrength;
    private final int losses;
    private final int gains;
    
    /**
     * Constructs a new Battle.
     * 
     * @param battleType the type of battle
     * @param enemyType the enemy race
     * @param enemyStrength the strength value
     * @param losses the amount of bit coin deducted from a fight lost
     * @param gains the amount of bit coin added from a fight won
     */
    public Battle(String battleType, String enemyType,
                  int enemyStrength, int losses, int gains) {
        
        this.battleType = battleType;
        this.enemyType = enemyType;
        this.enemyStrength = enemyStrength;
        this.losses = losses;
        this.gains = gains;         
    }
    
    /**
     * This method returns the BattleType of the enemy (Battle). Either ambush, 
     * fight or skirmish.
     * 
     * @return an enumerated 'BattleType'
     */
    public BattleType getBattleType() {
        switch (battleType) {
            case "Ambush":
                return BattleType.AMBUSH;
            case "Fight":
                return BattleType.FIGHT;
            default:
                return BattleType.SKIRMISH;
        }
    }
    
    /**
     * Used to get losses.
     * 
     * @return the amount of bit coin deducted if the fight is lost
     */
    public int getLosses() {
        return losses;
    }
    
    /**
     * Used to get gains.
     * 
     * @return the amount of bit coin added if the fight is won
     */
    public int getGains() {
        return gains;
    }
    
    /**
     * Used to get the strength value of the enemy (Battle).
     * 
     * @return an integer representing the strength
     */
    public int getStrength() {
        return enemyStrength;
    }
    
    /**
     * Formats a string with battleType, enemyType, enemyStrength, 
     * losses and gains.
     * 
     * @return a formatted String
     */
    @Override
    public String toString() {
        String s =  "\nBattle Type: " + battleType + 
                    "\nEnemy: " + enemyType + 
                    "\nStrength: " + String.valueOf(enemyStrength) +
                    "\nPotential losses: " + String.valueOf(losses) +
                    "\nPotential gains: " + String.valueOf(gains);
        return s;
    }
}
