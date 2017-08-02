package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Movie;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class FavoriteMoviesPanel extends JPanel
{
    
    // PROPERTIES
    private final ArrayList<Movie> movies;
    private final int             start;
    private final int             end;
    
    private MovieItemView     movieItemView;
    private String            userName;
    
    // CONSTRUCTOR
    public FavoriteMoviesPanel( ArrayList<Movie> movies, int start, int end)
    {
        this.movies  = movies;
        this.start = start;
        this.end = end;
        this.userName = userName;
        
        this.setLayout(new GridLayout(0, 5));
        
        
    }
}
