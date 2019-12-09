package OODCwk;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aston Turner
 * @author Jason Hitching
 * 
 */
public class Wing extends Force implements Serializable {
    private final int strikers;
    private final ArrayList<BattleType> battleType;
    
    /**
     * Constructs a new Wing.
     * 
     * @param forceName the name
     * @param battleStrength the strength value
     * @param activationFee the fee charged on activation
     * @param strikers the number of strikers
     */
    public Wing(String forceName, int battleStrength, int activationFee, 
            int strikers) {
        super(forceName, battleStrength, activationFee);
        this.strikers = strikers;
        this.battleType = new ArrayList<>();
        this.battleType.add(BattleType.AMBUSH);
        this.battleType.add(BattleType.SKIRMISH);
    }
    
    /**
     * Used to check what battle types a Wing is capable of engaging in.
     * 
     * @return an ArrayList containing enumerated BattleTypes.
     */
    @Override
    public ArrayList<BattleType> getBattleType() {
        return battleType;
    }
    
    /**
     * This method calls the super class method and concatenates
     * the Wing specific variable 'strikers', and then returns the String.
     * 
     * @return a formatted String
     */
    @Override
    public String toString() {
        String s =  "";

        return s.format("%s Strikers: %-2s", super.toString(),
                String.valueOf(this.strikers));
    }
}
