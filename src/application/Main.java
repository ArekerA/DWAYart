package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import client.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//

public class Main extends Application implements Runnable {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1200, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("DWAY");
			primaryStage.setResizable(false);
			Pomocnicza.dodajobrazy(0);
			Rzad.przypiszzdjecia();
			OknoLogowania.oknologowania(primaryStage, root);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		//Server.main(args);
		Client.main(args);
		launch(args);
		//test();
		
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("dziala");
		}
	}
	public static void test()
	{
		int port = 755;
		System.out.println("Pobieranie Startv2");

	    try {
			Socket socket = new Socket("127.0.0.1", port);
		    File myFile = new File("C:\\Koala.jpg");
		    /*static int count;*/
		    byte[] buffer = new byte[(int) myFile.length()];
		    FileInputStream fis = new FileInputStream(myFile);
		    BufferedInputStream in = new BufferedInputStream(fis);
		    OutputStream out = null;
				in.read(buffer,0,buffer.length);
		    out = socket.getOutputStream();
		    System.out.println("Sending files");
		    out.write(buffer,0, buffer.length);
		    out.flush();
		    out.close();
		    in.close();
		    socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Finished sending");
	}
}
