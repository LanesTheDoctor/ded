/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goldcoastesports;


public class Competition
{
    private String game;
    private String location;
    private String competitionDate;
    private String team;
    private int points;
    
    
    //Constructor method
    public Competition (String game, String location, String competitionDate, String team, int points)
    {
        this.game = game;
        this.location = location;
        this.competitionDate = competitionDate;
        this.team = team;
        this.points = points;
    }
    
    //get methods
    public String getGame()
    {
        return game;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public String getCompetitionDate()
    {
        return competitionDate;
    }
    
    public String getTeam()
    {
        return team;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    
    //set methods
    public void setGame (String game)
    {
        this.game = game;
    }
    
    public void setLocation (String location)
    {
        this.location = location;
    }
    
    public void setCompetitionDate (String competitionDate)
    {
        this.competitionDate = competitionDate;
    }
    
    public void setTeam (String team)
    {
        this.team = team;
    }
    
    public void setPoints (int points)
    {
        this.points = points;
    }
    
    @Override
    public String toString()
    {
        //retun csv string formatt
        return game + ", " + location + ", " + competitionDate + ", " + team + ", " + points;
    }
}


