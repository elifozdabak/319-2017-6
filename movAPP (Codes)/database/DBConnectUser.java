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
public class DBConnectUser 
{
    private Connection connection;
    private Statement statement;
    private Statement updateStatement;
    private ResultSet resultSet;
    
    public DBConnectUser()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase?autoReconnect=true&useSSL=false", "root", "12345678Ab");
            statement = connection.createStatement();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
    }
    
    protected boolean userIDCheck(String userID) 
    {
        try
        {
            String query = "select * from user_id_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                String dataUserID = resultSet.getString("userID");
                if(userID.equals(dataUserID))
                {
                    return true;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
        return false;
    }
    
    protected void getData()
    {
        try
        {
            String query = "select * from user_id_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                String userID = resultSet.getString("userID");
                String userName = resultSet.getString("userName");
                String userSurname = resultSet.getString("userSurname");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
    }
    
    protected void getTopRatedList()
    {
        try
        {
            String query = "select * from user_id_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                String userID = resultSet.getString("userID");
                String userName = resultSet.getString("userName");
                String userSurname = resultSet.getString("userSurname");
                System.out.println("ID: " + userID + " - Name: " + userName);
                
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
    }

    protected void SignUps(String userID, String userName, String userSurname, String userEmail) 
    {
        try
        {
            String query = "insert into user_id_table (userID, userName, userSurname, userEMail) values('" + userID + "','" + userName + "','" + userSurname + "','" + userEmail + "')";
            statement.executeUpdate(query);
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
    }   
    
    protected void addWillWatchMovie(String userID, int movieID) 
    {
        try
        {
            String query = "insert into user_willwatch_table (userID, movieID) values('" + userID + "','" + movieID + "')";
            statement.executeUpdate(query);
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        } 
    } 
    
    protected void removeWillWatch(String userID, int movieID)
    {
        try
        {
            String query = "DELETE FROM user_willwatch_table WHERE userID = '" + userID + "' AND movieID = '" + movieID + "'"; 
            statement.executeUpdate(query);
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        } 
    }
    
    protected boolean isInWillWatchList(String userID, int movieID) 
    {
        boolean result = false;
        try
        {
            String query = "select * from user_willwatch_table";
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
    
    protected ArrayList<Movie> getWillWatchList(String userID)
    {
        ArrayList<Movie> resultList = new ArrayList<>();
        DBConnectMovie movieConnection = new DBConnectMovie();
        try
        {
            String query = "select * from user_willwatch_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(userID.equals(resultSet.getString("userID")))
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
