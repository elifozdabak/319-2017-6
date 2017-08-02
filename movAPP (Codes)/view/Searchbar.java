package view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class Searchbar extends JPanel //JComboBox
{
    private final JTextField     usernameField;
    
    // CONSTRUCTOR
    public Searchbar() 
    {
        this.setBackground(null);

        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(100, 35));
        usernameField.setBorder(null);
        this.add(usernameField);
    }
    
    // METHODS
    protected String getUserID()
    {
        return usernameField.getText();
    }
    protected void addLast(String text)
    {
        usernameField.setText( usernameField.getText() + text);
    }
    protected void deleteLast()
    {
        try
        {
            String userName = usernameField.getText();
            usernameField.setText(userName.substring(0, userName.length() - 1));
        }
        catch(Exception ex)
        {
            System.out.println("Error (view.Searchbar.deleteLast()): " + ex);
        } 
    }
    protected void deleteAll()
    {
        usernameField.setText("");
    }
    protected String getText()
    {
        return usernameField.getText();
    }
    
}