package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */

public class Starship extends Force{
    private int laserCannons;
    private int photonTorpedoes;
    
    public Starship(String forceRef, String forceName, int battleStr,
            int laserCannons, int photonTorpedoes){
        
        super(forceRef, forceName, battleStr, (30*laserCannons));
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
