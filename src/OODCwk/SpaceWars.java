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

public class SpaceWars implements SWIM,Serializable {
    private String admiralName;     // User-defined name.
    private int warchest;           // User's amount of bitcoin.
    
    // United Forces Fleet (UFF).
    HashMap<String, Force> UFF = new HashMap<String, Force>();
    
    // Active Star Fleet (ASF).
    HashMap<String, Force> ASF = new HashMap<String, Force>();
    
    // Contains a list of Battles, the Key is the Battle Number.
    HashMap<Integer, Battle> battles = new HashMap<Integer, Battle>();      

//**************** SWIM **************************  
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SpaceWars(String admiral) {
        this.admiralName = admiral;
        this.warchest = 1000;
        setupForces();
        setupBattles();
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
    public String toString() {
        return  ("Admiral: " + admiralName + ".\n" +
                 "Warchest: " + getWarchest() + ".\n" + 
                 "Defeated: " + String.valueOf(isDefeated()) + ".\n" +
                 "Active Forces: " + getASFleet() + ".\n");
    }
        
    /** returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */
    public boolean isDefeated() {
        if(warchest <= 0 && ASF.isEmpty()) return true;
        return false; // call lose game/game over function?!!!!!!!!!!!!
    }
    
    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest() {
        return warchest;
    }
    
    public void decreaseWarchest(int amount) {
        warchest -= amount;
    }
    
    public void increaseWarchest(int amount) {
        warchest += amount;
    }
    
    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFleet(String ref) {
        if(UFF.containsKey(ref)) return true;
        return false;
    }
    
    /**Returns a String representation of all forces in the United Forces Fleet(UFF)
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public String getUFFleet() {   
        if(UFF.isEmpty()) return "UFF is empty";

        Set<String> keySet = UFF.keySet();
        String s = "";
        
        for(String elem : keySet){
            s += "Reference: " + elem + UFF.get(elem) + "\n" + "***************\n";
        }
        return s;
    }
        
    /** Returns details of the force in the game with the given reference code 
     * @return details of the force in the game with the given reference code
     **/
    public String getForce(String ref) {
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
      * 2 if not enough money, 3 if no such force, 4 if force is destroyed
     **/       
    public int activateForce(String ref) {    
        
        if(!isInASFleet(ref) && !isInUFFleet(ref))return 3; // No force exists.      
        if(!isInUFFleet(ref))return 1;  // Force isn't in the UFF.
        
        /* Not enough bit coin in warchest to activate Force. */
        if(getWarchest() < (UFF.get(ref).getFee()))return 2;
        
        /* Force is in the UFF and needs to be activated into the ASF. */
        else {  
            if((UFF.get(ref).isDestroyed())){
                return 4;   // Force is destroyed.
            }
            ASF.put(ref, UFF.get(ref));
            UFF.remove(ref);
            (ASF.get(ref)).setActive();
            
            // Decrease warchest by activation fee amount.
            decreaseWarchest(ASF.get(ref).getFee()); 
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
        if(isInASFleet(ref)){
            UFF.put(ref, ASF.get(ref));
            ASF.remove(ref);
            
            /* If force is not destroyed. */
            if(!(UFF.get(ref)).isDestroyed()){ 
                (UFF.get(ref)).setDocked();
                
                // Increase warchest by activation fee amount.
                increaseWarchest(UFF.get(ref).getFee()/2);             
            }
        }
    }
    

    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet() {
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
     public boolean isBattle(int battleNo) {
         if (battles.containsKey(battleNo)){
             return true;
         }
         return false;
     }
    
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int battleNo) {
        String s = "\nNo such battle";
        
        if(isBattle(battleNo)){
            s = "Battle Number: " + battleNo + battles.get(battleNo).toString();
        }
        
        return s;
    }
    
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles() { 
        if(battles.isEmpty()) return "No battles exist";

        Set<Integer> keySet = battles.keySet();
        String s = "";

        for(Integer elem : keySet){
            s += "Reference: " + elem + battles.get(elem) + "\n" + "***************\n";
        }
        
        return s;
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
    public int doBattle(int battleNo) {
        if(!battles.containsKey(battleNo)) return -1;   // Battle does not exist
        
        int battleStrength = (battles.get(battleNo)).getStrength();
        String suitableForce;
        
        if((checkFights(battleNo)) != null){
            suitableForce = checkFights(battleNo);
        } else {
            fightLost(battleNo);
            
            /* Battle lost as no suitable forces found, deduct losses. */
            return 1; 
        }
        
        /* Fight won, add gains to warchest. */
        if (ASF.get(suitableForce).getStrength() >= battleStrength) { 
            fightWon(battleNo);
            return 0;
            
        /* Battle lost on battle strength , battle losses  deducted
         * destroy the force.
         */
        } else if (ASF.get(suitableForce).getStrength() < battleStrength) { 
            fightLost(battleNo);
            ASF.get(suitableForce).setDestroyed();
            recallForce(suitableForce);
            
            /* Battle lost admiral completely defeated. */
            if (ASF.isEmpty() && warchest < 120) { 
                return 3;
            }     
        }     
        return 2;   // Battle lost on battle strength.
    }
        
        
    public void fightWon(int battleNo) {
        warchest += battles.get(battleNo).getGains();
    }
    
    public void fightLost(int battleNo) {
        warchest -= battles.get(battleNo).getLosses();
    }
    
    public String checkFights(int battleNo) {
        ArrayList<BattleType> tempList = new ArrayList<BattleType>();
        Battle b = battles.get(battleNo);       // 'b' is a Battle object.
        BattleType bType = b.getBattleType();   // The battleType of a Battle.
        
        Set<String> key = ASF.keySet();
        for(String elem : key){
            tempList = (ASF.get(elem).getBattleType());
            
            /* If the battletype is a match. */
            if(tempList.contains(bType)){             
                return elem;
            }
        }
        return null;
 }
        
    //*******************************************************************************
    private void setupForces() {
        
        /* Wing: <ref>, <forceName, battleStrength, activationFee, strikers>.
         *
         * Starship: <ref>, <forceName, battleStrength, activationFee, lasers, 
         * torpedoes>.
         *
         * WarBird: <ref>, <forceName, battleStrength, activationFee, hasCloak>.
         */
        
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
    
    private void setupBattles() {
        
        /* Battle: <battleNo>, <battleType, enemyType, enemyStrength, 
         * losses, gains>.
         */
        battles.put(1, new Battle("Fight", "Borg", 200, 300, 100));
        battles.put(2, new Battle("Skirmish", "Kardassians", 700, 200, 120));
        battles.put(3, new Battle("Ambush", "Ferengi", 100, 400, 150));
        battles.put(4, new Battle("Fight", "Ewoks", 600, 600, 200));
        battles.put(5, new Battle("Ambush", "Borg", 500, 400, 90));
        battles.put(6, new Battle("Skirmish", "Groaners", 150, 100, 100));
        battles.put(7, new Battle("Fight", "Borg", 150, 500, 300));
        battles.put(8, new Battle("Ambush", "Wailers", 300, 300, 300));
    }
    
    //*******************************************************************************
    //*******************************************************************************
  
//These methods are not needed until Task 4.4
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
    * @param fname name of file storing requests
    */
    public void saveGame(String fname) {      
    }
    
    /** reads all information about the game from the specified file 
    * and returns a SWIM object
    * @param fname name of file storing the game
    * @return the game (as a SWIM object)
    */
    public SWIM restoreGame(String fname) {   
        return null;
    } 
        
    private void readBattles(String fname) {
    }   
}



