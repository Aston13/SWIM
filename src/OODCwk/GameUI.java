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
        SWIM gp;
        
        try {
            System.out.println(welcomeLogo());
            System.out.println("Enter the admiral's name...");
            String s = myIn.nextLine();
            gp = new SpaceWars(s);  // Create SpaceWars game.
            
            /* To test readBattles(), replace above by 
             * gp = new SpaceWars(s, "Olenka.txt");
            */
            
            choice = 100;
            
            while (choice != 0 ) {
                if(gp.isDefeated()){
                    System.out.println(gameOverLogo());
                    break;
                };
                choice = getMenuItem();
                
                if (choice == 1) {
                    
                    /* List United Forces Fleet (UFF) forces. */
                    System.out.println(gp.getUFFleet()); 
                    
                } else if (choice == 2) {
                    
                    /* List Active Star Fleet (ASF) forces. */
                    System.out.println(gp.getASFleet());
                    
                } else if (choice == 3) {
                    
                    /* Get force by reference. */
                    System.out.println("Enter force reference...");
                    String forceRef = (myIn.nextLine()).trim();
                    System.out.println(gp.getForce(forceRef));
                    
                } else if (choice == 4) {
                    
                    /* Activate force by reference. */
                    System.out.println("Enter force reference...");
                    String nme = (myIn.nextLine()).trim();
                    
                    if(!gp.isDefeated()) {
                        result = gp.activateForce(nme.toUpperCase());
                    }
                    
                    System.out.println(activation(result) +
                        "\nWar Chest: " + gp.getWarchest());
                    
                } else if (choice == 5) {
                    
                    /* Engage in battle by Battle Number. */
                    System.out.println("Enter battle number...");
                    int battleNo = (Integer.parseInt(myIn.nextLine()));
                    System.out.println(battling(gp.doBattle(battleNo)));
                    
                } else if (choice == 6) {
                    
                    /* Recall a force by reference. */
                    System.out.println("Enter force reference...");
                    String ref = (myIn.nextLine()).trim();
                    
                    if(gp.getForce(ref) != null && gp.isInASFleet(ref)) { 
                        gp.recallForce(ref);
                        System.out.println("\nForce " + ref +
                        " has been recalled" + "\nWar Chest: " + gp.getWarchest());
  
                    } else {
                        System.out.println("That force does not exist within the"
                                           + " Active Star Fleet."); 
                    }
                    
                } else if (choice==7) {
                    
                    /* View the state of the game. Outputted as a String. */
                    System.out.println(gp.toString());
                } // else if (choice == 8) // Task 4.4 only {
//                    System.out.println("Write to file");
//                    gp.saveGame("myfile.txt");
//                }
//                else if (choice == 9) // Task 4.4 only
//                {
//                    System.out.println("Restore from file");
//                    gp = gp.restoreGame("myfile.txt");
//                    System.out.println(gp.toString());               
//                }
            }     
        }
        catch (IOException e) {System.out.println (e);}
        
        catch (NumberFormatException e){System.out.println("Error - Input"
                + " choice must be a number 1 - 7");}
        
        System.out.println("Thank-you for playing.");
    }
    
    private static int getMenuItem()throws IOException {   
        int choice = 100;
        System.out.println("\nMain Menu");
        System.out.println("0. Quit");
        System.out.println("1. List forces in United Forces Fleet");
        System.out.println("2. List forces in admiral's Active Star Fleet"); 
        System.out.println("3. View a force");
        System.out.println("4. Activate a force into admiral's Active Star Fleet");
        System.out.println("5. Engage in a battle");
        System.out.println("6. Recall a force");
        System.out.println("7. View the state of the game");
        //Task 4.4 only
//        System.out.println("8. Save this game");
//        System.out.println("9. Restore a game");
       
        while (choice < 0 || choice  > 9) {
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
                        + "was destroyed.";
            case 3:return "The battle was lost and the admiral is completely"
                        + " defeated";
        }
        return "That battle does not exist.";
    }
    
    private static String welcomeLogo(){
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
    
    private static String gameOverLogo(){
        return (" _____                        _____                \n" +
"|  __ \\                      |  _  |               \n" +
"| |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __ \n" +
"| | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|\n" +
"| |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |   \n" +
" \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   \n" +
"                                                   \n" +
"                                                   \n" + 
                battling(3));
    }
}