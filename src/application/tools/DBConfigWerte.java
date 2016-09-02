package application.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DBConfigWerte {
	
	private Path dateiPfad;
	private List<String> geleseneZeilen;
	private Map<String, String> configWerte = new HashMap<>();
	private static String passwort;
	private static String userName;
	private static String treiber;
	private static String url;
		
	public DBConfigWerte() throws IOException {
		dateiPfad = MeinFileChooser.chooseFile().toPath();
		geleseneZeilen = Files.lines(dateiPfad).collect(Collectors.toList());
		for (String zeile : geleseneZeilen) {
			String[] zeilenArray = zeile.split("=");
			configWerte.put(zeilenArray[0].trim(), zeilenArray[1].trim());
		}
	}

	public String getValue(String property) {
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
	
	public void pruefenObWertExisitiert(String property) {
		if (!existiert(property)) {
			throw new NoSuchElementException("Element existiert nicht!");
		}
	}
	
	public boolean existiert(String property) {
		return configWerte.containsKey(property);
	}
	
	// TODO DBConfigWerte
	// Daten für Zugang a
}
