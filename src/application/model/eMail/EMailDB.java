package application.model.eMail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import application.model.person.Person;
import application.tools.DBConnect;

/**
 * @author Lydia Pflug
 * @date 16.09.2016
 */

public class EMailDB {
	
	private static PreparedStatement ps;
	private static Connection conn;
	
	public static void verbinden() throws SQLException {
		conn = DBConnect.connect();
	}

	public static void erstelleEMail(Person p, EMail eM) throws SQLException {
		
		verbinden();
		
		try {
			ps = conn.prepareStatement("INSERT INTO EMail (EMailAdresse,Gehoert) "
					+ "VALUES(?,?)");
			ps.setString(1, eM.getEMailAdresse());
			ps.setInt(2, p.getPersonID());
			ps.executeUpdate();
		
			 // Aenderungen committed
			conn.commit();
			
		} catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        	// alle Aenderungen zurueckgerollt
        	conn.rollback();
        	
        } finally {
        	if (ps != null) ps.close();
        	conn.close();
        }
	}

	public static void aendereEMail(Person p, EMail eM) throws SQLException {
		
		verbinden();
		
		try {
			ps = conn.prepareStatement("UPDATE EMail SET "
					+ "EMailAdresse = ? "
					+ ", Gehoert = ? "
					+ "WHERE EMailID = ?");
			
			ps.setString(1, eM.getEMailAdresse());
			ps.setInt(2, p.getPersonID());
			ps.setInt(3, eM.getEMailID());
			ps.executeUpdate();
			
	       	 // Aenderungen commited
			conn.commit();
			
		} catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        	// alle Aenderungen zurueckgerollt
        	conn.rollback();
        	
        } finally {
        	if (ps != null) ps.close();
        	conn.close();
        }
		
	}

	public static void loescheEMail(EMail eM) throws SQLException {
		
		verbinden();
		
		try {
			// Adresse und alle Referenzen zu Personen loeschen
			ps = conn.prepareStatement("DELETE FROM EMail WHERE EMailID = ?");
      
        	 ps.setInt(1, eM.getEMailID());
        	 ps.executeUpdate();        	
        	 
	       	 // Aenderungen commited
        	 conn.commit();     	 
				 	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        	// alle Aenderungen zurueckgerollt
        	conn.rollback();
        	
        } finally {
        	if (ps != null) ps.close();
        	conn.close();
        }		
		
	}
}
