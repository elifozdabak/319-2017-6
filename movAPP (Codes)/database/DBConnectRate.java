/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import model.Movie;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */
public class DBConnectRate
{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStmt;
    
    public DBConnectRate()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedatabase?autoReconnect=true&useSSL=false", "root", "12345678Ab");
            statement = connection.createStatement();
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
    }
    
    protected int getRate(String userID, int movieID)
    {
        try
        {
            String query = "select * from movie_rate_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(userID.equals(resultSet.getString("userID")) && movieID == resultSet.getInt("movieID"))
                {
                    return resultSet.getInt("userMovieRate");
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return -1;
    }
    
    protected boolean isRated(String userID, int movieID)
    {
        boolean result = false;
        try
        {
            String query = "select * from movie_rate_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(userID.equals(resultSet.getString("userID")) && movieID == resultSet.getInt("movieID"))
                {
                    result = true;
                } 
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
            result = false;
        }
        return result;
    }
    
    protected void changeRate(String userID, int movieID, int newRate)
    {
        try
        {
            String query = "DELETE FROM movie_rate_table WHERE userID = '" + userID + "' AND movieID = '" + movieID + "'"; 
            statement.executeUpdate(query);
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        setRate(userID, movieID, newRate);
    }
    
    protected void setRate(String userID, int movieID, int newRate)
    {
        try
        {
            String query = "insert into movie_rate_table (userID, movieID, userMovieRate) values('" + userID + "','" + movieID + "','" + newRate + "')";
            statement.executeUpdate(query);
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        } 
    }
    
    protected ArrayList<Movie> getTopRatedList(String userID)
    {
        ArrayList<Movie> resultList = new ArrayList<>();
        DBConnectMovie movieConnection = new DBConnectMovie();
        try
        {
            String query = "select * from movie_rate_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(userID.equals(resultSet.getString("userID")) && resultSet.getInt("userMovieRate") > 3)
                {
                    resultList.add(movieConnection.findMovie(resultSet.getInt("movieID")));
                } 
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return resultList;
    }
}