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


	/** 
	 * hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objAdresse == null) ? 0 : objAdresse.hashCode());
		result = prime * result + ((objPerson == null) ? 0 : objPerson.hashCode());
		return result;
	}


	/**
	 * equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WohnhaftIn other = (WohnhaftIn) obj;
		if (objAdresse == null) {
			if (other.objAdresse != null) {
				return false;
			}
		} else if (!objAdresse.equals(other.objAdresse)) {
			return false;
		}
		if (objPerson == null) {
			if (other.objPerson != null) {
				return false;
			}
		} else if (!objPerson.equals(other.objPerson)) {
			return false;
		}
		return true;
	}
	
	
}
