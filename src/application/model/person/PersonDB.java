package application.model.person;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.tools.DBConnect;

/**
 * @author Lydia Pflug
 * @date 25.08.2016
 */

public class PersonDB {

    private static PreparedStatement ps;
	
    
    // TODO Person ohne Geburtsdatum ermöglichen!!!
    public static void erstellePerson(Person p, EMail eM) throws SQLException, IOException {
    	
		// AutoCommit ausgestellt
		DBConnect.connect().setAutoCommit(false);
    	
    	try {	
    		ps = DBConnect.connect().prepareStatement("INSERT INTO Person (Name,"
        	 		+ "Vorname1, Vorname2, Geschlecht,Geburtsdatum, HandyNr1, HandyNr2) "
        	 		+ "VALUES(?,?,?,?,?,?,'')");
        	 ps.setString(1, p.getName());
        	 ps.setString(2, p.getVorname1());
        	 ps.setString(3, p.getVorname2());
        	 ps.setString(4, p.getGeschlecht());
        	 ps.setDate(5, java.sql.Date.valueOf(p.getGeburtsdatum()));
        	 ps.setString(6, p.getHandyNr1());
        	 // TODO hier Umsetzung fuer 2. Handynummer einfuegen
        	 ps.executeUpdate();
        	 
        	 // Statement geloescht
        	 ps.close();
        	 
        	 // TODO E-Mails extra 
        	 ps = DBConnect.connect().prepareStatement("INSERT INTO EMail (EMailAdresse, Gehoert) "
         	 		+ "VALUES(?,?)");
         	 ps.setString(1, eM.getEMailAdresse());
         	 ps.setObject(2, eM.getGehoert());
         	 ps.executeUpdate();
        	 
        	 // Aenderungen commited
        	 DBConnect.connect().commit();
        	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        	// alle Aenderungen zurueckgerollt
        	DBConnect.connect().rollback();
        	
        } finally {
        	if (ps != null) ps.close();
            DBConnect.close();
        }
    }

    // TODO weiter anpassen
    
	public static void aenderePerson(Person p) throws SQLException, IOException {
    	
		// AutoCommit ausgestellt
		DBConnect.connect().setAutoCommit(false);
		
    	try {
    		ps = DBConnect.connect().prepareStatement("UPDATE Person SET "
    			+ "Name = ?,"
    			+ "Vorname1 = ?,"
    			+ "Vorname2 = ?,"
    			+ "Geschlecht = ?,"
    			+ "Geburtsdatum = ?,"
    			+ "HandyNr1 = ?,"
    			+ "EMailAdresse1 = ? "
    			+ "WHERE PersonID = ?");
      
        	 ps.setString(1, p.getName());
        	 ps.setString(2, p.getVorname1());
        	 ps.setString(3, p.getVorname2());
        	 ps.setString(4, p.getGeschlecht());
        	 ps.setDate(5, java.sql.Date.valueOf(p.getGeburtsdatum()));
        	 ps.setString(6, p.getHandyNr1());
        	 ps.setString(7, p.geteMailAdresse1());
        	 ps.setInt(8, p.getPersonID());
        	 ps.executeUpdate();
        	 
        	 // Aenderungen commited
        	 DBConnect.connect().commit();
        	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        	// alle Aenderungen zurueckgerollt
        	DBConnect.connect().rollback();
        	
        } finally {
        	if (ps != null) ps.close();
            DBConnect.close();
        }		
	}

	public static void loeschePerson(Person p) throws SQLException, IOException {
		
		// AutoCommit ausgestellt
		DBConnect.connect().setAutoCommit(false);
		
		try {
    		ps = DBConnect.connect().prepareStatement("DELETE FROM Person "
    			+ "WHERE PersonID = ?");
      
        	 ps.setInt(1, p.getPersonID());
        	 ps.executeUpdate();
        	 
        	 // Aenderungen commited
        	 DBConnect.connect().commit();
        	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        	// alle Aenderungen zurueckgerollt
        	DBConnect.connect().rollback();
        	
        } finally {
        	if (ps != null) ps.close();
            DBConnect.close();
        }		
		
	}
	
}
