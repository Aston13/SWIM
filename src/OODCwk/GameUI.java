package OODCwk; 
import java.io.*;
import java.util.*;

/**s
 * provides a  command line interface
 * 
 * @author A.A.Marczyk
 * @version 06/10/19
 */
public class GameUI
{
    private static Scanner myIn = new Scanner(System.in);

    public static void main(String[] args)
    {
        int choice;
        String admiralName;
        int result = -1;
        SWIM gp;
        
        try
        {
            System.out.println("Enter admiral's name");
            String s = myIn.nextLine();
            gp = new SpaceWars(s); // create
            // To test readBattles(), replace above by 
            // gp = new SpaceWars(s, "Olenka.txt"); 
            System.out.println(gp.getAllBattles());
            choice = 100;
            while (choice != 0 )
            {
                choice = getMenuItem();
                if (choice == 1)  //list UFFleet
                {
                    System.out.println(gp.getUFFleet());// provide code!!
                    
                }
                else if (choice == 2) //List active Star Fleet
                {
                    System.out.println(gp.getASFleet());
                }
                else if (choice == 3) //get Force
                {
                    System.out.println("Enter force reference");
                    String forceRef = (myIn.nextLine()).trim();
                    System.out.println(gp.getForce(forceRef)); // provide code
                } 
                else if (choice == 4) //activate Force
                {   
                    System.out.println("Enter force reference");
                    String nme = (myIn.nextLine()).trim();
                    if(!gp.isDefeated()) //optional
                    {
                        result = gp.activateForce(nme);
                    }
                    System.out.println(activation(result) + "\nWar chest = " + gp.getWarchest());
                }
                else if (choice == 5) //do battle
                {
                    System.out.println("Enter battle number");
                    int battleNo = (myIn.nextInt());
                    gp.doBattle(battleNo); // provide code
                }
                
                else if (choice == 6) //recall force
                {
                    System.out.println("Enter Force reference");
                    String ref = (myIn.nextLine()).trim();
                    if(gp.getForce(ref) != null && gp.isInASFleet(ref))
                    {
                        gp.recallForce(ref);
                        System.out.println("\nForce " + ref +
                        " recalled" + "\nWar Chest: " + gp.getWarchest());
                    }
                }
                else if (choice==7) //view game state
                {
                    System.out.println(gp.toString());
                }
//                else if (choice == 8) // Task 4.4 only
//                {
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
    
    private static int getMenuItem()throws IOException
    {   int choice = 100;  
        System.out.println("Main Menu");
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
       
        
        while (choice < 0 || choice  > 9)
        {
            System.out.println("Enter the number of your choice");
            choice =  Integer.parseInt(myIn.nextLine());
        }
        return choice;        
    } 
    
    private static String activation(int code)
    {
        switch (code)
        {
            case 0:return "force is activated"; 
            case 1:return "force is not in the UFF"; 
            case 2:return "not enough money";
            case 3:return "no such force";
            default: return "Error";
        }
    }
    
    private static String battling(int code)
    {
        switch (code)
        {
            case 0:return "Battle won"; 
            case 1:return "Battle lost as no suitable force available"; 
            case 2:return "Battle lost on battle strength, force destroyed";
            case 3:return "battle is lost and admiral completely defeated ";
        }
        return " no such battle ";
    }                                         
}