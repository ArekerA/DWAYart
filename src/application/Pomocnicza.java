package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

public abstract class Pomocnicza {

	static public ArrayList<Image> obrazy = new ArrayList<Image>(0);

	public static void dodajobrazy() {
		obrazy.add(new Image("1.jpg"));
		obrazy.add(new Image("2.jpg"));
		obrazy.add(new Image("3.jpg"));
		obrazy.add(new Image("4.jpg"));
		obrazy.add(new Image("5.jpg"));
		obrazy.add(new Image("6.jpg"));
		obrazy.add(new Image("7.jpg"));
		obrazy.add(new Image("8.jpg"));
		obrazy.add(new Image("9.jpg"));
		obrazy.add(new Image("10.jpg"));
		obrazy.add(new Image("11.jpg"));
		obrazy.add(new Image("12.jpg"));
		obrazy.add(new Image("13.jpg"));
		obrazy.add(new Image("14.jpg"));
		obrazy.add(new Image("15.jpg"));
	}

	public static ArrayList<Image> getObrazy() {
		return obrazy;
	}

	public static void setObrazy(ArrayList<Image> obrazy) {
		Pomocnicza.obrazy = obrazy;
	}
}
