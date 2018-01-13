package data;

import java.io.Serializable;
import java.util.Date;

public class Coment implements Serializable{
	private User author;
	private String text;
	private Date date;
	private int picture;
	public Coment(User author, String text, Date date, int picture) {
		super();
		this.author = author;
		this.text = text;
		this.date = date;
		this.picture=picture;
	}
	public User getAuthor() {
		return author;
	}
	public String getText() {
		return text;
	}
	public Date getDate() {
		return date;
	}
	public int getPicture() {
		return picture;
	}
	@Override
	public String toString() {
		return  date+ " "+ author + ": " + text +"\n";
	}

}
