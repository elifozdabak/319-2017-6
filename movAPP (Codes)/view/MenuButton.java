package view;

import constants.UIColors;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class MenuButton extends JButton 
{
    public MenuButton(String name) 
    {
        super(name);
        this.setBorder(null);
        this.setBackground(UIColors.LIGHTBLUE);
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 50));
    }
    
    public MenuButton(String name, int dimension)
    {
        super(name);
        this.setBorder(null);
        this.setBackground(UIColors.LIGHTBLUE);
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(dimension, 50));
        this.setBorder(new LineBorder(UIColors.LIGHTBLUE));
    }
    
    public MenuButton(String name, int dimension, int dimension2)
    {
        super(name);
        this.setBorder(null);
        this.setBackground(UIColors.LIGHTBLUE);
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(dimension, dimension2));
    }
    
    public void setBorderColor(Color color)
    {
        this.setBorder(new LineBorder(color));
    }
}