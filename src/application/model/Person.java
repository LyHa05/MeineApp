package application.model;

import java.sql.Date;
import java.time.LocalDate;

import application.util.DateUtil;
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
	private StringProperty eMailAdresse1;
	private StringProperty eMailAdresse2;
	private StringProperty eMailAdresse3;
	private StringProperty eMailAdresse4;
	private StringProperty eMailAdresse5;
	
	
	public Person() {
		this(0, null, null, null, null, 0, 0, 0, null, null, null, null, null, null, null);
	}
	
//	public Person(int pID, String name, String vname1, String vname2) {
//		this.personID = new SimpleIntegerProperty(pID);
// 		this.name = new SimpleStringProperty(name);
// 		this.vorname1 = new SimpleStringProperty(vname1);
// 		this.vorname2 = new SimpleStringProperty(vname2);
//	}
	
	public Person(int pID, String name, String vname1, String vname2, String geschl,
		int gJahr, int gMonat, int gTag, String handy1, String handy2, String eMail1, String eMail2,
		String eMail3, String eMail4, String eMail5) {
		
		this.personID = new SimpleIntegerProperty(pID);
 		this.name = new SimpleStringProperty(name);
 		this.vorname1 = new SimpleStringProperty(vname1);
 		this.vorname2 = new SimpleStringProperty(vname2);
 		this.geschlecht = new SimpleStringProperty(geschl);
 		this.geburtsdatum = new SimpleObjectProperty<LocalDate>(LocalDate.of(gJahr, gMonat, gTag));
 		this.handyNr1 = new SimpleStringProperty(handy1);
 		this.handyNr2 = new SimpleStringProperty(handy2);
 		this.eMailAdresse1 = new SimpleStringProperty(eMail1);
 		this.eMailAdresse2 = new SimpleStringProperty(eMail2);
 		this.eMailAdresse3 = new SimpleStringProperty(eMail3);
 		this.eMailAdresse4 = new SimpleStringProperty(eMail4);
 		this.eMailAdresse5 = new SimpleStringProperty(eMail5);
	}
	
	public Person(int pID, String name, String vname1, String vname2, String geschl,
			Date gDatum, String handy1, String handy2, String eMail1, String eMail2,
			String eMail3, String eMail4, String eMail5) {
			
			this.personID = new SimpleIntegerProperty(pID);
	 		this.name = new SimpleStringProperty(name);
	 		this.vorname1 = new SimpleStringProperty(vname1);
	 		this.vorname2 = new SimpleStringProperty(vname2);
	 		this.geschlecht = new SimpleStringProperty(geschl);
	 		this.geburtsdatum = new SimpleObjectProperty<LocalDate>(gDatum.toLocalDate());
	 		this.handyNr1 = new SimpleStringProperty(handy1);
	 		this.handyNr2 = new SimpleStringProperty(handy2);
	 		this.eMailAdresse1 = new SimpleStringProperty(eMail1);
	 		this.eMailAdresse2 = new SimpleStringProperty(eMail2);
	 		this.eMailAdresse3 = new SimpleStringProperty(eMail3);
	 		this.eMailAdresse4 = new SimpleStringProperty(eMail4);
	 		this.eMailAdresse5 = new SimpleStringProperty(eMail5);
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
	
	/**
	 * @return the eMailAdresse1
	 */
	public String geteMailAdresse1() {
		return eMailAdresse1.get();
	}

	/**
	 * @param eMailAdresse1 the eMailAdresse1 to set
	 */
	public void seteMailAdresse1(String eMailAdresse1) {
		this.eMailAdresse1.set(eMailAdresse1);
	}
	
    public StringProperty eMailAdresse1Property() {
        return eMailAdresse1;
    }

	/**
	 * @return the eMailAdresse2
	 */
	public String geteMailAdresse2() {
		return eMailAdresse2.get();
	}

	/**
	 * @param eMailAdresse2 the eMailAdresse2 to set
	 */
	public void seteMailAdresse2(String eMailAdresse2) {
		this.eMailAdresse2.set(eMailAdresse2);
	}

    public StringProperty eMailAdresse2Property() {
        return eMailAdresse2;
    }
	
	/**
	 * @return the eMailAdresse3
	 */
	public String geteMailAdresse3() {
		return eMailAdresse3.get();
	}

	/**
	 * @param eMailAdresse3 the eMailAdresse3 to set
	 */
	public void seteMailAdresse3(String eMailAdresse3) {
		this.eMailAdresse3.set(eMailAdresse3);
	}
	
    public StringProperty eMailAdresse3Property() {
        return eMailAdresse3;
    }

	/**
	 * @return the eMailAdresse4
	 */
	public String geteMailAdresse4() {
		return eMailAdresse4.get();
	}

	/**
	 * @param eMailAdresse4 the eMailAdresse4 to set
	 */
	public void seteMailAdresse4(String eMailAdresse4) {
		this.eMailAdresse4.set(eMailAdresse4);
	}

    public StringProperty eMailAdresse4Property() {
        return eMailAdresse4;
    }
	
	/**
	 * @return the eMailAdresse5
	 */
	public String geteMailAdresse5() {
		return eMailAdresse5.get();
	}

	/**
	 * @param eMailAdresse5 the eMailAdresse5 to set
	 */
	public void seteMailAdresse5(String eMailAdresse5) {
		this.eMailAdresse5.set(eMailAdresse5);
	}
	
    public StringProperty eMailAdresse5Property() {
        return eMailAdresse5;
    }
	
}
