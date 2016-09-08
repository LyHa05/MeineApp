package application.model.adresse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.model.person.Person;
import application.tools.DBConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Lydia Pflug
 * @date 30.08.2016
 */

public class AdressDB {
	
	private static PreparedStatement ps;
	private static ResultSet rs;

	public static void erstelleAdresseFuerPerson(Adresse a, Person p) throws SQLException, IOException {
		try {
    		// AutoCommit ausgestellt
    		DBConnect.connect().setAutoCommit(false);
			
			ps = DBConnect.connect().prepareStatement("INSERT INTO Adresse (Strasse"
					+ ", Zusatz, PLZ ,Ort ,Land ,FestnetzNr) "
					+ "VALUES(?,?,?,?,?,?)");
			ps.setString(1, a.getStrasse());
			ps.setString(2, a.getZusatz());
			ps.setString(3, a.getPlz());
			ps.setString(4, a.getOrt());
			ps.setString(5, a.getLand());
			ps.setString(6, a.getFestnetzNr());
			ps.executeUpdate();
			ps.close();
			
			ps = DBConnect.connect().prepareStatement(""
					+ "INSERT INTO WohnhaftIn VALUES(?,(SELECT MAX(AdressID) From Adresse),0)");
			ps.setInt(1, p.getPersonID());
			ps.executeUpdate();
			ps.close();
			
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

	public static void erstelleAdresse(Adresse a) throws SQLException, IOException {
		try {
    		// AutoCommit ausgestellt
    		DBConnect.connect().setAutoCommit(false);
    		
			ps = DBConnect.connect().prepareStatement("INSERT INTO Adresse (Strasse, Zusatz, PLZ ,Ort ,Land ,FestnetzNr) "
					+ "VALUES(?,?,?,?,?,?)");
			ps.setString(1, a.getStrasse());
			ps.setString(2, a.getZusatz());
			ps.setString(3, a.getPlz());
			ps.setString(4, a.getOrt());
			ps.setString(5, a.getLand());
			ps.setString(6, a.getFestnetzNr());
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

	public static void aendereAdresse(Adresse a) throws SQLException, IOException {
		try {
    		// AutoCommit ausgestellt
    		DBConnect.connect().setAutoCommit(false);
			
			ps = DBConnect.connect().prepareStatement("UPDATE Adresse SET "
					+ "Strasse = ? "
					+ ", Zusatz = ? "
					+ ", PLZ = ? "
					+ ", Ort = ? "
					+ ", Land = ? "
					+ ", FestnetzNr = ? "
					+ "WHERE AdressID = ?");
			
			ps.setString(1, a.getStrasse());
			ps.setString(2, a.getZusatz());
			ps.setString(3, a.getPlz());
			ps.setString(4, a.getOrt());
			ps.setString(5, a.getLand());
			ps.setString(6, a.getFestnetzNr());
			ps.setInt(7, a.getAdressID());
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

	public static void zuordnenAdresse(Adresse a, Person p) throws SQLException, IOException {
		try {
			
    		// AutoCommit ausgestellt
    		DBConnect.connect().setAutoCommit(false);
			
			ps = DBConnect.connect().prepareStatement(""
					+ "INSERT INTO WohnhaftIn VALUES(?,?,0)");
			ps.setInt(1, p.getPersonID());
			ps.setInt(2, a.getAdressID());
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

	public static void loescheAdresseFuerPerson(Adresse a, Person p) throws SQLException, IOException {
		try {
			
    		// AutoCommit ausgestellt
    		DBConnect.connect().setAutoCommit(false);
			
			// Pruefen, inwieweit andere Personen zur Adresse eine Referenz besitzen
			ArrayList<Integer> personIDErgebnisse = new ArrayList<Integer>();
			
			ps = DBConnect.connect().prepareStatement("SELECT PersonID FROM WohnhaftIn"
					+ " WHERE AdressID = ?");
			ps.setInt(1, a.getAdressID());
			rs = ps.executeQuery();
				
			while (rs.next()) {
				personIDErgebnisse.add(rs.getInt(1));
			}
			
			// Verbindungen schliessen
			rs.close();
			ps.close();
			DBConnect.close();
			
			// Adresse und Referenz fuer Person loeschen
			if (personIDErgebnisse.size() == 1) {
				ps = DBConnect.connect().prepareStatement(""
						+ "DELETE FROM WohnhaftIn WHERE AdressID = ? "
						+ "DELETE FROM Adresse WHERE AdressID = ?");
	      
	        	 ps.setInt(1, a.getAdressID());
	        	 ps.setInt(2, a.getAdressID());
	        	 ps.executeUpdate();
				
			// Referenz der Person zur Adresse loeschen
			} else if (personIDErgebnisse.size() > 1) {
				ps = DBConnect.connect().prepareStatement(""
						+ "DELETE FROM WohnhaftIn WHERE PersonID = ? AND AdressID = ?");
	      
	        	ps.setInt(1, p.getPersonID()); 
				ps.setInt(2, a.getAdressID());
	        	 ps.executeUpdate();
	        	 
	             Alert alert = new Alert(AlertType.ERROR);
	             alert.setTitle("Error");
	             alert.setHeaderText("Adresse konnte nicht vollstaendig geloescht werden");
	             // TODO Personen, bei denen Referenz noch besteht, auflisten
	             alert.setContentText("Die Adresse ist noch bei ... hinterlegt");

	             alert.showAndWait();
			}
			
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

	// TODO Pruefen, ob ueberhaupt Verknuepfung besteht
	public static void loescheAdresse(Adresse a) throws SQLException, IOException {
		try {
    		// AutoCommit ausgestellt
    		DBConnect.connect().setAutoCommit(false);
			
			// Adresse und alle Referenzen zu Personen loeschen
			ps = DBConnect.connect().prepareStatement(""
					+ "DELETE FROM WohnhaftIn WHERE AdressID = ? "
					+ "DELETE FROM Adresse WHERE AdressID = ?");
      
        	 ps.setInt(1, a.getAdressID());
        	 ps.setInt(2, a.getAdressID());
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
