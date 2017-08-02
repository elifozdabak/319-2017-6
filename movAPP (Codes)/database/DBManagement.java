/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import model.Movie;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */
public class DBManagement 
{
    // PROPERTIES
    DBConnectMovie movieConnection;
    DBConnectUser  userConnection;
    DBConnectRate  rateConnection;
    
    // CONSTRUCTORS
    public DBManagement()
    {
        movieConnection = new DBConnectMovie();
        userConnection  = new DBConnectUser();
        rateConnection  = new DBConnectRate();
    }
    
    // METHODS
    
    // Movie Methods
    public double getRate(int movieID)
    {
        return movieConnection.getRate(movieID);
    }
    
    public Movie findMovie(int movieID)
    {
        return movieConnection.findMovie(movieID);
    }
    
    public ArrayList<Movie> getTopRatedMovies()
    {
        return movieConnection.getTopRatedMovies();
    }
    
    public ArrayList<Movie> getDesiredCategory(String desiredCategory)
    {
        return movieConnection.getDesiredCategory(desiredCategory);
    }
    
    public ArrayList<Movie> getAllMovies()
    {
        return movieConnection.getAllMovies();
    }    
    
    public int getTotalMovieCount()
    {
        return movieConnection.getTotalMovieCount();
    }
    
    public void changeRate(int movieID, double newRate)
    {
        movieConnection.changeRate(movieID, newRate);
    }
    
    public void changeTotalRate(int movieID, int movieTotalRate)
    {
        movieConnection.changeTotalRate(movieID, movieTotalRate);
    }

    // Rate Methods
    public int getRate(String userID, int movieID)
    {
        return rateConnection.getRate(userID, movieID);
    }
    
    public boolean isRated(String userID, int movieID)
    {
        return rateConnection.isRated(userID, movieID);
    }
    
    public void changeRate(String userID, int movieID, int newRate)
    {
        rateConnection.changeRate(userID, movieID, newRate);
    }
    
    public void setRate(String userID, int movieID, int newRate)
    {
        rateConnection.setRate(userID, movieID, newRate);
    }
    
    public ArrayList<Movie> getTopRatedList(String userID)
    {
        return rateConnection.getTopRatedList(userID);
    }
    
    // User Methods
    public boolean userIDCheck(String userID) 
    {
        return userConnection.userIDCheck(userID);
    }
    
    public void getData()
    {
        userConnection.getData();
    }
    
    public void getTopRatedList()
    {
        userConnection.getTopRatedList();
    }

    public void SignUps(String userID, String userName, String userSurname, String userEmail) 
    {
        userConnection.SignUps(userID, userName, userSurname, userEmail);
    }    
    
    public void addWillWatchMovie(String userID, int movieID) 
    {
        userConnection.addWillWatchMovie(userID, movieID);
    }
    
    public void removeWillWatch(String userID, int movieID)
    {
        userConnection.removeWillWatch(userID, movieID);
    }
    
    public boolean isInWillWatchList(String userID, int movieID)
    {
        return userConnection.isInWillWatchList(userID, movieID);
    }
    
    public ArrayList<Movie> getWillWatchList(String userID)
    {
        return userConnection.getWillWatchList(userID);
    }
    
}
