package OODCwk;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aston Turner
 * @author Jason Hitching
 * 
 */
public class Starship extends Force implements Serializable {
    private int laserCannons;
    private int photonTorpedoes;
    private ArrayList<BattleType> listType;
    
    /**
     * Constructs a new Starship.
     * 
     * @param forceName the name
     * @param battleStrength the strength value
     * @param activationFee the fee charged on activation
     * @param laserCannons the amount of laser cannons
     * @param photonTorpedoes the amount of photon torpedoes
     */
    public Starship(String forceName, int battleStrength, int activationFee,
            int laserCannons, int photonTorpedoes) {
        
        super(forceName, battleStrength, activationFee);
        this.listType = new ArrayList<>();
        this.laserCannons = laserCannons;
        this.photonTorpedoes = photonTorpedoes;
        this.listType.add(BattleType.FIGHT);
        this.listType.add(BattleType.SKIRMISH);
    }
    
    /**
     * Used to check what battle types a Starship is capable of engaging in.
     * 
     * @return an ArrayList containing enumerated BattleTypes.
     */
    @Override
    public ArrayList<BattleType> getBattleType() {
        return this.listType;
    }
    
    /**
     * This method calls the super class method and concatenates the Starship
     * specific variables 'laserCannons' and 'photonTorpedoes' and then returns
     * the String.
     * 
     * @return a formatted String
     */
    @Override
    public String toString() {
        String s =  super.toString() + 
                    "\nLaser Cannons: " + String.valueOf(laserCannons) + 
                    "\nTorpedoes: " + String.valueOf(photonTorpedoes);
        return s;
    } 
   
}
