package view;

import constants.UIColors;
import database.DBManagement;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import model.Movie;
import model.User;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class CategoryPanel extends JPanel implements ActionListener
{
    // PROPERTIES
    private DefaultListModel defaultListModel;
    private FormButton       formButton1;
    private FormButton       formButton2;
    private FormButton       formButton3;
    private FormButton       formButton4;    
    private JLabel           headerLabel;
    private JLabel           statusLabel;
    private JPanel           categoryPanel;
    private JButton          showButton;
    private JList            movieList;
    private Movie             movie;
    private String username;
    private final DBManagement databaseConnection = new DBManagement();
    
    private ArrayList<Movie> cat1List;
    private ArrayList<Movie> cat2List;
    private ArrayList<Movie> cat3List;
    private ArrayList<Movie> cat4List;
    private MenuVirtualKeyboard menuVirtualKeyboard = new MenuVirtualKeyboard();
    
    private boolean isUser;
    private User user;
    
    private SearchView sw;
    
    //CONSTRUCTOR
    public CategoryPanel(boolean isUser, User user)
    {
        this.isUser = isUser;
        this.user = user;
        
        cat1List = databaseConnection.getDesiredCategory("Action");
        cat2List = databaseConnection.getDesiredCategory("Drama");
        cat3List = databaseConnection.getDesiredCategory("Comedy");
        cat4List = databaseConnection.getDesiredCategory("Crime");
        
        prepeareGUI();
        showList();
    }
    
    // METHODS
    private void prepeareGUI()
    {
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        
        statusLabel.setSize(200, 1000);
        
        setBackground(UIColors.NIGHTBLUE);
    }
    
    private void showList()
    {
        setLayout(new BorderLayout());
        headerLabel.setText("Control in action: JList");
        
        defaultListModel = new DefaultListModel();
        
        formButton1 = new FormButton("Action");
        formButton2 = new FormButton("Drama");
        formButton3 = new FormButton("Comedy");
        formButton4 = new FormButton("Crime");
        
        formButton1.setBackground(UIColors.NIGHTBLUE);
        formButton2.setBackground(UIColors.NIGHTBLUE);
        formButton3.setBackground(UIColors.NIGHTBLUE);
        formButton4.setBackground(UIColors.NIGHTBLUE);
        
        formButton1.addActionListener( this);
        formButton2.addActionListener( this);
        formButton3.addActionListener( this);
        formButton4.addActionListener( this);
        
        movieList = new JList( defaultListModel);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieList.setSelectedIndex(0);

        
        if (defaultListModel.size() > 20)
        {
            movieList.setVisibleRowCount(20);
        }
        else
        {
            movieList.setVisibleRowCount(defaultListModel.size());
        }
        
        JScrollPane movieListScrollPane = new JScrollPane(movieList);
        
        showButton = new FormButton("Show");
        showButton.setBackground(UIColors.NIGHTBLUE);
        showButton.setPreferredSize(new Dimension(200, 50));
        
        categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(2, 2));
        
        categoryPanel.add(formButton1);
        categoryPanel.add(formButton2);
        categoryPanel.add(formButton3);
        categoryPanel.add(formButton4);
        categoryPanel.setPreferredSize(new Dimension(300, 120));
        
        sw = new SearchView();
        
        JPanel sp = new JPanel();
        
        sp.setLayout(new GridLayout(1, 0));
        sp.add(sw);
        
        JPanel xx = new JPanel();
        xx.setLayout(new BorderLayout());
        
        xx.add(categoryPanel, BorderLayout.NORTH);
        xx.add(sp, BorderLayout.CENTER);
        
        
        
        
        
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        
        add(xx, BorderLayout.NORTH);
        add(movieListScrollPane, BorderLayout.CENTER);
        newPanel.add(showButton, BorderLayout.CENTER);
        
        
        newPanel.add(menuVirtualKeyboard, BorderLayout.PAGE_END);
        add(newPanel, BorderLayout.SOUTH);
        
        addActionListeners();
        
        sw.getSearchButton().addActionListener(this);
    }
    
    private void addActionListeners()
    {
        for(int i = 0 ; i < getfirstRowCount() ; i++)
        {
            getFirstRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < getsecondRowCount(); i++)
        {
            getSecondRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < getthirdRowCount(); i++)
        {
            getThirdRow()[i].addActionListener(this);
        }
        
        for(int i = 0 ; i < getfourthRowCount(); i++)
        {
            getFourthRow()[i].addActionListener(this);
        }  
    }
    

    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ( e.getSource().equals(formButton1))
        {
            showSelectedCategory(cat1List);
        }
        else if ( e.getSource().equals(formButton2))
        {
            showSelectedCategory(cat2List);
        }
        else if ( e.getSource().equals(formButton3))
        {
            showSelectedCategory(cat3List);
        }
        else if ( e.getSource().equals(formButton4))
        {
           showSelectedCategory(cat4List); 
        }
        else if (e.getSource().equals(sw.getSearchButton()))
        {
            showSelectedCategory(getSearchResult());
        }
        for(int i = 0 ; i < getfirstRowCount() ; i++)
        {
            if(e.getSource().equals(getFirstRow()[i]))
            {
                if(getFirstRow()[i].getText().equalsIgnoreCase("Del"))
                {
                    sw.deleteLast();
                }
                else
                {
                    sw.addLast(getFirstRow()[i].getText());
                }
            }
        }
        
        for(int i = 0 ; i < getsecondRowCount(); i++)
        {
            if(e.getSource().equals(getSecondRow()[i]))
            {
                sw.addLast(getSecondRow()[i].getText());
            }
        }
        
        for(int i = 0 ; i < getthirdRowCount(); i++)
        {
            if(e.getSource().equals(getThirdRow()[i]))
            {
                if(getThirdRow()[i].getText().equalsIgnoreCase("Spc"))
                {
                   sw.addLast(" ");
                }
                else
                {
                    sw.addLast(getThirdRow()[i].getText());
                }
            }
        }
        
        for(int i = 0 ; i < getfourthRowCount(); i++)
        {
            if(e.getSource().equals(getFourthRow()[i]))
            {
                if(getFourthRow()[i].getText().equalsIgnoreCase("Space"))
                {
                    
                }
                else
                {
                    sw.addLast(getFourthRow()[i].getText());
                }
            }
        }    
    }
    
    public JButton getShowButton()
    {
        return showButton;
    }
    
    public Movie getSelectedMovie()
    {
        try
        {
            return (Movie) movieList.getSelectedValue();
        }
        catch(Exception ex)
        {
            System.out.println("Error (View.CategoryPanel.getSelectedMovie()): " + ex);
        }
        return null;        
    }
    
    
    public void showSelectedCategory(ArrayList<Movie> categoryList)
    {
        try
        {
            defaultListModel.removeAllElements();
            for(int i = 0 ; i < categoryList.size() ; i++)
            {
                defaultListModel.addElement( categoryList.get(i));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error (view.FormEntryView.CategoryPanel.showSelectedCategory(ArrayList<Movie> categoryList)): " + ex);
        }
    }
    
    // Keyboard Methods
    public boolean getcapsEnabled()
    {
        return menuVirtualKeyboard.getcapsEnabled();
    }
    
    public void setCapsEnabled(boolean newCapsEnabled)
    {
        menuVirtualKeyboard.setCapsEnabled(newCapsEnabled);
    }
    public MenuButton[] getFirstRow()
    {
        return menuVirtualKeyboard.getFirstRow();
    }
    public MenuButton[] getSecondRow()
    {
        return menuVirtualKeyboard.getSecondRow();
    }   
    public MenuButton[] getThirdRow()
    {
        return menuVirtualKeyboard.getThirdRow();
    }      
    public MenuButton[] getFourthRow()
    {
        return menuVirtualKeyboard.getFourthRow();
    }
    public int getfirstRowCount()
    {
        return menuVirtualKeyboard.getfirstRowCount();
    }
    public int getsecondRowCount()
    {
        return menuVirtualKeyboard.getsecondRowCount();
    }
    public int getthirdRowCount()
    {
        return menuVirtualKeyboard.getthirdRowCount();
    }
    public int getfourthRowCount()
    {
        return menuVirtualKeyboard.getfourthRowCount();
    }

    public void changeCharacter(boolean isCapsEnabled)
    {
        if(isCapsEnabled)
        {
            makeCharactersUpper(menuVirtualKeyboard.getFirstRow());
            makeCharactersUpper(menuVirtualKeyboard.getSecondRow());
            makeCharactersUpper(menuVirtualKeyboard.getThirdRow());
            makeCharactersUpper(menuVirtualKeyboard.getFourthRow());
        }
        else
        {
            makeCharactersLower(menuVirtualKeyboard.getFirstRow());
            makeCharactersLower(menuVirtualKeyboard.getSecondRow());
            makeCharactersLower(menuVirtualKeyboard.getThirdRow());
            makeCharactersLower(menuVirtualKeyboard.getFourthRow());
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
    
    public ArrayList<Movie> getSearchResult()
    {
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Movie> allMovies = databaseConnection.getAllMovies();
        
        for(int i = 0 ; i < allMovies.size() ; i++)
        {
            if(allMovies.get(i).getName().toLowerCase().contains(sw.getText().toLowerCase()))
            {
                result.add(allMovies.get(i));
            }
        }
        return result;
    }
    
    public JButton getSearchButton()
    {
        return sw.getSearchButton();
    }
}