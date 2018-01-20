package serwer;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
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
import data.Favorite;
import data.Picture;
import data.SuperUser;


public class SerwerThread extends Thread {
	Socket mySocket;
	String sciezka = "E:\\xampp\\htdocs\\img\\";
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
					case "getUserPictures":
						System.out.println("zaczynam getPictures");
				        objOutputStream.writeObject(JDBC.getPictures(Integer.parseInt(data[1])));
			            objOutputStream.flush();
						System.out.println("koñczê getPictures");
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
					case "getUserFavorites":
						System.out.println("zaczynam getUserFavorites");
				        objOutputStream.writeObject(JDBC.getUserFavorites(Integer.parseInt(data[1])));
			            objOutputStream.flush();
						System.out.println("koñczê getUserFavorites");
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
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
				String str = ""+JDBC.addUser(p.getLogin(), p.getName(), p.getEmail(), p.getPassword(), p.getSex(), p.getType());
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
		
		else if(mySocket.getLocalPort() == 756)
		{
			mySocket.setTcpNoDelay(true);
			System.out.println("Rozpoczynam dodawanie Like");
			InputStream inputStream = mySocket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
            Favorite p = (Favorite) objInputStream.readObject();
            JDBC.addLike(p.getId_u(), p.getId_p());
            PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
			String str = "true";
			out.println(str);
			out.flush();
			System.out.println("Koñczê dodawanie Like");
			
		}
			else if(mySocket.getLocalPort() == 755)
			{
		        int bytesRead;
		        int current = 0;
		        FileOutputStream fos = null;
		        BufferedOutputStream bos = null;
		        Socket sock = null;
		        try {
		        	System.out.println("Rozpoczynam odbieranie obrazu");

					int FILE_SIZE = 1024*1024*500;
					byte [] mybytearray  = new byte [FILE_SIZE ];
					byte [] mybytearray2  = new byte [5 ];
					InputStream is = mySocket.getInputStream();
					bytesRead = is.read(mybytearray2,0,mybytearray2.length);
					bytesRead = is.read(mybytearray,0,mybytearray.length);
					int i = 0;
					i = (int)(mybytearray2[3]& 0xFF);
					i <<=8;
					i += (int)(mybytearray2[2]& 0xFF);
					i <<=8;
					i += (int)(mybytearray2[1]& 0xFF);
					i <<=8;
					i += (int)(mybytearray2[0]& 0xFF);
					current = bytesRead;

					do {
						bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
						if(bytesRead >= 0) current += bytesRead;
					} while(bytesRead > -1);
					String s = "";
					s = ((mybytearray2[4])==1?"jpg":((mybytearray2[4])==2?"bmp":((mybytearray2[4])==3?"gif":((mybytearray2[4])==4?"png":((mybytearray2[4])==5?"wbmp":"jpeg")))));
					fos = new FileOutputStream(sciezka+i+"."+s);
					bos = new BufferedOutputStream(fos);
					bos.write(mybytearray, 0 , current);
					bos.flush();
		        }
		        finally {
		        	if (fos != null) fos.close();
		        	if (bos != null) bos.close();
		        	if (sock != null) sock.close();
		        }
	        	System.out.println("Koñczê odbieranie obrazu");
			}
			else if(mySocket.getLocalPort() == 757)
			{
				mySocket.setTcpNoDelay(true);
				System.out.println("Rozpoczynam dodawanie obrazu");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
	            Picture p = (Picture) objInputStream.readObject();
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
				String str = ""+JDBC.addPictures(p.getTitle(),p.getDescription(), Integer.parseInt(p.getUrl()), p.getAuthor().getId());
				out.println(str);
				out.flush();
				System.out.println("Koñczê dodawanie obrazu");
				
			}
			else if(mySocket.getLocalPort() == 758)
			{
		        int bytesRead;
		        int current = 0;
		        FileOutputStream fos = null;
		        BufferedOutputStream bos = null;
		        Socket sock = null;
		        try {
		        	System.out.println("Rozpoczynam odbieranie obrazu usera");

					int FILE_SIZE = 1024*1024*500;
					byte [] mybytearray  = new byte [FILE_SIZE ];
					byte [] mybytearray2  = new byte [5 ];
					InputStream is = mySocket.getInputStream();
					bytesRead = is.read(mybytearray2,0,mybytearray2.length);
					bytesRead = is.read(mybytearray,0,mybytearray.length);
					int i = 0;
					i = (int)(mybytearray2[3]& 0xFF);
					i <<=8;
					i += (int)(mybytearray2[2]& 0xFF);
					i <<=8;
					i += (int)(mybytearray2[1]& 0xFF);
					i <<=8;
					i += (int)(mybytearray2[0]& 0xFF);
					current = bytesRead;

					do {
						bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
						if(bytesRead >= 0) current += bytesRead;
					} while(bytesRead > -1);
					String s = "";
					s = ((mybytearray2[4])==1?"jpg":((mybytearray2[4])==2?"bmp":((mybytearray2[4])==3?"gif":((mybytearray2[4])==4?"png":((mybytearray2[4])==5?"wbmp":"jpeg")))));
					fos = new FileOutputStream(sciezka+"avatars\\"+i+"."+s);
					bos = new BufferedOutputStream(fos);
					bos.write(mybytearray, 0 , current);
					bos.flush();
		        }
		        finally {
		        	if (fos != null) fos.close();
		        	if (bos != null) bos.close();
		        	if (sock != null) sock.close();
		        }
	        	System.out.println("Koñczê odbieranie obrazu usera");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
