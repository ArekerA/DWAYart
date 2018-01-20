package application;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import data.Favorite;
import data.Picture;
import data.SuperPicture;



public abstract class Pomocnicza {

	static public ArrayList<SuperPicture> obrazy = new ArrayList<SuperPicture>(0);
	static public ArrayList<Picture> p = new ArrayList<Picture>(0);
	public static void dodajobrazy(int k) {   
		obrazy.clear();
		p.clear();
		if(k==0)	// G³ówna
		{
			p = getPictures(0, 100, "date DESC");	
			for(int i=0;i<p.size();i++)
			{
			obrazy.add(new SuperPicture(p.get(i)));
			}
		}
		if(k==1)	// TOPKA 
		{
			p = getPictures(0, 100, "favorites DESC");	
			for(int i=0;i<p.size();i++)
			{
				if(p.get(i).getFavorites()>0)
			obrazy.add(new SuperPicture(p.get(i)));
			}
		}
		if(k==2)	// Ulubione
		{
			p = getUserFavorites(Static.user.getId());
			
			
			for(int i=0;i<p.size();i++)
			{
			obrazy.add(new SuperPicture(p.get(i)));
			}
		}
		if(k==3)	// Moje foty
		{
			p = getUserPictures(Static.user.getId());				
			for(int i=0;i<p.size();i++)
			{
			obrazy.add(new SuperPicture(p.get(i)));
			}
		}
		/*
		obrazy.add(new Image("http://localhost/img/0.jpg"));
		obrazy.add(new Image("http://localhost/img/6.gif"));
		obrazy.add(new Image("http://localhost/img/7.png"));
		*/
	}

	public static ArrayList<SuperPicture> getObrazy() {
		return obrazy;
	}

	public static void setObrazy(ArrayList<SuperPicture> obrazy) {
		Pomocnicza.obrazy = obrazy;
	}

	public static ArrayList<Picture> getPictures(int ofs, int il, String sort) {
		try {
			int port = 752;
			System.out.println("Pobieranie Startv2");
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "getPictures,"+ofs+","+il+","+sort;		// Do serwera  Switch; getPictures,0,20,date DESC

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();

			System.out.println("Pobieranie Start");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			ArrayList<Picture> p = (ArrayList<Picture>) objInputStream.readObject();
			System.out.println("Pobieranie Koniec");
			socket.close();
			return p;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}public static ArrayList<Picture> getUserFavorites(int i) {
		try {
			int port = 752;
			System.out.println("Pobieranie Startv2");
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "getUserFavorites,"+i;		// Do serwera  Switch; getPictures,0,20,date DESC

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();

			System.out.println("Pobieranie Start");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			ArrayList<Picture> p = (ArrayList<Picture>) objInputStream.readObject();
			System.out.println("Pobieranie Koniec");
			socket.close();
			return p;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	public static ArrayList<Picture> getUserPictures(int i) {
		try {
			int port = 752;
			System.out.println("Pobieranie Startv2");
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "getUserPictures,"+i;		// Do serwera  Switch; getPictures,0,20,date DESC

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();

			System.out.println("Pobieranie Start");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			ArrayList<Picture> p = (ArrayList<Picture>) objInputStream.readObject();
			System.out.println("Pobieranie Koniec");
			socket.close();
			return p;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
public static ArrayList<Favorite> getFavorites(int z) {
	try {
		int port = 752;
		System.out.println("Pobieranie Startv2");
		Socket socket = new Socket("127.0.0.1", port);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

		String str = "getFavorites,"+z;		// Do serwera  Switch; getFavorites,2

		socket.setTcpNoDelay(true);
		out.println(str);
		out.flush();

		System.out.println("Pobieranie Start");
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objInputStream = null;
		objInputStream = new ObjectInputStream(inputStream);
		ArrayList<Favorite> p = (ArrayList<Favorite>) objInputStream.readObject();
		System.out.println("Pobieranie Koniec");
		socket.close();
		return p;
	} catch (Exception e) {
		System.err.println(e);
		return null;
	}
}
}
