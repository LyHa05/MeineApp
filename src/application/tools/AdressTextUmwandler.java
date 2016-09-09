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
		
	public static void dateiErstellen() throws IOException {
		
//		File f = new File();
		System.out.println(file.getAbsolutePath() +  "\\" + "DatenAdressEtiketten.txt");
		fw = new FileWriter(file.getAbsolutePath() +  "\\" + "DatenAdressEtiketten.txt");

//		fw = new FileWriter("DatenAdressEtiketten.txt");
		bw = new BufferedWriter(fw);
	}
	
	public static void dateiSchreiben(ArrayList<WohnhaftIn> datenAuswahl) throws IOException {
		bw.append("1.Zeile; 2.Zeile; 3.Zeile");
		bw.newLine();
		
		ArrayList<WohnhaftIn> druckDatenAuswahlEinzel = new ArrayList<>();
		ArrayList<WohnhaftIn> druckDatenAuswahlDoppel = new ArrayList<>();
		
		for(WohnhaftIn wi1 : datenAuswahl) {
			for(WohnhaftIn wi2 : datenAuswahl) {
				if((new Integer(wi1.getObjAdresse().getAdressID())).equals(new Integer(wi2.getObjAdresse().getAdressID()))
						&&
						(!(new Integer(wi1.getObjPerson().getPersonID())).equals(new Integer(wi2.getObjAdresse().getAdressID())))) {
					if (!druckDatenAuswahlDoppel.contains(wi1)) {druckDatenAuswahlDoppel.add(wi1);}
					if (!druckDatenAuswahlDoppel.contains(wi2)) {druckDatenAuswahlDoppel.add(wi2);}
				}
			}
		}
		
		druckDatenAuswahlEinzel.addAll(datenAuswahl);
		druckDatenAuswahlEinzel.removeAll(druckDatenAuswahlDoppel);
		
		System.out.println("druckDatenAuswahlEinzel: " + druckDatenAuswahlEinzel);
		System.out.println("druckDatenAuswahlDoppel: " + druckDatenAuswahlDoppel);
		
		
		for(WohnhaftIn wi : druckDatenAuswahlEinzel) {
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
