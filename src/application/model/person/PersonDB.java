package application.model.person;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.tools.DBConnect;

/**
 * @author Lydia Pflug
 * @date 25.08.2016
 */

public class PersonDB {

    private static PreparedStatement ps;
	
    public static void erstellePerson(Person p) throws SQLException {
    	
    	try {ps = DBConnect.connect().prepareStatement("INSERT INTO Person (PersonID, Name,"
        	 		+ "Vorname1, Vorname2, Geschlecht,Geburtsdatum, HandyNr1, EMailAdresse1) "
        	 		+ "VALUES(NEXT VALUE FOR PersonIDSequence,?,?,?,?,?,?,?)");
        	 ps.setString(1, p.getName());
        	 ps.setString(2, p.getVorname1());
        	 ps.setString(3, p.getVorname2());
        	 ps.setString(4, p.getGeschlecht());
        	 ps.setDate(5, java.sql.Date.valueOf(p.getGeburtsdatum()));
        	 ps.setString(6, p.getHandyNr1());
        	 ps.setString(7, p.geteMailAdresse1());
        	 ps.executeUpdate();
        	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        	
        } finally {
        	if (ps != null) ps.close();
            DBConnect.close();
        }
    }

//    public static void aenderePerson(Connection verbindung, Person p) throws SQLException {
	public static void aenderePerson(Person p) throws SQLException {
    	
    	try {ps = DBConnect.connect().prepareStatement("UPDATE Person SET "
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
        	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        } finally {
        	if (ps != null) ps.close();
            DBConnect.close();
        }		
	}

	public static void loeschePerson(Person p) throws SQLException {
		try {ps = DBConnect.connect().prepareStatement("DELETE FROM Person "
    			+ "WHERE PersonID = ?");
      
        	 ps.setInt(1, p.getPersonID());
        	 ps.executeUpdate();
        	 
        } catch (SQLException e) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.err.println("Error" + e);
        } finally {
        	if (ps != null) ps.close();
            DBConnect.close();
        }		
		
	}
	
}
