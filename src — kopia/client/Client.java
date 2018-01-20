package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import server.Polaczenie;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		(new Thread(new Laczezserverem())).start();
	}

}
