package serwer;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import data.Coment;
import data.SuperUser;


public class SerwerThread extends Thread {
	Socket mySocket;
	public SerwerThread(Socket socket){
		super();
		mySocket = socket;
	}

	public void run()
	{
		try {
			if(mySocket.getLocalPort() == 752)
			{
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
					case "getPicturesFav":
						System.out.println("zaczynam getPicture");
				        objOutputStream.writeObject(JDBC.getPictures(Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3])));
			            objOutputStream.flush();
						System.out.println("koÅ„czÄ™ getPicture");
						break;
					case "getComents":
						System.out.println("zaczynam getComents");
				        objOutputStream.writeObject(JDBC.getComents(Integer.parseInt(data[1])));
			            objOutputStream.flush();
						System.out.println("koñczê getComents");
						break;
					case "getFavorites":
						System.out.println("zaczynam getFavorites");
				        objOutputStream.writeObject(JDBC.getFavorites(Integer.parseInt(data[1])));
			            objOutputStream.flush();
						System.out.println("koñczê getFavorites");
						break;
					case "isFavorite":
						System.out.println("zaczynam isFavorite");
				        objOutputStream.writeObject(JDBC.isFavorite(Integer.parseInt(data[1]),Integer.parseInt(data[2])));
			            objOutputStream.flush();
						System.out.println("koñczê isFavorite");
						break;
					case "countFavorites":
						System.out.println("zaczynam countFavorites");
				        objOutputStream.writeObject(JDBC.countFavorites(Integer.parseInt(data[1])));
			            objOutputStream.flush();
						System.out.println("koñczê countFavorites");
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
			}
			else if(mySocket.getLocalPort() == 753)
			{
				
				mySocket.setTcpNoDelay(true);
				System.out.println("Rozpoczynam tworzenie u¿ytkownika");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
	            SuperUser p = (SuperUser) objInputStream.readObject();
	            JDBC.addUser(p.getLogin(), p.getName(), p.getEmail(), p.getPassword(), p.getSex(), p.getType());
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
				String str = "true";
				out.println(str);
				out.flush();
				System.out.println("Koñczê resjestracje");
			}
			else if(mySocket.getLocalPort() == 754)
			{
				mySocket.setTcpNoDelay(true);
				System.out.println("Rozpoczynam dodawanie komentarzy");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
	            Coment p = (Coment) objInputStream.readObject();
	            JDBC.addComent(p.getText(), p.getPicture(), p.getAuthor().getId());
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
				String str = "true";
				out.println(str);
				out.flush();
				System.out.println("Koñczê dodawanie komentarzy");
			}
			else if(mySocket.getLocalPort() == 755)
			{
				byte[] contents = new byte[10000];
		        FileOutputStream fos = new FileOutputStream("E:\\xampp\\htdocs\\img\\data2.bin");
		        BufferedOutputStream bos = new BufferedOutputStream(fos);
		        InputStream is = mySocket.getInputStream();
		        
		        //No of bytes read in one read() call
		        int bytesRead = 0; 
		        
		        while((bytesRead=is.read(contents))!=-1)
		            bos.write(contents, 0, bytesRead); 
		        bos.flush(); 
			}
	        
	        System.out.println("File saved successfully!");
		} catch (Exception e) {
			System.err.println(e);
		}
		finally
		{
			try {
				mySocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
