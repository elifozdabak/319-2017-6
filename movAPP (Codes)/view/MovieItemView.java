package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.Movie;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class MovieItemView extends JPanel
{
    // PROPERTIES
    private final ImagePanel imgPanel;
    private final Movie       movie;
    
    // CONSTRUCTOR
    public MovieItemView(String img, Movie movie) 
    {
        this.movie = movie;
        
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(100, 300));
        
        imgPanel = new ImagePanel(img, movie);
        imgPanel.setBackground(Color.WHITE);
        
        this.add(imgPanel, BorderLayout.CENTER);
        this.add(new MovieInfoPanel( movie), BorderLayout.SOUTH);
        
        this.setBackground(Color.WHITE);       
    }
    
   public Movie getMovie() 
   {
       return movie;
   }
   
   public ImagePanel getImagePanel() 
   {
       return imgPanel;
   }
   
   // Return image for TopMoviesPanel for using in projectActionListener
   public JButton getImgButton()
   {
       return imgPanel.getImgButton();
   }
   // Returns movie for TopMoviesPanel for using in projectActionListener
   public Movie getMovieFromImgPanel()
   {
       return imgPanel.getMovieFromImgPanel();
   }
}
