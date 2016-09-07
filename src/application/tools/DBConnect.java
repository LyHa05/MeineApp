package application.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static Connection conn;
	private static String url = "jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14";
	private static String user;
	private static String pass;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
//	private static String database;


	public static Connection connect() throws SQLException {
		
		try {

			Class.forName(driver).newInstance();

		} catch (ClassNotFoundException cnfe) {

			System.err.println("Error: " + cnfe.getMessage());

		} catch (InstantiationException ie) {

			System.err.println("Error: " + ie.getMessage());

		} catch (IllegalAccessException iae) {

			System.err.println("Error: " + iae.getMessage());

		}

		// MS SQL
//		conn = DriverManager.getConnection(url + ";databasename=" + database + ";user=" + user + ";password=" + pass);
//		jdbc:sqlserver://localhost;databasename=test4;user=sa;password=start123$
		
		// Oracle SQL
		conn = DriverManager.getConnection(url, user, pass);

		System.out.println("Verbindung hergestellt.");
		
		return conn;

	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {

		if (conn != null && !conn.isClosed())

			return conn;

		connect();

		return conn;

	}
	
	/**
	 * @param url the url to set
	 */
	public static void setUrl(String url) {
		DBConnect.url = url;
	}

	/**
	 * @param user the user to set
	 */
	public static void setUser(String user) {
		DBConnect.user = user;
	}

	/**
	 * @param pass the pass to set
	 */
	public static void setPass(String pass) {
		DBConnect.pass = pass;
	}

	/**
	 * @param driver the driver to set
	 */
	public static void setDriver(String driver) {
		DBConnect.driver = driver;
	}
	
//	/**
//	 * @param database the database to set
//	 */
//	public static void setDatabase(String database) {
//		DBConnect.database = database;
//	}

	public static void close() throws SQLException {
		if (conn != null && !conn.isClosed())
			conn.close();
	}

}
