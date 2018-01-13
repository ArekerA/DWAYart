package data;

import java.io.Serializable;
import java.util.Date;

public class Coment implements Serializable{
	private User author;
	private String text;
	private Date date;
	public Coment(User author, String text, Date date) {
		super();
		this.author = author;
		this.text = text;
		this.date = date;
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
	@Override
	public String toString() {
		return  date+ " "+ author + ": " + text +"\n";
	}

}
