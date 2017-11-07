package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class MainMenu {

	static void wyswietlmenu(BorderPane root, Stage primaryStage) {

		Zegar.dodajzegar(primaryStage, root);

		Image logodway = new Image("img/logo-white.png");
		Image homebtt = new Image("img/home.png");
		Image favbtt = new Image("img/fav.png");
		Image bestbtt = new Image("img/best.png");
		Image profilebtt = new Image("img/profile.png");
		Image addbtt = new Image("img/add.png");
		Image logoutbtt = new Image("img/logout.png");

		final VBox vb = new VBox();
		vb.setVisible(true);
		vb.setLayoutX(20);
		vb.setLayoutY(80);
		vb.setSpacing(10);
		final VBox vb1 = new VBox();
		vb1.setVisible(true);
		vb1.setLayoutX(primaryStage.getWidth() * 0.33 + 20);
		vb1.setLayoutY(80);
		vb1.setSpacing(10);
		final VBox vb2 = new VBox();
		vb2.setVisible(true);
		vb2.setLayoutX(primaryStage.getWidth() * 0.66 + 20);
		vb2.setLayoutY(80);
		vb2.setSpacing(10);


		for (int i = 0; i < Rzad.skalowaneivy.size(); i++) {
			if (i % 3 == 0) {
				Rzad.wyswietlanierzedu(vb, Rzad.getSkalowaneivy().get(i));
			} else if (i % 3 == 1) {
				Rzad.wyswietlanierzedu(vb1, Rzad.getSkalowaneivy().get(i));
			} else if (i % 3 == 2) {
				Rzad.wyswietlanierzedu(vb2, Rzad.getSkalowaneivy().get(i));
			}
		}

		ImageView iv1 = new ImageView();
		iv1.setFitHeight(34);
		iv1.setFitWidth(66);
		iv1.setLayoutY(20);
		iv1.setLayoutX(primaryStage.getWidth());
		iv1.setImage(logodway);

		ImageView home = new ImageView();
		home.setImage(homebtt);
		home.setFitWidth(64);
		home.setFitHeight(64);

		ImageView fav = new ImageView();
		fav.setImage(favbtt);
		fav.setFitWidth(64);
		fav.setFitHeight(64);

		ImageView best = new ImageView();
		best.setImage(bestbtt);
		best.setFitWidth(64);
		best.setFitHeight(64);

		ImageView profile = new ImageView();
		profile.setImage(profilebtt);
		profile.setFitWidth(64);
		profile.setFitHeight(64);

		ImageView add = new ImageView();
		add.setImage(addbtt);
		add.setFitWidth(64);
		add.setFitHeight(64);

		ImageView logout = new ImageView();
		logout.setImage(logoutbtt);
		logout.setFitWidth(64);
		logout.setFitHeight(64);

		Button home1 = new Button();
		home1.setGraphic(home);
		home1.setLayoutY(36);
		home1.setLayoutX(primaryStage.getWidth());

		Button fav1 = new Button();
		fav1.setGraphic(fav);
		fav1.setLayoutY(36);
		fav1.setLayoutX(primaryStage.getWidth());

		Button best1 = new Button();
		best1.setGraphic(best);
		best1.setLayoutY(36);
		best1.setLayoutX(primaryStage.getWidth());

		Button profile1 = new Button();
		profile1.setGraphic(profile);
		profile1.setLayoutY(36);
		profile1.setLayoutX(primaryStage.getWidth());
		profile1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					MojProfil.mojprofil(root, primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Button add1 = new Button();
		add1.setGraphic(add);
		add1.setLayoutY(36);
		add1.setLayoutX(primaryStage.getWidth());
		add1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					Image temp = Wyborzdjecia.wyborzdysku(primaryStage);
					// Trzeba stworzyæ Menu osobne, w którym bêdziemy dodawaæ zdjêcie i Tagi
					// Tu elegancko trzeba za³atwiæ zapisywanie tego pliku w miejsce bin/img/ 
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

		Button logout1 = new Button();
		logout1.setGraphic(logout);
		logout1.setLayoutY(36);
		logout1.setLayoutX(primaryStage.getWidth());
		logout1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					root.getChildren().clear();
					OknoLogowania.oknologowania(primaryStage, root);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Duration czas = new Duration(100);
		TranslateTransition translateTransition = new TranslateTransition(czas, iv1);
		translateTransition.setByX(-primaryStage.getWidth() + 10);
		translateTransition.setAutoReverse(false);
		translateTransition.play();

		TranslateTransition translateTransition1 = new TranslateTransition(czas, home1);
		translateTransition1.setDelay(czas);
		translateTransition1.setByX(-primaryStage.getWidth() + 10 + iv1.getFitWidth() + 40);
		translateTransition1.setAutoReverse(false);
		translateTransition1.play();

		translateTransition1.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				TranslateTransition translateTransition2 = new TranslateTransition(czas, fav1);
				translateTransition2
						.setByX(-primaryStage.getWidth() + iv1.getFitWidth() + 20 + home.getFitWidth() + 20);
				translateTransition2.setAutoReverse(false);
				translateTransition2.play();
				translateTransition2.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						TranslateTransition translateTransition2 = new TranslateTransition(czas, best1);
						translateTransition2.setByX(-primaryStage.getWidth() + iv1.getFitWidth() + home.getFitWidth()
								+ 20 + best.getFitWidth() + 10);
						translateTransition2.setAutoReverse(false);
						translateTransition2.play();
						translateTransition2.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								TranslateTransition translateTransition2 = new TranslateTransition(czas, profile1);
								translateTransition2.setByX(-primaryStage.getWidth() + iv1.getFitWidth()
										+ home.getFitWidth() + best.getFitWidth() + 20 + profile.getFitWidth());
								translateTransition2.setAutoReverse(false);
								translateTransition2.play();
								translateTransition2.setOnFinished(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {

										TranslateTransition translateTransition3 = new TranslateTransition(czas, add1);
										translateTransition3.setByX(-primaryStage.getWidth() + iv1.getFitWidth()
												+ home.getFitWidth() + best.getFitWidth() + 10 + profile.getFitWidth()
												+ add.getFitWidth());
										translateTransition3.setAutoReverse(false);
										translateTransition3.play();
										translateTransition3.setOnFinished(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {

												TranslateTransition translateTransition4 = new TranslateTransition(czas,
														logout1);
												translateTransition4.setByX(-primaryStage.getWidth() + iv1.getFitWidth()
														+ home.getFitWidth() + best.getFitWidth() + 10
														+ profile.getFitWidth() + add.getFitWidth() - 10
														+ logout.getFitWidth());
												translateTransition4.setAutoReverse(false);
												translateTransition4.play();

											}
										});

									}
								});

							}
						});

					}
				});

			}
		});

		for (int i1 = 0; i1 < Rzad.getIvy().size(); i1++) {
			FadeTransition ft1 = new FadeTransition(czas, Rzad.getIvy().get(i1));
			ft1.setFromValue(0);
			ft1.setToValue(1);
			ft1.setAutoReverse(true);
			ft1.play();
		}

		
		ScrollBar sc = new ScrollBar();
		sc.setMin(-100);
		sc.setMax(100);
		sc.setOrientation(Orientation.VERTICAL);
		sc.resize(25, primaryStage.getHeight());
		sc.setMax(80 * Rzad.getIvy().size());
		sc.setLayoutY(0);
		sc.setLayoutX(primaryStage.getWidth() - sc.getWidth());
		sc.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				
				
				vb.setLayoutY(-new_val.doubleValue());				
				vb1.setLayoutY(-new_val.doubleValue());
				vb2.setLayoutY(-new_val.doubleValue());
				int i=0;
				while(i!=-1)
				{
					if(!vb.getChildren().get(i).equals(null))
					{
						System.out.println(vb.getChildren().get(i).getLocalToSceneTransform().getTy());
						if(!vb.getChildren().get(i+1).equals(null))
						{
							if(vb.getChildren().get(i).getLayoutY()>-69)
							vb.getChildren().get(i).setOpacity(((vb.getChildren().get(i).getLocalToSceneTransform().getTy()+(vb.getChildren().get(i+1).getLayoutY()-vb.getChildren().get(i).getLayoutY())/2)-70)*0.008);
						}
						else if(vb.getChildren().get(i).getLayoutY()>-69)
						vb.getChildren().get(i).setOpacity(vb.getChildren().get(i).getLocalToSceneTransform().getTy()*0.008);
						if(!vb1.getChildren().get(i).equals(null))
						{
							System.out.println(vb1.getChildren().get(i).getLocalToSceneTransform().getTy());
							if(!vb1.getChildren().get(i+1).equals(null))
							{
								if(vb1.getChildren().get(i).getLayoutY()>-69)
								vb1.getChildren().get(i).setOpacity(((vb1.getChildren().get(i).getLocalToSceneTransform().getTy()+(vb1.getChildren().get(i+1).getLayoutY()-vb1.getChildren().get(i).getLayoutY())/2)-70)*0.008);
							}
							else if(vb1.getChildren().get(i).getLayoutY()>-69)
							vb1.getChildren().get(i).setOpacity(vb1.getChildren().get(i).getLocalToSceneTransform().getTy()*0.008);
							if(!vb2.getChildren().get(i).equals(null))
							{
								System.out.println(vb2.getChildren().get(i).getLocalToSceneTransform().getTy());
								if(!vb2.getChildren().get(i+1).equals(null))
								{
									if(vb2.getChildren().get(i).getLayoutY()>-69)
									vb2.getChildren().get(i).setOpacity(((vb2.getChildren().get(i).getLocalToSceneTransform().getTy()+(vb2.getChildren().get(i+1).getLayoutY()-vb2.getChildren().get(i).getLayoutY())/2)-70)*0.008);
								}
								else if(vb2.getChildren().get(i).getLayoutY()>-69)
								vb2.getChildren().get(i).setOpacity(vb2.getChildren().get(i).getLocalToSceneTransform().getTy()*0.008);
							}
						}
						i++;
					}
					else
					{
						i=-1;
					}
				}
				
			}
		});

		root.getChildren().add(vb);
		root.getChildren().add(vb1);
		root.getChildren().add(vb2);
		root.getChildren().add(iv1);
		root.getChildren().add(home1);
		root.getChildren().add(fav1);
		root.getChildren().add(best1);
		root.getChildren().add(profile1);
		root.getChildren().add(add1);
		root.getChildren().add(logout1);
		root.getChildren().add(sc);
	}
}
