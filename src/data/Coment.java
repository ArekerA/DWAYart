package data;

public class Coment {
	private User author;
	private String text;
	public Coment(User author, String text) {
		super();
		this.author = author;
		this.text = text;
	}
	public User getAuthor() {
		return author;
	}
	public String getText() {
		return text;
	}
	@Override
	public String toString() {
		return text;
	}
}
