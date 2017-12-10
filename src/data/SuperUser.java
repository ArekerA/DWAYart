package data;

public class SuperUser extends User {
	private String login;
	public SuperUser(int id, String name, String email, int sex, String login) {
		super(id, name, email, sex);
		this.login = login;
	}

}
