package application;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PodgladZdjecia {

	public static void pokliku(int i) {
		Stage primaryStage = new Stage();
		BorderPane root = new BorderPane();
		ImageView iv = new ImageView(Rzad.ivy.get(i));
		iv.setFitWidth(500);
		iv.setFitHeight(500);
		iv.setPreserveRatio(true);
		root.getChildren().add(iv);
		Scene scene = new Scene(root, iv.getFitWidth() + 440, iv.getFitHeight() + 40);
		scene.getStylesheets().add("application/window.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
