package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PodgladZdjecia {

	public static void pokliku(int i) {
		if (Rzad.ivy.get(i).getFitWidth() < 1000 || Rzad.ivy.get(i).getFitHeight() < 500) {
			Stage primaryStage = new Stage();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, Rzad.ivy.get(i).getFitWidth() + 440, Rzad.ivy.get(i).getFitHeight() + 40);
			scene.getStylesheets().add("application/window.css");
			Rzad.ivy.get(i).setLayoutX(20);
			Rzad.ivy.get(i).setLayoutY(20);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.getChildren().add(Rzad.ivy.get(i));
		} else {
			Rzad.ivy.get(i).setFitWidth(Rzad.ivy.get(i).getFitWidth() * 0.2);
			Rzad.ivy.get(i).setFitHeight(Rzad.ivy.get(i).getFitHeight() * 0.2);
			Stage primaryStage = new Stage();
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, Rzad.ivy.get(i).getFitWidth() + 440, Rzad.ivy.get(i).getFitHeight() + 40);
			scene.getStylesheets().add("application/window.css");
			Rzad.ivy.get(i).setLayoutX(20);
			Rzad.ivy.get(i).setLayoutY(20);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.getChildren().add(Rzad.ivy.get(i));
		}
	}
}
