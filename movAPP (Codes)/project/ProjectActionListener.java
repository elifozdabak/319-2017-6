package project;

import view.FrameViewer;
import view.TopFixedPanelViewer;
import view.HomePageViewer;
import database.DBManagement;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Movie;
import model.User;
import view.FavoriteMoviesPanel;
import view.LoginPanel;
import view.LoginVirtualKeyboard;
import view.SignupPanel;
import view.CategoryPanel;
import view.MenuVirtualKeyboard;
import view.MoviePageViewer;
import view.SignupVirtualKeyboard;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class ProjectActionListener implements ActionListener 
{
    // PROPERTIES
    // Frames and Panels
    private final TopFixedPanelViewer  topFixedPanelGuest; 
    private final TopFixedPanelViewer  topFixedPanelLoggedIn;
    private final HomePageViewer       homePageViewer;
    private final FrameViewer          frameViewer;
    private final LoginPanel           loginPanel;
    private final CategoryPanel        categoryListPanel;
    private final SignupPanel          signupPanel;
    private FavoriteMoviesPanel           favoriteMoviesPanel;
    private int signupKeyboardClickCount = 0;
    
    // Keyboards
    private final LoginVirtualKeyboard   loginVirtualKeyboard;
    private final SignupVirtualKeyboard  signupVirtualKeyboard;
    
    // Database Connections
    private Movie     movie;
    private User      user;
    private final DBManagement databaseConnection;
    
    private MoviePageViewer moviePageViewer;
    
    // CONSTRUCTOR
    public ProjectActionListener( FrameViewer frameViewer,
                               HomePageViewer  homePageViewer,
                               TopFixedPanelViewer topFixedPanel, 
                               CategoryPanel categoryListPanel,
                               LoginPanel loginPanel,
                               SignupPanel signupPanel)
    {  
        this.frameViewer         = frameViewer;
        this.homePageViewer      = homePageViewer;
        this.topFixedPanelGuest  = topFixedPanel;
        this.categoryListPanel   = categoryListPanel;
        this.loginPanel          = loginPanel;
        this.signupPanel         = signupPanel;
        
        topFixedPanelLoggedIn    = new TopFixedPanelViewer(true);
        loginVirtualKeyboard     = new LoginVirtualKeyboard();
        signupVirtualKeyboard    = new SignupVirtualKeyboard();
        databaseConnection = new DBManagement();
        
        // Listeners
        (topFixedPanelGuest.getLogoButton()).addActionListener( this);
        (topFixedPanelGuest.getLogInButton()).addActionListener(this);
        (topFixedPanelGuest.getSignUpButton()).addActionListener(this);
        
        (topFixedPanelLoggedIn.getUsersTopListButton()).addActionListener( this);
        (topFixedPanelLoggedIn.getLogoButton()).addActionListener( this);
        (topFixedPanelLoggedIn.getLogoutButton()).addActionListener(this);
        (topFixedPanelLoggedIn.getWillWatchButton()).addActionListener(this);
        
        (loginPanel.getLoginButton()).addActionListener( this);
        (loginPanel.getSignInButton()).addActionListener( this);
        (loginPanel.getGuestHomePageButton()).addActionListener( this);
        
        (signupPanel.getbackToLogInButton()).addActionListener( this);
        (signupPanel.getSignupButton()).addActionListener( this);
        
        (categoryListPanel.getShowButton()).addActionListener( this);
        
        
        for(int i = 0 ; i < homePageViewer.getNumberOfTopRatedMovies() ; i++)
        {
            (homePageViewer.getImgButtonList().get(i)).addActionListener(this);
        }
        for(int i = 0 ; i < signupVirtualKeyboard.getfirstRowCount() ; i++)
        {
            signupVirtualKeyboard.getFirstRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < signupVirtualKeyboard.getsecondRowCount(); i++)
        {
            signupVirtualKeyboard.getSecondRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < signupVirtualKeyboard.getthirdRowCount(); i++)
        {
            signupVirtualKeyboard.getThirdRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < signupVirtualKeyboard.getfourthRowCount(); i++)
        {
            signupVirtualKeyboard.getFourthRow()[i].addActionListener(this);
        }  
        
        for(int i = 0 ; i < signupVirtualKeyboard.getfifthRowCount(); i++)
        {
            signupVirtualKeyboard.getFifthRow()[i].addActionListener(this);
        } 

        for(int i = 0 ; i < categoryListPanel.getfirstRowCount() ; i++)
        {
            categoryListPanel.getFirstRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < categoryListPanel.getsecondRowCount(); i++)
        {
            categoryListPanel.getSecondRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < categoryListPanel.getthirdRowCount(); i++)
        {
            categoryListPanel.getThirdRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < categoryListPanel.getfourthRowCount(); i++)
        {
            categoryListPanel.getFourthRow()[i].addActionListener(this);
        }                
        
        
        //menuVirtualKeyboard.firstButton().addActionListener(this);
        
        loginPanel  = this.frameViewer.getLoginPanel();
        signupPanel = this.frameViewer.getSignupPanel();
    }
    
    // METHODS
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the selected text field inside the sign up panel changes, this 
        // part makes the first character upperCase again.
        if(signupPanel.getTextFieldChange())
        {
            signupVirtualKeyboard.setCapsEnabled(true);
            signupVirtualKeyboard.changeCharacter(true);
            frameViewer.repaintAndRevalidate();
            signupKeyboardClickCount = 0;
        }
        // Takes the guest user from login panel to guest home page panel.
        if(e.getSource().equals(loginPanel.getGuestHomePageButton()))
        {
            frameViewer.removeAll();
            frameViewer.add(topFixedPanelGuest, BorderLayout.NORTH);
            frameViewer.add( homePageViewer); 
            frameViewer.add(categoryListPanel, BorderLayout.WEST);
            frameViewer.repaintAndRevalidate();
        }
        else if(e.getSource().equals(categoryListPanel.getShowButton()) && categoryListPanel.getSelectedMovie() != null)
        {
            moviePageViewer = new MoviePageViewer(categoryListPanel.getSelectedMovie(), frameViewer.getIsUser(), user);
            frameViewer.removeAll();
            if(frameViewer.getIsUser())
            {
                frameViewer.add(topFixedPanelLoggedIn, BorderLayout.NORTH);
            }
            else
            {
                frameViewer.add(topFixedPanelGuest, BorderLayout.NORTH);
            }
            frameViewer.add( moviePageViewer); 
            frameViewer.add(categoryListPanel, BorderLayout.WEST);
            frameViewer.repaintAndRevalidate();
        }
        // Returns to the homepage by pressing the logo 
        else if( e.getSource().equals(topFixedPanelGuest.getLogoButton()) || e.getSource().equals(topFixedPanelLoggedIn.getLogoButton()))
        {
            frameViewer.removeAll();
            if(frameViewer.getIsUser())
            {
                frameViewer.add(topFixedPanelLoggedIn, BorderLayout.NORTH);
            }
            else
            {
                frameViewer.add(topFixedPanelGuest, BorderLayout.NORTH);
            }
            homePageViewer.setTopMovies(databaseConnection.getTopRatedMovies());
            homePageViewer.revalidate();
            homePageViewer.repaint();
            frameViewer.add( homePageViewer, BorderLayout.CENTER);
            frameViewer.add(categoryListPanel, BorderLayout.WEST);
            frameViewer.repaintAndRevalidate();
        }
        // Logging out for logged in user
        else if( e.getSource().equals(topFixedPanelLoggedIn.getLogoutButton()))
        {
            // For deleting the old userID from login panel
            loginPanel.deleteAll();
            
            frameViewer.setIsUser(false);
            frameViewer.removeAll();
            frameViewer.add(loginVirtualKeyboard, BorderLayout.SOUTH);
            frameViewer.add(loginPanel, BorderLayout.CENTER);
            frameViewer.repaintAndRevalidate();   
        }
        // Login Check
        else if( e.getSource().equals(loginPanel.getLoginButton()))
        {
            // If userID is true
            if ( databaseConnection.userIDCheck(loginPanel.getUserID() + ""))
            {
                user = new User(loginPanel.getUserID() + "");
                frameViewer.removeAll();
                frameViewer.setIsUser(true);
                frameViewer.add(topFixedPanelLoggedIn, BorderLayout.NORTH);
                frameViewer.add(categoryListPanel, BorderLayout.WEST);
                frameViewer.add( homePageViewer); 
                frameViewer.repaintAndRevalidate();
            }
            // If userID is false
            else
            {
                ImageIcon icon = new ImageIcon("logo.png");
                Image img = icon.getImage();
                Image imgResized = img.getScaledInstance(120, 50, java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(imgResized);
                
                JOptionPane.showMessageDialog( new JFrame(),
                        "User ID and Pasword did not match",
                        "ID - PASSWORD MISSMATCH",
                        JOptionPane.ERROR_MESSAGE,
                        icon);
            }
        }
        // Takes guest user to the signIn page from login page
        else if( e.getSource().equals( loginPanel.getSignInButton()))
        {
            frameViewer.removeAll();
            frameViewer.add( signupPanel);
            frameViewer.add( signupVirtualKeyboard, BorderLayout.SOUTH);
            frameViewer.repaintAndRevalidate();
        }
        // Get back to login page from Signup page
        else if(e.getSource().equals( signupPanel.getbackToLogInButton()))
        {
            frameViewer.removeAll();
            frameViewer.add(loginPanel, BorderLayout.CENTER);
            frameViewer.add(loginVirtualKeyboard, BorderLayout.SOUTH);
            frameViewer.repaintAndRevalidate();
        }
        else if(e.getSource().equals(topFixedPanelLoggedIn.getWillWatchButton()))
        {
            categoryListPanel.showSelectedCategory(databaseConnection.getWillWatchList(user.getUserID()));
        }
        // Takes Guest user to the login page
        else if(e.getSource().equals(topFixedPanelGuest.getLogInButton()))
        {            
            frameViewer.removeAll();
            frameViewer.add(loginPanel, BorderLayout.CENTER);
            frameViewer.add(loginVirtualKeyboard, BorderLayout.SOUTH);
            frameViewer.repaintAndRevalidate();
        }
        // Takes Guest User To The SignIn Page
        else if(e.getSource().equals(topFixedPanelGuest.getSignUpButton()))
        {  
            frameViewer.removeAll();
            frameViewer.add( signupPanel);
            frameViewer.add( signupVirtualKeyboard, BorderLayout.SOUTH);
            frameViewer.repaintAndRevalidate();
        }
        else if(e.getSource().equals(topFixedPanelLoggedIn.getUsersTopListButton()))
        {
            categoryListPanel.showSelectedCategory(databaseConnection.getTopRatedList(user.getUserID()));
        }
        // Creating an account for new user
        else if( e.getSource().equals(signupPanel.getSignupButton()))
        {
            if ( signupPanel.getTextEntryName().equals("")        ||
                    signupPanel.getTextEntrySurname().equals("")  ||
                    signupPanel.getTextEntryUserID().equals("")   ||
                    signupPanel.getTextEntryEmail().equals("")    ||
                    !isValidEmailAddress(signupPanel.getTextEntryEmail()) ||
                    !isValidID(signupPanel.getTextEntryUserID())  ||
                    databaseConnection.userIDCheck(signupPanel.getTextEntryUserID()))
            {
                if(!isValidID(signupPanel.getTextEntryUserID()))
                {
                    ImageIcon icon = new ImageIcon("logo.png");
                    Image img = icon.getImage();
                    Image imgResized = img.getScaledInstance(120, 50, java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(imgResized);

                    JOptionPane.showMessageDialog( new JFrame(),
                            "Your Turkish National ID is wrong. Please try again",
                            "WRONG ID",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                }
                if(!isValidEmailAddress(signupPanel.getTextEntryEmail()))
                {
                    ImageIcon icon = new ImageIcon("logo.png");
                    Image img = icon.getImage();
                    Image imgResized = img.getScaledInstance(120, 50, java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(imgResized);

                    JOptionPane.showMessageDialog( new JFrame(),
                            "Please check your e-mail address and try again",
                            "WRONG E-MAIL",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                }
                if( databaseConnection.userIDCheck(signupPanel.getTextEntryUserID()))
                {
                    ImageIcon icon = new ImageIcon("logo.png");
                    Image img = icon.getImage();
                    Image imgResized = img.getScaledInstance(120, 50, java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(imgResized);

                    JOptionPane.showMessageDialog( new JFrame(),
                            "There is an account using that ID. If you did not open an account before, please connect with your customer representative",
                            "EXISTING ACCOUNT",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                }
                else
                {
                    ImageIcon icon = new ImageIcon("logo.png");
                    Image img = icon.getImage();
                    Image imgResized = img.getScaledInstance(120, 50, java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(imgResized);

                    JOptionPane.showMessageDialog( new JFrame(),
                            "There are some empty fields. Please fill all of them",
                            "EMPTY FIELDS",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                }
            }
            else
            {
                databaseConnection.SignUps(signupPanel.getTextEntryUserID(), signupPanel.getTextEntryName(), signupPanel.getTextEntrySurname(), signupPanel.getTextEntryEmail());
                frameViewer.removeAll();
                frameViewer.add( loginPanel);
                frameViewer.add(loginVirtualKeyboard, BorderLayout.SOUTH);
                frameViewer.repaintAndRevalidate();
            }
        }
        for(int i = 0 ; i < homePageViewer.getNumberOfTopRatedMovies() ; i++)
        {
            if(e.getSource().equals(homePageViewer.getImgButtonList().get(i)))
            {
                moviePageViewer = new MoviePageViewer(databaseConnection.findMovie(homePageViewer.getAllTopMovies().get(i)), frameViewer.getIsUser(), user);
                frameViewer.removeAll();
                if(frameViewer.getIsUser())
                {
                    frameViewer.add(topFixedPanelLoggedIn, BorderLayout.NORTH);
                }
                else
                {
                    frameViewer.add(topFixedPanelGuest, BorderLayout.NORTH);
                }
                frameViewer.add(categoryListPanel, BorderLayout.WEST);
                frameViewer.add(moviePageViewer, BorderLayout.CENTER);
                frameViewer.repaintAndRevalidate();
            }
        }        
        
        for(int i = 0 ; i < signupVirtualKeyboard.getfirstRowCount() ; i++)
        {
            if(e.getSource().equals(signupVirtualKeyboard.getFirstRow()[i]))
            {
                if(signupVirtualKeyboard.getFirstRow()[i].getText().equals("Delete"))
                {
                    signupPanel.deleteLastCharacter();
                }
                else
                {
                    signupPanel.setText(signupVirtualKeyboard.getFirstRow()[i].getText());
                    signupKeyboardClickCount++;
                }
                signupPanel.setTextFieldChange(false);
            }
            if(signupKeyboardClickCount == 1)
            {
                signupVirtualKeyboard.setCapsEnabled(false);
                signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                frameViewer.repaintAndRevalidate();
            }
        }
        
        for(int i = 0 ; i < signupVirtualKeyboard.getsecondRowCount(); i++)
        {
            if(e.getSource().equals(signupVirtualKeyboard.getSecondRow()[i]))
            {
                if(signupVirtualKeyboard.getcapsEnabled())
                {
                    signupPanel.setText(signupVirtualKeyboard.getSecondRow()[i].getText());
                    signupKeyboardClickCount++;
                }
                else
                {
                    signupPanel.setText(signupVirtualKeyboard.getSecondRow()[i].getText());
                    signupKeyboardClickCount++;
                }
                signupPanel.setTextFieldChange(false);
            }
            if(signupKeyboardClickCount == 1)
            {
                signupVirtualKeyboard.setCapsEnabled(false);
                signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                frameViewer.repaintAndRevalidate();
            }
        }
        
        for(int i = 0 ; i < signupVirtualKeyboard.getthirdRowCount(); i++)
        {
            if(e.getSource().equals(signupVirtualKeyboard.getThirdRow()[i]))
            {
                if(signupVirtualKeyboard.getThirdRow()[i].getText().equalsIgnoreCase("Caps"))
                {
                    signupVirtualKeyboard.setCapsEnabled(!signupVirtualKeyboard.getcapsEnabled());
                    signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                    frameViewer.repaintAndRevalidate();
                }
                else if(signupVirtualKeyboard.getThirdRow()[i].getText().equalsIgnoreCase("Enter"))
                {
                    
                }
                else
                {
                    if(signupVirtualKeyboard.getcapsEnabled())
                    {
                        signupPanel.setText(signupVirtualKeyboard.getThirdRow()[i].getText());
                        signupKeyboardClickCount++;
                    }
                    else
                    {
                        signupPanel.setText(signupVirtualKeyboard.getThirdRow()[i].getText());
                        signupKeyboardClickCount++;
                    }
                }
                signupPanel.setTextFieldChange(false);
            }
            if(signupKeyboardClickCount == 1)
            {
                signupVirtualKeyboard.setCapsEnabled(false);
                signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                frameViewer.repaintAndRevalidate();
            }
        }
        
        for(int i = 0 ; i < signupVirtualKeyboard.getfourthRowCount(); i++)
        {
            if(e.getSource().equals(signupVirtualKeyboard.getFourthRow()[i]))
            {
                if(signupVirtualKeyboard.getFourthRow()[i].getText().equalsIgnoreCase("Space"))
                {
                    signupPanel.setText(" ");
                    signupVirtualKeyboard.setCapsEnabled(true);
                    signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                    frameViewer.repaintAndRevalidate();
                    signupKeyboardClickCount = 0;
                    
                }
                else
                {
                    if(signupVirtualKeyboard.getcapsEnabled())
                    {
                        signupPanel.setText(signupVirtualKeyboard.getFourthRow()[i].getText());
                        signupKeyboardClickCount++;
                    }
                    else
                    {
                        signupPanel.setText(signupVirtualKeyboard.getFourthRow()[i].getText());
                        signupKeyboardClickCount++;
                    }
                }
                signupPanel.setTextFieldChange(false);
            }
            if(signupKeyboardClickCount == 1)
            {
                signupVirtualKeyboard.setCapsEnabled(false);
                signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                frameViewer.repaintAndRevalidate();
            }
        }  
        for(int i = 0 ; i < signupVirtualKeyboard.getfifthRowCount(); i++)
        {
            if(e.getSource().equals(signupVirtualKeyboard.getFifthRow()[i]))
            {
                signupPanel.setText(signupVirtualKeyboard.getFifthRow()[i].getText());
                signupPanel.setTextFieldChange(false);
            }
            if(signupKeyboardClickCount == 1)
            {
                signupVirtualKeyboard.setCapsEnabled(false);
                signupVirtualKeyboard.changeCharacter(signupVirtualKeyboard.getcapsEnabled());
                frameViewer.repaintAndRevalidate();
            }
        }
    }
    
    
    
    public User getUser()
    {
        return user;
    }
    
    public boolean isValidEmailAddress(String email) 
    {
       boolean result = true;
       try 
       {
          InternetAddress emailAddr = new InternetAddress(email);
          emailAddr.validate();
       } 
       catch (AddressException ex) 
       {
          result = false;
       }
       return result;
    }
    
    public boolean isValidID(String userID)
    {
        int digit1;
        int digit2;
        int digit3;
        int digit4;
        int digit5;
        int digit6;
        int digit7;
        int digit8;
        int digit9;
        int digit10;
        int digit11;
        // All characters must be digits
        try
        {
            digit1 = Integer.parseInt(userID.charAt(0) + "");
            digit2 = Integer.parseInt(userID.charAt(1) + "");
            digit3 = Integer.parseInt(userID.charAt(2) + "");
            digit4 = Integer.parseInt(userID.charAt(3) + "");
            digit5 = Integer.parseInt(userID.charAt(4) + "");
            digit6 = Integer.parseInt(userID.charAt(5) + "");
            digit7 = Integer.parseInt(userID.charAt(6) + "");
            digit8 = Integer.parseInt(userID.charAt(7) + "");
            digit9 = Integer.parseInt(userID.charAt(8) + "");
            digit10 = Integer.parseInt(userID.charAt(9) + "");
            digit11 = Integer.parseInt(userID.charAt(10) + "");
        }
        catch(NumberFormatException ex)
        {
            System.out.println("error: " + ex);
            return false;
        }
        // Must Have 11 Digits
        if(userID.length() != 11)
        {
            System.err.println("uzunluk");
            return false;
        }
        // First digit != 0
        if(digit1 == 0)
        {
            System.err.println("ilkkarakter");
            return false;
        }
        
        if( (((digit1 + digit3 + digit5 + digit7 + digit9) * 7) - (digit2 + digit4 + digit6 + digit8)) % 10 != digit10)
        {
            System.err.println("onuncu if");
            return false;
        }
        
        if((digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8 + digit9 + digit10) %10 != digit11)
        {
            System.err.println("son if");
            return false;
        }
        
        return true;
    }
}
