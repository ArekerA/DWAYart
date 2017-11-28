package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TworzenieBazyDAnych {
	/**
	 * Metoda ³aduje sterownik jdbc
	 * @return true/false
	 */
	public static boolean checkDriver(String driver) {
		// LADOWANIE STEROWNIKA
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance();
			return true;
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			return false;
		}
	}
	/**
	 * Metoda s³u¿y do nawi¹zania po³¹czenia z baz¹ danych
	 * 
	 * @param adress - adres bazy danych
	 * @param dataBaseName - nazwa bazy
	 * @param userName - login do bazy
	 * @param password - has³o do bazy
	 * @return - po³¹czenie z baz¹
	 */
	public static Connection connectToDatabase(String kindOfDatabase, String adress,
			String dataBaseName, String userName, String password) {
		System.out.print("\nLaczenie z baz¹ danych:");
		String baza = kindOfDatabase + adress + "/" + dataBaseName;
		// objasnienie opisu bazy:
		// jdbc: - mechanizm laczenia z baza (moze byc inny, np. odbc)
		// mysql: - rodzaj bazy
		// adress - adres serwera z baza (moze byc tez z nazwy)
		// dataBaseName - nazwa bazy
		java.sql.Connection connection = null;
		try {
			connection = DriverManager.getConnection(baza, userName, password);
		} catch (SQLException e) {
			System.out.println("Blad przy po³¹czeniu z baz¹ danych!");
			System.exit(1);
		}
		return connection;
	}
	/**
	 * Metoda s³u¿y do po³¹czenia z MySQL bez wybierania konkretnej bazy
	 * @return referencja do uchwytu bazy danych
	 */
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
	
	/**
	 * tworzenie obiektu Statement przesy³aj¹cego zapytania do bazy connection
	 * 
	 * @param connection - po³¹czenie z baz¹
	 * @return obiekt Statement przesy³aj¹cy zapytania do bazy
	 */
	private static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("B³¹d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(3);
		}
		return null;
	}

	/**
	 * Zamykanie po³¹czenia z baz¹ danych
	 * 
	 * @param connection - po³¹czenie z baz¹
	 * @param s - obiekt przesy³aj¹cy zapytanie do bazy
	 */
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

	/**
	 * Wykonanie kwerendy i przes³anie wyników do obiektu ResultSet
	 * 
	 * @param s - Statement
	 * @param sql - zapytanie
	 * @return wynik
	 */
	private static ResultSet executeQuery(Statement s, String sql) {
		try {
			return s.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return null;
	}
	private static int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return -1;
	}
	
	/**
	 * Wyœwietla dane uzyskane zapytaniem select
	 * @param r - wynik zapytania
	 */
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
	/**
	 * Metoda pobiera dane na podstawie nazwy kolumny
	 */
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

	public static void main(String[] args) {
		if (checkDriver("com.mysql.jdbc.Driver"))
			System.out.println(" ... OK");
		else
			System.exit(1);
		// 2 sposób po³¹czenia
		Connection con = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
		Statement st = createStatement(con);
		// próba wybrania bazy
		if (executeUpdate(st, "USE nowaBaza;") == 0)
			System.out.println("Baza wybrana");
		else {
			System.out.println("Baza nie istnieje! Tworzymy bazê: ");
			if (executeUpdate(st, "create Database users;") == 1)
				System.out.println("Baza utworzona");
			else
				System.out.println("Baza nieutworzona!");
			if (executeUpdate(st, "USE nowaBaza;") == 0)
				System.out.println("Baza wybrana");
			else
				System.out.println("Baza niewybrana!");
		}
		if (executeUpdate(st,
				"CREATE TABLE uzytkownicy_ ( id INT NOT NULL, login VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL, name VARCHAR(50) NOT NULL, sex VARCHAR(50) NOT NULL, PRIMARY KEY (id) );") == 0)
			System.out.println("Tabela utworzona");
		else
			System.out.println("Tabela nie utworzona!");
		String sql = "INSERT INTO uzytkownicy_ VALUES(1, 'admin', 'admin', 'admin@admin.com', 'admin', 'male');";
		executeUpdate(st, sql);
		sql = "Select * from uzytkownicy_;";
		printDataFromQuery(executeQuery(st, sql));
		closeConnection(con, st);
	/*	java.sql.Connection connection = connectToDatabase("jdbc:mysql://", "127.0.0.1", "hotel", "root", "root");
		if (connection != null)
			System.out.print(" polaczenie OK\n");
		// WYKONYWANIE OPERACJI NA BAZIE DANYCH
		System.out.println("Pobieranie danych z bazy:");
		sql = "Select * from rezerwacje;";
		Statement s = createStatement(connection);
		ResultSet r = executeQuery(s, sql);
		// printDataFromQuery(r);
		sqlGetDataByName(r);
		closeConnection(connection, s);*/
	}
}
