package OODCwk; 
import java.io.*;
import java.util.*;

/**
 * Provides a Command Line Interface (CLI) for SpaceWars.
 * 
 * @author A.A.Marczyk
 * @author Aston Turner
 * @author Jason Hitching
 * @version 25/10/2019
 *
 */
public class GameUI {
    private static Scanner myIn = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        String admiralName;
        int result = -1;
        int battleNo;
        SWIM gp;
        
        try {
            System.out.println(welcomeLogo());
            System.out.println("Enter your name, admiral...");
            String s = myIn.nextLine();
            
            //gp = new SpaceWars(s);  // Create SpaceWars game.
            gp = new SpaceWars(s, "olenka.txt");
            
            /* To test readBattles(), replace above by 
             * gp = new SpaceWars(s, "Olenka.txt");
            */
            
            System.out.println("\n\n\n\n\n\nWelcome aboard, Admiral " + s + ".");
            choice = 100;
            
            while (choice != 0 ) {
                if(gp.isDefeated()){
                    System.out.println(gameOverLogo());
                    break;
                }
                
                choice = getMenuItem();
                
                switch (choice) {
                    case 1:
                        
                        /* List United Forces Fleet (UFF) forces. */
                        System.out.println(gp.getUFFleet());
                        break;
                    case 2:
                        
                        /* List Active Star Fleet (ASF) forces. */
                        System.out.println(gp.getASFleet());
                        break;
                    case 3:
                        
                        /* Get force by reference. */
                        System.out.println("Enter force reference...");
                        String forceRef = ((myIn.nextLine()).trim()).toUpperCase();
                        System.out.println(gp.getForce(forceRef));
                        break;
                    case 4:
                        
                        /* Activate force by reference. */
                        System.out.println("Enter force reference...");
                        String nme = ((myIn.nextLine()).trim()).toUpperCase();
                        if(!gp.isDefeated()) {
                            result = gp.activateForce(nme.toUpperCase());
                        }   System.out.println(activation(result) +
                                "\nWar Chest: " + gp.getWarchest());
                        break;
                    case 5:
                        
                        /* Engage in battle by Battle Number. */
                        System.out.println("Enter battle number...");
                        battleNo = (Integer.parseInt(myIn.nextLine()));
                        System.out.println(battling(gp.doBattle(battleNo)));
                        System.out.println("War Chest: " + gp.getWarchest());
                        break;
                    case 6:
                        
                        /* Recall a force by reference. */
                        System.out.println("Enter force reference...");
                        String ref = ((myIn.nextLine()).trim()).toUpperCase();
                        if(gp.getForce(ref) != null && gp.isInASFleet(ref)) {
                            gp.recallForce(ref);
                            System.out.println("\nForce " + ref +
                                    " has been recalled" + "\nWar Chest: " + gp.getWarchest());
                            
                        } else {
                            System.out.println("That force does not exist within the"
                                    + " Active Star Fleet.");
                        }   break;
                    case 7:
                        
                        /* View the state of the game. Outputted as a String. */
                        System.out.println(gp.toString());
                        break;
                    case 8:
                        
                        /* Save game to file. */
                        System.out.println("Game Saved.");
                        gp.saveGame("myfile.txt");
                        break;
                    case 9:
                        
                        /* Load game from file. */
                        System.out.println("Game Loaded.");
                        gp = gp.restoreGame("myfile.txt");
                        System.out.println(gp.toString());
                        break;
                    case 10:
                        
                        /* View all possible battles. */
                        System.out.println(gp.getAllBattles());
                        break;
                    case 11:
                        
                        /* View a specific battle. */
                        System.out.println("Enter battle reference...");
                        battleNo = (Integer.parseInt(myIn.nextLine()));
                        System.out.println(gp.getBattle(battleNo));
                        break;
                    default:
                        break;               
                }
            }     
        }
        catch (IOException e) {System.out.println (e);}
        
        catch (NumberFormatException e) {
            System.out.println("Error - Input for choice must be a number "
                    + "between 1 and 11.\nPlease restart the game and try again.");
        }
        
        System.out.println("Thank-you for playing.");
    }
    
    private static int getMenuItem()throws IOException {   
        int choice = 100;
        System.out.println("\nMain Menu");
        System.out.println("0.  Quit");
        System.out.println("1.  List forces in United Forces Fleet");
        System.out.println("2.  List forces in admiral's Active Star Fleet"); 
        System.out.println("3.  View a force");
        System.out.println("4.  Activate a force into admiral's Active Star Fleet");
        System.out.println("5.  Engage in a battle");
        System.out.println("6.  Recall a force");
        System.out.println("7.  View the state of the game");
        System.out.println("8.  Save this game");
        System.out.println("9.  Restore a game");
        System.out.println("10. List all battles");
        System.out.println("11. View a battle");
       
        while (choice < 0 || choice  > 11) {
            System.out.println("Enter the number of your choice...");
            choice =  Integer.parseInt(myIn.nextLine());
        }
        return choice;        
    } 
    
    private static String activation(int code) {
        switch (code) {
            case 0:return "The force has been activated."; 
            case 1:return "That force is not in the UFF."; 
            case 2:return "You do not have enough bit coin to do that.";
            case 3:return "That force does not exist.";
            case 4:return "The force is destroyed and cannot be activated.";
            default: return "Error";
        }
    }
    
    private static String battling(int code) {
        switch (code) {
            case 0:return "The battle was won!"; 
            case 1:return "The battle was lost as no suitable force is available."; 
            case 2:return "The battle was lost on battle strength and the force"
                        + " was destroyed.";
            case 3:return "That battle does not exist.";
        }
        return "The battle was lost and the admiral is completely"
                        + " defeated";
    }
    
    private static String welcomeLogo() {
        return "                     `. ___\n" +
"                    __,' __`.                _..----....____\n" +
"        __...--.'``;.   ,.   ;``--..__     .'    ,-._    _.-'\n" +
"  _..-''-------'   `'   `'   `'     O ``-''._   (,;') _,'\n" +
",'________________                          \\`-._`-','\n" +
" `._              ```````````------...___   '-.._'-:\n" +
"    ```--.._      ,.                     ````--...__\\-.\n" +
"            `.--. `-`                       ____    |  |`\n" +
"              `. `.                       ,'`````.  ;  ;`\n" +
"                `._`.        __________   `.      \\'__/`\n" +
"                   `-:._____/______/___/____`.     \\  `\n" +
"                               |       `._    `.    \\\n" +
"                               `._________`-.   `.   `.___\n" +
"\n\n" + 
                "      ____                    _      __           \n" +
"     / __/__  ___ ________   | | /| / /__ ________\n" +
"    _\\ \\/ _ \\/ _ `/ __/ -_)  | |/ |/ / _ `/ __(_-<\n" +
"   /___/ .__/\\_,_/\\__/\\__/   |__/|__/\\_,_/_/ /___/\n" +
"      /_/                                         \n\n";
    }
    
    private static String gameOverLogo() {
        return (" _____                        _____                \n" +
"|  __ \\                      |  _  |               \n" +
"| |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __ \n" +
"| | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|\n" +
"| |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |   \n" +
" \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   \n" +
"                                                   \n" +
"                                                   \n" + 
                battling(-1));
    }
}