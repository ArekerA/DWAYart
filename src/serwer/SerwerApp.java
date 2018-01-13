package serwer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SerwerApp extends Application {

	public void start(Stage primaryStage) {
		(new Serwer(752)).start();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1200, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("DWAY");
		primaryStage.setResizable(false);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
