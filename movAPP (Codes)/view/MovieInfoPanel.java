package view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Movie;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class MovieInfoPanel extends JPanel
{
    // CONSTRUCTOR
    public MovieInfoPanel(Movie movie)
    {
        
        this.setLayout(new GridLayout(5,0));
        this.setBackground(null);
        
        JLabel authorLabel = new JLabel("Director : " + movie.getDirector(), SwingConstants.CENTER);
        
        this.add(new JLabel(movie.getName(), SwingConstants.CENTER));
        this.add(new JLabel("Genre: " + movie.getGenre(), SwingConstants.CENTER));
        this.add(new JLabel("Year : " + movie.getYear(), SwingConstants.CENTER));
        this.add(new JLabel("Duration : " + movie.getDuration(), SwingConstants.CENTER));
        this.add(authorLabel);
    }
}
