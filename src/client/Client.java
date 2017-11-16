package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket=new Socket();
		InetSocketAddress sa=new InetSocketAddress("127.0.0.1", 1324);
		socket.connect(sa);
		if(socket.isConnected())
			System.out.println("Uzyskano Po³¹czenie z "+sa);
			System.out.println("Zaraz zostanie uruchomiona aplikacja");
			Thread.sleep(2000);
	}

}
