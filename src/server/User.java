package server;

import java.util.Date;

import javafx.scene.image.Image;

public class User {
	String login;
	String password;
	String name;
	Date dob = new Date();
	int age;
	Image profilephoto;

	public Image getProfilephoto() {
		return profilephoto;
	}

	public void setProfilephoto(Image profilephoto) {
		this.profilephoto = profilephoto;
	}

	public User(String login, String password, String name, Date dob) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.dob = dob;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
