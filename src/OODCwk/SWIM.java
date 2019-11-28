package OODCwk; 
import java.io.*;
/**
 * This interface specifies the behaviour expected from the SWIM
 * system as required for 6COM1037 - Nov 2019
 * 
 * @author A.A.Marczyk 
 * @version 25/10/2019
 */
 

public interface SWIM extends Serializable 
{
 //**************** SWIM **************************  
    
    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * Star Fleet,(or, "No forces" if Star Fleet is empty)
     **/
    public String toString();
      
    /** returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated();
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest();
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFleet(String ref);
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF)
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public String getUFFleet();
        
    /** Returns details of the force in the game with the given reference code 
     * @return details of the force in the game with the given reference code
     **/
    public String getForce(String ref);
    
    
 // ***************** active Star Fleet Forces ************************   
    /** Allows a force to be activated into the active Star Fleet(ASF), but 
     * only if there are enough resources for the activation fee.The force's 
     * state is set to "active"
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF
      * 2 if not enough money, 3 if no such force
     **/       
    public int activateForce(String ref);
    
        
    /** Returns true if the force with the reference code is in 
     * the active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref);
    
    
    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if they are in the active Star Fleet(ASF)
     * pre-condition: isInASFleet(ref)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref);
        
        
    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet();
       
    
//**********************Battles************************* 
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
     public boolean isBattle(int num);
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int num);
    
    
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles();
     
     
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
    public int doBattle(int battleNo);
    
    
    //These methods are not needed until Task 4.4
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname);
    
    /** reads all information about the game from the specified file 
    * and returns a SWIM object
    * @param fname name of file storing the game
    * @return the game (as a SWIM object)
    *
    * @param fname name of file storing the game
     * @return the game (as a SpaceWars object)
    */
    public SWIM restoreGame(String fname);
}
