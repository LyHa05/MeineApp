package application.model.gs;

import application.model.person.Person;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Lydia Pflug
 * @date 01.09.2016
 */

public class Geschenk {

	private IntegerProperty geschenkID;
	private IntegerProperty jahr;
	private StringProperty anlass;
	private StringProperty memo;
	private IntegerProperty preis;
	private ObjectProperty<Person> erhaelt;
	
	public Geschenk() {
		
	}
	
	public Geschenk(int gID, int jahr, String anlass, String memo, int preis, Person person) {
		this.geschenkID = new SimpleIntegerProperty(gID);
		this.jahr = new SimpleIntegerProperty(jahr);
		this.anlass = new SimpleStringProperty(anlass);
		this.memo = new SimpleStringProperty(memo);
		this.preis = new SimpleIntegerProperty(preis);
		this.erhaelt = new SimpleObjectProperty<Person>(person);
	}
	
	// Typ muss fuer Person nicht geprueft werden, da nur Person uebergeben wird
	// und da Instanzvariable gehoert nur eine Person zugewiesen werden kann 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Geschenk(int gID, int jahr, String anlass, String memo, int preis, Object person) {
		this.geschenkID = new SimpleIntegerProperty(gID);
		this.jahr = new SimpleIntegerProperty(jahr);
		this.anlass = new SimpleStringProperty(anlass);
		this.memo = new SimpleStringProperty(memo);
		this.preis = new SimpleIntegerProperty(preis);
		this.erhaelt = new SimpleObjectProperty(person);
	}
	
	public int getGeschenkID() {
		return geschenkID.get();
	}

	public void setGeschenkID(int geschenkID) {
		this.geschenkID.set(geschenkID);
	}

    public IntegerProperty geschenkIDProperty() {
        return geschenkID;
    }
    
	public int getJahr() {
		return jahr.get();
	}

	public void setJahrID(int jahr) {
		this.jahr.set(jahr);
	}

    public IntegerProperty jahrProperty() {
        return jahr;
    }
	
	public String getAnlass() {
		return anlass.get();
	}

	public void setAnlass(String anlass) {
		this.anlass.set(anlass);
	}

    public StringProperty anlassProperty() {
        return anlass;
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
        
    public int getPreis() {
		return preis.get();
	}

	public void setPreis(int preis) {
		this.preis.set(preis);
	}

    public IntegerProperty preisProperty() {
        return preis;
    }
    
    public Person getErhaelt() {
    	return erhaelt.get();
    }

    public void setErhaelt(Person person) {
    	this.erhaelt.set(person);
    }
    
    public ObjectProperty<Person> personProperty() {
    	return erhaelt;
    }
}
