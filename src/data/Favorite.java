package data;

import java.io.Serializable;
import java.util.Date;

public class Favorite implements Serializable{
	private int id_u;
	private Date date;
	private int id_p;
	public Favorite(int i, Date date, int picture) {
		super();
		this.id_u = i;
		this.date = date;
		this.id_p=picture;
	}
	



	public int getId_u() {
		return id_u;
	}




	public void setId_u(int id_u) {
		this.id_u = id_u;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public int getId_p() {
		return id_p;
	}




	public void setId_p(int id_p) {
		this.id_p = id_p;
	}




	@Override
	public String toString() {
		return "Favorite [id_u=" + id_u + ", date=" + date + ", id_p=" + id_p + "]";
	}

}
