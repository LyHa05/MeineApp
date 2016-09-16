package application.model.stammdaten;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StammdatenKategorie {
	
	private IntegerProperty kategorieID;
	private StringProperty kategorie;
	
	public StammdatenKategorie() {
		this.kategorieID = new SimpleIntegerProperty(0);
		this.kategorie = new SimpleStringProperty(null);
	}
	
	public StammdatenKategorie(int kID, String kategorie) {
		this.kategorieID = new SimpleIntegerProperty(kID);
		this.kategorie = new SimpleStringProperty(kategorie);
	}
	
	/**
	 * @return the kategorieID
	 */
	public int getKategorieID() {
		return kategorieID.get();
	}

	/**
	 * @param kategorieID the kategorieID to set
	 */
	public void setKategorieID(int kategorieID) {
		this.kategorieID.set(kategorieID);
	}

    public IntegerProperty kategorieIDProperty() {
        return kategorieID;
    }
    
	/**
	 * @return the kategorie
	 */
	public String getKategorie() {
		return kategorie.get();
	}

	/**
	 * @param kategorie the kategorie to set
	 */
	public void setKategorie(String kategorie) {
		this.kategorie.set(kategorie);
	}
	
    public StringProperty kategorieProperty() {
        return kategorie;
    }

}
