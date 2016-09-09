package application.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import application.model.adresse.Adresse;
import application.model.adresse.WohnhaftIn;
import application.model.person.Person;

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
		ArrayList<WohnhaftIn> druckDatenAuswahlEinzel = new ArrayList<>();
		ArrayList<WohnhaftIn> druckDatenAuswahlDoppel = new ArrayList<>();
		HashMap<Integer,ArrayList<WohnhaftIn>> druckDatenAuswahlDoppelHash = new HashMap<>();
		
		bw.append("1.Zeile; 2.Zeile; 3.Zeile");
		bw.newLine();
	
		// Adressen mit mehreren Bewohnern trennen
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
		
		// Adressen mit einem Bewohner in Datei schreiben
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
		
		// Personen einer Adresse zusammen fuehren
		for(WohnhaftIn wi : druckDatenAuswahlDoppel) {
			if (druckDatenAuswahlDoppelHash.containsKey(wi.getObjAdresse().getAdressID())) {
				druckDatenAuswahlDoppelHash.get(wi.getObjAdresse().getAdressID()).add(wi);
			} else {
				ArrayList<WohnhaftIn> wohnendePersonen = new ArrayList<> ();
				wohnendePersonen.add(wi);
				druckDatenAuswahlDoppelHash.put(wi.getObjAdresse().getAdressID(), wohnendePersonen);
			}
		}
		
		System.out.println(druckDatenAuswahlDoppelHash);
		
		for (Entry<Integer, ArrayList<WohnhaftIn>> eintrag : druckDatenAuswahlDoppelHash.entrySet()) {
		    
		    ArrayList<WohnhaftIn> value = eintrag.getValue();
		    boolean gleicherNachName = false;
		    
		    for (WohnhaftIn wi1 : value) {
		    	for (WohnhaftIn wi2 : value) {
		    
			    	if (wi1.getObjPerson().getName() == wi2.getObjPerson().getName()) {
			    		gleicherNachName = true;
			    	}
			    	
		    	}
		    }
		    
	    	Iterator<WohnhaftIn> vIter = value.iterator();
	    	
		    if (gleicherNachName) {
		    	
//			    int i = 0;
//		    	while (vIter.hasNext()) {
//		    		System.out.println("Name: " + value.get(i).getObjPerson().getVorname1());
//		    		System.out.println("i: " + i);
//		    		bw.append(value.get(i).getObjPerson().getVorname1());
//		    		if (vIter.hasNext()) {bw.append(" & ");} 
//		    		i++;
//		    	}
		    	
		    	for (int j = 0; j < value.size(); j++) {
		    		System.out.println("Name: " + value.get(j).getObjPerson().getVorname1());
		    		System.out.println("j: " + j);
		    		bw.append(value.get(j).getObjPerson().getVorname1());
		    		if (j < value.size()-1) {bw.append(" & ");} 
					
				}
		    	
		    	
		    } else {
		    	
		    	int k = 0;
		    	while (vIter.hasNext()) {
		    		
		    		if (value.get(k).getObjPerson().getName() != "") {
			    		bw.append(value.get(k).getObjPerson().getVorname1());
			    		bw.append(" ");
			    		bw.append(value.get(k).getObjPerson().getName());
			    		if (vIter.hasNext()) {bw.append(" & ");} 
		    		}
		    		k++;
		    		
		    	}
		    	
		    	int m = 0;
		    	while (vIter.hasNext()) {
		    		
		    		if (value.get(m).getObjPerson().getName() == "") {
			    		bw.append(value.get(m).getObjPerson().getVorname1());
			    		bw.append(" ");
			    		bw.append(value.get(m).getObjPerson().getName());
		    		}
		    		m++;
		    		
		    	}
		    }
		    
//		    
//		    Integer key = eintrag.getKey();
		    
		    
			bw.append("; ");
			bw.append(value.get(0).getObjAdresse().getStrasse());
			bw.append("; ");
			bw.append(value.get(0).getObjAdresse().getPlz());
			bw.append(" ");
			bw.append(value.get(0).getObjAdresse().getOrt());
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
