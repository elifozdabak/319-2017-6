package view;

import constants.UIColors;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class MenuVirtualKeyboard extends JPanel
{  
    //Individual keyboard rows  
    String firstRow[] = {"1","2","3","4","5","6","7","8","9","0","DEL"};
    String secondRow[] = {"Q","W","E","R","T","Y","U","I","O","P", "Ğ", "Ü"};
    String thirdRow[] = {"A","S","D","F","G","H","J","K","L","Ş", "İ", "SPC"};
    String fourthRow[] = {"Z","X","C","V","B","N","M","Ö","Ç",",","."};


    //Jbuttons corresponting to each individual rows 
    MenuButton first[];

    MenuButton second[];

    MenuButton third[];

    MenuButton fourth[];

    public boolean capsEnabled = true;
    
    //default color of the button to be repainted when key released 
    Color cc = new JButton().getBackground();

    public MenuVirtualKeyboard()
    {
        this.setBackground(UIColors.NIGHTBLUE);
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

        // First Row
        first = new MenuButton[firstRow.length];
        JPanel p = new JPanel(new GridLayout(1, 3));
        p.setBackground(UIColors.NIGHTBLUE);

        for(int i = 0; i < firstRow.length; ++i) 
        {
            MenuButton b = new MenuButton(firstRow[i], 24);
            b.setBorderColor(UIColors.NIGHTBLUE);
            b.setBackground(UIColors.NIGHTBLUE);
            first[i] = b;
            p.add(first[i]);
        }
        jpKeyboard.add(p);


        // Second Row
        second = new MenuButton[secondRow.length];
        p = new JPanel(new GridLayout(1, secondRow.length));
        p.setBackground(UIColors.NIGHTBLUE);

        for(int i = 0; i < secondRow.length; ++i) 
        {
            MenuButton b = new MenuButton(secondRow[i], 22);
            b.setBorderColor(UIColors.NIGHTBLUE);
            b.setBackground(UIColors.NIGHTBLUE);
            second[i] = b;
            p.add(second[i]);

        }
        jpKeyboard.add(p);


        // Third Row
        third = new MenuButton[thirdRow.length];
        p = new JPanel(new GridLayout(1, thirdRow.length));
        p.setBackground(UIColors.NIGHTBLUE);

        for(int i = 0; i < thirdRow.length; ++i)
        {
            MenuButton b = new MenuButton(thirdRow[i], 22);
            b.setBorderColor(UIColors.NIGHTBLUE);
            b.setBackground(UIColors.NIGHTBLUE);
            third[i] = b;
            p.add(third[i]);
        }
        jpKeyboard.add(p);


        // Fourth Row
        fourth = new MenuButton[fourthRow.length];
        p = new JPanel(new GridLayout(1, fourthRow.length));
        p.setBackground(UIColors.NIGHTBLUE);
        for(int i = 0; i < fourthRow.length; ++i)
        {
            MenuButton b = new MenuButton(fourthRow[i], 24);
            b.setBorderColor(UIColors.NIGHTBLUE);
            b.setBackground(UIColors.NIGHTBLUE);
            fourth[i] = b;
            p.add(fourth[i]);
        }
        jpKeyboard.add(p);



        jpKeyboard.add(p);
       
    } //end of initWidgets   
    
    
    public boolean getcapsEnabled()
    {
        return capsEnabled;
    }
    
    public void setCapsEnabled(boolean newCapsEnabled)
    {
        capsEnabled = newCapsEnabled;
    }
    
    
    
    public MenuButton[] getFirstRow()
    {
        return first;
    }
    public MenuButton[] getSecondRow()
    {
        return second;
    }   
    public MenuButton[] getThirdRow()
    {
        return third;
    }      
    public MenuButton[] getFourthRow()
    {
        return fourth;
    }
    public int getfirstRowCount()
    {
        return firstRow.length;
    }
    public int getsecondRowCount()
    {
        return secondRow.length;
    }
    public int getthirdRowCount()
    {
        return thirdRow.length;
    }
    public int getfourthRowCount()
    {
        return fourthRow.length;
    }

    
    public void changeCharacter(boolean isCapsEnabled)
    {
        if(isCapsEnabled)
        {
            makeCharactersUpper(first);
            makeCharactersUpper(second);
            makeCharactersUpper(third);
            makeCharactersUpper(fourth);
        }
        else
        {
            makeCharactersLower(first);
            makeCharactersLower(second);
            makeCharactersLower(third);
            makeCharactersLower(fourth);
        }
    }
    
    private void makeCharactersLower(MenuButton[] arrayInput)
    {
        for (int i = 0 ; i < arrayInput.length ; i++) 
        {
            if( arrayInput[i].getText().equalsIgnoreCase("Enter") || 
                arrayInput[i].getText().equalsIgnoreCase("Caps")  || 
                arrayInput[i].getText().equalsIgnoreCase("Space") ||
                arrayInput[i].getText().equalsIgnoreCase("Delete")) {}
            else
            {
               arrayInput[i].setText(arrayInput[i].getText().toLowerCase()); 
            }
            
        }
    }
    
    private void makeCharactersUpper(MenuButton[] arrayInput)
    {
        for (int i = 0 ; i < arrayInput.length ; i++) 
        {
            if( arrayInput[i].getText().equalsIgnoreCase("Enter") || 
                arrayInput[i].getText().equalsIgnoreCase("Caps")  || 
                arrayInput[i].getText().equalsIgnoreCase("Space") ||
                arrayInput[i].getText().equalsIgnoreCase("Delete")) {}
            else
            {
               arrayInput[i].setText(arrayInput[i].getText().toUpperCase()); 
            }
        }
    }
}//end of class