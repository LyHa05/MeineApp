package application.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DBConfigWerte {
	
	private static Path dateiPfad;
	private static List<String> geleseneZeilen;
	private static Map<String, String> configWerte = new HashMap<>();
		
	public static void dateiLesen() throws IOException {
//		dateiPfad = MeinFileChooser.chooseFile().toPath();
		dateiPfad = Paths.get("C:/Users/DBConfigWerte.txt");
		geleseneZeilen = Files.lines(dateiPfad).collect(Collectors.toList());
		for (String zeile : geleseneZeilen) {
			String[] zeilenArray = zeile.split("=");
			configWerte.put(zeilenArray[0].trim(), zeilenArray[1].trim());
		}
	}

	public static String getValue(String property) {
		pruefenObWertExisitiert(property);

		return configWerte.get(property);

	}
	
	public int getIntValue(String property) {
		pruefenObWertExisitiert(property);
		try {
			return Integer.parseInt(configWerte.get(property));
		} catch (NumberFormatException nfe) {
			System.err.println(property + "kann nicht in einen Integer umgewandelt werden.");
		}
		return 0;
	}
	
	public static void pruefenObWertExisitiert(String property) {
		if (!existiert(property)) {
			throw new NoSuchElementException("Element existiert nicht!");
		}
	}
	
	public static boolean existiert(String property) {
		return configWerte.containsKey(property);
	}
	
	public static void anmeldungsDatenUebergen() throws SQLException, IOException {
		
		dateiLesen();
		
		DBConnect.setUrl(getValue("url"));
		DBConnect.setDriver(getValue("driver"));
		DBConnect.setPass(getValue("passwort"));
		DBConnect.setUser(getValue("userName"));
		DBConnect.setDatabase(getValue("database"));
	}

}
