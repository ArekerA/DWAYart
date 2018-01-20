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
		this.date = date;
		this.text = text;
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
	public void setAuthor(User author) {
		this.author = author;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setPicture(int picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return  date+ " "+ author + ": " + text +"\n";
	}

}
