/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 * This class was implemented by watching a video from youtube.
 * Link: 
 */
public class MediaPlayer extends Application 
{  
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("MEDIA PLAYER");
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent doubleClicked) 
            {
                if(doubleClicked.getClickCount() == 2)
                {
                    stage.setFullScreen(Boolean.TRUE);
                }
                else if(doubleClicked.getClickCount() == 1)
                {
                    stage.setFullScreen(Boolean.FALSE);
                }
            }
        });
        
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }

    
}
