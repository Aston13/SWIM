package OODCwk; 
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the behaviour expected from a SWIM
 * system as required for 6COM1037 - Nov 2019.
 * 
 * @author A.A.Marczyk 
 * @author Aston Turner
 * @author Jason Hitching
 * @version 25/10/2019
 * 
 */
public class SpaceWars implements SWIM,Serializable {
    private final String admiralName;     // User-defined name.
    private int warchest;           // User's amount of bitcoin.
    
    // United Forces Fleet (UFF).
    private final HashMap<String, Force> UFF = new HashMap<>();
    
    // Active Star Fleet (ASF).
    private final HashMap<String, Force> ASF = new HashMap<>();
    
    // Contains a list of Battles, the Key is the Battle Number.
    private final HashMap<Integer, Battle> battles = new HashMap<>();      

//**************** SWIM **************************  
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SpaceWars(String admiral, String battleFile) throws IOException {
        this.admiralName = admiral;
        this.warchest = 1000;
        readBattles(battleFile);
        setupForces();
    }
    
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
    @Override
    public String toString() {
        return  ("Admiral:\t" + admiralName + "\n" +
                 "Warchest:\t" + getWarchest() + "\n" + 
                 "Defeated:\t" + String.valueOf(isDefeated()) + "\n\n" +
                 "Active Forces:\n" + getASFleet() + "\n");
    }
        
    /** returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled.
     */
    public boolean isDefeated() {
        if(getWarchest() < checkCheapestDockedForce() && ASF.isEmpty()) {
            return true;
        } 
        return false; // call lose game/game over function?!!!!!!!!!!!!
    }
    
    /** 
     * Returns the number of bit coins in the war chest.
     * 
     * @return the number of bit coins in the war chest
     */
    public int getWarchest() {
        return warchest;
    }
    
    private void decreaseWarchest(int amount) {
        warchest -= amount;
    }
    
    private void increaseWarchest(int amount) {
        warchest += amount;
    }
    
    /**
     * Checks if a passed in force reference matches a reference in the
     * UFF list.
     * 
     * @param ref reference of the force
     * @return true if the force exists in the UFF fleet, otherwise false
     **/
    public boolean isInUFFleet(String ref) {
        if(UFF.containsKey(ref)) return true;
        return false;
    }
    
    /**
     * Checks if any forces are in the United Forces Fleet. If there are, the 
     * list is formatted into a String and returned.
     * 
     * @return a String representation of all forces in the UFF, if any
     **/
    public String getUFFleet() {   
        if(UFF.isEmpty()) return "The United Forces Fleet is empty.";

        Set<String> keySet = UFF.keySet(); 
        String s = "\nUnited Forces Fleet\n===================\n";
        s += s.format("%-5s %-13s %-16s %-10s %-13s %-33s\n", "Ref", "Name",
                "Activation Cost", "Strength", "State", "Special Abilities");
   
        for(String elem : keySet){
            s += s.format("\u001B[33m%-4s | %s\n", elem, UFF.get(elem).toString());
        }
        return s + "\u001B[0m";
    }
        
    /** 
     * Retrieves details of the force in the game with the given reference code,
     * if it exists.
     * 
     * @return a formatted String of the force, if found
     **/
    public String getForce(String ref) {
        String s = "\nNo such force";
        
        if (UFF.containsKey(ref)){
            s = "\nUnited Forces Fleet\n===================\n";
            s += s.format("%-5s %-13s %-16s %-10s %-13s %-33s\n", "Ref", "Name",
                    "Activation Cost", "Strength", "State", "Special Abilities");
            s += s.format("\u001B[33m%-4s | %s\n", ref, UFF.get(ref).toString());
        } else if (ASF.containsKey(ref)){
            s = "\nActive Star Fleet\n=================\n";
            s += s.format("%-5s %-13s %-16s %-10s %-13s %-33s\n", "Ref", "Name",
                    "Activation Cost", "Strength", "State", "Special Abilities");
            s += s.format("\u001B[33m%-4s | %s\n", ref, ASF.get(ref).toString());
        }
        return s + "\u001B[0m";
    }
    
