package application.model.gs;

import application.model.person.Person;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
	}
	
}
