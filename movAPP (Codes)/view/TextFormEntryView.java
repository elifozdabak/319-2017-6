package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class TextFormEntryView extends JPanel
{
    private final JTextField field;
    
    public TextFormEntryView(String title, int length) 
    {
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(null);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel);
        
        field = new JTextField(length);
        field.setPreferredSize(new Dimension(length * 5, 35));
        field.setBorder(null);
        this.add(field); 
    }
    
    // METHODS
    public String getTextEntery()
    {
        return field.getText();
    }

    public void setTextEntery(String stringInput) 
    {
        field.setText(field.getText() + stringInput);
    }
    
    public JTextField getTextField()
    {
        return field;
    }
    
    public void deleteLastCharacter()
    {
        try
        {
            field.setText(field.getText().substring(0, field.getText().length() - 1));
        }
        catch(Exception ex)
        {
            
        }
    }
}
