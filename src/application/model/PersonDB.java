package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class PersonDB {

    private static PreparedStatement ps;
	
    public static void erstellePerson(Connection verbindung, Person p) throws SQLException {
    	
    	try {ps = verbindung.prepareStatement("INSERT INTO Person (PersonID, Name,"
        	 		+ "Vorname1, Vorname2, Geschlecht,Geburtsdatum, HandyNr1, EMailAdresse1)"
        	 		+ "VALUES(NEXT VALUE FOR PersonIDSequence,?,?,?,?,?,?,?)");
        	 ps.setString(1, p.getName());
        	 ps.setString(2, p.getVorname1());
        	 ps.setString(3, p.getVorname2());
        	 ps.setString(4, p.getGeschlecht());
        	 ps.setDate(5, java.sql.Date.valueOf(p.getGeburtsdatum()));
        	 ps.setString(6, p.getHandyNr1());
        	 ps.setString(7, p.geteMailAdresse1());
        	 ps.executeUpdate();
        	 
        	 System.out.println("Insert");
        	 
        } catch (SQLException ex) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.out.println("Kein Insert");
        } finally {
            ps.close();
//            verbindung.close();
        }
    }

	public static void aenderePerson(Connection verbindung, Person p) throws SQLException {
    	
//		ps = verbindung.prepareStatement("DECLARE @PersonID INT "
//				+ "Set @PersonID = ?"
//				+ "UPDATE Person SET "
//    			+ "Name = ?,"
//    			+ "Vorname1 = ?,"
//    			+ "Vorname2 = ?,"
//    			+ "Geschlecht = ?,"
//    			+ "Geburtsdatum = ?,"
//    			+ "HandyNr1 = ?,"
//    			+ "EMailAdresse1 = ?"
//    			+ "WHERE PersonID = @PersonID");
//      
//			 ps.setInt(1, p.getPersonID());
//			 ps.setString(2, p.getName());
//        	 ps.setString(3, p.getVorname1());
//        	 ps.setString(4, p.getVorname2());
//        	 ps.setString(5, p.getGeschlecht());
//        	 ps.setDate(6, java.sql.Date.valueOf(p.getGeburtsdatum()));
//        	 ps.setString(7, p.getHandyNr1());
//        	 ps.setString(8, p.geteMailAdresse1());
//        	 ps.executeUpdate();
//        	 
//        	 System.out.println("Update");
		
    	try {ps = verbindung.prepareStatement("UPDATE Person SET "
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
        	 
        	 System.out.println("Update");
        	 
        } catch (SQLException ex) {
//        	TODO Fehlerbehandlung ordentlich einrichten
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
//                    ex);
        	System.out.println(ex.getMessage());
        	System.out.println("Kein Update");
        } finally {
            ps.close();
//            verbindung.close();
        }		
	}
	
}
