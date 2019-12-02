package OODCwk; 
import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from a SWIM
 * system as required for 6COM1037 - Nov 2019
 * 
 * @author A.A.Marczyk 
 * @amended by Aston Turner & Jason Hitching
 * @version 25/10/2019
 */

public class SpaceWars implements SWIM,Serializable 
{
    // fields
    HashMap<String, Force> UFF = new HashMap<String, Force>(); //UnitedForcesFleet
    HashMap<String, Force> ASF = new HashMap<String, Force>(); //ActiveStarFleet
    
    int warchest; //Users warchest (SEPERATE CLASS??)
    
    ArrayList<Battle> battles = new ArrayList<Battle>();
    String admiralName; //Users admiral name
  

//**************** SWIM **************************  
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SpaceWars(String admiral)
    {
        this.admiralName = admiral;
        this.warchest = 1000;
        setupForces(); // loads force data 
        setupBattles(); // loads battle data
    }
    
    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * Star Fleet,(or, "No forces" if Star Fleet is empty)
     **/
    public String toString()
    {
        return "Nothing here";
    }
        
      
    /** returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated()
    {
        return false;
    }
    
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest()
    {
        return warchest;
    }
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFleet(String ref) 
    {
        return true; // change this
    }
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF)
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public String getUFFleet()
    {   
        if(UFF.isEmpty()) return "UFF is empty";

        Set<String> keySet = UFF.keySet();
        String s = "";
        for(String elem : keySet){
            s += UFF.get(elem) + "\n" + "***************\n";
            }
        
        return s;
    }
        
    /** Returns details of the force in the game with the given reference code 
     * @return details of the force in the game with the given reference code
     **/
    public String getForce(String ref)
    {
        
        String s = "\nNo such force";
        
        if (UFF.containsKey(ref)){
            s = UFF.get(ref).toString();
        } else if (ASF.containsKey(ref)){
            s = ASF.get(ref).toString(); 
        }
        
        return s;
    }     
    
    
 // ***************** active Star Fleet Forces ************************   
    /** Allows a force to be activated into the active Star Fleet(ASF), but 
     * only if there are enough resources for the activation fee.The force's 
     * state is set to "active"
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF
      * 2 if not enough money, 3 if no such force
     **/       
    public int activateForce(String ref)
    {    
        //No such force exists
        if(!ASF.containsKey(ref) && !UFF.containsKey(ref))return 3;       
        //Force isn't in the UFF
        if(!UFF.containsKey(ref))return 1;       
        //Not enough bit coin in warchest
        if(getWarchest() < (UFF.get(ref).getFee()))return 2;
        
        // Force is in the UFF and needs to be activated into the ASF,
        // and deduct from warchest warchest =- getFee()
        return 0;
    }
    
        
    /** Returns true if the force with the reference code is in 
     * the active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref) {
        if(ASF.containsKey(ref)){
            return true;
        }
        return false;
    }
    
    
    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if they are in the active Star Fleet(ASF)
     * pre-condition: isInASFleet(ref)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref) {
        if (isInASFleet(ref)){
            // Move force from ASF to UFF
            // Add to warchest
        }
    }   

    
        
        
    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        // ADD CONDITIONS/TRY-CATCH
        if(ASF.isEmpty()) return "ASF is empty";
        
        String s = "";
        Set<String> keySet = ASF.keySet();
        for(String elem : keySet){
            s += ASF.get(elem) + "\n" + "***************\n";
        }
        return s;
    }
       
    
//**********************Battles************************* 
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
     public boolean isBattle(int num)
     {
         return false;
     }
    
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int num)
    {
        return "No such battle";
    }
    
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        return "Nothing";
    }
     
     
    /** Retrieves the battle represented by the battle number.Finds 
      * a force from the active Star Fleet which can engage in the battle.The  
      * results of battle will be one of the following: 
      * 0 - Battle won, battle gains added to the warchest, 
      * 1 - Battle lost as no suitable force available, battle losses 
      * deducted from warchest 
      * 2 - Battle lost on battle strength , battle losses 
      * deducted from warchest and destroy the force
      * 3 - If a battle is lost and admiral completely defeated (no resources and 
      * no forces to recall) 
      * -1 - no such battle
      * @param battleNo is the number of the battle
      * @return an int showing the result of the battle
      */ 
    public int doBattle(int battleNo)
    {
        return 3;
    }
     
    //*******************************************************************************
    private void setupForces()
    {
        UFF.put("IW1", new Wing("IW1", "Twisters", 200, 200));
        UFF.put("SS2", new Force("SS2", "Enterprise", 300, 200));
        UFF.put("WB3", new Force("WB3", "Droop", 300, 100));
        UFF.put("IW4", new Force("IW4", "Wingers", 200, 400));
        UFF.put("WB5", new Force("WB5", "Hang", 400, 300));
        UFF.put("SS6", new Force("SS6", "Voyager", 450, 200));
        UFF.put("SS7", new Force("SS7", "Explorer", 120, 65));
        UFF.put("WB9", new Force("WB9", "Hover", 300, 400));
        UFF.put("IW10", new Force("IW10", "Flyers", 200, 150));   
    }
    
    private void setupBattles()
    {
    }
    

    //*******************************************************************************
    //*******************************************************************************
  
//These methods are not needed until Task 4.4
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
    * @param fname name of file storing requests
    */
    public void saveGame(String fname)
    {      
    }
    
    /** reads all information about the game from the specified file 
    * and returns a SWIM object
    * @param fname name of file storing the game
    * @return the game (as a SWIM object)
    */
    public SWIM restoreGame(String fname)
    {   
        return null;
    } 
        
    private void readBattles(String fname)
    {
    }   
}



