package data;

import java.util.ArrayList;
import java.util.Date;

import javafx.scene.image.Image;

public class SuperPicture  {
	private int id;
	private String title;
	private String url;
	private String description;
	private User author;
	private ArrayList<Coment> coments;
	private ArrayList<Tag> tags;
	private long favorites;
	private Date date;
	private Image Image ;

	public SuperPicture(Picture p) {
		this.url = p.getUrl();
		this.id = p.getId();
		this.title = p.getTitle();
		this.description = p.getDescription();
		this.author = p.getAuthor();
		this.coments = p.getComents();
		this.tags = p.getTags();
		this.date = p.getDate();
		this.favorites = p.getFavorites();
		this.Image = new Image(url);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public ArrayList<Coment> getComents() {
		return coments;
	}

	public void setComents(ArrayList<Coment> coments) {
		this.coments = coments;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public long getFavorites() {
		return favorites;
	}

	public void setFavorites(long favorites) {
		this.favorites = favorites;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}


}
