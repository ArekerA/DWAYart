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
		//Client.main(args);
		//launch(args);
		test();
		
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("dziala");
		}
	}
	public static void test()
	{
		FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
		int port = 755;
		System.out.println("Pobieranie Startv2");

	    try {
			Socket socket = new Socket("127.0.0.1", port);
		    File myFile = new File("C:\\Users\\Marlena\\Pictures\\LG x-power\\20161014_114457.jpg");
		    /*static int count;*/
	          byte [] mybytearray  = new byte [(int)myFile.length()];
	          byte [] mybytearray2  = new byte [5];
	          
	          int i = 2045;
	          //===============
	          mybytearray2[0] = (byte) (i&0xFF);
	          mybytearray2[1] = (byte) ((i>>8)&0xFF);
	          mybytearray2[2] = (byte) ((i>>16)&0xFF);
	          mybytearray2[3] = (byte) ((i>>24)&0xFF);
	          //==============
	          mybytearray2[4] = 1;
	          
	          fis = new FileInputStream(myFile);
	          bis = new BufferedInputStream(fis);
	          bis.read(mybytearray,0,mybytearray.length);
	          os = socket.getOutputStream();
	          System.out.println("Sending C:\\Koala.jpg(" + mybytearray.length + " bytes)");
	          os.write(mybytearray2,0,mybytearray2.length);
	          os.write(mybytearray,0,mybytearray.length);
	          os.flush();
		    socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Finished sending");
	}
}
