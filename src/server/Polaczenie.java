package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Polaczenie implements Runnable{

	@Override
	public void run() {
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(1324);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Socket przychodzace=ss.accept();
			Scanner in=new Scanner(przychodzace.getInputStream());
			System.out.println("Odpowiadam z servera "+in.nextLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
