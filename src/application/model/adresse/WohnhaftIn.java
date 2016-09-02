package application.model.adresse;

import application.model.person.Person;

/**
 * @author Lydia Pflug
 * @date 19.08.2016
 */

public class WohnhaftIn {
	
	private Person objPerson;
	private Adresse objAdresse;

	
	public WohnhaftIn(Person oPerson, Adresse oAdresse) {
		this.objPerson = oPerson;
		this.objAdresse = oAdresse;
	}


	/**
	 * @return the objPerson
	 */
	public Person getObjPerson() {
		return objPerson;
	}


	/**
	 * @param objPerson the objPerson to set
	 */
	public void setObjPerson(Person objPerson) {
		this.objPerson = objPerson;
	}


	/**
	 * @return the objAdresse
	 */
	public Adresse getObjAdresse() {
		return objAdresse;
	}


	/**
	 * @param objAdresse the objAdresse to set
	 */
	public void setObjAdresse(Adresse objAdresse) {
		this.objAdresse = objAdresse;
	}
	
	
}
