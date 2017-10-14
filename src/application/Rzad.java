package application;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Rzad {

	public static ArrayList<ImageView> ivy = new ArrayList<ImageView>();

	public static void przypiszzdjecia() {
		for (int i = 0; i < Pomocnicza.getObrazy().size(); i++) {
			ImageView iv = new ImageView();
			iv.setImage(Pomocnicza.getObrazy().get(i));
			iv.setFitWidth(Pomocnicza.getObrazy().get(i).getWidth());
			iv.setFitHeight(Pomocnicza.getObrazy().get(i).getHeight());
			ivy.add(iv);
		}
	}

	/*public static Point2D wyswietlZdjecie(Point2D point, ImageView ivy, BorderPane root, int i, Stage primaryStage) {
		double ratio = (ivy.getFitWidth() / ivy.getFitHeight());
		ivy.setFitHeight(0.23 * primaryStage.getHeight());
		ivy.setFitWidth(ivy.getFitHeight() * ratio);
		ivy.setLayoutX(point.getX());
		ivy.setLayoutY(point.getY());
		point = new Point2D(point.getX() + ivy.getFitWidth() + 15, point.getY());
		return point;
	}
	
	public static void ustalanieswiatla(Stage primaryStage)
	{
		double space;
		double pomocnicza=0;
		space=primaryStage.getWidth()-30;
		int i=0;
		while(pomocnicza<primaryStage.getWidth()-pomocnicza-30)
		{
			pomocnicza+=Rzad.getIvy().get(i).getFitWidth();
			space-=pomocnicza;
			i++;
			System.out.println(space);
		}
		System.out.println(space);
	}
	*/
	public static double[] ustalanieswiatla(Stage primaryStage)
	{
		double a[] = {0,0};
		for(int i=0;i<Rzad.ivy.size();i++)
		{
			double ratio = (ivy.get(i).getFitWidth() / ivy.get(i).getFitHeight());
			ivy.get(i).setFitHeight(0.23 * primaryStage.getHeight());
			ivy.get(i).setFitWidth(ivy.get(i).getFitHeight() * ratio);
		}
		double space;
		double pomocnicza=0;
		space=primaryStage.getWidth()-30;
		int i=0;
		while(pomocnicza<primaryStage.getWidth()-pomocnicza-30)
		{
			pomocnicza+=Rzad.getIvy().get(i).getFitWidth();
			space-=pomocnicza;
			if(space>14){
				i++;			
			}
			else
			{
				space=(space+pomocnicza)/i;
				System.out.println(space);
				a[0]=space;
				a[1]=i;
			}
		}
		return a;
	}
	
	public static double[] wyswietlanierzedu(Point2D point, BorderPane root, double[] spaceandi)
	{
		int i=0;
		while(i<=spaceandi[1]+1)
		{
			Rzad.getIvy().get(i).setLayoutY(point.getY());
			if(i==0)
			{
				Rzad.getIvy().get(i).setLayoutX(15/2+spaceandi[0]);
			}
			else
			{
				Rzad.getIvy().get(i).setLayoutX(Rzad.getIvy().get(i-1).getLayoutX()+Rzad.getIvy().get(i-1).getFitWidth()+spaceandi[0]);
			}
			root.getChildren().add(Rzad.getIvy().get(i));
			i++;			
		}
		System.out.println(spaceandi[1]);
		return spaceandi;
	}

	public static ArrayList<ImageView> getIvy() {
		return ivy;
	}

	public static void setIvy(ArrayList<ImageView> ivy) {
		Rzad.ivy = ivy;
	}

	void wyswietlrzad(Stage primaryStage) {

	}
}
