package view;

import database.DBManagement;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.User;

import project.ProjectActionListener;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class FrameViewer extends JFrame 
{
    // PROPERTIES   
    private final TopFixedPanelViewer   topFixedPanel;
    private final HomePageViewer        homePage;
    private final LoginPanel            loginPanel;
    private final CategoryPanel         categoryPanel;
    private final SignupPanel           signupPanel;
    private final ProjectActionListener actionListener;
    private JPanel                      currentPanel;
    private final DBManagement databaseConnection = new DBManagement();
    
    private final LoginVirtualKeyboard  loginVirtualKeyboard;
    private final MenuVirtualKeyboard   menuVirtualKeyboard;
    
    private final int NUMBER_OF_TOP_RATED_MOVIES = 10;
    
    private boolean isUser;
    private User user;
     
    // CONSTRUCTOR
    public FrameViewer(boolean isUser, User user)
    {
        this.isUser = isUser;
        this.user = user;
        this.setTitle("MovAPP");
        // Initilazing topFixedPanel, homePage, topListPanel, loginPanel, signinPanel and actionListener
        topFixedPanel  = new TopFixedPanelViewer(isUser);
        homePage       = new HomePageViewer(isUser, user, databaseConnection.getAllMovies());
        categoryPanel  = new CategoryPanel(isUser, user);
        loginPanel     = new LoginPanel();
        signupPanel    = new SignupPanel();
        
        loginVirtualKeyboard = new LoginVirtualKeyboard();
        menuVirtualKeyboard  = new MenuVirtualKeyboard();
        
        
        // Setting layout to loginPanel
        loginPanel.setLayout(new GridBagLayout());
        
        this.add( topFixedPanel, BorderLayout.NORTH);
        this.add( homePage, BorderLayout.CENTER);
        this.add( categoryPanel, BorderLayout.WEST);
        currentPanel = homePage;
        
        
        actionListener = new ProjectActionListener(this, homePage, topFixedPanel,
                categoryPanel, loginPanel, signupPanel);
        

    }
    
    // METHODS
    public void add(JPanel panel)
    {
        add(panel, BorderLayout.CENTER);
        repaint();
        revalidate();
        currentPanel = panel;
    }
    
    public void remove()
    {
        remove(currentPanel);
        repaint();
        revalidate();
    }
    
    public void removeAll()
    {
        this.getContentPane().removeAll();
        repaint();
        revalidate();
    }
    
    public void repaintAndRevalidate()
    {
        repaint();
        revalidate();
    }
    
    public LoginPanel getLoginPanel() 
    {
        return loginPanel;
    }
    
    public SignupPanel getSignupPanel() 
    {
        return signupPanel;
    }
    
    public JPanel getCurrentPanel() 
    {
        return currentPanel;
    }
    
    public void setIsUser(boolean isUser)
    {
        this.isUser = isUser;
    }
    
    public boolean getIsUser()
    {
        return isUser;
    }

}
