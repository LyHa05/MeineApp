package application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;
import application.model.Person;


public class PersonDB {

	   static final String JDBC_DRIVER =
			   "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	      "oracle.jdbc.driver.OracleDriver";
	   static final String DB_URL =
	      "jdbc:mysql://localhost:3306/mydatabase";
	   static final String DB_USER = "user1";
	   static final String DB_PASS = "secret";
	   private JdbcRowSet rowSet = null;
	   public PersonDB() {
		   //Achtung Anpassung an Oracle!!!
	      try {
//	         Class.forName(JDBC_DRIVER);
//	         rowSet = new JdbcRowSetImpl();
//	         rowSet.setUrl(DB_URL);
//	         rowSet.setUsername(DB_USER);
//	         rowSet.setPassword(DB_PASS);
//	         rowSet.setCommand("SELECT * FROM Person");
//	         rowSet.execute();
	         
				// Abfrage definieren
				String query = "SELECT * FROM Person";
				// Aufruf des Treibers
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				// Datenbankverbindung herstellen
				Connection con = DriverManager.getConnection(
						"jdbc:sqlserver://localhost;databasename=test4;user=sa;password=start123$"); // Kennung
																														// eintragen
				System.out.println("Connection Successful ");
				Statement stmt = con.createStatement();
				ResultSet rst = stmt.executeQuery(query);
				ResultSetMetaData md = rst.getMetaData();
	         
	      } catch (SQLException | ClassNotFoundException ex) {
	         ex.printStackTrace();
	      }
	   }
	   public Person insert(Person p) {
	      try {
	         rowSet.moveToInsertRow();
	         rowSet.updateInt("PersonId", p.getPersonID());
	         rowSet.updateString("Name", p.getName());
	         rowSet.updateString("Vorname1", p.getVorname1());
	         rowSet.updateString("Vorname2", p.getVorname2());
	         rowSet.updateString("Geschlecht", p.getGeschlecht());
	         rowSet.updateDate("Geburtsdatum", java.sql.Date.valueOf(p.getGeburtsdatum()));
	         rowSet.updateString("HandyNr1", p.getHandyNr1());
	         rowSet.updateString("HandyNr2", p.getHandyNr2());
	         rowSet.updateString("EMailAdresse1",p.geteMailAdresse1());
	         rowSet.updateString("EMailAdresse2",p.geteMailAdresse2());
	         rowSet.updateString("EMailAdresse3",p.geteMailAdresse3());
	         rowSet.updateString("EMailAdresse4",p.geteMailAdresse4());
	         rowSet.updateString("EMailAdresse5",p.geteMailAdresse5());
	         rowSet.insertRow();
	         rowSet.moveToCurrentRow();
	      } catch (SQLException ex) {
	         try {
	            rowSet.rollback();
	            p = null;
	         } catch (SQLException e) {

	         }
	         ex.printStackTrace();
	      }
	      return p;
	   }

	   public Person update(Person p) {
	      try {		 
		         rowSet.updateInt("PersonId", p.getPersonID());
		         rowSet.updateString("Name", p.getName());
		         rowSet.updateString("Vorname1", p.getVorname1());
		         rowSet.updateString("Vorname2", p.getVorname2());
		         rowSet.updateString("Geschlecht", p.getGeschlecht());
		         rowSet.updateDate("Geburtsdatum", java.sql.Date.valueOf(p.getGeburtsdatum()));
		         rowSet.updateString("HandyNr1", p.getHandyNr1());
		         rowSet.updateString("HandyNr2", p.getHandyNr2());
		         rowSet.updateString("EMailAdresse1",p.geteMailAdresse1());
		         rowSet.updateString("EMailAdresse2",p.geteMailAdresse2());
		         rowSet.updateString("EMailAdresse3",p.geteMailAdresse3());
		         rowSet.updateString("EMailAdresse4",p.geteMailAdresse4());
		         rowSet.updateString("EMailAdresse5",p.geteMailAdresse5());
		         rowSet.insertRow();	         
		         rowSet.updateRow();
	         rowSet.moveToCurrentRow();
	      } catch (SQLException ex) {
	         try {
	            rowSet.rollback();
	         } catch (SQLException e) {

	         }
	         ex.printStackTrace();
	      }
	      return p;
	   }

	   public void delete() {
	      try {
	         rowSet.moveToCurrentRow();
	         rowSet.deleteRow();
	      } catch (SQLException ex) {
	         try {
	            rowSet.rollback();
	         } catch (SQLException e) { }
	         ex.printStackTrace();
	      }

	   }

	   public Person moveFirst() {
	      Person p = new Person();
	      try {
	         rowSet.first();
	         p.setPersonID(rowSet.getInt("PersonID"));
	         p.setName(rowSet.getString("Name"));
	         p.setVorname1(rowSet.getString("Vorname1"));
	         p.setVorname2(rowSet.getString("Vorname2"));
	         p.setGeschlecht(rowSet.getString("Geschlecht"));
	         p.setGeburtsdatum(rowSet.getDate("Geburtsdatum"));
	         p.setHandyNr1(rowSet.getString("HandyNr1"));
	         p.setHandyNr2(rowSet.getString("HandyNr2"));
	         p.seteMailAdresse1(rowSet.getString("EMailAdresse1"));
	         p.seteMailAdresse2(rowSet.getString("EMailAdresse2"));
	         p.seteMailAdresse3(rowSet.getString("EMailAdresse3"));
	         p.seteMailAdresse4(rowSet.getString("EMailAdresse4"));
	         p.seteMailAdresse5(rowSet.getString("EMailAdresse5"));
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return p;
	   }

	   public Person moveLast() {
	      Person p = new Person();
	      try {
	         rowSet.last();
	         p.setPersonId(rowSet.getInt("personId"));
	         p.setFirstName(rowSet.getString("firstName"));
	         p.setMiddleName(rowSet.getString("middleName"));
	         p.setLastName(rowSet.getString("lastName"));
	         p.setEmail(rowSet.getString("email"));
	         p.setPhone(rowSet.getString("phone"));

	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return p;
	   }

	   public Person moveNext() {
	      Person p = new Person();
	      try {
	         if (rowSet.next() == false)
	            rowSet.previous();
	         p.setPersonId(rowSet.getInt("personId"));
	         p.setFirstName(rowSet.getString("firstName"));
	         p.setMiddleName(rowSet.getString("middleName"));
	         p.setLastName(rowSet.getString("lastName"));
	         p.setEmail(rowSet.getString("email"));
	         p.setPhone(rowSet.getString("phone"));

	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return p;
	   }

	   public Person movePrevious() {
	      Person p = new Person();
	      try {
	         if (rowSet.previous() == false)
	            rowSet.next();
	         p.setPersonId(rowSet.getInt("personId"));
	         p.setFirstName(rowSet.getString("firstName"));
	         p.setMiddleName(rowSet.getString("middleName"));
	         p.setLastName(rowSet.getString("lastName"));
	         p.setEmail(rowSet.getString("email"));
	         p.setPhone(rowSet.getString("phone"));

	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return p;
	   }

	   public Person getCurrent() {
	      Person p = new Person();
	      try {
	         rowSet.moveToCurrentRow();
	         p.setPersonId(rowSet.getInt("personId"));
	         p.setFirstName(rowSet.getString("firstName"));
	         p.setMiddleName(rowSet.getString("middleName"));
	         p.setLastName(rowSet.getString("lastName"));
	         p.setEmail(rowSet.getString("email"));
	         p.setPhone(rowSet.getString("phone"));
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return p;
	   }
	}
