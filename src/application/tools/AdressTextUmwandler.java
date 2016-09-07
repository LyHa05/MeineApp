package application.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import application.model.adresse.WohnhaftIn;

public class AdressTextUmwandler {
	
	private static BufferedWriter bw;
	private static FileWriter fw;
	private static File file;
	
	// TODO DirectoryChooser integrieren und File umwandlen
	
	public static void dateiErstellen() throws IOException {
		
//		File f = new File(MeinDirectoryChooser.chooseDirectory());
		fw = new FileWriter(file.getAbsolutePath() +  "/" + "DatenAdressEtiketten.txt");
//		fw = new FileWriter("DatenAdressEtiketten.txt");
		bw = new BufferedWriter(fw);
	}
	
	public static void dateiSchreiben(ArrayList<WohnhaftIn> datenAuswahl) throws IOException {
		bw.append("1.Zeile; 2.Zeile; 3.Zeile");
		bw.newLine();
		
		for(WohnhaftIn wi : datenAuswahl) {
			bw.append(wi.getObjPerson().getVorname1());
			bw.append(" ");
			bw.append(wi.getObjPerson().getName());
			bw.append("; ");
			bw.append(wi.getObjAdresse().getStrasse());
			bw.append("; ");
			bw.append(wi.getObjAdresse().getPlz());
			bw.append(" ");
			bw.append(wi.getObjAdresse().getOrt());
			bw.newLine();	
		}
		
		bw.close();
	}

	/**
	 * @param file the file to set
	 */
	public static void setFile(File file) {
		AdressTextUmwandler.file = file;
	}
	
	

}
