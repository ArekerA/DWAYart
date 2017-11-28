package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Polaczenie implements Runnable{

	@Override
	public void run() {
		ServerSocket ss = null;
		String doswitcha=null;
		
		try {
			ss = new ServerSocket(1324);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Socket przychodzace=ss.accept();
			Scanner in=new Scanner(przychodzace.getInputStream());
			doswitcha=in.nextLine();
			switch(doswitcha)
					{
			case "Rejestracja":
			{
				User user=new User(in.nextLine(), in.nextLine());
				System.out.println("Wszyscy u¿ytkownicy:");
				System.out.println(User.userzy.size());
				in.close();
			}
			default:
			{
				break;
			}
					}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
