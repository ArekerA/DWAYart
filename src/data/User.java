package data;

public class User {
	private int id;
	private String name;
	private String email;
	private int sex;
	public User(int id, String name, String email, int sex) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getSex() {
		return sex;
	}
	@Override
	public String toString() {
		return name;
	}
}
