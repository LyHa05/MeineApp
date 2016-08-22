package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Lydia Pflug
 * @date 19.08.2016
 */

public class Adresse {

	private IntegerProperty adressID;
	private StringProperty strasse;
	private StringProperty zusatz;
	private StringProperty plz;
	private StringProperty ort;
	private StringProperty land;
	private StringProperty festnetzNr;
	
	public Adresse(int aID, String str, String zusatz, String plz, String ort,
			String land, String fnNr) {

		this.adressID = new SimpleIntegerProperty(aID);
		this.strasse = new SimpleStringProperty(str);
		this.zusatz = new SimpleStringProperty(zusatz);
		this.plz = new SimpleStringProperty(plz);
		this.ort = new SimpleStringProperty(ort);
		this.land = new SimpleStringProperty(land);
		this.festnetzNr = new SimpleStringProperty(fnNr);
		
	}

	/**
	 * @return the adressID
	 */
	public IntegerProperty getAdressID() {
		return adressID;
	}

	/**
	 * @param adressID the adressID to set
	 */
	public void setAdressID(IntegerProperty adressID) {
		this.adressID = adressID;
	}

	/**
	 * @return the strasse
	 */
	public StringProperty getStrasse() {
		return strasse;
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(StringProperty strasse) {
		this.strasse = strasse;
	}

	/**
	 * @return the zusatz
	 */
	public StringProperty getZusatz() {
		return zusatz;
	}

	/**
	 * @param zusatz the zusatz to set
	 */
	public void setZusatz(StringProperty zusatz) {
		this.zusatz = zusatz;
	}

	/**
	 * @return the plz
	 */
	public StringProperty getPlz() {
		return plz;
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(StringProperty plz) {
		this.plz = plz;
	}

	/**
	 * @return the ort
	 */
	public StringProperty getOrt() {
		return ort;
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(StringProperty ort) {
		this.ort = ort;
	}

	/**
	 * @return the land
	 */
	public StringProperty getLand() {
		return land;
	}

	/**
	 * @param land the land to set
	 */
	public void setLand(StringProperty land) {
		this.land = land;
	}

	/**
	 * @return the festnetzNr
	 */
	public StringProperty getFestnetzNr() {
		return festnetzNr;
	}

	/**
	 * @param festnetzNr the festnetzNr to set
	 */
	public void setFestnetzNr(StringProperty festnetzNr) {
		this.festnetzNr = festnetzNr;
	}
	
	
}
