package view;

import constants.UIColors;
import database.DBManagement;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Movie;
import model.User;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class HomePageViewer extends JPanel
{
    // PROPERTIES
    private final JScrollPane    jScrollPane;
    private final DBManagement databaseConnection;
    private TopMoviesPanel topMoviesPanel;
    private final ArrayList<Movie> movieArrayList;
   
    private boolean isUser;
    private User user;
    
    // CONSTRUCTOR
    public HomePageViewer(boolean isUser, User user, ArrayList<Movie> movieArrayList)
    {
        this.isUser = isUser;
        this.user = user;
        this.movieArrayList = movieArrayList;
        
        databaseConnection = new DBManagement();
        
        // For User
         
        // For Guest
        
        // For Both
        topMoviesPanel = new TopMoviesPanel(movieArrayList, movieArrayList.size());
        jScrollPane  = new JScrollPane(topMoviesPanel);

        jScrollPane.setBackground(UIColors.LIGHTBLUE);

        this.setLayout( new BorderLayout());
        this.add( jScrollPane, BorderLayout.CENTER);
    }
    
    public void setIsUser(boolean isUser)
    {
        this.isUser = isUser;
    }
    
    // Returns image list for ProjectActionListener
    public ArrayList<JButton> getImgButtonList()
    {
        return topMoviesPanel.getImgButtonList();
    }
    // Returns movie for ProjectActionListener
    public Movie getMovieFromImgPanel()
    {
        return topMoviesPanel.getMovieFromImgPanel();
    }
    
    public ArrayList<Integer> getAllTopMovies()
    {
        ArrayList<Integer> topMoviesID = new ArrayList<Integer>();
        for(int i = 0 ; i < movieArrayList.size() ; i++)
        {
            topMoviesID.add(i, (movieArrayList).get(i).getID());
        }
        return topMoviesID;
    }
    
    public int getNumberOfTopRatedMovies()
    {
        return movieArrayList.size();
    }
    
    public void setTopMovies(ArrayList<Movie> topRatedMovies)
    {
        topMoviesPanel.setTopMovies(topRatedMovies);
    }
}
