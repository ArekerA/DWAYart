package application;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Rzad {

	public static ArrayList<ImageView> ivy = new ArrayList<ImageView>();
	public static ArrayList<ImageView> skalowaneivy = new ArrayList<ImageView>();


	public static ArrayList<ImageView> getSkalowaneivy() {
		return skalowaneivy;
	}

	public static void setSkalowaneivy(ArrayList<ImageView> skalowaneivy) {
		Rzad.skalowaneivy = skalowaneivy;
	}

	

	public static void skalowanie() {
		double skala;
		for (int i = 0; i < Pomocnicza.getObrazy().size(); i++) {
			ImageView iv = new ImageView();
			iv.setImage(Pomocnicza.getObrazy().get(i));
			iv.setFitWidth(330);
			skala=Pomocnicza.getObrazy().get(i).getWidth()/300;
			iv.setFitHeight(Pomocnicza.getObrazy().get(i).getHeight()/skala);
			skalowaneivy.add(iv);
		}
	}
	public static void przypiszzdjecia() {
		for (int i = 0; i < Pomocnicza.getObrazy().size(); i++) {
			ImageView iv = new ImageView();
			iv.setImage(Pomocnicza.getObrazy().get(i));
			iv.setFitWidth(Pomocnicza.getObrazy().get(i).getWidth());
			iv.setFitHeight(Pomocnicza.getObrazy().get(i).getHeight());
			ivy.add(iv);			
			ivy.get(i).setPickOnBounds(true); // Przeoczystoœæ obrazka
			ivy.get(i).setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
				Stage primaryStage=new Stage();
				BorderPane root = new BorderPane();
				Scene scene = new Scene(root, iv.getFitWidth(), iv.getFitHeight());
				primaryStage.setScene(scene);
				primaryStage.show();
				root.getChildren().add(iv);
			});
			
	}

		skalowanie();
	}
	public static void wyswietlanierzedu(VBox root, ImageView iv)
	{
		root.getChildren().add(iv);
	}
	

	public static ArrayList<ImageView> getIvy() {
		return ivy;
	}

	public static void setIvy(ArrayList<ImageView> ivy) {
		Rzad.ivy = ivy;
	}

}
