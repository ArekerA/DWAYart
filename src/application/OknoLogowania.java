package application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;

import data.SuperUser;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OknoLogowania {

	public static void oknologowania(Stage primaryStage, BorderPane root) {
	
		Zegar.dodajzegar(primaryStage, root);

		////////// IMAGE VIEW
		Image logodway = new Image("img/logo-white.png");

		ImageView iv1 = new ImageView();
		iv1.setFitHeight(165);
		iv1.setFitWidth(330);
		iv1.setLayoutY(((primaryStage.getHeight() - iv1.getFitHeight()) / 2));
		iv1.setLayoutX(((primaryStage.getWidth() - iv1.getFitWidth()) / 2));
		iv1.setImage(logodway);

		Image log_in = new Image("img/login.png");

		ImageView iv2 = new ImageView();
		iv2.setImage(log_in);
		iv2.setFitHeight(45);
		iv2.setFitWidth(45);
		iv2.setLayoutY(primaryStage.getHeight() - 250);
		iv2.setLayoutX(iv1.getLayoutX());

		Image register = new Image("img/register.png");

		ImageView iv3 = new ImageView();
		iv3.setImage(register);
		iv3.setFitHeight(45);
		iv3.setFitWidth(45);
		iv3.setLayoutY(primaryStage.getHeight() - 200);
		iv3.setLayoutX((iv1.getLayoutX() + iv1.getFitWidth()) - iv3.getFitWidth());
		
		/////////// TEXT
		Text sig_in = new Text("...sign in");
		sig_in.setStyle("-fx-font-size: 30pt;");
		sig_in.setFill(Color.WHITE);
		sig_in.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		sig_in.setLayoutY(primaryStage.getHeight() - 260 + iv2.getFitHeight());
		sig_in.setLayoutX((iv2.getLayoutX() + iv2.getFitWidth() + 40));

		Text register_text = new Text("register...");
		register_text.setStyle("-fx-font-size: 30pt;");
		register_text.setFill(Color.WHITE);
		register_text.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		register_text.setLayoutY(primaryStage.getHeight() - 210 + iv3.getFitHeight());
		register_text.setLayoutX(sig_in.getLayoutX() + 25);
		
		Text error = new Text("B酬dny login lub has硂");
		error.setStyle("-fx-font-size: 30pt;");
		error.setFill(Color.RED);
		error.resize(iv1.getFitWidth() - iv2.getFitWidth() - 25, iv2.getFitHeight());
		error.setLayoutY(primaryStage.getHeight() - 150 + iv2.getFitHeight());
		error.setLayoutX((iv2.getLayoutX() + iv2.getFitWidth() -55 ));
		error.setVisible(false);
		
		////////// TEXT AREA
		TextField login = new TextField();
		login.setPromptText("l0gin");
		login.resize(iv1.getFitWidth(), 30);
		login.setLayoutY(((primaryStage.getHeight() - iv1.getFitHeight()) / 2));
		login.setLayoutX(0 - login.getWidth());

		PasswordField password = new PasswordField();
		password.setPromptText("pa55word");
		password.setLayoutY(((primaryStage.getHeight() - iv1.getFitHeight()) / 2) + 50);
		password.setLayoutX(primaryStage.getWidth());
		password.resize(iv1.getFitWidth(), 30);

		/////////// BUTTONY
		Button zalogujsie = new Button();
		zalogujsie.setGraphic(iv2);
		zalogujsie.resize(45, 45);
		zalogujsie.setLayoutY(primaryStage.getHeight() - 250);
		zalogujsie.setLayoutX(iv1.getLayoutX());

		Button zaresie = new Button();
		zaresie.setGraphic(iv3);
		zaresie.resize(45, 45);
		zaresie.setLayoutY(primaryStage.getHeight() - 200);
		zaresie.setLayoutX((iv1.getLayoutX() + iv1.getFitWidth()) - iv3.getFitWidth());

		/////////// Wykonanie
		zalogujsie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					try {
						int port = 752;
						
						Socket socket = new Socket("127.0.0.1", port);
						PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						
						String str = "login,"+login.getText()+","+sha256(password.getText());
						
						socket.setTcpNoDelay(true);
						out.println(str);
						out.flush();
						
						System.out.println("rozpoczynam odbi贸r");
						InputStream inputStream = socket.getInputStream();
						ObjectInputStream objInputStream = null;
						objInputStream = new ObjectInputStream(inputStream);
						
			            Static.user = (SuperUser) objInputStream.readObject();
			            
						System.out.println("ko艅cz臋 odbi贸r");
						socket.close();
					} catch (Exception e) {
						System.err.println(e);
					}
					if(Static.user != null)		///// ZMIANA NA CZAS PRAC BO LOGOWANIE DENERWUJE
					{
						error.setVisible(false);
						root.getChildren().clear();
						MainMenu.wyswietlmenu(root, primaryStage);
					}
					else
					{
						error.setVisible(true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	sig_in.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
		try {
			try {
				int port = 1324;
				
				Socket socket = new Socket("127.0.0.1", port);
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String str = "login,"+login.getText()+","+sha256(password.getText());
				
				socket.setTcpNoDelay(true);
				out.println(str);
				out.flush();
				
				System.out.println("rozpoczynam odbi贸r");
				InputStream inputStream = socket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
				
	            Static.user = (SuperUser) objInputStream.readObject();
	            
				System.out.println("ko艅cz臋 odbi贸r");
				socket.close();
			} catch (Exception w) {
				System.err.println(w);
			}
			if(Static.user != null)		///// ZMIANA NA CZAS PRAC BO LOGOWANIE DENERWUJE
			{
				error.setVisible(false);
				root.getChildren().clear();
				MainMenu.wyswietlmenu(root, primaryStage);
			}
			else
			{
				error.setVisible(true);
			}
		} catch (Exception z) {
			// TODO Auto-generated catch block
			z.printStackTrace();
		}
	});

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
		
		register_text.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			Rejestracja.wyswietlmenu(root, primaryStage);
		});

		////////// ANIMACJE
		Duration czas = new Duration(2000);
		TranslateTransition translateTransition = new TranslateTransition(czas, iv1);
		translateTransition.setByY(-((primaryStage.getHeight() - iv1.getFitHeight()) / 2));
		translateTransition.setAutoReverse(false);
		translateTransition.play();

		TranslateTransition translateTransition1 = new TranslateTransition(czas, login);
		translateTransition1.setByX(((primaryStage.getWidth() - iv1.getFitWidth()) / 2) + login.getWidth());
		translateTransition1.setAutoReverse(false);
		translateTransition1.play();

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
		root.getChildren().add(error);

	}
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
}
