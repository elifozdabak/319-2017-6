package view;

import database.DBConnectMovie;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Movie;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class TopMoviesPanel extends JPanel
{
    // PROPERTIES
    private ArrayList<Movie> movie;
    private MovieItemView movieItemView;
    private final DBConnectMovie movieConnection;
    private final MenuVirtualKeyboard menuVirtualKeyboard = new MenuVirtualKeyboard();
    private ArrayList<JButton> imgButtonList;
    
    // CONSTRUCTOR
    public TopMoviesPanel( ArrayList<Movie> movie, int numberOfTopRatedMovies) 
    {
        this.movie  = movie;
        movieConnection = new DBConnectMovie();
        this.setLayout(new BorderLayout());
        
        GridLayout grid = new GridLayout(0,5);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,5));
        
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
        imgButtonList = new ArrayList<>();
        
        for( int i = 0 ; i < movie.size() ; i++)
        {
            movieItemView = new MovieItemView("moviePictures/" + movie.get(i).getID() + "(1).jpg", movie.get(i));
            panel.add(movieItemView,BorderLayout.CENTER);
            imgButtonList.add(i, movieItemView.getImgButton());
        } 
    }
    
    // Returns image list for HomePageViewer for using in projectActionListener
    public ArrayList<JButton> getImgButtonList()
    {
        return imgButtonList;
    }
    // Returns movie for HomePageViewer for using in projectActionListener
    public Movie getMovieFromImgPanel()
    {
        return movieItemView.getMovieFromImgPanel();
    }
    
    public void setTopMovies(ArrayList<Movie> movie)
    {
        this.movie = movie;
    }
    
    
    
            
}
