package application.model.person;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EMail {
	
	private IntegerProperty eMailID;
	private StringProperty eMailAdresse;
	private ObjectProperty<Person> gehoert;

	public EMail(int eID, String eAdresse, Person p) {
		this.eMailID = new SimpleIntegerProperty(eID);
		this.eMailAdresse = new SimpleStringProperty(eAdresse);
		this.gehoert = new SimpleObjectProperty(p);
	}
	
	public EMail() {
		this.eMailID = new SimpleIntegerProperty(0);
		this.eMailAdresse = new SimpleStringProperty(null);
		this.gehoert = new SimpleObjectProperty(null);
	}

	/**
	 * @return the personID
	 */
	public int getEMailID() {
		return eMailID.get();
	}

	/**
	 * @param personID the personID to set
	 */
	public void setEMailID(int eMailID) {
		this.eMailID.set(eMailID);
	}

    public IntegerProperty eMailIDProperty() {
        return eMailID;
    }
    
    /**
	 * @return the personID
	 */
	public String getEMailAdresse() {
		return eMailAdresse.get();
	}

	/**
	 * @param personID the personID to set
	 */
	public void setEMailAdresse(String eMailAdresse) {
		this.eMailAdresse.set(eMailAdresse);
	}

    public StringProperty eMailAdresseProperty() {
        return eMailAdresse;
    }
    
    /**
	 * @return the personID
	 */
	public Person getGehoert() {
		return gehoert.get();
	}

	/**
	 * @param personID the personID to set
	 */
	public void setGehoert(Person p) {
		this.gehoert.set(p);
	}

    public ObjectProperty<Person> gehoertProperty() {
        return gehoert;
    }
    
}
