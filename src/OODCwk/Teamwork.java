package OODCwk; 


/**
 * Details of your team
 * 
 * @author Aston Turner and Jason Hitching
 * @version (26/11/2019)
 */
public class Teamwork
{
    private String[] details = new String[7];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team
        // It will help us if the surname of programmer1 comes
        // alphabetically before the surname of programmer2
        details[0] = "Spaghetti";
        details[1] = "Hitching";
        details[2] = "Jason";
        details[3] = "16063233";
        details[4] = "Turner";
        details[5] = "Aston";
        details[6] = "16052488";
    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
