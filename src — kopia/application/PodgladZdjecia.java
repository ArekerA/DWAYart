package application;

import java.awt.MouseInfo;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import data.Coment;
import data.Favorite;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PodgladZdjecia {
	
	public static Coment komentarz(TextField addComent , int i)
	{
		 Coment c1 = new Coment(Static.user, addComent.getText(), new Date() ,i);
		 return c1;
	}
	public static Favorite like(int i)
	{
		Favorite c1 = new Favorite(Static.user.getId(), new Date(), i);
		 return c1;
	}
	
	public static Text inicjalkomentow(int i)
	{
		ArrayList<Coment> c = new ArrayList<Coment>(0);
		c = getComents(i);

		Text cc = new Text(c.toString());
		if (c.size() != 0) {
			String z = "";
			for (int i1 = 0; i1 < c.size(); i1++) {
				z = z + c.get(i1).getDate() + " " + c.get(i1).getAuthor() + ":\n" + c.get(i1).getText() + "\n\n";
				
			}
			cc.setWrappingWidth(250);
			cc.setText(z);
		} else {
			cc.setText("Jeszcze nie ma komentarzy, Twój mo¿e byæ pierwszy.");
		}
		cc.setStyle("-fx-font-size: 15pt;");
		cc.setFill(Color.BLACK);
		cc.setWrappingWidth(295);
		return cc;
	}
	public static void pokliku(int i) {

		Stage primaryStage = new Stage();
		BorderPane root = new BorderPane();
		primaryStage.setResizable(false);
	
		/// SKALOWANIE OBRAZU
		ImageView iv = new ImageView(Rzad.ivy.get(i));
	
		iv.setFitHeight(Pomocnicza.getObrazy().get(i).getImage().getHeight());
		iv.setFitWidth(Pomocnicza.getObrazy().get(i).getImage().getWidth());
		if (iv.getFitHeight() > iv.getFitWidth()) // Sprawdzamy czy zdjêcie jest szersze czy wy¿sze i w
													// zale¿noœci od tego ustawiamy i przystosowujemy
		{
			double k;
			if (iv.getFitHeight() > 650) {
				iv.setFitHeight(650);
			}
			if (iv.getFitHeight() < 350) {
				iv.setFitHeight(350);
			}
			k = Pomocnicza.getObrazy().get(i).getImage().getHeight() / iv.getFitHeight();
			iv.setFitWidth(Pomocnicza.getObrazy().get(i).getImage().getWidth() / k);
			iv.setLayoutY(20);
			iv.setLayoutX(20);
		} else {
			double k;
			if (iv.getFitWidth() > 800) {
				iv.setFitWidth(800);
			}
			if (iv.getFitWidth() < 400) {
				iv.setFitWidth(400);
			}
			k = Pomocnicza.getObrazy().get(i).getImage().getWidth() / iv.getFitWidth();
			iv.setFitHeight(Pomocnicza.getObrazy().get(i).getImage().getHeight() / k);
			iv.setLayoutX(20);
			iv.setLayoutY(20);
		}
		///////// TEXT
		System.out.println(Pomocnicza.p.get(i).getFavorites());

		Text title = new Text("Title");
		title.setText("" + Pomocnicza.p.get(i).getTitle());
		title.setVisible(true);
		title.setLayoutX(30);
		title.setLayoutY(50);
		title.setStyle("-fx-font-size: 25pt;");
		title.setFill(Color.WHITE);
		title.toFront();
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		title.setEffect(r);
		title.setVisible(false);

		Text desc = new Text("Desc");
		desc.setText("" + Pomocnicza.p.get(i).getDescription());
		desc.setVisible(true);
		desc.setStrokeWidth(iv.getImage().getWidth());
		desc.setLayoutX(30);
		desc.setLayoutY(150);
		desc.setStyle("-fx-font-size: 15pt;");
		desc.setFill(Color.WHITE);
		desc.toFront();
		desc.setTextAlignment(TextAlignment.JUSTIFY);
		desc.setWrappingWidth(iv.getFitWidth() - 20);
		desc.setEffect(r);
		desc.setVisible(false);

		Text size = new Text("Desc");
		size.setText("Size: " + (int) iv.getFitWidth() + " x " + (int) iv.getFitHeight() + "  Author: "
				+ Pomocnicza.p.get(i).getAuthor());
		size.setVisible(true);
		size.setStrokeWidth(iv.getImage().getWidth());
		size.setLayoutX(30);
		size.setLayoutY(iv.getFitHeight() - 20);
		size.setStyle("-fx-font-size: 10pt;");
		size.setFill(Color.WHITE);
		size.toFront();
		size.setTextAlignment(TextAlignment.JUSTIFY);
		size.setWrappingWidth(iv.getFitWidth() - 20);
		size.setEffect(r);
		size.setVisible(false);

		Text kom = new Text("Coments");
		kom.setText("Coments:");
		kom.setLayoutX(iv.getFitWidth() + 30);
		kom.setLayoutY(50);
		kom.setStyle("-fx-font-size: 30pt;");
		kom.setFill(Color.WHITE);
		
		ImageView likeiv=new ImageView();
		likeiv.setImage(new Image("img/serce.png"));
		Button like=new Button();
		like.setGraphic(likeiv);
		like.setLayoutX(iv.getFitWidth()*0.5);
		like.setLayoutY(iv.getFitHeight()*0.5);
		like.setOpacity(0.3);
		like.setVisible(false);
		like.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) { // LIKE
				try {
					
					if(like.getOpacity()==1) {
					FadeTransition ft = new FadeTransition(new Duration(300), like);
					ft.setFromValue(like.getOpacity());
					ft.setToValue(0);
					ft.setAutoReverse(false);
					ft.play();
					ft.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							FadeTransition ft1 = new FadeTransition(new Duration(300), like);
							System.out.println("UNLIKE");
								ft1.setFromValue(like.getOpacity());
								ft1.setToValue(0.3);
								ft1.setAutoReverse(true);
								ft1.play();
								
						}
					});
					}
					else   // UNlike
					{
						
						FadeTransition ft = new FadeTransition(new Duration(300), like);
						ft.setFromValue(like.getOpacity());
						ft.setToValue(0);
						ft.setAutoReverse(false);
						ft.play();
						ft.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								System.out.println("LIKE");
								
								FadeTransition ft1 = new FadeTransition(new Duration(300), like);
								ft1.setFromValue(iv.getOpacity());
								ft1.setToValue(1);
								ft1.setAutoReverse(false);
								ft1.play();			
								try {
								      
									 
							    	 int port = 756;
									Socket socket = new Socket("127.0.0.1", port);
									System.out.println("Dodaje Like");
									socket.setTcpNoDelay(true);
									OutputStream outputStream = socket.getOutputStream();
									ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
									objOutputStream.writeObject(like(Pomocnicza.getObrazy().get(i).getId()));
									objOutputStream.flush();
									socket.close();
							      }
							      catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
							      }}
						});
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		root.getChildren().add(like);

		///// SCROLL PANEL KOMENTARZE

		

		final ScrollPane sp = new ScrollPane();
		sp.setVmax(440);
		sp.setLayoutX(iv.getFitWidth() + 35);
		sp.setLayoutY(65);
		sp.setVisible(true);
		sp.resize(300, iv.getFitHeight() - 135);
		sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		sp.setContent(inicjalkomentow(Pomocnicza.getObrazy().get(i).getId()));
		
		
		TextField AddComent = new TextField();
		AddComent.setPromptText("Po napisaniu komentarza wciœnij ENTER");
		AddComent.resize(300, iv.getFitHeight() - sp.getHeight()-50);
		AddComent.setLayoutX(iv.getFitWidth() + 30);
		AddComent.setLayoutY(sp.getHeight()+70);
		
		
		AddComent.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER && !AddComent.getText().equals(new String(""))) {
		      try {
			      
			 
		    	  int port = 754;
				Socket socket = new Socket("127.0.0.1", port);
				System.out.println("Dodaje Komentarz");
				socket.setTcpNoDelay(true);
				OutputStream outputStream = socket.getOutputStream();
				ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
				objOutputStream.writeObject(komentarz(AddComent,Pomocnicza.getObrazy().get(i).getId()));
				objOutputStream.flush();
				socket.close();
		      }
		      catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		      }
		      AddComent.clear();
		      sp.setContent(inicjalkomentow(Pomocnicza.getObrazy().get(i).getId()));
			   
		      
		   }
		});

	
