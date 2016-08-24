package application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static Connection conn;

	// TODO
	// private static String url = "jdbc:mysql://localhost/company";
	//
	// private static String user = "root";
	//
	// private static String pass = "root";
	
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public Connection connect() throws SQLException {

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

	public Connection getConnection() throws SQLException, ClassNotFoundException {

		if (conn != null && !conn.isClosed())

			return conn;

		connect();

		return conn;

	}

}
