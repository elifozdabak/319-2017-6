package view;

import constants.UIColors;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class LoginPanel extends JPanel 
{
    // PROPERTIES
    private final JPanel exteriorPanel;
    private final JPanel interiorPanel;
    
    private FormButton            loginButton;
    private  static FormEntryView userText;
    private JButton               signInButton;
    private JButton               guestHomePageButton;
    
    // CONSTRUCTOR
    public LoginPanel() 
    {
        this.setBackground(UIColors.LIGHTBLUE);
        exteriorPanel = new JPanel();
        interiorPanel = new JPanel();
        placeComponents();
    }

    private void placeComponents() 
    {
        exteriorPanel.setPreferredSize(new Dimension(400, 300));
        exteriorPanel.setBackground(UIColors.NIGHTBLUE);
        
        interiorPanel.setLayout(new GridLayout(5,1));
        interiorPanel.setBackground(UIColors.NIGHTBLUE);
        
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font(loginLabel.getFont().getName(), Font.PLAIN, 18));
        interiorPanel.add(loginLabel);
        
        userText = new FormEntryView("user_icon.png");
        interiorPanel.add(userText);

        // Creating a LogIn Button
        JPanel loginPanel = new JPanel();
        loginButton = new FormButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(270, 50));
        loginPanel.add(loginButton);
        loginPanel.setBackground(null);
        interiorPanel.add(loginPanel);
        
        // Creating a Button which basically creates a Sigup Panel 
        signInButton = new JButton("Don't you have an account? Got an account from here");
        signInButton.setHorizontalAlignment(SwingConstants.RIGHT);
        signInButton.setForeground(UIColors.LIGHTBLUE);
        signInButton.setBorder(null);
        signInButton.setBackground(null);
        interiorPanel.add(signInButton);
        
        guestHomePageButton = new JButton("Go back to home page");
        guestHomePageButton.setHorizontalAlignment(SwingConstants.RIGHT);
        guestHomePageButton.setForeground(UIColors.LIGHTBLUE);
        guestHomePageButton.setBorder(null);
        guestHomePageButton.setBackground(null);
        interiorPanel.add(guestHomePageButton);
        
        exteriorPanel.add(interiorPanel, BorderLayout.CENTER);
        add(exteriorPanel);
    }
    
    // METHODS
    public JButton getLoginButton()
    {
        return loginButton;
    }
    
    public String getUserID()
    {
        return userText.getUserID();
    }
    
    public JButton getSignInButton()
    {
        return signInButton;
    }
    
    public static void addText(char charInput)
    {
        userText.addLast(charInput);
    }
    
    protected static void deleteLast()
    {
        userText.deleteLast();
    }
    
    public void deleteAll()
    {
        userText.deleteAll();
    }
    
    public JButton getGuestHomePageButton()
    {
        return guestHomePageButton;
    }
}