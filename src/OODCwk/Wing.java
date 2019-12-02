package OODCwk;

/**
 *
 * @author Aston Turner & Jason Hitching
 */
public class Wing extends Force{
    private int strikers;
    
    /**
     * Wing constructor
     * @param forceRef Unique force reference
     * @param forceName Forces name
     * @param strikers Number of strikers on the force
     */
    public Wing(String forceRef, String forceName, int strikers, int activationFee){
        super(forceRef, forceName, 20*strikers, activationFee);
        this.strikers = strikers;
    }
    
    /**
     * Get the wings number of strikers
     * @return number of strikers
     */
    private int getStrikers(){
        return strikers;
    }
    
    /**
     * Retrieve a formatted string with details of the Wing class
     * @return return a formatted string
     */
    public String toString(){
        String s =  super.toString() +
                    "\nStrikers: " + String.valueOf(strikers);
        return s;
    }
}