 // ***************** Active Star Fleet Forces ************************   
    /** 
     * Allows a force to be activated into the active Star Fleet(ASF), but 
     * only if there are enough resources for the activation fee. The force's 
     * state is set to "active".
     * 
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF
      * 2 if not enough money, 3 if no such force, 4 if force is destroyed
     **/       
    public int activateForce(String ref) {    
        
        if(!isInASFleet(ref) && !isInUFFleet(ref)){return 3;} // No force exists.
        
        else if(!isInUFFleet(ref)){return 1;}  // Force isn't in the UFF.
        
        /* Not enough bit coin in warchest to activate Force. */
        else if(getWarchest() < (UFF.get(ref)).getFee()){return 2;}
        
        else if((UFF.get(ref).isDestroyed())){return 4;} // Force is destroyed.
        
        /* Force is in UFF, isn't destroyed and user has enough bitcoin. */
        else if (!isDefeated()){
            ASF.put(ref, UFF.get(ref));
            UFF.remove(ref);
            (ASF.get(ref)).setActive();

            // Decrease warchest by activation fee amount.
            decreaseWarchest((ASF.get(ref)).getFee()); 
            return 0;
        }
        
        return -1;
    }
      
    /** 
     * Checks if a force is in the Active Star Fleet.
     * 
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref) {
        if(ASF.containsKey(ref)) return true;
        return false;
    }

    /** 
     * Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only  
     * if they are in the Active Star Fleet(ASF). A pre-condition is based on 
     * the method: isInASFleet(ref).
     * 
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
    
    /**
     * Creates a list of forces (if any) in the ASF.
     * 
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet() {
        if(ASF.isEmpty()) return "The Active Star Fleet is empty.";

        Set<String> keySet = ASF.keySet(); 
        String s = "\nActive Star Fleet\n=================\n";
        s += s.format("%-5s %-13s %-16s %-10s %-13s %-33s\n", "Ref", "Name",
                "Activation Cost", "Strength", "State", "Special Abilities");
   
        for(String elem : keySet){
            s += s.format("\u001B[33m%-4s | %s\n", elem, ASF.get(elem).toString());
        }
        return s + "\u001B[0m";
        
    }
       
    
//**********************Battles************************* 
    /** 
     * Checks if a battle exists.
     * 
     * @param battleNo the reference number of the battle
     * @return true if the number represents a battle. Otherwise, false.
     **/
     public boolean isBattle(int battleNo) {
         if (battles.containsKey(battleNo)){
             return true;
         }
         return false;
     }
    
    /** 
     * Provides a String representation of a battle given by 
     * the battle number.
     * 
     * @param battleNo reference of the Battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int battleNo) {
        String s = "\nThat battle does not exist.";
        
        if(isBattle(battleNo)){
            s = "\nBattles\n=======\n";
            s += s.format("%-6s %-10s %-13s %-8s %-6s %-33s\n", "Battle Number",
                    "Type", "Enemy", "Strength", "Losses", "Gains");
            s += s.format("\u001B[33m%12s | %s\n", battleNo,
                    battles.get(battleNo).toString());
        }
        
        return s + "\u001B[0m";
    }
    
    /** 
     * Checks if any battles exist, if so, the battles list is formatted into
     * a String and returned.
     * 
     * @return a String representation of all battles, if any
     **/
    public String getAllBattles() { 
        if(battles.isEmpty()) return "No battles exist.";

        Set<Integer> keySet = battles.keySet();
        String s = "\nBattles\n=======\n";
        s += s.format("%-6s %-10s %-13s %-8s %-6s %-33s\n", "Battle Number",
                "Type", "Enemy", "Strength", "Losses", "Gains");

        for(Integer elem : keySet) {
            s += s.format("\u001B[33m%12s | %s\n", elem,
                    battles.get(elem));
        }

        return s + "\u001B[0m";
    }
     
     
    /** 
     * Retrieves the battle represented by the battle number.Finds 
     * a force from the active Star Fleet which can engage in the battle.The  
     * results of battle will be one of the following: 
     * 
     *  0 - Battle won, battle gains added to the war chest,
     * 
     *  1 - Battle lost as no suitable force available, battle losses 
     *      deducted from war chest
     * 
     *  2 - Battle lost on battle strength , battle losses 
     *      deducted from war chest and destroy the force
     * 
     *  3 - no such battle
     * 
     *  -1 - If a battle is lost and admiral completely defeated (no resources and 
     *      no forces to recall)
     * 
     * @param battleNo is the reference number of the battle
     * @return an integer showing the result of a battle
     */ 
    public int doBattle(int battleNo) {
        
        
        if(!battles.containsKey(battleNo)) return 3;   // Battle does not exist
        
        int battleStrength = (battles.get(battleNo)).getStrength();
        String suitableForce;
        
        if((checkFights(battleNo)) != null) {
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
            if (ASF.isEmpty() && getWarchest() < checkCheapestDockedForce()) { 
                return -1;
            }
            return 2;
        }     
        return -1;   // Battle lost on battle strength.
    }
        
