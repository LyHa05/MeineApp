package application.model.stammdaten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.tools.DBConnect;

public class StammdatenDB {
	
    // Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static ArrayList<String> stringArray;
    
	private static Connection conn;
	
	private static void verbinden() throws SQLException {
		conn = DBConnect.connect();
	}

	public static ArrayList<String> comboBoxDatenLesen(String kategorie) throws SQLException {
		
		stringArray = new ArrayList<String>();
		
		verbinden();

		try {    	
	        // Execute query and store result in a resultset
	        ps = conn.prepareStatement(""
	        		+ "Select StammdatenWert.Wert "
	        		+ "FROM StammdatenWert "
	        		+ "JOIN StammdatenKategorie ON StammdatenWert.KategorieID = StammdatenKategorie.KategorieID "
	        		+ "WHERE StammdatenKategorie.Kategorie Like ?"
	        		);
	        ps.setString(1, kategorie);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            //get string from db,whichever way and add some sample data in personComboBoxDaten
	        	stringArray.add(rs.getString(1));
	        }
		} catch (SQLException e) {
			System.err.println("Error" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			conn.close();
		}
		return stringArray;
	}

}
