package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OknoLogowania {

	public static void oknologowania(Stage primaryStage, BorderPane root)
	{
		// UWaga: 

		Pomocnicza.dodajobrazy();
		Rzad.przypiszzdjecia();

		Image logodway = new Image("img/logo-white.png");
		Image log_in = new Image("img/login.png");		
		Image register = new Image("img/register.png");

		ImageView iv1 = new ImageView();
		iv1.setFitHeight(165);
		iv1.setFitWidth(330);
		iv1.setLayoutY(((primaryStage.getHeight() - iv1.getFitHeight()) / 2));
		iv1.setLayoutX(((primaryStage.getWidth() - iv1.getFitWidth()) / 2));
		iv1.setImage(logodway);

		ImageView iv2 = new ImageView();
		iv2.setImage(log_in);
		iv2.setFitHeight(45);
		iv2.setFitWidth(45);
		iv2.setLayoutY(primaryStage.getHeight() - 250);
		iv2.setLayoutX(iv1.getLayoutX());

		ImageView iv3 = new ImageView();
		iv3.setImage(register);
		iv3.setFitHeight(45);
		iv3.setFitWidth(45);
		iv3.setLayoutY(primaryStage.getHeight() - 200);
		iv3.setLayoutX((iv1.getLayoutX() + iv1.getFitWidth()) - iv3.getFitWidth());

		Button zalogujsie = new Button();
		zalogujsie.setGraphic(iv2);
		zalogujsie.resize(45, 45);
		zalogujsie.setLayoutY(primaryStage.getHeight() - 250);
		zalogujsie.setLayoutX(iv1.getLayoutX());
		zalogujsie.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					root.getChildren().clear();
					MainMenu.wyswietlmenu(root, primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Button zaresie = new Button();
		zaresie.setGraphic(iv3);
		zaresie.resize(45, 45);
		zaresie.setLayoutY(primaryStage.getHeight() - 200);
		zaresie.setLayoutX((iv1.getLayoutX() + iv1.getFitWidth()) - iv3.getFitWidth());
		zaresie.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					root.getChildren().clear();
					Rejestracja.wyswietlmenu(root, primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Text sig_in = new Text("sign in...");
		sig_in.setStyle("-fx-font-size: 30pt;");
		sig_in.setFill(Color.WHITE);
		sig_in.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		sig_in.setLayoutY(primaryStage.getHeight() - 260 + iv2.getFitHeight());
		sig_in.setLayoutX((iv2.getLayoutX() + iv2.getFitWidth() + 10));

		Text register_text = new Text("register...");
		register_text.setStyle("-fx-font-size: 30pt;");
		register_text.setFill(Color.WHITE);
		register_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		register_text.setLayoutY(primaryStage.getHeight() - 210 + iv3.getFitHeight());
		register_text.setLayoutX(sig_in.getLayoutX() + 10);

		Duration czas = new Duration(2000);
		TranslateTransition translateTransition = new TranslateTransition(czas, iv1);
		translateTransition.setByY(-((primaryStage.getHeight() - iv1.getFitHeight()) / 2));
		translateTransition.setAutoReverse(false);
		translateTransition.play();

		TextArea login = new TextArea();
		login.setPromptText("l0gin");
		login.resize(iv1.getFitWidth(), 30);
		login.setLayoutY(((primaryStage.getHeight() - iv1.getFitHeight()) / 2));
		// login.setLayoutX(((primaryStage.getWidth()-iv1.getFitWidth())/2));
		login.setLayoutX(0 - login.getWidth());
		// login.setLayoutY(primaryStage.getHeight());
		TranslateTransition translateTransition1 = new TranslateTransition(czas, login);
		translateTransition1.setByX(((primaryStage.getWidth() - iv1.getFitWidth()) / 2) + login.getWidth());
		translateTransition1.setAutoReverse(false);
		translateTransition1.play();

		PasswordField password = new PasswordField();
		password.setPromptText("pa55word");
		password.setLayoutY(((primaryStage.getHeight() - iv1.getFitHeight()) / 2) + 50);
		// password.setLayoutX(((primaryStage.getWidth()-iv1.getFitWidth())/2));
		password.setLayoutX(primaryStage.getWidth());
		password.resize(iv1.getFitWidth(), 30);
		TranslateTransition translateTransition2 = new TranslateTransition(czas, password);
		translateTransition2.setByX(-((primaryStage.getWidth() - iv1.getFitWidth()) / 2) - password.getWidth());
		translateTransition2.setAutoReverse(false);
		translateTransition2.play();

		FadeTransition ft = new FadeTransition(czas, zalogujsie);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setAutoReverse(true);
		ft.play();

		FadeTransition ft2 = new FadeTransition(czas, zaresie);
		ft2.setFromValue(0);
		ft2.setToValue(1);
		ft2.setAutoReverse(true);
		ft2.play();
		FadeTransition ft3 = new FadeTransition(czas, sig_in);
		ft3.setFromValue(0);
		ft3.setToValue(1);
		ft3.setAutoReverse(true);
		ft3.play();

		FadeTransition ft1 = new FadeTransition(czas, register_text);
		ft1.setFromValue(0);
		ft1.setToValue(1);
		ft1.setAutoReverse(true);
		ft1.play();

		translateTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

			}
		});

		root.getChildren().add(iv1);
		root.getChildren().add(zalogujsie);
		root.getChildren().add(sig_in);
		root.getChildren().add(register_text);

		root.getChildren().add(zaresie);
		root.getChildren().add(login);
		root.getChildren().add(password);

	}
}
