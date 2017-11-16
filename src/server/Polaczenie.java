package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
