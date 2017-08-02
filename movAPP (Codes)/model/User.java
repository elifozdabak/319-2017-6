package model;

import java.util.ArrayList;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class User 
{
    // PROPERTIES
    private String name;
    private String surname;
    private String email;
    private String userID;
    private ArrayList<Movie> willWatchMovies;
    
    // CONSTRUCTORS
    public User (String name, String surname, String userID, String email) 
    {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.email = email;
        willWatchMovies = new ArrayList<>();
    }
    
    public User(String userID)
    {
        this.userID = userID;
        willWatchMovies = new ArrayList<>();
    }
    
    // METHODS
    public String getUserID()
    {
        return userID;
    }
}
