package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Wyborzdjecia {

	public static Image wyborzdysku(Stage primaryStage)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select your pic...");
		//fileChooser.showOpenDialog(primaryStage);
		//configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
        	Image image=new Image("file:///"+file.getPath());
        	return image;
        }
        else
		return null;
	}
        
     
      
}
