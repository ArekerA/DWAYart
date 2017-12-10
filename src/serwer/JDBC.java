package serwer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import data.Coment;
import data.Picture;
import data.SuperUser;
import data.Tag;
import data.User;

public class JDBC {
	static Connection con;
	
	public static boolean checkDriver(String driver) {
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance();
			System.out.println(" ... OK");
			return true;
		} catch (Exception e) {
			System.out.println("... ERROR");
			return false;
		}
	}
	public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		try {
			conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
					connectionProps);
		} catch (SQLException e) {
			System.out.println("B³¹d po³¹czenia z baz¹ danych! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(2);
		}
		System.out.println("Po³¹czenie z baz¹ danych: ... OK");
		return conn;
	}
	private static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("B³¹d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(3);
		}
		return null;
	}
	private static void closeConnection(Connection connection, Statement s) {
		System.out.print("\nZamykanie polaczenia z baz¹:");
		try {
			s.close();
			connection.close();
		} catch (SQLException e) {
			System.out
					.println("Bl¹d przy zamykaniu pol¹czenia z baz¹! " + e.getMessage() + ": " + e.getErrorCode());;
			System.exit(4);
		}
		System.out.print(" zamkniêcie OK");
	}
	private static ResultSet executeQuery(Statement s, String sql) {
		try {
			return s.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(sql + "\n\tZapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return null;
	}
	private static int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(sql + "\n\tZapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return -1;
	}
	private static void printDataFromQuery(ResultSet r) {
		ResultSetMetaData rsmd;
		try {
			rsmd = r.getMetaData();
			int numcols = rsmd.getColumnCount(); // pobieranie liczby kolumn
			// wyswietlanie nazw kolumn:
			for (int i = 1; i <= numcols; i++) {
				System.out.print("\t" + rsmd.getColumnLabel(i) + "\t|");
			}
			System.out
					.print("\n____________________________________________________________________________\n");
			/**
			 * r.next() - przejœcie do kolejnego rekordu (wiersza) otrzymanych wyników
			 */
			// wyswietlanie kolejnych rekordow:
			while (r.next()) {
				for (int i = 1; i <= numcols; i++) {
					Object obj = r.getObject(i);
					if (obj != null)
						System.out.print("\t" + obj.toString() + "\t|");
					else
						System.out.print("\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}
	}
	public static void sqlGetDataByName(ResultSet r) {
		System.out.println("Pobieranie danych z wykorzystaniem nazw kolumn");
		try {
			ResultSetMetaData rsmd = r.getMetaData();
			int numcols = rsmd.getColumnCount();
			// Tytul tabeli z etykietami kolumn zestawow wynikow
			for (int i = 1; i <= numcols; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "\t|\t");
			}
			System.out
			.print("\n____________________________________________________________________________\n");
			while (r.next()) {
				int size = r.getMetaData().getColumnCount();
				for(int i = 1; i <= size; i++){
					switch(r.getMetaData().getColumnTypeName(i)){
					case "INT":
						System.out.print(r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					case "DATE":
						System.out.print(r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					case "VARCHAR":
						System.out.print(r.getString(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					default:
						System.out.print(r.getMetaData().getColumnTypeName(i));
					}
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}
	}
	public static void init()
	{
		if (!checkDriver("com.mysql.jdbc.Driver"))
			System.exit(1);
		con = getConnection("jdbc:mysql://", "127.0.0.1", 3306, "root", "");
		Statement st = createStatement(con);
		if (executeUpdate(st, "USE DWAYart;") == 0)
			System.out.println("Baza DWAYart wybrana");
		else {
			System.out.println("Baza nie istnieje! Tworzymy bazê: ");
			if (executeUpdate(st, "create Database DWAYart;") == 1)
				System.out.println("Baza DWAYart utworzona");
			else
			{
				System.out.println("Baza DWAYart nieutworzona!");
				System.exit(10);
			}
			if (executeUpdate(st, "USE DWAYart;") == 0)
				System.out.println("Baza DWAYart wybrana");
			else
			{
				System.out.println("Baza DWAYart niewybrana!");
				System.exit(11);
			}
		}
		if (executeUpdate(st,
				"CREATE TABLE users ( id INT NOT NULL, login VARCHAR(50) NOT NULL, name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, pass VARCHAR(64) NOT NULL, sex TINYINT NOT NULL,  PRIMARY KEY (id) );") == 0)
			System.out.println("Tabela users utworzona");
		else
			System.out.println("Tabela users nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE pictures ( id INT NOT NULL, title VARCHAR(100) NOT NULL, description VARCHAR(1000) NOT NULL, type TINYINT NOT NULL, author INT NOT NULL, PRIMARY KEY (id), FOREIGN KEY (author) REFERENCES users(id) );") == 0)
			System.out.println("Tabela pictures utworzona");
		else
			System.out.println("Tabela pictures nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE comments ( id INT NOT NULL, text VARCHAR(1000) NOT NULL, picture INT NOT NULL, author INT NOT NULL, PRIMARY KEY (id),  FOREIGN KEY (picture) REFERENCES pictures(id), FOREIGN KEY (author) REFERENCES users(id) );") == 0)
			System.out.println("Tabela comments utworzona");
		else
			System.out.println("Tabela comments nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE tags ( id INT NOT NULL, text VARCHAR(50) NOT NULL, PRIMARY KEY (id) );") == 0)
			System.out.println("Tabela tags utworzona");
		else
			System.out.println("Tabela tags nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE tags_to_pictures ( id_t INT NOT NULL, id_p INT NOT NULL, PRIMARY KEY (id_t, id_p), FOREIGN KEY (id_t) REFERENCES tags(id), FOREIGN KEY (id_p) REFERENCES pictures(id));") == 0)
			System.out.println("Tabela tags_to_pictures utworzona");
		else
			System.out.println("Tabela tags_to_pictures nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE favorites ( id_u INT NOT NULL, id_p INT NOT NULL, PRIMARY KEY (id_u, id_p), FOREIGN KEY (id_u) REFERENCES users(id), FOREIGN KEY (id_p) REFERENCES pictures(id));") == 0)
			System.out.println("Tabela favorites utworzona");
		else
			System.out.println("Tabela favorites nie utworzona!");
		
		
		
		
		String sql;
		sql = "INSERT INTO users VALUES(0, 'init', 'init', 'init', 'init', 0);";
		executeUpdate(st, sql);
		sql = "INSERT INTO pictures VALUES(0, 'init', 'init', 0, 0);";
		executeUpdate(st, sql);
		sql = "INSERT INTO comments VALUES(0, 'init', 0, 0);";
		executeUpdate(st, sql);
		sql = "INSERT INTO tags VALUES(0, 'init');";
		executeUpdate(st, sql);
		sql = "INSERT INTO tags_to_pictures VALUES(0, 0);";
		executeUpdate(st, sql);
		sql = "INSERT INTO favorites VALUES(0, 0);";
		executeUpdate(st, sql);
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close()
	{
		Statement st = createStatement(con);
		closeConnection(con, st);
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int addUser(String login, String name, String email, String pass, int sex)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from users;");
			r.next();
			int id = ((int)r.getObject(1)+1);
			executeUpdate(st, "INSERT INTO users VALUES("+id+", '"+login+"', '"+name+"', '"+email+"', '"+pass+"', "+sex+");");
			st.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int addPictures(String title, String description, int type, int author)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from pictures;");
			r.next();
			int id = ((int)r.getObject(1)+1);
			executeUpdate(st, "INSERT INTO pictures VALUES("+id+", '"+title+"', '"+description+"', "+type+", "+author+");");
			st.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int addComent(String text, int picture, int author)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from comments;");
			r.next();
			int id = ((int)r.getObject(1)+1);
			executeUpdate(st, "INSERT INTO comments VALUES("+id+", '"+text+"', "+picture+", "+author+");");
			st.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int addTag(String text)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from tags;");
			r.next();
			int id = ((int)r.getObject(1)+1);
			executeUpdate(st, "INSERT INTO tags VALUES("+id+", '"+text+"');");
			st.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static void addTagToPicture(int tag, int picture)
	{
		Statement st = createStatement(con);
		executeUpdate(st, "INSERT INTO tags_to_pictures VALUES("+tag+", "+picture+");");
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static SuperUser login(String user, String pass)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select id,name,email,sex,login from users where login='"+user+"' and pass='"+pass+"';");
			r.next();
			SuperUser u = new SuperUser((int)r.getObject(1), r.getObject(2).toString(), r.getObject(3).toString(), (int)r.getObject(4), r.getObject(5).toString());
			st.close();
			return u;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d logowania " + user + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static User getUser(int id)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select id,name,email,sex from users where id='"+id+"';");
			r.next();
			User u = new User((int)r.getObject(1), r.getObject(2).toString(), r.getObject(3).toString(), (int)r.getObject(4));
			st.close();
			return u;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d u¿ytkownika " + id + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Coment> getComents(int picture)
	{
		ArrayList<Coment> c = new ArrayList<Coment>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select text,author from comments where picture="+picture+";");
			while (r.next())
				c.add(new Coment(getUser((int)r.getObject(2)),r.getObject(1).toString()));
			st.close();
		} catch (SQLException e) {
			System.out.println("====\nBl¹d komentarzy do obrazu " + picture + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
		}
		finally {
			return c;
		}
	}
	public static ArrayList<Tag> getTags(int picture)
	{
		ArrayList<Tag> c = new ArrayList<Tag>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT tags.id,tags.text FROM `tags_to_pictures` LEFT JOIN (tags,pictures) ON (tags_to_pictures.id_t = tags.id and tags_to_pictures.id_p = pictures.id) WHERE id_p = '"+picture+"';");
			while(r.next())
				c.add(new Tag(r.getObject(2).toString(), (int)r.getObject(1)));
			st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d tagów dla obrazu " + picture + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static Picture getPicture(int id)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE id = '"+id+"' GROUP BY id;");
			r.next();
			System.out.println((int)r.getObject(5));
			System.out.println((int)r.getObject(4));
			Picture p = new Picture("http://127.0.0.1/img/"+id+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))), id, r.getObject(2).toString(), r.getObject(3).toString(), getUser((int) r.getObject(4)), getComents(id), getTags(id), (long)r.getObject(6));
			st.close();
			return p;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazu " + id + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Picture> getPictures(Tag t)
	{
		ArrayList<Picture> c = new ArrayList<Picture>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id_p FROM `tags_to_pictures` WHERE id_t = '"+t.getId()+"';");
			while(r.next())
				c.add(getPicture((int)r.getObject(1)));
			st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów dla tagu " + t + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Picture> getPictures(User u)
	{
		ArrayList<Picture> c = new ArrayList<Picture>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE author = '"+u.getId()+"' GROUP BY id;");
			while(r.next())
				c.add(new Picture("http://127.0.0.1/img/"+(int)r.getObject(1)+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))), (int)r.getObject(1), r.getObject(2).toString(), r.getObject(3).toString(), getUser((int)r.getObject(3)), getComents((int)r.getObject(1)), getTags((int)r.getObject(1)),(long)r.getObject(6)));
			st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów dla u¿ytkownika " + u + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static void main(String[] args) {
		
		init();
		System.out.println(login("init","init").getName());
		System.out.println(getComents(0));
		System.out.println(getTags(1));
		System.out.println(getPicture(0));
		close();
	}
}

