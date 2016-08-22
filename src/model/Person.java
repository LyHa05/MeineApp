package model;

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
	private StringProperty eMailAdresse1;
	private StringProperty eMailAdresse2;
	private StringProperty eMailAdresse3;
	private StringProperty eMailAdresse4;
	private StringProperty eMailAdresse5;
	
//	Pruefen, inwieweit dieser Konstruktor nötig ist bzw. angepasst werden muss
//	public Person() {
//		this(null, null, null, null, null
//			,null, null, null, null, null
//			,null, null, null);
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

	/**
	 * @return the personID
	 */
	public IntegerProperty getPersonID() {
		return personID;
	}

	/**
	 * @param personID the personID to set
	 */
	public void setPersonID(IntegerProperty personID) {
		this.personID = personID;
	}

	/**
	 * @return the name
	 */
	public StringProperty getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(StringProperty name) {
		this.name = name;
	}

	/**
	 * @return the vorname1
	 */
	public StringProperty getVorname1() {
		return vorname1;
	}

	/**
	 * @param vorname1 the vorname1 to set
	 */
	public void setVorname1(StringProperty vorname1) {
		this.vorname1 = vorname1;
	}

	/**
	 * @return the vorname2
	 */
	public StringProperty getVorname2() {
		return vorname2;
	}

	/**
	 * @param vorname2 the vorname2 to set
	 */
	public void setVorname2(StringProperty vorname2) {
		this.vorname2 = vorname2;
	}

	/**
	 * @return the geschlecht
	 */
	public StringProperty getGeschlecht() {
		return geschlecht;
	}

	/**
	 * @param geschlecht the geschlecht to set
	 */
	public void setGeschlecht(StringProperty geschlecht) {
		this.geschlecht = geschlecht;
	}

	/**
	 * @return the geburtsdatum
	 */
	public ObjectProperty<LocalDate> getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * @param geburtsdatum the geburtsdatum to set
	 */
	public void setGeburtsdatum(ObjectProperty<LocalDate> geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 * @return the handyNr1
	 */
	public StringProperty getHandyNr1() {
		return handyNr1;
	}

	/**
	 * @param handyNr1 the handyNr1 to set
	 */
	public void setHandyNr1(StringProperty handyNr1) {
		this.handyNr1 = handyNr1;
	}

	/**
	 * @return the handyNr2
	 */
	public StringProperty getHandyNr2() {
		return handyNr2;
	}

	/**
	 * @param handyNr2 the handyNr2 to set
	 */
	public void setHandyNr2(StringProperty handyNr2) {
		this.handyNr2 = handyNr2;
	}

	/**
	 * @return the eMailAdresse1
	 */
	public StringProperty geteMailAdresse1() {
		return eMailAdresse1;
	}

	/**
	 * @param eMailAdresse1 the eMailAdresse1 to set
	 */
	public void seteMailAdresse1(StringProperty eMailAdresse1) {
		this.eMailAdresse1 = eMailAdresse1;
	}

	/**
	 * @return the eMailAdresse2
	 */
	public StringProperty geteMailAdresse2() {
		return eMailAdresse2;
	}

	/**
	 * @param eMailAdresse2 the eMailAdresse2 to set
	 */
	public void seteMailAdresse2(StringProperty eMailAdresse2) {
		this.eMailAdresse2 = eMailAdresse2;
	}

	/**
	 * @return the eMailAdresse3
	 */
	public StringProperty geteMailAdresse3() {
		return eMailAdresse3;
	}

	/**
	 * @param eMailAdresse3 the eMailAdresse3 to set
	 */
	public void seteMailAdresse3(StringProperty eMailAdresse3) {
		this.eMailAdresse3 = eMailAdresse3;
	}

	/**
	 * @return the eMailAdresse4
	 */
	public StringProperty geteMailAdresse4() {
		return eMailAdresse4;
	}

	/**
	 * @param eMailAdresse4 the eMailAdresse4 to set
	 */
	public void seteMailAdresse4(StringProperty eMailAdresse4) {
		this.eMailAdresse4 = eMailAdresse4;
	}

	/**
	 * @return the eMailAdresse5
	 */
	public StringProperty geteMailAdresse5() {
		return eMailAdresse5;
	}

	/**
	 * @param eMailAdresse5 the eMailAdresse5 to set
	 */
	public void seteMailAdresse5(StringProperty eMailAdresse5) {
		this.eMailAdresse5 = eMailAdresse5;
	}
	
}
