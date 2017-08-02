package model;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class Movie 
{
    //PROPERTIES
    private final int ID;
    private final int year;
    private final String name;
    private final String description;
    private final String director;
    private final String genre;
    private final String cast;
    private final String duration;
    private  double rate;
    private final int totalRate;
    
    
    // CONSTRUCTOR
    public Movie( int ID, String name, String description, String director, 
                  double rate, String genre, String cast,int year, String duration, int totalRate)
    {
    	this.ID          = ID;
    	this.name        = name;
    	this.description = description;
        this.director    = director;
        this.genre       = genre;
        this.cast        = cast;
        this.rate        = rate;     
        this.year        = year;
        this.duration    = duration;
        this.totalRate   = totalRate;
    }
    // METHODS
    public int getID() 
    {
        return ID;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public String getDirector()
    {
        return director;
    }
        public double getRate()
    {
        return rate;
    }
    public String getGenre()
    {
        return this.genre;
    }
    public String getCast()
    {
        return cast;
    }
    public int getYear()
    {
        return year;
    }
    public String getDuration()
    {
        return duration;
    }
    public int getTotalRate()
    {
        return totalRate;
    }
    
    public void setRate(double rate)
    {
        this.rate = rate;
    }

    
    public String toString()
    {
        return name;
    }


    

    
}