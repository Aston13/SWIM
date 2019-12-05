package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */

public class Starship extends Force{
    private int laserCannons;
    private int photonTorpedoes;
    
    
    public Starship(String forceName, int battleStrength, int activationFee,
            int laserCannons, int photonTorpedoes){
        
        super(forceName, battleStrength, activationFee);
        this.laserCannons = laserCannons;
        this.photonTorpedoes = photonTorpedoes;  
    }
    
    public String toString(){
        String s =  super.toString() + 
                    "\nLaser Cannons: " + String.valueOf(laserCannons) + 
                    "\nTorpedoes: " + String.valueOf(photonTorpedoes);
        return s;
    } 
   
}
