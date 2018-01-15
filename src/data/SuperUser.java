package data;

import java.io.Serializable;

public class SuperUser extends User implements Serializable{
	private String login;
	private String password;
	public SuperUser(int id, String name, String email, int sex, String login, short type, String password) {
		super(id, name, email, sex, type);
		this.login = login;
		this.password = password;
	}
	
	public SuperUser(String name, String email, int sex, String login,String password) {
		super(name, email, sex);
		this.login=login;
		this.password=password;
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}
	public String getLogin() {
		return login;
	}

}
