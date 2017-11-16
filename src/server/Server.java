package server;

import java.io.IOException;

public class Server {

	public static void main(String[] args) throws IOException, InterruptedException {
		(new Thread(new Polaczenie())).start();
	}

}
