package view;

import constants.UIColors;
import database.DBManagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.border.LineBorder;
import model.Movie;
import model.User;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class MoviePageViewer extends JPanel implements ActionListener
{
    // PROPERTIES
    // Buttons
    private JButton     rateStar1Button;
    private JButton     rateStar2Button;
    private JButton     rateStar3Button;
    private JButton     rateStar4Button;
    private JButton     rateStar5Button;
    private JButton rightButton;
    private JButton leftButton;
    private FormButton  watchButton;
    private FormButton  willWatchButton;
    private FormButton  removeFromWillWatchListButton;
    // Panels
    private JPanel exteriorPanel;
    private JPanel interiorPanel;
    private JPanel ratePanel;
    // Models
    private final Movie movie;
    // Database
    private final DBManagement databaseConnection;
    // Images
    private ImageIcon   rateStar;
    private ImageIcon   ratedStar;
    // Others
    private final boolean    isUser;
    private final User       user;
    private JLabel           rateLabel;
    private JLabel           titleLabel;
    private JLabel           movieLabel;
    private JLabel authorLabel;
    private JLabel genreLabel;
    private JLabel imgLabel;
    private int leftRightCount = 1;
    

    // CONSTRUCTOR
    public MoviePageViewer( Movie movie, boolean isUser, User user)
    {
        // Initilazing the variables
        this.isUser = isUser;
        this.user   = user;
        this.movie = movie;
        // Database
        databaseConnection = new DBManagement();
        // Panels (Used in right panel)
        exteriorPanel = new JPanel();
        interiorPanel = new JPanel();
        ratePanel     = new JPanel();
        // Buttons (Used in right panel)
        rateStar1Button   = new JButton();
        rateStar2Button   = new JButton();
        rateStar3Button   = new JButton();
        rateStar4Button   = new JButton();
        rateStar5Button   = new JButton();
        willWatchButton   = new FormButton("Watch Later");
        removeFromWillWatchListButton = new FormButton("Remove from Watch Later List");
        // Others
        titleLabel   = new JLabel("MOVIE INFORMATION");        
        movieLabel   = new JLabel("TITLE : "  + movie.getName());
        authorLabel  = new JLabel("DIRECTOR : " + movie.getDirector());
        genreLabel   = new JLabel("GENRE : "  + movie.getGenre());
        rateLabel    = new JLabel("RATE : "   + movie.getRate());
        
        this.setLocation(300,100);
        this.setSize(800, 600);
        this.setVisible(true);
        
        creatingMoviePageViewer();  
    }
    
    
    // METHODS
    // creatingMoviePageViewer(): This method creates left and right panels.
    private void creatingMoviePageViewer()
    {
        this.setLayout(new GridLayout(1,2));
        this.add(creatingLeftPanel());
        this.add(creatingRightPanel());
    }
    
    // creatingRightPanel(): This method creates a right panel for that class.
    private JPanel creatingRightPanel()
    {
        // exteriorPanel characteristics
        exteriorPanel.setPreferredSize(new Dimension(600, 600));
        exteriorPanel.setBackground(UIColors.NIGHTBLUE);
        // interiorPanel characteristics
        interiorPanel.setLayout(new GridLayout(9,1));
        interiorPanel.setBackground(UIColors.NIGHTBLUE);
        interiorPanel.setPreferredSize(new Dimension(300, 550));
        
        // titleLabel characteristics
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 18));
        interiorPanel.add(titleLabel);
        
        // movieLabel, authorLabel, genreLabel, rateLabel characteristics
        movieLabel.setForeground(Color.WHITE);
        authorLabel.setForeground(Color.WHITE);
        genreLabel.setForeground(Color.WHITE);
        rateLabel.setForeground(Color.WHITE);
        interiorPanel.add(movieLabel);
        interiorPanel.add(authorLabel);
        interiorPanel.add(genreLabel);
        interiorPanel.add(rateLabel);
        
        // Resizing rateStar icon
        rateStar = new ImageIcon("rateStar.png");
        Image imgRateStar = rateStar.getImage();
        Image imgResizedRateStar = imgRateStar.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        rateStar = new ImageIcon(imgResizedRateStar);
        // Resizing ratedStar icon
        ratedStar = new ImageIcon("ratedStar.png");
        Image imgRatedStar = ratedStar.getImage();
        Image imgResizedRatedStar = imgRatedStar.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        ratedStar = new ImageIcon(imgResizedRatedStar);
        
        // Adding rateStar1 icon to the Button
        rateStar1Button = rateStarButton();
        rateStar2Button = rateStarButton();
        rateStar3Button = rateStarButton();
        rateStar4Button = rateStarButton();
        rateStar5Button = rateStarButton();
        
        ratePanel = new JPanel();
        ratePanel.setBackground(UIColors.NIGHTBLUE);
        
        if(isUser)
        {
            int userRate = databaseConnection.getRate(user.getUserID(), movie.getID());
            switch (userRate) 
            {
                case -1:
                    rateStar1Button.setIcon(rateStar);
                    rateStar2Button.setIcon(rateStar);
                    rateStar3Button.setIcon(rateStar);
                    rateStar4Button.setIcon(rateStar);
                    rateStar5Button.setIcon(rateStar);
                    createRatePanel(ratePanel);                 
                    break;
                case 1:
                    rateStar1Button.setIcon(ratedStar);
                    rateStar2Button.setIcon(rateStar);
                    rateStar3Button.setIcon(rateStar);
                    rateStar4Button.setIcon(rateStar);
                    rateStar5Button.setIcon(rateStar);
                    createRatePanel(ratePanel); 
                    break;
                case 2:
                    rateStar1Button.setIcon(ratedStar);
                    rateStar2Button.setIcon(ratedStar);
                    rateStar3Button.setIcon(rateStar);
                    rateStar4Button.setIcon(rateStar);
                    rateStar5Button.setIcon(rateStar);
                    createRatePanel(ratePanel);  
                    break;
                case 3:
                    rateStar1Button.setIcon(ratedStar);
                    rateStar2Button.setIcon(ratedStar);
                    rateStar3Button.setIcon(ratedStar);
                    rateStar4Button.setIcon(rateStar);
                    rateStar5Button.setIcon(rateStar);
                    createRatePanel(ratePanel);  
                    break;
                case 4:
                    rateStar1Button.setIcon(ratedStar);
                    rateStar2Button.setIcon(ratedStar);
                    rateStar3Button.setIcon(ratedStar);
                    rateStar4Button.setIcon(ratedStar);
                    rateStar5Button.setIcon(rateStar);
                    createRatePanel(ratePanel);  
                    break;
                case 5:
                    rateStar1Button.setIcon(ratedStar);
                    rateStar2Button.setIcon(ratedStar);
                    rateStar3Button.setIcon(ratedStar);
                    rateStar4Button.setIcon(ratedStar);
                    rateStar5Button.setIcon(ratedStar);
                    createRatePanel(ratePanel);  
                    break;
                default:
                    break;                        
            }
        }
        else
        {
            createRatePanel(ratePanel);
        }
        
        interiorPanel.add(ratePanel);
        
        JPanel watchLaterPanel = new JPanel();
        watchLaterPanel.setBackground(null);
        
                
        willWatchButton.setPreferredSize(new Dimension(270, 50));
        willWatchButton.addActionListener(this);
        removeFromWillWatchListButton.setPreferredSize(new Dimension(270, 50));
        removeFromWillWatchListButton.addActionListener(this);
        // Put willWatchButton or removeFromWillWatchListButton
        if(isUser && databaseConnection.isInWillWatchList(user.getUserID(), movie.getID()))
        {
            watchLaterPanel.add(removeFromWillWatchListButton);
        }
        else
        {
            watchLaterPanel.add(willWatchButton);
        }
        

        
        // Watch button
        JPanel watchPanel = new JPanel();
        watchButton = new FormButton("WATCH");
        watchButton.setPreferredSize(new Dimension(270, 50));
        watchButton.setPreferredSize(new Dimension(270, 50));
        watchPanel.add(watchButton);
        watchPanel.setBackground(null);
        watchButton.setPreferredSize(new Dimension(270, 50));
        watchButton.addActionListener(this);
        
        // Adding everything to the Panels
        interiorPanel.add(watchLaterPanel);
        interiorPanel.add(watchPanel);
        exteriorPanel.add(interiorPanel);
        
        return exteriorPanel;
    }
    
    public JPanel creatingLeftPanel()
    {
        JPanel leftPanel       = new JPanel();
        JTextPane textPane     = new JTextPane();
        JLabel topLeftLabel    = new JLabel();
        JPanel topLeftPanel    = new JPanel();
        JScrollPane scrollPane = new JScrollPane(textPane);
        
        topLeftPanel.setLayout(new BorderLayout());
        
        topLeftLabel.setBackground(UIColors.NIGHTBLUE);
        imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("moviePictures/" + movie.getID() + "(1).jpg"));
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new GridBagLayout());
        imgPanel.add(imgLabel);
        imgPanel.setBackground(null);
        imgPanel.setBorder(null);
        
        ImageIcon leftIcon = new ImageIcon("left_arrow.png");
        Image img = leftIcon.getImage();
        Image imgResized = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        leftIcon = new ImageIcon(imgResized);
        leftButton = new JButton(leftIcon);
        leftButton.setBackground(null);
        leftButton.setBorder(null);
        leftButton.addActionListener(this);
        
        ImageIcon rightIcon = new ImageIcon("right_arrow.png");
        Image img2 = rightIcon.getImage();
        Image imgResized2 = img2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        rightIcon = new ImageIcon(imgResized2);
        rightButton = new JButton(rightIcon);
        rightButton.setBackground(null);
        rightButton.setBorder(null);
        rightButton.addActionListener(this);
        
        
        topLeftPanel.add(leftButton, BorderLayout.WEST);
        topLeftPanel.add(imgPanel, BorderLayout.CENTER);
        topLeftPanel.add(rightButton, BorderLayout.EAST);
        
        
        
        
        
        topLeftPanel.setBackground(UIColors.NIGHTBLUE);
        
        textPane.setBackground(UIColors.NIGHTBLUE);
        textPane.setFont(new Font(textPane.getFont().getName(), Font.PLAIN, 18));
        scrollPane.setForeground(Color.WHITE);
        textPane.setForeground(Color.WHITE);

        ArrayList<Movie> x = databaseConnection.getDesiredCategory(movie.getGenre());
        
        String textPaneString = "You may also like: \n";
        
        int count = 0;
        for (int i = 0; i < x.size(); i++) 
        {
            if(! movie.getName().equalsIgnoreCase(x.get(i).getName()))
            {
                count++;
                textPaneString = textPaneString + x.get(i).getName() + "\n";
            }
            if(count > 4)
            {
                break;
            }
            
        }
        textPane.setText("\n" + "     CAST : \n" + movie.getCast() + "\n \n" + "     "
                        + "SUMMARY : \n" + movie.getDescription()  + "\n \n" + "     "
                        + textPaneString);
        
        
        textPane.setFont(new Font(textPane.getFont().getName(), Font.ITALIC, 16));
        textPane.setEditable(false);
        textPane.setCaretPosition(0);
        textPane.setBorder(new LineBorder(UIColors.NIGHTBLUE));
        
        leftPanel.setLayout( new GridLayout(2,1));
        
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize( new Dimension(10,10));
        scrollPane.setBorder(new LineBorder(UIColors.NIGHTBLUE));
        
        leftPanel.add(topLeftPanel);
        leftPanel.add(scrollPane);
        return leftPanel;
    }    
    
     public static JPanel buildMargin(int x) 
    {
        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(x, 40));
        marginPanel.setBackground(null);
        marginPanel.setBorder(null);
        return marginPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals( rateStar1Button))
        {
            if(isUser)
            {
                rateStar1Button.setIcon(ratedStar);
                rateStar2Button.setIcon(rateStar);
                rateStar3Button.setIcon(rateStar);
                rateStar4Button.setIcon(rateStar);
                rateStar5Button.setIcon(rateStar);
                
                rateMovie(user.getUserID(), movie.getID(), 1); 
            }
            else
            {
               error("UNKNOWN USER","For rating movies, you need to be Logged in!");
            }
        }
        else if(e.getSource().equals( rateStar2Button))
        {
            if(isUser)
            {
                rateStar1Button.setIcon(ratedStar);
                rateStar2Button.setIcon(ratedStar);
                rateStar3Button.setIcon(rateStar);
                rateStar4Button.setIcon(rateStar);
                rateStar5Button.setIcon(rateStar);
                
                rateMovie(user.getUserID(), movie.getID(), 2);
            }
            else
            {
               error("UNKNOWN USER","For rating movies, you need to be Logged in!");
            }
        }
        else if(e.getSource().equals( rateStar3Button))
        {
            if(isUser)
            {
                rateStar1Button.setIcon(ratedStar);
                rateStar2Button.setIcon(ratedStar);
                rateStar3Button.setIcon(ratedStar);
                rateStar4Button.setIcon(rateStar);
                rateStar5Button.setIcon(rateStar);
                
                rateMovie(user.getUserID(), movie.getID(), 3); 
            }
            else
            {
               error("UNKNOWN USER","For rating movies, you need to be Logged in!");
            }
        }
        else if(e.getSource().equals( rateStar4Button))
        {
            if(isUser)
            {
                rateStar1Button.setIcon(ratedStar);
                rateStar2Button.setIcon(ratedStar);
                rateStar3Button.setIcon(ratedStar);
                rateStar4Button.setIcon(ratedStar);
                rateStar5Button.setIcon(rateStar);
                
                rateMovie(user.getUserID(), movie.getID(), 4); 
            }
            else
            {
               error("UNKNOWN USER","For rating movies, you need to be Logged in!");
            }
        }
        else if(e.getSource().equals( rateStar5Button))
        {
            if(isUser)
            {
                rateStar1Button.setIcon(ratedStar);
                rateStar2Button.setIcon(ratedStar);
                rateStar3Button.setIcon(ratedStar);
                rateStar4Button.setIcon(ratedStar);
                rateStar5Button.setIcon(ratedStar);
                
                rateMovie(user.getUserID(), movie.getID(), 5);
            }
            else
            {
               error("UNKNOWN USER","For rating movies, you need to be Logged in!");
            }
        }
        else if(e.getSource().equals(watchButton))
        {
            try {
                Desktop.getDesktop().open(new File("movies/" + movie.getID() + ".mp4"));
                
            } catch (Exception ex) {
                Logger.getLogger(MoviePageViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource().equals(willWatchButton) || e.getSource().equals(removeFromWillWatchListButton))
        {
            if(isUser)
            {
                if(databaseConnection.isInWillWatchList(user.getUserID(), movie.getID()))
                {
                    databaseConnection.removeWillWatch(user.getUserID(), movie.getID());
                }
                else
                {
                    databaseConnection.addWillWatchMovie(user.getUserID(), movie.getID());
                }  
            }
            else
            {
               error("UNKNOWN USER","For adding the movies to your personal "
                       + "Watch Later List, you need to be Logged in!");
            }
            this.repaint();
            this.revalidate();
        }
        else if(e.getSource().equals(leftButton))
        {
            leftRightCount--;
            if(leftRightCount < 1)
            {
                leftRightCount = 3;
            }
            imgLabel.setIcon(new ImageIcon("moviePictures/" + movie.getID() + "(" + leftRightCount + ").jpg"));
        }
        else if(e.getSource().equals(rightButton))
        {
            leftRightCount++;
            if (leftRightCount > 3) 
            {
                leftRightCount = 1;
            }     
            imgLabel.setIcon(new ImageIcon("moviePictures/" + movie.getID() + "(" + leftRightCount + ").jpg"));
        }
    }
    
    public JButton getWillWatchButton()
    {
        return willWatchButton;
    }
    
    public JButton getRemoveFromWillWatchListButton()
    {
        return removeFromWillWatchListButton;
    }
    
    private void error(String errorTitle, String errorMessage)
    {
        ImageIcon icon = new ImageIcon("logo.png");
        Image img = icon.getImage();
        Image imgResized = img.getScaledInstance(120, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(imgResized);

        JOptionPane.showMessageDialog( new JFrame(),
            errorMessage,
            errorTitle,
            JOptionPane.ERROR_MESSAGE,
            icon);
    }
    
    private void rateMovie(String userID, int movieID, int rateInput)
    {    
        // If rate is getting directly from the movie (movie.getRate()) 
        // program sometimes computes the new rate wrong
        double oldRate = databaseConnection.getRate(movieID);
        int totalRate = movie.getTotalRate();
        double newRate;
        
        if(databaseConnection.isRated(userID, movieID))
        {
            newRate = ((oldRate * totalRate) + rateInput - databaseConnection.getRate(userID, movieID)) / totalRate;
            databaseConnection.changeRate(userID, movieID, rateInput);      
            databaseConnection.changeRate(movieID, newRate);
        }
        else
        {
            newRate = ((oldRate * totalRate) + rateInput) / (totalRate + 1);
            databaseConnection.setRate(userID, movieID, rateInput);
            databaseConnection.changeRate(movieID, newRate);
            databaseConnection.changeTotalRate(movieID, totalRate + 1);
        }
    }
    
    private JButton rateStarButton()
    {
        JButton button = new JButton(rateStar);
        button.setBackground(UIColors.NIGHTBLUE);
        button.setBorder(null);
        button.addActionListener(this);
        return button;
    }
    
    private void createRatePanel(JPanel panel)
    {
        panel.add(rateStar1Button);
        panel.add(buildMargin(10));
        panel.add(rateStar2Button);
        panel.add(buildMargin(10));
        panel.add(rateStar3Button);
        panel.add(buildMargin(10));
        panel.add(rateStar4Button);
        panel.add(buildMargin(10));
        panel.add(rateStar5Button);
    }

}
