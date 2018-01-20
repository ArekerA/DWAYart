package application;

import data.SuperUser;
import data.User;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MojProfil {

	public static void mojprofil(BorderPane root, Stage primaryStage) {
		root.getChildren().clear();
		Zegar.dodajzegar(primaryStage, root);

		///////////// ImageView
		Image logodway = new Image("img/logo-red.png");

		ImageView iv1 = new ImageView();
		iv1.setFitHeight(34);
		iv1.setFitWidth(66);
		iv1.setLayoutY(20);
		iv1.setLayoutX(primaryStage.getWidth());
		iv1.setImage(logodway);

		Image unknow = new Image("http://127.0.0.1/img/avatars/"+Static.user.getId()+"."+((Static.user.getType())==1?"jpg":(Static.user.getType())==2?"bmp":(Static.user.getType())==3?"gif":(Static.user.getType())==4?"png":(Static.user.getType())==5?"wbmp":"jpeg"));
		System.out.println("http://127.0.0.1/img/avatars/"+Static.user.getId()+"."+((Static.user.getType())==1?"jpg":(Static.user.getType())==2?"bmp":(Static.user.getType())==3?"gif":(Static.user.getType())==4?"png":(Static.user.getType())==5?"wbmp":"jpeg"));
		ImageView iv2 = new ImageView();
		iv2.setFitHeight(primaryStage.getHeight() / 2);
		iv2.setFitWidth(primaryStage.getHeight() / 2);
		iv2.setLayoutY(primaryStage.getHeight() * 0.15);
		iv2.setLayoutX(primaryStage.getWidth() - iv2.getFitWidth() - 200);
		iv2.setImage(unknow);

		Image log_in = new Image("img/login.png");

		ImageView iv5 = new ImageView(log_in);
		iv5.setFitHeight(45);
		iv5.setFitWidth(45);
		iv5.setLayoutY(550);
		iv5.setLayoutX(15);
		
		///////////// TEXT
		Text Back = new Text("    Back");
		Back.setStyle("-fx-font-size: 30pt;");
		Back.setFill(Color.WHITE);
		Back.setLayoutY(primaryStage.getHeight() * 0.90 + 10);
		Back.setLayoutX(14);

		Text rejestracja = new Text("Welcome "+Static.user.getName());
		rejestracja.setStyle("-fx-font-size: 30pt;");
		rejestracja.setFill(Color.WHITE);
		// login_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25,
		// iv2.getFitHeight());
		rejestracja.setLayoutY(50);
		rejestracja.setLayoutX(primaryStage.getWidth());

		Text login_text = new Text("login:");
		login_text.setStyle("-fx-font-size: 20pt;");
		login_text.setFill(Color.WHITE);
		// login_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25,
		// iv2.getFitHeight());
		login_text.setLayoutY(primaryStage.getHeight() * 0.15);
		login_text.setLayoutX(15);

		Text email = new Text("emai1 address:");
		email.setStyle("-fx-font-size: 20pt;");
		email.setFill(Color.WHITE);
		// email.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		email.setLayoutY(primaryStage.getHeight() * 0.25);
		email.setLayoutX(15);

		Text name = new Text("name:");
		name.setStyle("-fx-font-size: 20pt;");
		name.setFill(Color.WHITE);
		// name.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		name.setLayoutY(primaryStage.getHeight() * 0.35);
		name.setLayoutX(15);

		Text sex = new Text("gender:");
		sex.setStyle("-fx-font-size: 20pt;");
		sex.setFill(Color.WHITE);
		// name.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		sex.setLayoutY(primaryStage.getHeight() * 0.45);
		sex.setLayoutX(15);

		Text loginarea = new Text(Static.user.getLogin());
		loginarea.setStyle("-fx-font-size: 22pt;");
		loginarea.setFill(Color.RED);
		loginarea.resize(250, 30);
		loginarea.setLayoutY((primaryStage.getHeight() * 0.15) + 30);
		loginarea.setLayoutX(45);

		Text namearea = new Text(Static.user.getName());
		namearea.setStyle("-fx-font-size: 22pt;");
		namearea.setFill(Color.RED);
		namearea.resize(250, 30);
		namearea.setLayoutY((primaryStage.getHeight() * 0.35) + 30);
		namearea.setLayoutX(45);

		Text mailarea = new Text(Static.user.getEmail());
		mailarea.setFill(Color.RED);
		mailarea.setStyle("-fx-font-size: 22pt;");
		mailarea.resize(250, 30);
		mailarea.setLayoutY((primaryStage.getHeight() * 0.25) + 30);
		mailarea.setLayoutX(45);

		Text sexarea = new Text(Static.user.getSex()==1?"men":Static.user.getSex()==2?"women":"none");
		sexarea.setStyle("-fx-font-size: 22pt;");
		sexarea.setFill(Color.RED);
		sexarea.resize(250, 30);
		sexarea.setLayoutY((primaryStage.getHeight() * 0.45) + 30);
		sexarea.setLayoutX(45);
		///////////// Wykonanie

		Back.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			Back.setPickOnBounds(true);
			root.getChildren().clear();
			MainMenu.wyswietlmenu(root, primaryStage);
		});

		Duration czas = new Duration(100);
		TranslateTransition translateTransition = new TranslateTransition(czas, iv1);
		translateTransition.setByX(-primaryStage.getWidth() + 10);
		translateTransition.setAutoReverse(false);
		translateTransition.play();

		TranslateTransition translateTransition1 = new TranslateTransition(czas, rejestracja);
		translateTransition1.setDelay(czas);
		translateTransition1.setByX(-primaryStage.getWidth() + 10 + iv1.getFitWidth() + 40);
		translateTransition1.setAutoReverse(false);
		translateTransition1.play();

		Duration czas1 = new Duration(5000);
		FadeTransition ft = new FadeTransition(czas1, iv2);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setAutoReverse(true);
		ft.play();

		root.getChildren().add(iv1);
		root.getChildren().add(login_text);
		root.getChildren().add(email);
		root.getChildren().add(name);
		root.getChildren().add(loginarea);
		root.getChildren().add(namearea);
		root.getChildren().add(mailarea);
		root.getChildren().add(rejestracja);
		root.getChildren().add(iv2);
		root.getChildren().add(sex);
		root.getChildren().add(sexarea);
		root.getChildren().add(iv5);
		root.getChildren().add(Back);

	}
}
