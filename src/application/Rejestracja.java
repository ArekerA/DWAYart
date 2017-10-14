package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Rejestracja {

	static void wyswietlmenu(BorderPane root, Stage primaryStage) {
		root.getChildren().clear();
		
		Image logodway = new Image("logo-red.png");
		Image unknow = new Image("unknow.png");
		Image choose = new Image("choose.png");
		Image conf = new Image("confirm.png");
		
		ImageView iv1 = new ImageView();
		iv1.setFitHeight(34);
		iv1.setFitWidth(66);
		iv1.setLayoutY(20);
		iv1.setLayoutX(primaryStage.getWidth());
		iv1.setImage(logodway);
		
		ImageView iv3 = new ImageView();
		iv3.setFitHeight(45);
		iv3.setFitWidth(45);
		/*iv3.setLayoutY(primaryStage.getHeight()*0.15);
		iv3.setLayoutX(primaryStage.getWidth()-iv2.getFitWidth()-200);*/
		iv3.setImage(choose);
		
		ImageView iv2 = new ImageView();
		iv2.setFitHeight(primaryStage.getHeight()/2);
		iv2.setFitWidth(primaryStage.getHeight()/2);
		iv2.setLayoutY(primaryStage.getHeight()*0.15);
		iv2.setLayoutX(primaryStage.getWidth()-iv2.getFitWidth()-200);
		iv2.setImage(unknow);
		
		ImageView iv4 = new ImageView();
		iv4.setFitHeight(45);
		iv4.setFitWidth(45);
		/*iv3.setLayoutY(primaryStage.getHeight()*0.15);
		iv3.setLayoutX(primaryStage.getWidth()-iv2.getFitWidth()-200);*/
		iv4.setImage(conf);
		
		Duration czas = new Duration(100);
		TranslateTransition translateTransition = new TranslateTransition(czas, iv1);
		translateTransition.setByX(-primaryStage.getWidth() + 10);
		translateTransition.setAutoReverse(false);
		translateTransition.play();
		
		Text rejestracja = new Text("Type informations...");
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

		Text password_text = new Text("password:");
		password_text.setStyle("-fx-font-size: 20pt;");
		password_text.setFill(Color.WHITE);
		//password_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		password_text.setLayoutY(primaryStage.getHeight()*0.25);
		password_text.setLayoutX(15);
		
		Text password_text2 = new Text("confirm password:");
		password_text2.setStyle("-fx-font-size: 20pt;");
		password_text2.setFill(Color.WHITE);
		//password_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		password_text2.setLayoutY(primaryStage.getHeight()*0.35);
		password_text2.setLayoutX(15);
		
		Text email = new Text("emai1 address:");
		email.setStyle("-fx-font-size: 20pt;");
		email.setFill(Color.WHITE);
		//email.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		email.setLayoutY(primaryStage.getHeight()*0.45);
		email.setLayoutX(15);
		
		Text name = new Text("name:");
		name.setStyle("-fx-font-size: 20pt;");
		name.setFill(Color.WHITE);
		//name.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		name.setLayoutY(primaryStage.getHeight()*0.55);
		name.setLayoutX(15);
		
		Text sex = new Text("sex:");
		sex.setStyle("-fx-font-size: 20pt;");
		sex.setFill(Color.WHITE);
		//name.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		sex.setLayoutY(primaryStage.getHeight()*0.65);
		sex.setLayoutX(15);
		
		
		Text confirm = new Text("Confirm");
		confirm.setStyle("-fx-font-size: 30pt;");
		confirm.setFill(Color.WHITE);
		//login_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		confirm.setLayoutY(primaryStage.getHeight()*0.80+10);
		confirm.setLayoutX(15);

		TextArea loginarea = new TextArea();
		loginarea.setPromptText("l0gin");
		loginarea.resize(250, 30);
		loginarea.setLayoutY((primaryStage.getHeight()*0.15)+10);
		loginarea.setLayoutX(15);
		
		TextArea namearea = new TextArea();
		namearea.setPromptText("nam3");
		namearea.resize(250, 30);
		namearea.setLayoutY((primaryStage.getHeight()*0.55)+10);
		namearea.setLayoutX(15);
		
		PasswordField password = new PasswordField();
		password.setPromptText("pa55word");
		password.resize(250, 30);
		password.setLayoutY((primaryStage.getHeight()*0.25)+10);
		// password.setLayoutX(((primaryStage.getWidth()-iv1.getFitWidth())/2));
		password.setLayoutX(15);
		
		PasswordField passwordc = new PasswordField();
		passwordc.setPromptText("c0nfirm pa55word");
		passwordc.resize(250, 30);
		passwordc.setLayoutY((primaryStage.getHeight()*0.35)+10);
		// password.setLayoutX(((primaryStage.getWidth()-iv1.getFitWidth())/2));
		passwordc.setLayoutX(15);
		
		TextArea mailarea = new TextArea();
		mailarea.setPromptText("emai1@address.com");
		mailarea.resize(250, 30);
		mailarea.setLayoutY((primaryStage.getHeight()*0.45)+10);
		mailarea.setLayoutX(15);
		
		Button wybierzplik=new Button("Wybierz Plik");
		//wybierzplik.resize(primaryStage.getWidth()/5,primaryStage.getHeight()/10);
		wybierzplik.setLayoutX((iv2.getLayoutX()+iv2.getFitWidth()/2)-iv3.getFitWidth()/2);
		wybierzplik.setLayoutY(iv2.getLayoutY()+iv2.getFitHeight()+40);
		wybierzplik.setGraphic(iv3);
		wybierzplik.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(Wyborzdjecia.wyborzdysku(primaryStage)!=null)
					{
						iv2.setImage(Wyborzdjecia.wyborzdysku(primaryStage));
						root.getChildren().remove(iv2);
						root.getChildren().add(iv2);
					}
					//System.out.println(iv2.getImage().impl_getUrl());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Button confirmdata=new Button("Wybierz Plik");
		//wybierzplik.resize(primaryStage.getWidth()/5,primaryStage.getHeight()/10);
		confirmdata.setLayoutX(265-iv4.getFitWidth());
		confirmdata.setLayoutY(primaryStage.getHeight()*0.8);
		confirmdata.setGraphic(iv4);

		ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
			    "Male", "Female", "Just artist...")
			);
		cb.resize(250, 30);
		cb.setLayoutY((primaryStage.getHeight()*0.65)+10);
		cb.setLayoutX(15);

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
		root.getChildren().add(password_text);
		root.getChildren().add(password_text2);
		root.getChildren().add(login_text);
		root.getChildren().add(email);
		root.getChildren().add(name);
		root.getChildren().add(loginarea);
		root.getChildren().add(namearea);
		root.getChildren().add(password);
		root.getChildren().add(passwordc);
		root.getChildren().add(mailarea);
		root.getChildren().add(rejestracja);
		root.getChildren().add(iv2);
		root.getChildren().add(wybierzplik);
		root.getChildren().add(sex);
		root.getChildren().add(cb);
		root.getChildren().add(confirm);
		root.getChildren().add(confirmdata);
		
	}
}
