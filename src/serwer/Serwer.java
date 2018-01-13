package serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Serwer extends Application{
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("DWAY");
			primaryStage.setResizable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
		int port = 752;
		JDBC.init();
		JDBC.initTest();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				(new SerwerThread(socket)).start();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
