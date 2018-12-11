package serwer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import data.Coment;
import data.Favorite;
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
		connectionProps.put("characterEncoding", "cp1250");
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
			if (executeUpdate(st, "create Database DWAYart DEFAULT CHARSET=cp1250;") == 1)
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
		executeUpdate(st, "SET NAMES cp1250;");
		if (executeUpdate(st,
				"CREATE TABLE users ( id INT NOT NULL, login VARCHAR(50) NOT NULL, name VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, pass VARCHAR(64) NOT NULL, sex TINYINT NOT NULL, type TINYINT NOT NULL,  PRIMARY KEY (id) ) DEFAULT CHARSET=cp1250;") == 0)
			System.out.println("Tabela users utworzona");
		else
			System.out.println("Tabela users nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE pictures ( id INT NOT NULL, title VARCHAR(100) NOT NULL, description VARCHAR(1000) NOT NULL, author INT NOT NULL, type TINYINT NOT NULL, date DATETIME NOT NULL, PRIMARY KEY (id), FOREIGN KEY (author) REFERENCES users(id) ) DEFAULT CHARSET=cp1250;") == 0)
			System.out.println("Tabela pictures utworzona");
		else
			System.out.println("Tabela pictures nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE comments ( id INT NOT NULL, text VARCHAR(1000) NOT NULL, picture INT NOT NULL, author INT NOT NULL, date DATE NOT NULL, PRIMARY KEY (id),  FOREIGN KEY (picture) REFERENCES pictures(id), FOREIGN KEY (author) REFERENCES users(id) ) DEFAULT CHARSET=cp1250;") == 0)
			System.out.println("Tabela comments utworzona");
		else
			System.out.println("Tabela comments nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE tags ( id INT NOT NULL, text VARCHAR(50) NOT NULL, PRIMARY KEY (id) ) DEFAULT CHARSET=cp1250;") == 0)
			System.out.println("Tabela tags utworzona");
		else
			System.out.println("Tabela tags nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE tags_to_pictures ( id_t INT NOT NULL, id_p INT NOT NULL, PRIMARY KEY (id_t, id_p), FOREIGN KEY (id_t) REFERENCES tags(id), FOREIGN KEY (id_p) REFERENCES pictures(id)) DEFAULT CHARSET=cp1250;") == 0)
			System.out.println("Tabela tags_to_pictures utworzona");
		else
			System.out.println("Tabela tags_to_pictures nie utworzona!");
		if (executeUpdate(st,
				"CREATE TABLE favorites ( id_u INT NOT NULL, id_p INT NOT NULL, date DATE NOT NULL, PRIMARY KEY (id_u, id_p), FOREIGN KEY (id_u) REFERENCES users(id), FOREIGN KEY (id_p) REFERENCES pictures(id)) DEFAULT CHARSET=cp1250;") == 0)
			System.out.println("Tabela favorites utworzona");
		else
			System.out.println("Tabela favorites nie utworzona!");
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initTest()
	{
		Statement st = createStatement(con);		
		String sql;
		sql = "INSERT INTO users VALUES(0, 'adam', 'Adam', 'adam@adam.com', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 1, 4),"
				+ "(1, 'superadam', 'Super Adam', 'sa@adam.com', '8bd7fc570dd3fd5e48991d6626d43c4c2f7a250aaeba5153798ec818156e43fa', 1, 4),"
				+ "(2, 'appol', 'Apolonia', 'appol@appol.pl', '65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5', 2, 4),"
				+ "(3, 'noname', 'No Name', 'no@name.com', '833a80c83380049bb9869950090f9f886c281b036c08a20d7a32af8c87864362', 3, 1);";
		executeUpdate(st, sql);
		sql = "INSERT INTO pictures VALUES(0, 'Husaria', 'Husarz na gryfie na tle miasta', 0, 1, '2017-12-11 05:04:03'),"
				+ "(1, 'Sandro', 'Najpotê¿niejszy Nekromanta', 1, 1, '2017-12-15 14:12:13'),"
				+ "(2, 'Czerwony Kapturek', 'Kapturek w bardziej mrocznej wersji', 1, 1, '2017-12-20 11:22:33'),"
				+ "(3, 'Ninja', 'Ninja', 1, 1, '2017-12-22 01:02:03'),"
				+ "(4, 'Wizard', 'Great wizard', 3, 1, '2017-12-23 01:02:03'),"
				+ "(5, 'Sylvana', 'Sylvana', 1, 1, '2017-12-25 04:02:03'),"
				+ "(6, 'Spiderman', 'Animowany spiderman', 2, 3, '2017-12-30 01:02:03'),"
				+ "(7, 'Miasto', 'Miasto przysz³oœci', 0, 4, '2017-12-30 01:22:23'),"
				+ "(8, 'Lady', 'Lady in red', 3, 1, '2017-12-31 02:11:25'),"
				+ "(9, 'Harley Quinn', 'Animowana postaæ', 2, 3, '2017-12-31 02:18:52'),"
				+ "(10, 'Zombie', 'Wszêdzie Zombie', 1, 1, '2017-12-31 03:20:03'),"
				+ "(11, 'Ogar', 'Animowany Piekielny ogar', 2, 3, '2017-12-31 03:22:42'),"
				+ "(12, 'Las', 'Zimowy las', 0, 1, '2017-12-31 03:40:20'),"
				+ "(13, 'Góry', 'Piêkne góry', 0, 1, '2017-12-31 04:10:03'),"
				+ "(14, 'Pani', 'Animowana pani', 2, 3, '2017-12-31 04:02:06'),"
				+ "(15, 'Nyx', 'Fallen Angel', 3, 1, '2017-12-31 04:15:53'),"
				+ "(16, 'Œcie¿ka', 'Œcie¿ka w górach', 0, 1, '2017-12-31 05:18:22'),"
				+ "(17, 'Wilki', 'Wilki', 1, 4, '2017-12-31 05:20:25'),"
				+ "(18, 'Kolos', 'Kolos w górach', 1, 1, '2017-12-31 05:42:52'),"
				+ "(19, 'Divina', 'The eye sees everything; the eye knows everything', 3, 1, '2017-12-31 06:08:43'),"
				+ "(20, 'Dolina', 'Dolina', 0, 4, '2017-12-31 07:10:48'),"
				+ "(21, 'Jeleñ', 'Zimowy jeleñ', 1, 1, '2017-12-31 08:43:06'),"
				+ "(22, 'Kora', 'Atakuj¹ca Kora', 2, 3, '2017-12-31 09:55:47'),"
				+ "(23, 'The Eagle Archery', 'The Eagle Archery', 3, 1, '2017-12-31 09:57:42'),"
				+ "(24, 'Jesieñ', 'Las Jesieni¹', 0, 4, '2017-12-31 09:59:35'),"
				+ "(25, 'Wyspa', 'Wyspa na morzu', 0, 1, '2017-12-31 11:35:56');";
		executeUpdate(st, sql);
		sql = "INSERT INTO comments VALUES(0, 'Great gif', 6, 3, '2017-12-31 12:20:48'),"
				+ "(1, 'Amazing animation', 14, 3, '2017-12-31 12:24:21'),"
				+ "(2, 'Bardzo ³adne zdjêcie', 16, 1, '2017-12-31 13:12:55'),"
				+ "(3, 'Dziêkujê', 16, 0, '2017-12-31 14:54:27'),"
				+ "(4, 'Thanks', 14, 2, '2017-12-31 15:42:38'),"
				+ "(5, 'Wspania³e', 16, 2, '2017-12-31 16:22:49'),"
				+ "(6, 'Great', 16, 3, '2017-12-31 17:38:11');";
		executeUpdate(st, sql);
		sql = "INSERT INTO tags VALUES(0, 'krajobraz'),"
				+ "(1, 'landscape'),"
				+ "(2, 'postaæ'),"
				+ "(3, 'character'),"
				+ "(4, 'animowany'),"
				+ "(5, 'animated'),"
				+ "(6, 'fanstsy'),"
				+ "(7, 'sci-fi'),"
				+ "(8, 'dark'),"
				+ "(9, 'las'),"
				+ "(10, 'forest');";
		executeUpdate(st, sql);
		sql = "INSERT INTO tags_to_pictures VALUES(0, 7),"
				+ "(0, 12),"
				+ "(0, 13),"
				+ "(0, 16),"
				+ "(0, 18),"
				+ "(0, 20),"
				+ "(0, 24),"
				+ "(0, 25),"
				+ "(1, 7),"
				+ "(1, 12),"
				+ "(1, 13),"
				+ "(1, 16),"
				+ "(1, 18),"
				+ "(1, 20),"
				+ "(1, 24),"
				+ "(1, 25),"
				+ "(2, 0),"
				+ "(2, 1),"
				+ "(2, 2),"
				+ "(2, 3),"
				+ "(2, 4),"
				+ "(2, 5),"
				+ "(2, 6),"
				+ "(2, 8),"
				+ "(2, 9),"
				+ "(2, 10),"
				+ "(2, 11),"
				+ "(2, 14),"
				+ "(2, 15),"
				+ "(2, 18),"
				+ "(2, 19),"
				+ "(2, 21),"
				+ "(2, 22),"
				+ "(3, 0),"
				+ "(3, 1),"
				+ "(3, 2),"
				+ "(3, 3),"
				+ "(3, 4),"
				+ "(3, 5),"
				+ "(3, 6),"
				+ "(3, 8),"
				+ "(3, 9),"
				+ "(3, 10),"
				+ "(3, 11),"
				+ "(3, 14),"
				+ "(3, 15),"
				+ "(3, 18),"
				+ "(3, 19),"
				+ "(3, 21),"
				+ "(3, 22),"
				+ "(4, 6),"
				+ "(4, 9),"
				+ "(4, 11),"
				+ "(4, 14),"
				+ "(4, 22),"
				+ "(5, 6),"
				+ "(5, 9),"
				+ "(5, 11),"
				+ "(5, 14),"
				+ "(5, 22),"
				+ "(6, 0),"
				+ "(6, 1),"
				+ "(6, 2),"
				+ "(6, 3),"
				+ "(6, 4),"
				+ "(6, 5),"
				+ "(6, 8),"
				+ "(6, 11),"
				+ "(6, 15),"
				+ "(6, 18),"
				+ "(6, 19),"
				+ "(6, 21),"
				+ "(6, 23),"
				+ "(7, 7),"
				+ "(8, 2),"
				+ "(8, 8),"
				+ "(8, 15),"
				+ "(8, 19),"
				+ "(8, 23),"
				+ "(9, 12),"
				+ "(9, 16),"
				+ "(9, 17),"
				+ "(9, 20),"
				+ "(9, 24),"
				+ "(10, 12),"
				+ "(10, 16),"
				+ "(10, 17),"
				+ "(10, 20),"
				+ "(10, 24);";
		executeUpdate(st, sql);
		sql = "INSERT INTO favorites VALUES(3, 6, '2017-12-31 19:22:48'),"
				+ "(0, 14, '2017-12-31 20:22:55'),"
				+ "(1, 14, '2017-12-31 20:45:20'),"
				+ "(3, 14, '2017-12-31 21:14:56'),"
				+ "(1, 16, '2017-12-31 21:55:01'),"
				+ "(2, 16, '2017-12-31 22:13:25'),"
				+ "(3, 16, '2017-12-31 23:41:30');";
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
	public static int addUser(String login, String name, String email, String pass, int sex, short type)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from users;");
			r.next();
			int id = ((int)r.getObject(1)+1);
			executeUpdate(st, "INSERT INTO users VALUES("+id+", '"+login+"', '"+name+"', '"+email+"', '"+pass+"', "+sex+","+type+");");
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
			executeUpdate(st, "INSERT INTO pictures VALUES("+id+", '"+title+"', '"+description+"', "+author+", "+type+", NOW());");
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
			executeUpdate(st, "INSERT INTO comments VALUES("+id+", '"+text+"', "+picture+", "+author+", NOW());");
			st.close();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int addLike(int id_u, int id_p)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from comments;");
			r.next();
	
			executeUpdate(st, "INSERT INTO favorites VALUES("+id_u+", '"+id_p+"', NOW());");
			st.close();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static int delLike(int id_u, int id_p)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select MAX(id) from comments;");
			r.next();
	
			executeUpdate(st, "DELETE FROM favorites WHERE id_u="+id_u+" AND id_p="+id_p+";");
			st.close();
			return 1;
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
			ResultSet r = executeQuery(st, "Select id,name,email,sex,login,type from users where login='"+user+"' and pass='"+pass+"';");
			r.next();
			SuperUser u = new SuperUser(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4), r.getString(5), r.getShort(6), pass);
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
			ResultSet r = executeQuery(st, "Select id,name,email,sex,type from users where id='"+id+"';");
			r.next();
			User u = new User((int)r.getObject(1), r.getObject(2).toString(), r.getObject(3).toString(), (int)r.getObject(4), r.getShort(5));
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
			ResultSet r = executeQuery(st, "Select text,author,date from comments where picture="+picture+";");
			while (r.next())
				c.add(new Coment(getUser((int)r.getObject(2)),r.getObject(1).toString(),r.getDate(3),picture));
			st.close();
		} catch (SQLException e) {
			System.out.println("====\nBl¹d komentarzy do obrazu " + picture + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
		}
		finally {
			return c;
		}
	}
	public static ArrayList<Favorite> getFavorites(int id_p)
	{
		ArrayList<Favorite> f = new ArrayList<Favorite>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select id_p,id_u,date from favorites where id_p="+id_p+";");
			while (r.next())
				f.add(new Favorite((int)r.getObject(2),r.getDate(3),id_p));
			st.close();
		} catch (SQLException e) {
			System.out.println("====\nBl¹d Polaikowan do obrazu " + id_p + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
		}
		finally {
			return f;
		}
	}
	public static Boolean isFavorite(int id_p, int id_u)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select id_p,id_u,date from favorites where id_p="+id_p+" and id_u ="+id_u+";");
			while (r.next())
				return new Boolean(true);
			st.close();
			return new Boolean(false);
		} catch (SQLException e) {
			System.out.println("====\nBl¹d Polaikowan do obrazu " + id_p + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return new Boolean(false);
		}
	}
	public static Integer countFavorites(int id_p)
	{
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "Select COUNT(id_p) from favorites where id_p="+id_p+";");
			while (r.next())
				return new Integer(r.getInt(1));
			st.close();
			return new Integer(0);
		} catch (SQLException e) {
			System.out.println("====\nBl¹d Polaikowan do obrazu " + id_p + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return new Integer(0);
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
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,date,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE id = '"+id+"' GROUP BY id;");
			r.next();
			System.out.println((int)r.getObject(5));
			System.out.println((int)r.getObject(4));
			Picture p = new Picture("http://127.0.0.1/img/"+id+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))), 
					id, 
					r.getObject(2).toString(), 
					r.getObject(3).toString(), 
					getUser((int) r.getObject(4)), 
					getComents(id), getTags(id), 
					(Date)r.getDate(6), 
					(long)r.getObject(7));
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
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,pictures.date,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE author = '"+u.getId()+"' GROUP BY id;");
			while(r.next())
				c.add(new Picture("http://127.0.0.1/img/"+(int)r.getObject(1)+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))),
						(int)r.getObject(1),
						r.getObject(2).toString(),
						r.getObject(3).toString(), 
						getUser((int)r.getObject(4)), 
						getComents((int)r.getObject(1)), 
						getTags((int)r.getObject(1)), 
						(Date)r.getDate(6), 
						(long)r.getObject(7)));
			st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów dla u¿ytkownika " + u + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Picture> getPictures(int u)
	{
		ArrayList<Picture> c = new ArrayList<Picture>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,pictures.date,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE author = '"+u+"' GROUP BY id;");
			while(r.next())
				c.add(new Picture("http://127.0.0.1/img/"+(int)r.getObject(1)+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))),
						(int)r.getObject(1),
						r.getObject(2).toString(),
						r.getObject(3).toString(), 
						getUser((int)r.getObject(4)), 
						getComents((int)r.getObject(1)), 
						getTags((int)r.getObject(1)), 
						(Date)r.getDate(6), 
						(long)r.getObject(7)));
			st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów dla u¿ytkownika " + u + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Picture> getUserFavorites(int i)
	{
		ArrayList<Picture> c = new ArrayList<Picture>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,pictures.date,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE "+i+" = favorites.id_u GROUP BY id;");
			while(r.next())
				c.add(new Picture("http://127.0.0.1/img/"+(int)r.getObject(1)+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))),
						(int)r.getObject(1),
						r.getObject(2).toString(),
						r.getObject(3).toString(), 
						getUser((int)r.getObject(4)), 
						getComents((int)r.getObject(1)), 
						getTags((int)r.getObject(1)), 
						(Date)r.getDate(6), 
						(long)r.getObject(7)));
			st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów dla u¿ytkownika " + i + "\n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Picture> getPictures(int offset, int howMany, String order)
	{
		ArrayList<Picture> c = new ArrayList<Picture>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,pictures.date,COUNT(favorites.id_p) AS favorites FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) GROUP BY id ORDER BY "+order+" LIMIT "+offset+","+howMany+";");
			while(r.next())
			{
				c.add(new Picture(
						"http://127.0.0.1/img/"+(int)r.getObject(1)+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))), 
						(int)r.getObject(1), 
						r.getObject(2).toString(), 
						r.getObject(3).toString(), 
						getUser((int)r.getObject(4)),
						getComents((int)r.getObject(1)), 
						getTags((int)r.getObject(1)),
						(Date)r.getDate(6),
						(long)r.getObject(7)));
			}st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów \n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
	public static ArrayList<Picture> getPictures(int offset, int howMany, int i)
	{
		ArrayList<Picture> c = new ArrayList<Picture>();
		try {
			Statement st = createStatement(con);
			ResultSet r = executeQuery(st, "SELECT id,title,description,author,type,pictures.date,COUNT(favorites.id_p) AS fav FROM `pictures` LEFT JOIN favorites ON (pictures.id = favorites.id_p) WHERE favorites.date BETWEEN (CURRENT_DATE() - INTERVAL 1 "+(i==0?"DAY":i==1?"WEEK":i==2?"MONTH":"YEAR")+") AND CURRENT_DATE() GROUP BY id ORDER BY fav DESC LIMIT "+offset+","+howMany+";");
			while(r.next())
			{
				c.add(new Picture(
						"http://127.0.0.1/img/"+(int)r.getObject(1)+"."+(((int)r.getObject(5))==1?"jpg":(((int)r.getObject(5))==2?"bmp":(((int)r.getObject(5))==3?"gif":(((int)r.getObject(5))==4?"png":(((int)r.getObject(5))==5?"wbmp":"jpeg"))))), 
						(int)r.getObject(1), 
						r.getObject(2).toString(), 
						r.getObject(3).toString(), 
						getUser((int)r.getObject(4)),
						getComents((int)r.getObject(1)), 
						getTags((int)r.getObject(1)),
						(Date)r.getDate(6),
						(long)r.getObject(7)));
			}st.close();
			return c;
		} catch (SQLException e) {
			System.out.println("====\nBl¹d obrazów \n" + e.getMessage() + ": " + e.getErrorCode() + "\n=====");
			return null;
		}
	}
}

