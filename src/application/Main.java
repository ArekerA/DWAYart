package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application implements Runnable {
	@Override
	public void start(Stage primaryStage) {
		try {     
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("DWAY");
			primaryStage.setResizable(false);			
			Zegar.dodajzegar(primaryStage, root);			
			OknoLogowania.oknologowania(primaryStage,root); 
			
		// Komentarz sssa
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args); 
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("dziala");
			try {
				// usypiamy w¹tek na 100 milisekund
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
