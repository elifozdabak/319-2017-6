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

public class DBConnectMovie
{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    public DBConnectMovie()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedatabase?autoReconnect=true&useSSL=false", "root", "12345678Ab");
            statement = connection.createStatement();
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Error: " + ex);
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
    }
    
    protected double getRate(int movieID)
    {
        try
        {
            String query = "select * from movie_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(movieID == resultSet.getInt("movieID"))
                {
                    return resultSet.getDouble("movieRate");
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
        return -1;
    }
    
    protected Movie findMovie(int movieID)
    {
        try
        {
            String query = "select * from movie_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(movieID == resultSet.getInt("MovieID"))
                {
                    return new Movie(resultSet.getInt("movieID"), resultSet.getString("movieName"), 
                            resultSet.getString("movieDescription"), resultSet.getString("movieDirector"),
                    resultSet.getDouble("movieRate"), resultSet.getString("movieGenre"), resultSet.getString("movieCast"),
                    resultSet.getInt("movieYear"), resultSet.getString("movieDuration"), resultSet.getInt("movieTotalRate"));
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return null;
    }
    
    protected ArrayList<Movie> getTopRatedMovies()
    {
        ArrayList<Movie> movies;
        movies = new ArrayList<>();
        int count = 0;
        
        try
        {
            String query = "select * from movie_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(resultSet.getDouble("movieRate") >= 4.0)
                {
                    movies.add(new Movie(resultSet.getInt("movieID"), resultSet.getString("movieName"),
                                resultSet.getString("movieDescription"), resultSet.getString("movieDirector"), 
                                resultSet.getDouble("movieRate"), resultSet.getString("movieGenre"),
                                resultSet.getString("movieCast"), resultSet.getInt("movieYear"), resultSet.getString("movieDuration"), resultSet.getInt("movieTotalRate")));
                    count++;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return(movies);
    }
    
    protected ArrayList<Movie> getDesiredCategory(String desiredCategory)
    {
        ArrayList<Movie> movies;
        movies = new ArrayList<>();
        try
        {
            String query = "select * from movie_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                if(resultSet.getString("movieGenre").toLowerCase().contains(desiredCategory.toLowerCase()))
                {
                    movies.add(new Movie(resultSet.getInt("movieID"), resultSet.getString("movieName"),
                                resultSet.getString("movieDescription"), resultSet.getString("movieDirector"), 
                                resultSet.getDouble("movieRate"), resultSet.getString("movieGenre"),
                                resultSet.getString("movieCast"), resultSet.getInt("movieYear"), resultSet.getString("movieDuration"), resultSet.getInt("movieTotalRate")));
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return(movies);
    }
    
    protected ArrayList<Movie> getAllMovies()
    {
        ArrayList<Movie> movies = new ArrayList<>();
        try
        {
            String query = "select * from movie_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                movies.add(new Movie(resultSet.getInt("movieID"), resultSet.getString("movieName"),
                            resultSet.getString("movieDescription"), resultSet.getString("movieDirector"), 
                            resultSet.getDouble("movieRate"), resultSet.getString("movieGenre"),
                            resultSet.getString("movieCast"), resultSet.getInt("movieYear"), resultSet.getString("movieDuration"), resultSet.getInt("movieTotalRate")));
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return(movies);
    }    
    
    protected int getTotalMovieCount()
    {
        int count = 0;
        ArrayList<Movie> movies = new ArrayList<>();
        try
        {
            String query = "select * from movie_table";
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                count++;
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
        return(count);
    }
    
    protected void changeRate(int movieID, double newRate)
    {
        int count = 1;
        try
        {
            String query = "update movie_table set movieRate = " + newRate + " where movieID = " + movieID;         
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.executeUpdate();
         }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);         
        }
    }
    
    protected void changeTotalRate(int movieID, int movieTotalRate)
    {
        int count = 1;
        try
        {
            String query = "update movie_table set movieTotalRate = " + movieTotalRate + " where movieID = " + movieID;         
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.executeUpdate();
         }
        catch(SQLException ex)
        {
            System.out.println("Error: " + ex);    
        }
    }
}
