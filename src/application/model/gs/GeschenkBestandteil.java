package application.model.gs;

import application.model.person.Person;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GeschenkBestandteil {

	private IntegerProperty geschenkBestandteilID;
	private StringProperty beschreibung;
	private StringProperty memo;
	private StringProperty kategorie;
	private StringProperty bestandteil;
	private ObjectProperty<Geschenk> bestandteilVon;
	
	public GeschenkBestandteil(int geschenkBestandteilID, String beschreibung, String memo,
			String kategorie, String bestandteil, Geschenk bestandteilVon) {
		
		this.geschenkBestandteilID = new SimpleIntegerProperty(geschenkBestandteilID);
		this.beschreibung = new SimpleStringProperty(beschreibung);
		this.memo = new SimpleStringProperty(memo);
		this.kategorie = new SimpleStringProperty(kategorie);
		this.bestandteil = new SimpleStringProperty(bestandteil);
		this.bestandteilVon = new SimpleObjectProperty<Geschenk>(bestandteilVon);
	}
	
	public int getGeschenkBestandteilID() {
		return geschenkBestandteilID.get();
	}

	public void setGeschenkBestandteilID(int geschenkBestandteilID) {
		this.geschenkBestandteilID.set(geschenkBestandteilID);
	}

    public IntegerProperty geschenkBestandteilIDProperty() {
        return geschenkBestandteilID;
    }
	
    public String getBeschreibung() {
		return beschreibung.get();
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung.set(beschreibung);
	}

    public StringProperty beschreibungProperty() {
        return beschreibung;
    }

    public String getMemo() {
		return memo.get();
	}

	public void setMemo(String memo) {
		this.memo.set(memo);
	}

    public StringProperty memoProperty() {
        return memo;
    }
    
    public String getKategorie() {
		return kategorie.get();
	}

	public void setKategorie(String kategorie) {
		this.kategorie.set(kategorie);
	}

    public StringProperty kategorieProperty() {
        return kategorie;
    }

    public String getBestandteil() {
		return bestandteil.get();
	}

	public void setBestandteil(String bestandteil) {
		this.bestandteil.set(bestandteil);
	}

    public StringProperty bestandteilProperty() {
        return bestandteil;
    }

    public Geschenk getBestandteilVon() {
    	return bestandteilVon.get();
    }

    public void setBestandteilVon(Geschenk geschenk) {
    	this.bestandteilVon.set(geschenk);
    }
    
    public ObjectProperty<Geschenk> bestandteilVonProperty() {
    	return bestandteilVon;
    }

	
}
