package application.model.stammdaten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.tools.DBConnect;

public class StammdatenWertDB {
	
	private static PreparedStatement ps;
	private static Connection conn;
	
	public static void verbinden() throws SQLException {
		conn = DBConnect.connect();
	}

	public static void erstelleWert(StammdatenKategorie sK, StammdatenWert sW) throws SQLException {

		verbinden();
		
		try {
			ps = conn.prepareStatement("INSERT INTO StammdatenWert (KategorieID, Wert) "
					+ "VALUES(?,?)");
			ps.setInt(1, sK.getKategorieID());
			ps.setString(2, sW.getWert());
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

	public static void aendereWert(StammdatenKategorie sK, StammdatenWert sW) throws SQLException {
		
		verbinden();
		
		try {
			ps = conn.prepareStatement("UPDATE StammdatenWert SET "
					+ "KategorieID = ? "
					+ ", Wert = ? "
					+ "WHERE WertID = ?");
			
			ps.setInt(1, sK.getKategorieID());
			ps.setString(2, sW.getWert());
			ps.setInt(3, sW.getWertID());
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

	// TODO Loeschen von Stammdatenwerte eigentlich nicht erlauben
	public static void loescheWert(StammdatenWert sW) throws SQLException {
		
		verbinden();
		
		try {
			// Adresse und alle Referenzen zu Personen loeschen
			ps = conn.prepareStatement("DELETE FROM StammdatenWert WHERE WertID = ?");
      
        	 ps.setInt(1, sW.getWertID());
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
