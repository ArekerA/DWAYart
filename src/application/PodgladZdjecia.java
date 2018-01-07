package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PodgladZdjecia {

	public static void pokliku(int i) {
		Stage primaryStage = new Stage();
		BorderPane root = new BorderPane();

		ImageView iv = new ImageView(Rzad.ivy.get(i));
		iv.setFitWidth(500/iv.getImage().getHeight()*iv.getImage().getWidth());
		iv.setFitHeight(500);
		iv.setPreserveRatio(true);
		iv.setLayoutX(20);
		iv.setLayoutY(20);
		root.getChildren().add(iv);

		Text title = new Text("Title");
		title.setText("Tekst");
		title.setVisible(true);
		title.setLayoutX(30);
		title.setLayoutY(50);
		title.setStyle("-fx-font-size: 30pt;");
		title.setFill(Color.WHITE);		
		title.toFront();
		Reflection r = new Reflection();
		r.setFraction(0.7f);		 
		title.setEffect(r);
		
		Text desc = new Text("Desc");
		desc.setText("To jest króciutki opis obrazeczka na którym siê znajdujemy");
		desc.setVisible(true);
		desc.setStrokeWidth(iv.getImage().getWidth());
		desc.setLayoutX(30);
		desc.setLayoutY(150);
		desc.setStyle("-fx-font-size: 20pt;");
		desc.setFill(Color.WHITE);
		desc.toFront();
		desc.setTextAlignment(TextAlignment.JUSTIFY);
		desc.setWrappingWidth(iv.getFitWidth()-20);
		desc.setEffect(r);
		
		Text kom = new Text("Coments");
		kom.setText("Coments:");
		kom.setLayoutX(iv.getFitWidth()+30);
		kom.setLayoutY(50);
		kom.setStyle("-fx-font-size: 30pt;");
		kom.setFill(Color.WHITE);
		
		
		final ScrollPane sp = new ScrollPane();
		sp.setVmax(440);
        sp.setLayoutX(iv.getFitWidth()+30);
        sp.setLayoutY(70);
        sp.setVisible(true);
		sp.resize(390, 430);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
      /*  sp.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    fileName.setText(imageNames[(new_val.intValue() - 1)/100]);
            }
        });*/
		
		root.getChildren().add(kom);
		root.getChildren().add(sp);
		
		
		iv.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(0.3);
				root.getChildren().add(title);
				root.getChildren().add(desc);
				iv.toBack();

			}
		});

		iv.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				iv.setOpacity(1);
				iv.toFront();
				root.getChildren().add(title);
				root.getChildren().remove(desc);
			}
		});

		Scene scene = new Scene(root, iv.getFitWidth() + 440, iv.getFitHeight() + 40);
		scene.getStylesheets().add("application/window.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
