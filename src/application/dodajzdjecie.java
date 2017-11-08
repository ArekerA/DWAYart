package application;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class dodajzdjecie {
	static void menudodawania(BorderPane root, Stage primaryStage) {

		Zegar.dodajzegar(primaryStage, root);
		//////////////// IMAGE VIEW
		Image logodway = new Image("img/logo-white.png");

		ImageView iv1 = new ImageView();
		iv1.setImage(logodway);
		iv1.setFitHeight(36);
		iv1.setFitWidth(66);
		iv1.setLayoutY(20);
		iv1.setLayoutX(10);

		Image unknow = new Image("img/yp.png");

		ImageView iv2 = new ImageView();
		iv2.setFitHeight(250);
		iv2.setFitWidth(375);
		iv2.setLayoutY(130);
		iv2.setLayoutX(725);
		iv2.setImage(unknow);

		Image choose = new Image("img/choose.png");

		ImageView iv3 = new ImageView();
		iv3.setFitHeight(50);
		iv3.setFitWidth(50);
		iv3.setImage(choose);
		iv3.setLayoutX(887);
		iv3.setLayoutY(400);

		iv3.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			Image temp = null;
			temp = Wyborzdjecia.wyborzdysku(primaryStage);
			iv3.setPickOnBounds(true);
			if (temp != null) {
				root.getChildren().remove(iv2);
				iv2.setImage(temp);

				if (temp.getHeight() >= temp.getWidth()) // Sprawdzamy czy zdjêcie jest szersze czy wy¿sze i w
															// zale¿noœci od tego ustawiamy i przystosowujemy
				{
					double k;
					iv2.setFitHeight(250);
					k = temp.getHeight() / 250;
					iv2.setFitWidth(temp.getWidth() / k);
					iv2.setLayoutY(130);
					iv2.setLayoutX(912 - iv2.getFitWidth() / 2);
				} else {
					double k;
					iv2.setFitWidth(375);
					k = temp.getWidth() / 375;
					iv2.setFitHeight(temp.getHeight() / k);
					iv2.setLayoutX(725);
					iv2.setLayoutY(225 - iv2.getFitHeight() / 2);
				}

				root.getChildren().add(iv2);
			}

		});

		Image conf = new Image("img/confirm.png");

		ImageView iv4 = new ImageView();
		iv4.setFitHeight(45);
		iv4.setFitWidth(45);
		iv4.setImage(conf);
		iv4.setLayoutY(primaryStage.getHeight() * 0.75 + 10);
		iv4.setLayoutX(205);

		Image log_in = new Image("img/login.png");

		ImageView iv5 = new ImageView(log_in);
		iv5.setFitHeight(45);
		iv5.setFitWidth(45);
		iv5.setLayoutY(550);
		iv5.setLayoutX(15);

		//////////////// TEXT

		Text t1 = new Text("image name:");
		t1.setStyle("-fx-font-size: 22pt;");
		t1.setFill(Color.WHITE);
		t1.setLayoutY(140);
		t1.setLayoutX(15);

		Text t2 = new Text("tags:");
		t2.setStyle("-fx-font-size: 22pt;");
		t2.setFill(Color.WHITE);
		t2.setLayoutY(220);
		t2.setLayoutX(15);

		Text t3 = new Text("owner name:");
		t3.setStyle("-fx-font-size: 22pt;");
		t3.setFill(Color.WHITE);
		t3.setLayoutY(310);
		t3.setLayoutX(15);

		Text t4 = new Text("Tu imie ownera");
		t4.setStyle("-fx-font-size: 25pt;");
		t4.setFill(Color.WHITE);
		t4.setLayoutY(355);
		t4.setLayoutX(17);

		Text t5 = new Text("description:");
		t5.setStyle("-fx-font-size: 22pt;");
		t5.setFill(Color.WHITE);
		t5.setLayoutY(140);
		t5.setLayoutX(415);

		Text dodawanie = new Text("Adding an image");
		dodawanie.setStyle("-fx-font-size: 30pt;");
		dodawanie.setFill(Color.WHITE);
		dodawanie.setLayoutY(45);
		dodawanie.setLayoutX(125);

		Text confirm = new Text("Confirm    ");
		confirm.setStyle("-fx-font-size: 30pt;");
		confirm.setFill(Color.WHITE);
		confirm.setLayoutY(primaryStage.getHeight() * 0.80 + 10);
		confirm.setLayoutX(15);

		Text Back = new Text("    Back");
		Back.setStyle("-fx-font-size: 30pt;");
		Back.setFill(Color.WHITE);
		Back.setLayoutY(primaryStage.getHeight() * 0.90 + 10);
		Back.setLayoutX(14);

		Text pom1 = new Text("Wypelnij wszystkie pola!!!");
		pom1.setStyle("-fx-font-size: 25pt;");
		pom1.setFill(Color.RED);
		pom1.setLayoutY(primaryStage.getHeight() * 0.80 + 10);
		pom1.setLayoutX(320);

		Text pom2 = new Text("Wybierz obraz!!!");
		pom2.setStyle("-fx-font-size: 25pt;");
		pom2.setFill(Color.RED);
		pom2.setLayoutY(primaryStage.getHeight() * 0.90 + 10);
		pom2.setLayoutX(320);

		///////////// POLA

		TextArea tt1 = new TextArea();
		tt1.setPromptText("");
		tt1.resize(250, 30);
		tt1.setWrapText(false); // Zawijanie wierszy
		tt1.setLayoutY(155);
		tt1.setLayoutX(15);

		TextArea tt2 = new TextArea();
		tt2.setPromptText("");
		tt2.resize(250, 30);
		tt2.setWrapText(false); // Zawijanie wierszy
		tt2.setLayoutY(235);
		tt2.setLayoutX(15);

		TextArea tt3 = new TextArea();
		tt3.setPromptText("");
		tt3.setWrapText(true); // Zawijanie wierszy
		tt3.resize(200, 250);
		tt3.setLayoutY(155);
		tt3.setLayoutX(410);
		////////////////// Wykonanie

		Back.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			Back.setPickOnBounds(true);
			root.getChildren().clear();
			MainMenu.wyswietlmenu(root, primaryStage);
		});

		confirm.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().remove(pom1);
			root.getChildren().remove(pom2);
			confirm.setPickOnBounds(true);
			if (tt2.getText().trim().isEmpty() == true || tt1.getText().trim().isEmpty() == true
					|| tt3.getText().trim().isEmpty() == true) // Spr czy pola s¹ puste
			{
				root.getChildren().add(pom1);
			} else {
				root.getChildren().remove(pom1);
			}

			if (iv2.getImage() == unknow) // Spr czy obrazek jest poczatkowy czy zmieniony
			{
				root.getChildren().add(pom2);
			} else {
				root.getChildren().remove(pom2);
			}

			if (tt2.getText().trim().isEmpty() == false && tt1.getText().trim().isEmpty() == false
					&& tt3.getText().trim().isEmpty() == false && iv2.getImage() != unknow) {

				////// Tu Zapis zdjêcia bedzie potrzebny + info z textarea

				root.getChildren().clear();
				MainMenu.wyswietlmenu(root, primaryStage);
			}

		});

		///////////// ROOT

		root.getChildren().add(iv1);
		root.getChildren().add(iv2);
		root.getChildren().add(iv3);
		root.getChildren().add(iv4);
		root.getChildren().add(iv5);
		root.getChildren().add(t1);
		root.getChildren().add(t2);
		root.getChildren().add(t3);
		root.getChildren().add(t4);
		root.getChildren().add(t5);
		root.getChildren().add(dodawanie);
		root.getChildren().add(Back);
		root.getChildren().add(confirm);
		root.getChildren().add(tt1);
		root.getChildren().add(tt2);
		root.getChildren().add(tt3);

	}
}