// Static.user.getId() , AddComent.getText(), i
		//// WYKONANIE
		
		like.setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				try {
					//iv.setOpacity(iv.getOpacity());
					iv.setVisible(true);
					like.setVisible(true);
					if(like.getOpacity()==0.3)
					like.setOpacity(like.getOpacity()+0.3);
					FadeTransition ft = new FadeTransition(new Duration(300), iv);
					//ft.setFromValue(iv.getOpacity());
					ft.setToValue(0.15);
					ft.setAutoReverse(true);
					ft.play();
					ft.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		like.setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				try {
					if(like.getOpacity()==0.6)
						like.setOpacity(0.3);
					iv.setOpacity(iv.getOpacity());
					iv.setVisible(true);
					FadeTransition ft = new FadeTransition(new Duration(300), iv);
					ft.setFromValue(iv.getOpacity());
					ft.setToValue(0.3);
					ft.setAutoReverse(true);
					ft.play();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}		});

		

		
		iv.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(true);
				desc.setVisible(true);
				size.setVisible(true);
				like.setVisible(true);
				like.toFront();
			}
		});

		iv.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setVisible(true);
				if(MouseInfo.getPointerInfo().getLocation().getX()>iv.getFitWidth()+primaryStage.getX())
					iv.setOpacity(1);
				else
					iv.setOpacity(iv.getOpacity());
				title.setVisible(false);
				desc.setVisible(false);
				size.setVisible(false);
				like.setVisible(false);
			}
		});

		title.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(true);
				desc.setVisible(true);
				size.setVisible(true);
				iv.toBack();
			}
		});

		title.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(false);
				desc.setVisible(false);
				size.setVisible(false);
				iv.toFront();
			}
		});

		desc.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(true);
				desc.setVisible(true);
				size.setVisible(true);
				iv.toBack();
			}
		});

		desc.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(false);
				desc.setVisible(false);
				size.setVisible(false);
				iv.toFront();
			}
		});

		size.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(true);
				desc.setVisible(true);
				size.setVisible(true);
				iv.toBack();
			}
		});

		size.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				title.setVisible(false);
				desc.setVisible(false);
				size.setVisible(false);
				iv.toFront();
			}
		});

		///// SCENA + ROOT
		Scene scene = new Scene(root, iv.getFitWidth() + 360, iv.getFitHeight() + 40);
		primaryStage.setTitle(Pomocnicza.p.get(i).getTitle());
		scene.getStylesheets().add("application/window.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		root.getChildren().add(iv);
		root.getChildren().add(title);
		root.getChildren().add(desc);
		root.getChildren().add(size);
		root.getChildren().add(kom);
		root.getChildren().add(sp);
		root.getChildren().add(AddComent);
	}
	public static ArrayList<Coment> getComents(int z) {
		try {
			int port = 752;
			System.out.println("Pobieranie Startv2");
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "getComents,"+z;		// Do serwera  Switch; getComents,2

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();

			System.out.println("Pobieranie Start");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			ArrayList<Coment> p = (ArrayList<Coment>) objInputStream.readObject();
			System.out.println("Pobieranie Koniec");
			socket.close();
			return p;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
