package application;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public abstract class Rzad {

	public static ArrayList<Image> ivy = new ArrayList<Image>();
	public static ArrayList<ImageView> skalowaneivy = new ArrayList<ImageView>();


	public static ArrayList<ImageView> getSkalowaneivy() {
		return skalowaneivy;
	}

	public static void dodajdoskalowanych(ImageView iv, int i)
	{			
		
		iv.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj			
		PodgladZdjecia.pokliku(i);	
		});
		iv.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			String musicFile = "sound.wav";     // For example
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		});
		skalowaneivy.add(iv);
	}
	
	public static void setSkalowaneivy(ArrayList<ImageView> skalowaneivy) {
		Rzad.skalowaneivy = skalowaneivy;
	}

	

	public static void skalowanie() {
		double skala;
		for (int i = 0; i < Pomocnicza.getObrazy().size(); i++) {
			ImageView iv = new ImageView();
			iv.setImage(Pomocnicza.getObrazy().get(i).getImage());
			iv.setFitWidth(330);
			skala=Pomocnicza.getObrazy().get(i).getImage().getWidth()/330;
			iv.setFitHeight(Pomocnicza.getObrazy().get(i).getImage().getHeight()/skala);			
			dodajdoskalowanych(iv, i);
		}
	}
	public static void przypiszzdjecia() {
		ivy.clear();
		skalowaneivy.clear();
		
		for (int i = 0; i < Pomocnicza.getObrazy().size(); i++) {
			Image iv = Pomocnicza.getObrazy().get(i).getImage();
			
			ivy.add(iv);
			
	}

		skalowanie();
	}
	public static void wyswietlanierzedu(VBox root, ImageView iv)
	{
		root.getChildren().add(iv);
	}
	
	public static void wyczyœæ(VBox root)
	{
		root.getChildren().clear();
	}

	public static ArrayList<Image> getIvy() {
		return ivy;
	}

	public static void setIvy(ArrayList<Image> ivy) {
		Rzad.ivy = ivy;
	}

}
