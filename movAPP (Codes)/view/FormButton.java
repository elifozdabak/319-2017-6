package view;

import constants.UIColors;
import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class FormButton extends JButton 
{
    // CONSTRUCTOR
    public FormButton(String name) 
    {
        super(name);
        this.setBorder(null);
        this.setBackground(UIColors.LIGHTBLUE);
        this.setForeground(Color.WHITE);
    }
}
