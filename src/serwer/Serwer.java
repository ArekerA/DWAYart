package serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.OknoLogowania;
import application.Pomocnicza;
import application.Rzad;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Serwer extends Application implements Runnable  {
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
			// tworzymy socket
			serverSocket = new ServerSocket(port);
			while (true) {
				// czekamy na zg�oszenie klienta ...
				Socket socket = serverSocket.accept();
				// tworzymy w�tek dla danego po��czenia i uruchamiamy go
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
