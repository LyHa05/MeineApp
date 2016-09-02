package application.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static Connection conn;

	// TODO Dialog für Abfrage der Login-Daten in DB
	// private static String url = "jdbc:mysql://localhost/company";
	//
	// private static String user = "root";
	//
	// private static String pass = "root";
	
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

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

		conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databasename=test4;user=sa;password=start123$");

		return conn;

	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		if (conn != null && !conn.isClosed())

			return conn;

		connect();

		return conn;

	}
	
	public static void close() throws SQLException {
		if (conn != null && !conn.isClosed())
			conn.close();
	}

}
