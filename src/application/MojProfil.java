package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
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
		Image logodway = new Image("img/logo-red.png");
		Image unknow = new Image("img/unknow.png");
		
		ImageView iv1 = new ImageView();
		iv1.setFitHeight(34);
		iv1.setFitWidth(66);
		iv1.setLayoutY(20);
		iv1.setLayoutX(primaryStage.getWidth());
		iv1.setImage(logodway);
		
		ImageView iv2 = new ImageView();
		iv2.setFitHeight(primaryStage.getHeight()/2);
		iv2.setFitWidth(primaryStage.getHeight()/2);
		iv2.setLayoutY(primaryStage.getHeight()*0.15);
		iv2.setLayoutX(primaryStage.getWidth()-iv2.getFitWidth()-200);
		iv2.setImage(unknow);
		
		
		Duration czas = new Duration(100);
		TranslateTransition translateTransition = new TranslateTransition(czas, iv1);
		translateTransition.setByX(-primaryStage.getWidth() + 10);
		translateTransition.setAutoReverse(false);
		translateTransition.play();
		
		Text rejestracja = new Text("Welcome XYZ");
		rejestracja.setStyle("-fx-font-size: 30pt;");
		rejestracja.setFill(Color.WHITE);
		//login_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		rejestracja.setLayoutY(50);
		rejestracja.setLayoutX(primaryStage.getWidth());
		
		Text login_text = new Text("login:");
		login_text.setStyle("-fx-font-size: 20pt;");
		login_text.setFill(Color.WHITE);
		//login_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		login_text.setLayoutY(primaryStage.getHeight()*0.15);
		login_text.setLayoutX(15);
		
		Text email = new Text("emai1 address:");
		email.setStyle("-fx-font-size: 20pt;");
		email.setFill(Color.WHITE);
		//email.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		email.setLayoutY(primaryStage.getHeight()*0.25);
		email.setLayoutX(15);
		
		Text name = new Text("name:");
		name.setStyle("-fx-font-size: 20pt;");
		name.setFill(Color.WHITE);
		//name.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		name.setLayoutY(primaryStage.getHeight()*0.35);
		name.setLayoutX(15);
		
		Text sex = new Text("sex:");
		sex.setStyle("-fx-font-size: 20pt;");
		sex.setFill(Color.WHITE);
		//name.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		sex.setLayoutY(primaryStage.getHeight()*0.45);
		sex.setLayoutX(15);

		Text loginarea = new Text();
		loginarea.setStyle("-fx-font-size: 22pt;");
		loginarea.setFill(Color.WHITE);
		loginarea.setText("l0gin");
		loginarea.resize(250, 30);
		loginarea.setLayoutY((primaryStage.getHeight()*0.15)+30);
		loginarea.setLayoutX(15);

		Text namearea = new Text();
		namearea.setStyle("-fx-font-size: 22pt;");
		namearea.setFill(Color.WHITE);
		namearea.setText("nam3");
		namearea.resize(250, 30);
		namearea.setLayoutY((primaryStage.getHeight()*0.35)+30);
		namearea.setLayoutX(15);
		
		Text mailarea = new Text();
		mailarea.setText("emai1@address.com");
		mailarea.setFill(Color.WHITE);
		mailarea.setStyle("-fx-font-size: 22pt;");
		mailarea.resize(250, 30);
		mailarea.setLayoutY((primaryStage.getHeight()*0.25)+30);
		mailarea.setLayoutX(15);
		
		Text sexarea = new Text();
		sexarea.setStyle("-fx-font-size: 22pt;");
		sexarea.setFill(Color.WHITE);
		sexarea.setText("sex");
		sexarea.resize(250, 30);
		sexarea.setLayoutY((primaryStage.getHeight()*0.45)+30);
		sexarea.setLayoutX(15);
		

		TranslateTransition translateTransition1 = new TranslateTransition(czas, rejestracja);
		translateTransition1.setDelay(czas);
		translateTransition1.setByX(-primaryStage.getWidth() + 10 + iv1.getFitWidth() + 40);
		translateTransition1.setAutoReverse(false);
		translateTransition1.play();
		/*translateTransition1.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				TranslateTransition translateTransition2 = new TranslateTransition(czas, loginarea);
				translateTransition2.setByX(-primaryStage.getWidth()+15);
				translateTransition2.setAutoReverse(false);
				translateTransition2.play();
				
			}}
		);*/
		Duration czas1=new Duration(5000);
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
		
	}
}
