package serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Serwer extends Thread{
	int port;
	public Serwer(int port){
		super();
		this.port = port;
	}

	public void run() {
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
