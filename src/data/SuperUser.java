package data;

import java.io.Serializable;

public class SuperUser extends User implements Serializable{
	private String login;
	public SuperUser(int id, String name, String email, int sex, String login, short type) {
		super(id, name, email, sex, type);
		this.login = login;
	}
	public String getLogin() {
		return login;
	}

}
