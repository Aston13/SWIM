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
    
    private int warchest; // Users warchest
    
    ArrayList<Battle> battles = new ArrayList<Battle>();
    private String admiralName; //Users admiral name
  

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
        return  ("Admiral: " + admiralName + ".\n" + // getAdmiralName()?
                 "Warchest: " + getWarchest() + ".\n" + 
                 "Defeated: " + String.valueOf(isDefeated()) + ".\n" +
                 "Active Forces: " + getASFleet() + ".\n");
    }
        
      
    /** returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated()
    {
        if(warchest <= 0 && ASF.isEmpty()) return true;
        return false; // call lose game/game over function?
    }
    
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest(){
        return warchest;
    }
    
    public void decreaseWarchest(int amount){
        warchest -= amount;
    }
    
    public void increaseWarchest(int amount){
        warchest += amount;
    }
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFleet(String ref) 
    {
        if(UFF.containsKey(ref)) return true;
        
        return false;
    }
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF)
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public String getUFFleet()
    {   
        if(UFF.isEmpty()) return "UFF is empty";

        Set<String> keySet = UFF.keySet();
        String s = "";
        //int n = 0;
        for(String elem : keySet){
            s += "Reference: " + elem + UFF.get(elem) + "\n" + "***************\n";
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
            s = "Reference: " + ref + UFF.get(ref).toString();
        } else if (ASF.containsKey(ref)){
            s = "Reference: " + ref + ASF.get(ref).toString(); 
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
        if(!isInASFleet(ref) && !isInUFFleet(ref))return 3;       
        //Force isn't in the UFF
        if(!isInUFFleet(ref))return 1;       
        //Not enough bit coin in warchest
        if(getWarchest() < (UFF.get(ref).getFee()))return 2;
        
        // Force is in the UFF and needs to be activated into the ASF,
        // and deduct from warchest warchest =- getFee()
        
        else {     
            ASF.put(ref, UFF.get(ref));
            UFF.remove(ref);
            
            decreaseWarchest(ASF.get(ref).getFee()); // Decrease warchest by activation fee amount
            return 0;     
        }
    }
    
        
    /** Returns true if the force with the reference code is in 
     * the active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref) {
        if(ASF.containsKey(ref)) return true;
        
        return false;
    }
    
    
    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if they are in the active Star Fleet(ASF)
     * pre-condition: isInASFleet(ref)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref) {
            UFF.put(ref, ASF.get(ref));
            ASF.remove(ref);
            increaseWarchest(UFF.get(ref).getFee()/2); // Increase warchest by activation fee amount  
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
            s += "Reference: " + elem + ASF.get(elem) + "\n" + "***************\n";
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
        // battle stuff
    }
     
    //*******************************************************************************
    private void setupForces()
    {
        // Wing: <ref>, <name, battle-strength, activation-fee, no-of-strikers>
        // Starship: <ref>, <name, battle-strength, activation-fee, lasers, torpedoes>
        // Warbird: <ref>, <name, battle-strength, activation-fee, cloaking>
        
        UFF.put("IW1", new Wing("Twisters", 200, 200, 10));
        UFF.put("SS2", new Starship("Enterprise", 200, 300, 10, 20));
        UFF.put("WB3", new WarBird("Droop", 100, 300, false));
        UFF.put("IW4", new Wing("Wingers", 400, 200, 20));
        UFF.put("WB5", new WarBird("Hang", 300, 400, true));
        UFF.put("SS6", new Starship("Voyager", 200, 450, 15, 10));
        UFF.put("SS7", new Starship("Explorer", 65, 120, 4, 5));
        UFF.put("WB9", new WarBird("Hover", 400, 300, false));
        UFF.put("IW10", new Wing("Flyers", 150, 200, 5));   
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



