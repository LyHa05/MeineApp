package application.model.person;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Lydia Pflug
 * @date 19.08.2016
 */

public class Person {

	private IntegerProperty personID;
	private StringProperty name;
	private StringProperty vorname1;
	private StringProperty vorname2;
	private StringProperty geschlecht;
	private ObjectProperty<LocalDate> geburtsdatum;
	private StringProperty handyNr1;
	private StringProperty handyNr2;
	
	
	public Person() {
		this.personID = new SimpleIntegerProperty(0);
 		this.name = new SimpleStringProperty(null);
 		this.vorname1 = new SimpleStringProperty(null);
 		this.vorname2 = new SimpleStringProperty(null);
 		this.geschlecht = new SimpleStringProperty(null);
 		this.geburtsdatum = new SimpleObjectProperty<LocalDate>(null);
 		this.handyNr1 = new SimpleStringProperty(null);
 		this.handyNr2 = new SimpleStringProperty(null);
	}
	
	public Person(int pID, String name, String vname1, String vname2, String geschl,
		int gJahr, int gMonat, int gTag, String handy1, String handy2) {
		
		this.personID = new SimpleIntegerProperty(pID);
 		this.name = new SimpleStringProperty(name);
 		this.vorname1 = new SimpleStringProperty(vname1);
 		this.vorname2 = new SimpleStringProperty(vname2);
 		this.geschlecht = new SimpleStringProperty(geschl);
 		this.geburtsdatum = new SimpleObjectProperty<LocalDate>(LocalDate.of(gJahr, gMonat, gTag));
 		this.handyNr1 = new SimpleStringProperty(handy1);
 		this.handyNr2 = new SimpleStringProperty(handy2);
	}
	
	public Person(int pID, String name, String vname1, String vname2, String geschl,
			Date gDatum, String handy1, String handy2) {
			
			this.personID = new SimpleIntegerProperty(pID);
	 		this.name = new SimpleStringProperty(name);
	 		this.vorname1 = new SimpleStringProperty(vname1);
	 		this.vorname2 = new SimpleStringProperty(vname2);
	 		this.geschlecht = new SimpleStringProperty(geschl);
	 		this.geburtsdatum = new SimpleObjectProperty<LocalDate>(gDatum.toLocalDate());
	 		this.handyNr1 = new SimpleStringProperty(handy1);
	 		this.handyNr2 = new SimpleStringProperty(handy2);
		}

	/**
	 * @return the personID
	 */
	public int getPersonID() {
		return personID.get();
	}

	/**
	 * @param personID the personID to set
	 */
	public void setPersonID(int personID) {
		this.personID.set(personID);
	}

    public IntegerProperty personIDProperty() {
        return personID;
    }
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name.set(name);
	}
	
    public StringProperty nameProperty() {
        return name;
    }


	/**
	 * @return the vorname1
	 */
	public String getVorname1() {
		return vorname1.get();
	}

	/**
	 * @param vorname1 the vorname1 to set
	 */
	public void setVorname1(String vorname1) {
		this.vorname1.set(vorname1);
	}

    public StringProperty vorname1Property() {
        return vorname1;
    }
	
	/**
	 * @return the vorname2
	 */
	public String getVorname2() {
		return vorname2.get();
	}

	/**
	 * @param vorname2 the vorname2 to set
	 */
	public void setVorname2(String vorname2) {
		this.vorname2.set(vorname2);
	}

    public StringProperty vorname2Property() {
        return vorname2;
    }
	
	/**
	 * @return the geschlecht
	 */
	public String getGeschlecht() {
		return geschlecht.get();
	}

	/**
	 * @param geschlecht the geschlecht to set
	 */
	public void setGeschlecht(String geschlecht) {
		this.geschlecht.set(geschlecht);
	}

    public StringProperty geschlechtProperty() {
        return geschlecht;
    }
	
	/**
	 * @return the geburtsdatum
	 */
	public LocalDate getGeburtsdatum() {
		return geburtsdatum.get();
	}

	/**
	 * @param geburtsdatum the geburtsdatum to set
	 */
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum.set(geburtsdatum);
	}
	
    public ObjectProperty<LocalDate> geburtsdatumProperty() {
        return geburtsdatum;
    }

	/**
	 * @return the handyNr1
	 */
	public String getHandyNr1() {
		return handyNr1.get();
	}

	/**
	 * @param handyNr1 the handyNr1 to set
	 */
	public void setHandyNr1(String handyNr1) {
		this.handyNr1.set(handyNr1);
	}

    public StringProperty handyNr1Property() {
        return handyNr1;
    }
	
	/**
	 * @return the handyNr2
	 */
	public String getHandyNr2() {
		return handyNr2.get();
	}

	/**
	 * @param handyNr2 the handyNr2 to set
	 */
	public void setHandyNr2(String handyNr2) {
		this.handyNr2.set(handyNr2);
	}

    public StringProperty handyNr2Property() {
        return handyNr2;
    }
	
	// TODO toString in Lists ersetzen
    public String toString() {
    	return (getVorname1() + " " + getName());
    }
	
}
