package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.image.Image;

public class Picture implements Serializable{
	private int id;
	private String title;
	private String url;
	private String description;
	private User author;
	private ArrayList<Coment> coments;
	private ArrayList<Tag> tags;
	private long favorites;
	private Date date;
	public Picture(String url, int id, String title, String description, User author, ArrayList<Coment> coments, ArrayList<Tag> tags, Date date, long favorites) {
		this.url = url;
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.coments = coments;
		this.tags = tags;
		this.date = date;
		this.favorites = favorites;
	}
	public String getUrl() {
		return url;
	}
	public ArrayList<Coment> getComents() {
		return coments;
	}
	public ArrayList<Tag> getTags() {
		return tags;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public User getAuthor() {
		return author;
	}
	public Date getDate() {
		return date;
	}
	public long getFavorites() {
		return favorites;
	}
	@Override
	public String toString() {
		return title;
	}

}