    private int checkCheapestDockedForce() {
        int cheapest = getWarchest() + 1;
        
        if(UFF.isEmpty()) return cheapest;

        Set<String> keySet = UFF.keySet();

        for(String elem : keySet) {
            if(!UFF.get(elem).isDestroyed()){
                cheapest = Math.min(cheapest,(UFF.get(elem)).getFee());
            }
        }

        return cheapest;
    }
    
    private void fightWon(int battleNo) {
        increaseWarchest(battles.get(battleNo).getGains());
    }
    
    private void fightLost(int battleNo) {
        decreaseWarchest(battles.get(battleNo).getLosses());
    }
    
    private String checkFights(int battleNo) {
        ArrayList<BattleType> tempList = new ArrayList<>();
        Battle b = battles.get(battleNo);       // 'b' is a Battle object.
        BattleType bType = b.getBattleType();   // The battleType of a Battle.
        
        Set<String> key = ASF.keySet();
        for(String elem : key) {
            tempList = (ASF.get(elem).getBattleType());
            
            /* If the battletype is a match. */
            if(tempList.contains(bType)) {             
                return elem;
            }
        }
        return null;
 }
        
    //*******************************************************************************
    private void setupForces() {
        
        /* 
         * Wing: <ref>, <forceName, battleStrength, activationFee, strikers>.
         *
         * Starship: <ref>, <forceName, battleStrength, activationFee, lasers, 
         * torpedoes>.
         *
         * WarBird: <ref>, <forceName, battleStrength, activationFee, hasCloak>.
         *
         * The HashMap key is always unique. Attempts to add the same key will 
         * result in the original entry being overwritten.
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
        
        /* 
         * Battle: <battleNo>, <battleType, enemyType, enemyStrength, 
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
    
    /* ***************   file write/read  ********************* */
    
    /** 
     * Writes the whole game object to the specified file.
     * 
     * @param fname name of file that the game will be saved to
     */
    public void saveGame(String fname) {
        try {
            FileOutputStream f = new FileOutputStream(new File(fname));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            o.writeObject(SpaceWars.this);

            o.close();
            f.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpaceWars.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SpaceWars.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** 
     * Reads all information about the game from the specified file 
     * and returns a SWIM object.
     * 
     * @param fname name of file storing the game
     * @return the game (as a SWIM object)
     * 
     */
    public SWIM restoreGame(String fname) {
        try {
            FileInputStream fi = new FileInputStream(new File(fname));
            ObjectInputStream oi = new ObjectInputStream(fi);
            
            SpaceWars sw1 = (SpaceWars) oi.readObject();
            
            oi.close();
            fi.close();
            
            return sw1;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SpaceWars.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SpaceWars.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SpaceWars.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
        
    /** 
     * Reads and loads in data from the passed in file. The data is used to 
     * setup the battles list.
     * 
     * @param battleFile name of file battle data is loaded from
     * 
     */
    private void readBattles(String battleFile) throws FileNotFoundException,
            IOException {
        String line;
        int count = 1;
        
        BufferedReader reader = new BufferedReader(new FileReader(battleFile));
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            
            if (parts.length >= 5) {
                
                Battle b1 = new Battle
                       (parts[0],                       // Battle type.
                        parts[1],                       // Enemy type.
                        Integer.parseInt(parts[2]),     // Enemy strength.
                        Integer.parseInt(parts[3]),     // Losses.
                        Integer.parseInt(parts[4]));    // Gains.
                
                battles.put(count, b1);
                count++;
            }  
        }
        reader.close();
    }   
}



