package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Zegar {

	public static void dodajzegar(Stage primaryStage, BorderPane root) {
		// long endTime = 300;
		Label timeLabel = new Label();
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
			final long diff = System.currentTimeMillis();
			if (diff < 0) {
				// timeLabel.setText( "00:00:00" );
				timeLabel.setText(timeFormat.format(0));
			} else {
				timeLabel.setText(timeFormat.format(diff));
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeLabel.resize(100,75);
		timeLabel.setTextFill(Color.WHITE);
		timeLabel.setStyle("-fx-font-size: 20pt;");
		timeLabel.setLayoutX(primaryStage.getWidth() - 150);
		timeLabel.setLayoutY(0);

		timeline.play();
		root.getChildren().add(timeLabel);
	}

}
