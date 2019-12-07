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
            System.out.println("Enter admiral's name");
            String s = myIn.nextLine();
            gp = new SpaceWars(s);  // Create SpaceWars game.
            
            /* To test readBattles(), replace above by 
             * gp = new SpaceWars(s, "Olenka.txt");
            */
            
            choice = 100;
            
            while (choice != 0 ) {
                choice = getMenuItem();
                
                if (choice == 1) {
                    
                    /* List United Forces Fleet (UFF) forces. */
                    System.out.println(gp.getUFFleet()); 
                    
                } else if (choice == 2) {
                    
                    /* List Active Star Fleet (ASF) forces. */
                    System.out.println(gp.getASFleet());
                    
                } else if (choice == 3) {
                    
                    /* Get force by reference. */
                    System.out.println("Enter force reference");
                    String forceRef = (myIn.nextLine()).trim();
                    System.out.println(gp.getForce(forceRef));
                    
                } else if (choice == 4) {
                    
                    /* Activate force by reference. */
                    System.out.println("Enter force reference");
                    String nme = (myIn.nextLine()).trim();
                    
                    if(!gp.isDefeated()) {
                        result = gp.activateForce(nme);
                    }
                    
                    System.out.println(activation(result) +
                        "\nWar chest = " + gp.getWarchest());
                    
                } else if (choice == 5) {
                    
                    /* Engage in battle by Battle Number. */
                    System.out.println("Enter battle number");
                    int battleNo = (Integer.parseInt(myIn.nextLine()));
                    System.out.println(battling(gp.doBattle(battleNo)));
                    
                } else if (choice == 6) {
                    
                    /* Recall a force by reference. */
                    System.out.println("Enter Force reference");
                    String ref = (myIn.nextLine()).trim();
                    
                    if(gp.getForce(ref) != null && gp.isInASFleet(ref)) { 
                        gp.recallForce(ref);
                        System.out.println("\nForce " + ref +
                        " recalled" + "\nWar Chest: " + gp.getWarchest());
  
                    } else {
                        System.out.println("No such force exists within the"
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
        System.out.println("Thank-you");
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
            System.out.println("Enter the number of your choice");
            choice =  Integer.parseInt(myIn.nextLine());
        }
        return choice;        
    } 
    
    private static String activation(int code) {
        switch (code) {
            case 0:return "force is activated"; 
            case 1:return "force is not in the UFF"; 
            case 2:return "not enough money";
            case 3:return "no such force";
            case 4:return "force is destroyed and cannot be activated";
            default: return "Error";
        }
    }
    
    private static String battling(int code) {
        switch (code) {
            case 0:return "Battle won"; 
            case 1:return "Battle lost as no suitable force available"; 
            case 2:return "Battle lost on battle strength, force destroyed";
            case 3:return "battle is lost and admiral completely defeated ";
        }
        return " no such battle ";
    }                                         
}