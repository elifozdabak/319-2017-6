package project;

import view.FrameViewer;
import database.DBConnectUser;
import java.awt.FlowLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import view.MovieInfoPanel;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 */

public class Project {
 
    public static void main(String[] args) 
    {
        // VARIABLES
        FrameViewer frame;
        
        // PROGRAM       
        frame = new FrameViewer(false, null);
        frame.setExtendedState(MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
