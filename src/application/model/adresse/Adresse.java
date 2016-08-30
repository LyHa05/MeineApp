package application.model.adresse;

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

	public Adresse() {
		this.adressID = new SimpleIntegerProperty(0);
		this.strasse = new SimpleStringProperty(null);
		this.zusatz = new SimpleStringProperty(null);
		this.plz = new SimpleStringProperty(null);
		this.ort = new SimpleStringProperty(null);
		this.land = new SimpleStringProperty(null);
		this.festnetzNr = new SimpleStringProperty(null);
	}

	/**
	 * @return the adressID
	 */
	public int getAdressID() {
		return adressID.get();
	}

	/**
	 * @param adressID the adressID to set
	 */
	public void setAdressID(int adressID) {
		this.adressID.set(adressID);
	}
	
    public IntegerProperty adressIDProperty() {
        return adressID;
    }

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse.get();
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse.set(strasse);
	}
	
    public StringProperty strasseProperty() {
        return strasse;
    }

	/**
	 * @return the zusatz
	 */
	public String getZusatz() {
		return zusatz.get();
	}

	/**
	 * @param zusatz the zusatz to set
	 */
	public void setZusatz(String zusatz) {
		this.zusatz.set(zusatz);
	}
	
    public StringProperty zusatzProperty() {
        return zusatz;
    }

	/**
	 * @return the plz
	 */
	public String getPlz() {
		return plz.get();
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(String plz) {
		this.plz.set(plz);
	}

    public StringProperty plzProperty() {
        return plz;
    }
	
	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort.get();
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort.set(ort);
	}
	
    public StringProperty ortProperty() {
        return ort;
    }

	/**
	 * @return the land
	 */
	public String getLand() {
		return land.get();
	}

	/**
	 * @param land the land to set
	 */
	public void setLand(String land) {
		this.land.set(land);
	}
	
    public StringProperty landProperty() {
        return land;
    }

	/**
	 * @return the festnetzNr
	 */
	public String getFestnetzNr() {
		return festnetzNr.get();
	}

	/**
	 * @param festnetzNr the festnetzNr to set
	 */
	public void setFestnetzNr(String festnetzNr) {
		this.festnetzNr.set(festnetzNr);
	}
	
	
}
