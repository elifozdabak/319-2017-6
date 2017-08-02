package view;

import constants.UIColors;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class SearchView extends JPanel
{  
    private Searchbar searchbar;
    private JButton iconButton;
    
    public SearchView() 
    {
        searchbar = new Searchbar();
        
        ImageIcon icon = new ImageIcon("search_icon.png");
        Image img = icon.getImage();
        Image imgResized = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(imgResized);
        
        // Adding resized icon the the JButton
        iconButton = new JButton(icon);
        iconButton.setPreferredSize(new Dimension(40, 40));
        iconButton.setBackground(null);
        iconButton.setBorder(null);
        
        this.setBackground(UIColors.NIGHTBLUE);
        this.add(iconButton);        
        this.add(searchbar);
    }
    
    public void addLast(String text)
    {
        searchbar.addLast(text);
    }
    
    public void deleteLast()
    {
        searchbar.deleteLast();
    }
    
    public JButton getSearchButton()
    {
        return iconButton;
    }
    
    public String getText()
    {
        return searchbar.getText();
    }
}
