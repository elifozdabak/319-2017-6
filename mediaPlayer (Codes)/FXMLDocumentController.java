/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @Author: Ahmet Batu Orhan
 * CS319 Term Project (Summer 2017)
 * This class was implemented by watching a video from youtube.
 * Link: 
 */
public class FXMLDocumentController implements Initializable 
{
    private String desiredFilePath;
    private MediaPlayer mediaPlayer;
    private String filePath;
    private String movieID;
    
    public FXMLDocumentController()
    {
        movieID = "C3";
        desiredFilePath = "file:/C:/Users/Ahmet%20Batu/Desktop/CS319/Project/project/movies/3.mp4" ;
    }
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider seekSlider;
    
    @FXML
    private Slider slider;
    
    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.mp4)", "*.mp4");
        
        filechooser.getExtensionFilters().add(filter);
        File file = filechooser.showOpenDialog(null);
        filePath = desiredFilePath;
        System.out.println("FILE PATH: " + filePath);
        
        if(filePath != null)
        {
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            slider.setValue(mediaPlayer.getVolume() * 100);
            slider.valueProperty().addListener(new InvalidationListener()
            {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                }
            });
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() 
            {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) 
                {
                    seekSlider.setValue(newValue.toSeconds());
                }
            });
            
            seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) 
                {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });
            
            mediaPlayer.play();
        }
        
    }
    
    @FXML
    private void pauseVideo(ActionEvent event)
    {
        mediaPlayer.pause();
    }
    
    @FXML
    public void playVideo(ActionEvent event, String movieID)
    {
        handleButtonAction(event);
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    @FXML
    private void stopVideo(ActionEvent event)
    {
        mediaPlayer.stop();
    }
    
    @FXML
    private void fastVideo(ActionEvent event)
    {
        mediaPlayer.setRate(1.5);
    }
    
    @FXML
    private void fasterVideo(ActionEvent event)
    {
        mediaPlayer.setRate(2.0);
    }
    
    @FXML
    private void slowVideo(ActionEvent event)
    {
        mediaPlayer.setRate(0.75);
    }
    
    @FXML
    private void slowerVideo(ActionEvent event)
    {
        mediaPlayer.setRate(0.5);
    } 
    
    @FXML
    private void exitVideo(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }   
    
}
