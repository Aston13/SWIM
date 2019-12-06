package OODCwk;

import java.util.ArrayList;

/**
 *
 * @author Aston Turner & Jason Hitching
 */

public class Starship extends Force{
    private int laserCannons;
    private int photonTorpedoes;
    private ArrayList<BattleType> listType;
    
    
    public Starship(String forceName, int battleStrength, int activationFee,
            int laserCannons, int photonTorpedoes){
        
        super(forceName, battleStrength, activationFee);
        this.listType = new ArrayList<BattleType>();
        this.laserCannons = laserCannons;
        this.photonTorpedoes = photonTorpedoes;
        this.listType.add(BattleType.FIGHT);
        this.listType.add(BattleType.SKIRMISH);
    }
    
    public ArrayList<BattleType> getBattleType(){
        return this.listType;
    }
    
    public String toString(){
        String s =  super.toString() + 
                    "\nLaser Cannons: " + String.valueOf(laserCannons) + 
                    "\nTorpedoes: " + String.valueOf(photonTorpedoes);
        return s;
    } 
   
}
