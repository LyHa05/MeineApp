package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class Person {

	private final IntegerProperty personID;
	private final StringProperty name;
	private final StringProperty vorname1;
	private final StringProperty vorname2;
	private final StringProperty geschlecht;
	private final ObjectProperty<LocalDate> geburtsdatum;
	private final StringProperty handyNr1;
	private final StringProperty handyNr2;
	private final StringProperty eMailAdresse1;
	private final StringProperty eMailAdresse2;
	private final StringProperty eMailAdresse3;
	private final StringProperty eMailAdresse4;
	private final StringProperty eMailAdresse5;
	
	public Person() {
		this(null, null, null, null, null
			,null, null, null, null, null
			,null, null, null);
	}
	
	public Person(int pID, String name, String vname1, String vname2, String geschl,
		LocalDate gDatum, String handy1, String handy2, String eMail1, String eMail2
		String eMail3, String eMail4, String eMail5) {
		
		this.personID = new SimpleIntegerProperty(pID)
	}
}

	public Person(Object object, Object object2, Object object3, Object object4, Object object5, Object object6,
			Object object7, Object object8, Object object9, Object object10, Object object11, Object object12,
			Object object13) {
		// TODO Auto-generated constructor stub
	}
