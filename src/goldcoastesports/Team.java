/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goldcoastesports;

public class Team
{
    private String teamName;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    
    //constructor method (w/ parameters)
    public Team (String teamName, String contactName, String contactPhone, String contactEmail)
    {
        this.teamName = teamName;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }
    
    //get methods
    public String getTeamName()
    {
        return teamName;
    }
    
    public String getContactName()
    {
        return contactName;
    }
    
    public String getContactPhone()
    {
        return contactPhone;
    }
    
    public String getContactEmail()
    {
        return contactEmail;
    }
    
    //set methods
    
    public void setTeamName (String teamName)
    {
        this.teamName = teamName;
    }
    
    public void setContactName (String contactName)
    {
        this.contactName = contactName;
    }
    
    public void setContactPhone (String contactPhone)
    {
        this.contactPhone = contactPhone;
    }
    
    public void setContactEmail (String contactEmail)
    {
        this.contactEmail = contactEmail;
    }
    
    //override to save external csv file
    @Override
    public String toString()
    {
        return teamName + ", " + contactName + ", " + contactEmail + ", " + contactPhone;
    }
}
