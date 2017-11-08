package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Wyborzdjecia {

	public static Image wyborzdysku(Stage primaryStage) {
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png",
				"*.bmp", "*.jpeg", "*.gif"); // Filter do FileChoosera
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(imageFilter);
		fileChooser.setTitle("Select your pic...");
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			Image image = new Image("file:///" + file.getPath());
			return image;
		} else
			return null;
	}

}
