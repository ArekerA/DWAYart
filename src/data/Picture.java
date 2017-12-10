package data;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Picture extends Image {
	private int id;
	private String title;
	private String description;
	private User author;
	private ArrayList<Coment> coments;
	private ArrayList<Tag> tags;
	private long favorites;
	public Picture(String url, int id, String title, String description, User author, ArrayList<Coment> coments, ArrayList<Tag> tags, long favorites) {
		super(url);
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.coments = coments;
		this.tags = tags;
		this.favorites = favorites;
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
	public long getFavorites() {
		return favorites;
	}
	@Override
	public String toString() {
		return title;
	}

}
