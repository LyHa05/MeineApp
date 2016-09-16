package application.model.stammdaten;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StammdatenWert {

	private IntegerProperty wertID;
	private ObjectProperty<StammdatenKategorie> kategorieID;
	private StringProperty wert;
	
	public StammdatenWert(int wID, StammdatenKategorie kID, String  wert) {
		this.wertID = new SimpleIntegerProperty(wID);
		this.kategorieID = new SimpleObjectProperty<StammdatenKategorie>(kID);
		this.wert = new SimpleStringProperty(wert);
	}
	
	public StammdatenWert(int wID, Object kID, String  wert) {
		this.wertID = new SimpleIntegerProperty(wID);
		this.kategorieID = new SimpleObjectProperty(kID);
		this.wert = new SimpleStringProperty(wert);
	}
	
	public StammdatenWert() {
		this.wertID = new SimpleIntegerProperty(0);
		this.kategorieID = new SimpleObjectProperty<StammdatenKategorie>(null);
		this.wert = new SimpleStringProperty(null);
	}
	
	/**
	 * @return the wertID
	 */
	public int getWertID() {
		return wertID.get();
	}

	/**
	 * @param wertID the wertID to set
	 */
	public void setWertID(int wertID) {
		this.wertID.set(wertID);
	}

    public IntegerProperty wertIDProperty() {
        return wertID;
    }
    
	/**
	 * @return the kategorieID
	 */
	public StammdatenKategorie getKategorieID() {
		return kategorieID.get();
	}

	/**
	 * @param kategorieID the kategorieID to set
	 */
	public void setKategorieID(StammdatenKategorie kategorieID) {
		this.kategorieID.set(kategorieID);
	}

    public ObjectProperty<StammdatenKategorie> kategorieIDProperty() {
        return kategorieID;
    }
        
	/**
	 * @return the wert
	 */
	public String getWert() {
		return wert.get();
	}

	/**
	 * @param kategorie the kategorie to set
	 */
	public void setWert(String wert) {
		this.wert.set(wert);
	}
	
    public StringProperty wertProperty() {
        return wert;
    }
}
