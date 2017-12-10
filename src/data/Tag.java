package data;

public class Tag {
	private String text;
	private int id;
	public Tag(String text, int id) {
		super();
		this.text = text;
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return text;
	}
}
