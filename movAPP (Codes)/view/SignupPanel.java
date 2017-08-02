package view;

import constants.UIColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class SignupPanel extends JPanel implements MouseListener
{
    // PROPERTIES
    private final JPanel exteriorPanel;
    private final JPanel interiorPanel;

    private TextFormEntryView name;
    private TextFormEntryView surname;
    private TextFormEntryView userID;
    private TextFormEntryView email;
    private boolean textFieldChanged = false;
    
    private JButton           backToLogInButton;
    private FormButton        signupButton;
    private boolean nameBoolean;
    private boolean surnameBoolean;
    private boolean userIDBoolean;
    private boolean emailBoolean;
    // CONSTRUCTOR
    public SignupPanel() 
    {
        setBackground(UIColors.LIGHTBLUE);
        exteriorPanel = new JPanel();
        interiorPanel = new JPanel();
        placeComponents();
        
        nameBoolean    = true;
        surnameBoolean = false;
        userIDBoolean  = false;
        emailBoolean   = false;
    }
    
    // METHODS
    private void placeComponents() 
    {
        exteriorPanel.setPreferredSize(new Dimension(600, 600));
        exteriorPanel.setBackground(UIColors.NIGHTBLUE);
        
        interiorPanel.setLayout(new GridLayout(9,1));
        interiorPanel.setBackground(UIColors.NIGHTBLUE);
        interiorPanel.setPreferredSize(new Dimension(300, 550));
        
        JLabel signinLabel = new JLabel("SIGNIN");
        signinLabel.setForeground(Color.WHITE);
        signinLabel.setFont(new Font(signinLabel.getFont().getName(), Font.PLAIN, 18));
        interiorPanel.add(signinLabel);
        
        name     = new TextFormEntryView("NAME", 20);
        surname  = new TextFormEntryView("SURNAME", 20);
        userID = new TextFormEntryView("User ID", 20);
        email    = new TextFormEntryView("EMAIL", 20);
        
        name.addMouseListener(this);
        surname.addMouseListener(this);
        userID.addMouseListener(this);
        email.addMouseListener(this);
        
        
        interiorPanel.add(name);
        interiorPanel.add(surname);
        interiorPanel.add(userID);
        interiorPanel.add(email);
        
        JPanel signupPanel = new JPanel();
        signupButton = new FormButton("SIGNUP");
        signupButton.setPreferredSize(new Dimension(270, 50));
        
        backToLogInButton = new JButton(" <== Back to login page");
        backToLogInButton.setHorizontalAlignment(SwingConstants.RIGHT);
        backToLogInButton.setForeground(UIColors.LIGHTBLUE);
        backToLogInButton.setBorder(null);
        backToLogInButton.setBackground(null);

        signupPanel.add(signupButton);
        signupPanel.setBackground(null);
        
        interiorPanel.add(signupPanel);
        interiorPanel.add(backToLogInButton);
        exteriorPanel.add(interiorPanel);
        add(exteriorPanel);
    }
    
    public JButton getbackToLogInButton()
    {
        return backToLogInButton;
    }
    
    public JButton getSignupButton()
    {
        return signupButton;
    }
    
    public String getTextEntryName()
    {
        return name.getTextEntery();
    }
    
    public String getTextEntrySurname()
    {
        return surname.getTextEntery();
    }
    
    public String getTextEntryUserID()
    {
        return userID.getTextEntery();
    }
    
    public String getTextEntryEmail()
    {
        return email.getTextEntery();
    }
    
    public void setText(String stringInput)
    {
        if(nameBoolean)
        {
            name.setTextEntery(stringInput);
        }
        else if(surnameBoolean)
        {
            surname.setTextEntery(stringInput);
            
        }
        else if(userIDBoolean)
        {
            userID.setTextEntery(stringInput);
        }
        else if(emailBoolean)
        {
            email.setTextEntery(stringInput);
        }
    }
    
    public void deleteLastCharacter()
    {
        if(nameBoolean)
        {
            name.deleteLastCharacter();
        }
        else if(surnameBoolean)
        {
            surname.deleteLastCharacter();
        }
        else if(userIDBoolean)
        {
            userID.deleteLastCharacter();
        }
        else if(emailBoolean)
        {
            email.deleteLastCharacter();
        }
        
    }
    
    public boolean getTextFieldChange()
    {
        return textFieldChanged;
    }
    
    public void setTextFieldChange(boolean b)
    {
        textFieldChanged = b;
    }
    
    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(e.getSource().equals(name))
        {
            if(!nameBoolean)
            {
                textFieldChanged = true;
                nameBoolean    = true;
                surnameBoolean = false;
                userIDBoolean  = false;
                emailBoolean   = false;
            }
        }
        else if(e.getSource().equals(surname))
        {
            if(!surnameBoolean)
            {
                System.out.println("SURNAME GİRDİM");
                textFieldChanged = true;
                nameBoolean    = false;
                surnameBoolean = true;
                userIDBoolean  = false;
                emailBoolean   = false;
            }          
        }
        else if(e.getSource().equals(userID))
        {
            if(!userIDBoolean)
            {
                textFieldChanged = true;
                nameBoolean    = false;
                surnameBoolean = false;
                userIDBoolean  = true;
                emailBoolean   = false;
            }          
        }
        else if(e.getSource().equals(email))
        {
            if(!emailBoolean)
            {
                textFieldChanged = true;
                nameBoolean    = false;
                surnameBoolean = false;
                userIDBoolean  = false;
                emailBoolean   = true;
            }          
        }        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}
