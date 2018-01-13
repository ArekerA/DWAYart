package serwer;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class SerwerThread extends Thread {
	Socket mySocket;
	public SerwerThread(Socket socket){
		super();
		mySocket = socket;
	}

	public void run()
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
			String str;
			while (!(str = in.readLine()).equals("exit")) {
				System.out.println(mySocket.getInetAddress() + " : " + str);
				String[] data = str.split(",");
				OutputStream outputStream = mySocket.getOutputStream();
		        ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
				switch (data[0]) {
				case "getPictures":
					System.out.println("zaczynam getPictures");
			        objOutputStream.writeObject(JDBC.getPictures(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]));
		            objOutputStream.flush();
					System.out.println("koñczê getPictures");
					break;
				case "login":
					System.out.println("zaczynam login");
			        objOutputStream.writeObject(JDBC.login(data[1], data[2]));
		            objOutputStream.flush();
					System.out.println("koñczê login");
					break;
				case "getUser":
					System.out.println("zaczynam getUser");
			        objOutputStream.writeObject(JDBC.getUser(Integer.parseInt(data[1])));
		            objOutputStream.flush();
					System.out.println("koñczê getUser");
					break;
				case "getPicture":
					System.out.println("zaczynam getPicture");
			        objOutputStream.writeObject(JDBC.getPicture(Integer.parseInt(data[1])));
		            objOutputStream.flush();
					System.out.println("koñczê getPicture");
					break;
				case "getComents":
					System.out.println("zaczynam getComents");
			        objOutputStream.writeObject(JDBC.getComents(Integer.parseInt(data[1])));
		            objOutputStream.flush();
					System.out.println("koñczê getComents");
					break;
				case "getTags":
					System.out.println("zaczynam getTags");
			        objOutputStream.writeObject(JDBC.getTags(Integer.parseInt(data[1])));
		            objOutputStream.flush();
					System.out.println("koñczê getTags");
					break;

				default:
					break;
				}
			}
			mySocket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
