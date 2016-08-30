package application.model.adresse;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.controller.DBConnect;
import application.model.person.Person;

/**
 * @author Lydia Pflug
 * @date 30.08.2016
 */

public class AdressDB {
	
	private static PreparedStatement ps;

	public static void erstelleAdresseFuerPerson(Adresse a, Person p) throws SQLException {
		try {
			ps = DBConnect.connect().prepareStatement("INSERT INTO Adresse (AdressID, Strasse"
					+ ", Zusatz, PLZ ,Ort ,Land ,FestnetzNr) "
					+ "VALUES(NEXT VALUE FOR AdressIDSequence,?,?,?,?,?,?) "
					+ "INSERT INTO WohnhaftIn VALUES(?,(SELECT MAX(AdressID) From Adresse))");
			ps.setString(1, a.getStrasse());
			ps.setString(2, a.getZusatz());
			ps.setString(3, a.getPlz());
			ps.setString(4, a.getOrt());
			ps.setString(5, a.getLand());
			ps.setString(6, a.getFestnetzNr());
			ps.setInt(7, p.getPersonID());
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

	public static void erstelleAdresse(Adresse a) throws SQLException {
		try {
			ps = DBConnect.connect().prepareStatement("INSERT INTO Adresse (AdressID, Strasse"
					+ ", Zusatz, PLZ ,Ort ,Land ,FestnetzNr) "
					+ "VALUES(NEXT VALUE FOR AdressIDSequence,?,?,?,?,?,?)");
			ps.setString(1, a.getStrasse());
			ps.setString(2, a.getZusatz());
			ps.setString(3, a.getPlz());
			ps.setString(4, a.getOrt());
			ps.setString(5, a.getLand());
			ps.setString(6, a.getFestnetzNr());
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

	public static void aendereAdresse(Adresse a) {
		// TODO Auto-generated method stub aendereAdresse
		
	}

	public static void zuordnenAdresse(Adresse a) {
		// TODO Auto-generated method stub zuordnenAdresse
		
	}

	public static void loescheAdresse(Adresse a) {
		// TODO Auto-generated method stub loescheAdresse
		
	}

}
