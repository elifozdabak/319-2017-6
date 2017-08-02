package view;

import constants.UIColors;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class LoginVirtualKeyboard extends JPanel implements ActionListener
{  
     //Individual keyboard rows  
    String firstRow[] = {"9","8","7"};
    String secondRow[] = {"6","5","4"};
    String thirdRow[] = {"3","2","1"};
    String fourthRow[] = {"0", "Delete"};
    
    MenuButton first[];

    MenuButton second[];

    MenuButton third[];
    
    MenuButton fourth[];
    
    //default color of the button to be repainted when key released 
    Color cc = new JButton().getBackground();

    public LoginVirtualKeyboard()
    {
        initWidgets();
    }

    /**
     * Method to initialize frame component 
     */
    private void initWidgets()
    {
        
        JPanel jpKeyboard = new JPanel();
        add(jpKeyboard, BorderLayout.SOUTH);
        jpKeyboard.setLayout(new GridLayout(4,1));
        
        this.setBackground(UIColors.LIGHTBLUE);
        
        // First Row
        first = new MenuButton[firstRow.length];
        JPanel p = new JPanel(new GridLayout(1, 3));
        p.setBackground(UIColors.LIGHTBLUE);
        
        for(int i = 0; i < firstRow.length; ++i) 
        {
            MenuButton b = new MenuButton(firstRow[i], 125);
            b.setBackground(UIColors.NIGHTBLUE);
            first[i] = b;
            p.add(first[i]);
        }
        jpKeyboard.add(p);

        
        // Second Row
        second = new MenuButton[secondRow.length];
        p = new JPanel(new GridLayout(1, secondRow.length));
        p.setBackground(UIColors.LIGHTBLUE);
        
        for(int i = 0; i < secondRow.length; ++i) 
        {
            MenuButton b = new MenuButton(secondRow[i], 125);
            b.setBackground(UIColors.NIGHTBLUE);
            second[i] = b;
            p.add(second[i]);

        }
        jpKeyboard.add(p);

       
        // Third Row
        third = new MenuButton[thirdRow.length];
        p = new JPanel(new GridLayout(1, thirdRow.length));
        p.setBackground(UIColors.LIGHTBLUE);
        
        for(int i = 0; i < thirdRow.length; ++i)
        {
            MenuButton b = new MenuButton(thirdRow[i], 125);
            b.setBackground(UIColors.NIGHTBLUE);
            third[i] = b;
            p.add(third[i]);
        }
        jpKeyboard.add(p);

        
        // Fourth Row
        fourth = new MenuButton[fourthRow.length];
        JPanel coloredPanel = new JPanel();
        coloredPanel.setBackground(UIColors.LIGHTBLUE);
        p = new JPanel(new GridLayout(1, 3));
        p.add(new MenuButton("", 125));
        for(int i = 0; i < fourthRow.length; ++i)
        {
            MenuButton b = new MenuButton(fourthRow[i], 125);
            b.setBackground(UIColors.NIGHTBLUE);
            fourth[i] = b;
            p.add(fourth[i]);
        }
        p.add(coloredPanel);
        jpKeyboard.add(p);
        
        
        for(JButton b : first)
        {
            b.addActionListener(this); 
        }
        for(JButton b : second)
        {
            b.addActionListener(this); 
        }
        for(JButton b : third)
        {
            b.addActionListener(this);
        }
        for(JButton b : fourth)
        {
            b.addActionListener(this);
        }
        
        
        }//end of initWidgets
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource().equals(first[0]))
            {
                LoginPanel.addText('9');
            }
            else if(e.getSource().equals(first[1]))
            {
                LoginPanel.addText('8');
            }
            else if(e.getSource().equals(first[2]))
            {
                LoginPanel.addText('7');
            }
            else if(e.getSource().equals(second[0]))
            {
                LoginPanel.addText('6');
            }
            else if(e.getSource().equals(second[1]))
            {
                LoginPanel.addText('5');
            }
            else if(e.getSource().equals(second[2]))
            {
                LoginPanel.addText('4');
            }
            else if(e.getSource().equals(third[0]))
            {
                LoginPanel.addText('3');
            }
            else if(e.getSource().equals(third[1]))
            {
                LoginPanel.addText('2');
            }
            else if(e.getSource().equals(third[2]))
            {
                LoginPanel.addText('1');
            }
            else if(e.getSource().equals(fourth[0]))
            {
                LoginPanel.addText('0');
            }
            else if(e.getSource().equals(fourth[1]))
            {
                LoginPanel.deleteLast();
            }
            
        }

}//end of class