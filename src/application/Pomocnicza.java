package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

public abstract class Pomocnicza {

	static public ArrayList<Image> obrazy = new ArrayList<Image>(0);

	public static void dodajobrazy() {   /// To trzeba czytaæ automatycznie z katalogu a nie tak rêcznie
		obrazy.add(new Image("http://localhost/img/1.jpg"));
		obrazy.add(new Image("http://localhost/img/2.jpg"));
		obrazy.add(new Image("img/3.jpg"));
		obrazy.add(new Image("img/4.jpg"));
		obrazy.add(new Image("img/5.jpg"));
		obrazy.add(new Image("img/6.jpg"));
		obrazy.add(new Image("img/7.jpg"));
		obrazy.add(new Image("img/8.jpg"));
		obrazy.add(new Image("img/9.jpg"));
		obrazy.add(new Image("img/10.jpg"));
		obrazy.add(new Image("img/11.jpg"));
		obrazy.add(new Image("img/12.jpg"));
		obrazy.add(new Image("img/13.jpg"));
		obrazy.add(new Image("img/14.jpg"));
		obrazy.add(new Image("img/15.jpg"));
		/*
		obrazy.add(new Image("http://localhost/img/0.jpg"));
		obrazy.add(new Image("http://localhost/img/6.gif"));
		obrazy.add(new Image("http://localhost/img/7.png"));
		*/
	}

	public static ArrayList<Image> getObrazy() {
		return obrazy;
	}

	public static void setObrazy(ArrayList<Image> obrazy) {
		Pomocnicza.obrazy = obrazy;
	}
}
