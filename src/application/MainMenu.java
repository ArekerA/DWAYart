package application;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class MainMenu {

	static void wyswietlmenu(BorderPane root, Stage primaryStage) {
		root.getChildren().clear();

		Image logodway = new Image("logo-white.png");
		Image homebtt = new Image("home.png");
		Image favbtt = new Image("fav.png");
		Image bestbtt = new Image("best.png");
		Image profilebtt = new Image("profile.png");
		Image addbtt = new Image("add.png");
		Image logoutbtt = new Image("logout.png");

		Point2D point = new Point2D(15, primaryStage.getHeight() * 0.13);
		/*
		int i = 0;
		while (i < Rzad.getIvy().size()) {
			Rzad.wyswietlZdjecie(point, Rzad.getIvy().get(i), root, 0, primaryStage);
			if (Rzad.wyswietlZdjecie(point, Rzad.getIvy().get(i), root, 0, primaryStage).getX() < primaryStage
					.getWidth()) {
				root.getChildren().add(Rzad.getIvy().get(i));
				// Rzad.wyswietlZdjecie(point,Rzad.getIvy().get(i),root,0,primaryStage);
				point = Rzad.wyswietlZdjecie(point, Rzad.getIvy().get(i), root, 0, primaryStage);
				i++;
				System.out.println(point.getX());
			} else {
				i--;
				point = new Point2D(15, point.getY() + primaryStage.getHeight() * 0.23 + 10);
				i++;
			}
		}*/
		
		Rzad.wyswietlanierzedu(point, root, Rzad.ustalanieswiatla(primaryStage));
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

		Button add1 = new Button();
		add1.setGraphic(add);
		add1.setLayoutY(36);
		add1.setLayoutX(primaryStage.getWidth());

		Button logout1 = new Button();
		logout1.setGraphic(logout);
		logout1.setLayoutY(36);
		logout1.setLayoutX(primaryStage.getWidth());
		logout1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					Main.start(primaryStage);
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

		root.getChildren().add(iv1);
		root.getChildren().add(home1);
		root.getChildren().add(fav1);
		root.getChildren().add(best1);
		root.getChildren().add(profile1);
		root.getChildren().add(add1);
		root.getChildren().add(logout1);
	}
}
