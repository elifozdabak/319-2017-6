package view;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class FormEntryView extends JPanel
{
    // PROPERTIES
    private final JTextField     usernameField;
    
    // CONSTRUCTOR
    public FormEntryView(String imgStr) 
    {
        this.setBackground(null);
        
        // Creating resized icon
        ImageIcon icon = new ImageIcon(imgStr);
        Image img = icon.getImage();
        Image imgResized = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(imgResized);
        
        // Adding resized icon the the JButton
        JButton iconButton = new JButton(icon);
        iconButton.setPreferredSize(new Dimension(40, 40));
        iconButton.setBackground(null);
        iconButton.setBorder(null);
        
        this.add(iconButton);

        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(100, 35));
        usernameField.setBorder(null);
        add(usernameField);
    }
    
    // METHODS
    protected String getUserID()
    {
        return usernameField.getText();
    }
    
    protected void addLast(char charInput)
    {
        usernameField.setText( usernameField.getText() + charInput);
    }
    
    protected void deleteLast()
    {
        String userName = usernameField.getText();
        usernameField.setText(userName.substring(0, userName.length() - 1));
    }
    
    protected void deleteAll()
    {
        usernameField.setText("");
    }
}