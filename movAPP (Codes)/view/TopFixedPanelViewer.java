package view;

import constants.UIColors;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class TopFixedPanelViewer extends JPanel
{
    // PROPERTIES
    // For Both LoggedIn User and Guest
    private final MenuButton aboutApplicationButton;
    private final JButton    logoButton;
    private final JPanel     horizontalPanel;
    private final JPanel     menuView;
    private boolean          loggedIn;
    
    // For LoggedIn User
    private final MenuButton usersTopListButton;
    private final MenuButton logoutButton;
    private final MenuButton willWatchButton;
    
    // For Guest
    private final MenuButton logInButton;
    private final MenuButton signUpButton;
    
    // CONSTRUCTOR
    public TopFixedPanelViewer(boolean loggedIn)
    {   
        this.loggedIn = loggedIn;
        this.setBorder(null);
        this.setPreferredSize(new Dimension(800, 70));
        this.setBackground(UIColors.NIGHTBLUE);
        
        // Initilazing properties
        // For both
        menuView = new JPanel();
        menuView.setLayout(new GridBagLayout());
        
        horizontalPanel = new JPanel();
        horizontalPanel.setBackground(UIColors.NIGHTBLUE); 
        
        logoButton = new JButton(IconCreator("logo.png"));
        logoButton.setBackground(UIColors.NIGHTBLUE);
        logoButton.setBorder(null);
        
        aboutApplicationButton = new MenuButton("About MovAPP");
        
        horizontalPanel.add(logoButton);
        horizontalPanel.add(buildMargin(300));   

        
        // For LoggedIn User
        usersTopListButton = new MenuButton("User's Top List");
        willWatchButton    = new MenuButton("Will-Watch List");
        logoutButton       = new MenuButton("Log Out");
        
        
        // For Guest
        logInButton  = new MenuButton("Log In");
        signUpButton = new MenuButton("Sign Up");
        
        // Determining if the user is Guest user or loggedIn user
        // Than adding related buttons to the horizontalPanel
        if(loggedIn)
        {
            horizontalPanel.add(aboutApplicationButton);
            horizontalPanel.add(buildMargin(30));
            horizontalPanel.add(willWatchButton);
            horizontalPanel.add(buildMargin(30));
            horizontalPanel.add(usersTopListButton);
            horizontalPanel.add(buildMargin(30));
            horizontalPanel.add(logoutButton);
        }
        else
        {
            horizontalPanel.add(buildMargin(200));
            horizontalPanel.add(aboutApplicationButton);
            horizontalPanel.add(buildMargin(30));
            horizontalPanel.add(logInButton);
            horizontalPanel.add(buildMargin(30));
            horizontalPanel.add(signUpButton);
            horizontalPanel.add(buildMargin(30));
        }
        menuView.add(horizontalPanel);
        this.add(menuView); 
    }
    
    // METHODS
    // Building Margin
    public static JPanel buildMargin(int x) 
    {
        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(x, 40));
        marginPanel.setBackground(null);
        marginPanel.setBorder(null);
        return marginPanel;
    }
    
    // Getter Methods for LoggedIn User
    public MenuButton getUsersTopListButton()
    {
        return usersTopListButton;
    }
    public MenuButton getLogoutButton()
    {
        return logoutButton;
    }
    public MenuButton getWillWatchButton()
    {
        return willWatchButton;
    }
    
    // Getter Methods for both LoggedIn and Guest User
    public JButton getLogoButton()
    {
        return logoButton;
    }
    public MenuButton getAboutApplicationButton()
    {
        return aboutApplicationButton;
    }    
    
    // Getter Methods for Guest User
    public MenuButton getLogInButton()
    {
        return logInButton;
    }    
    public MenuButton getSignUpButton()
    {
        return signUpButton;
    }
    
    // changeLoggedInStatus(boolean status): This method changes the loggedIn 
    // boolean valuse inside this class.
    public void changeLoggedInStatus(boolean status)
    {
        this.loggedIn = status;
    }
    
    // IconCreator(String iconPath): This method creates an icon 
    // from the related path.
    private ImageIcon IconCreator(String iconPath)
    {
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image imgResized = img.getScaledInstance(90, 35, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(imgResized);
        return icon;
    }

}
