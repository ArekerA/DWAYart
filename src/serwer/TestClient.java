package serwer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

import data.Coment;
import data.Picture;
import data.SuperUser;

public class TestClient {
	public static void main(String args[]) {
		try {
			int port = 752;
			
			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			/*
			String str = "getPictures,0,20,date DESC";
			
			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			
			System.out.println("rozpoczynam odbiór");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			
            ArrayList<Picture> p = (ArrayList<Picture>) objInputStream.readObject();
            System.out.println(p.get(0).getDate());
            
			System.out.println("koñczê odbiór");
			*/
			String str = "login,superadam,"+sha256("adam123");
			
			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();
			
			System.out.println("rozpoczynam odbiÃ³r");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			
            SuperUser p = (SuperUser) objInputStream.readObject();
            System.out.println(p.getEmail());
            
			System.out.println("koÅ„czÄ™ odbiÃ³r");
			socket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	public static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
}