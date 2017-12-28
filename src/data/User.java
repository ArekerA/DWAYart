package data;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;
	private String email;
	private int sex;
	private short type;
	public User(int id, String name, String email, int sex, short type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.type = type;
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
	public short getType() {
		return type;
	}
	@Override
	public String toString() {
		return name;
	}
}
