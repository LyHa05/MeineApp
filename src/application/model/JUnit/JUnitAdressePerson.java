/**
 * 
 */
package application.model.JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.model.adresse.Adresse;
import application.model.adresse.DepricatedWohnhaftIn;
import application.model.person.Person;

/**
 * @author Lydia Pflug
 * @date 22.08.2016
 *
 */
public class JUnitAdressePerson {

	private Person p1;
	private Person p2;
	private Person p3;
	private Adresse a1;
	private Adresse a2;
	private Adresse a3;
	private DepricatedWohnhaftIn ap1;
	private DepricatedWohnhaftIn ap2;
	private DepricatedWohnhaftIn ap3;
	private DepricatedWohnhaftIn ap4;
	private DepricatedWohnhaftIn ap5;
	private DepricatedWohnhaftIn ap6;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p1 = new Person(11, "Meyer", "Hans", null, "maennlich", 1970, 7, 20, "0175123456", null, "123@web.de", null, null, null, null);
		p2 = new Person(12, "Meyer", "Lisa", null, "weiblich", 1973, 10, 25, "0175123457", null, "456@web.de", null, null, null, null);
		p3 = new Person(13, "Meyer", "Lischen", null, "weiblich", 1990, 5, 14, null, null, null, null, null, null, null);
		a1 = new Adresse(21, "Am Weg 3", null, "Berlin","12345", "D" , "010123456");
		a2 = new Adresse(22, "Unter der Bruecke", null, "Berlin","12346", "D" , null);
		a3 = new Adresse(23, "Am Berg 5", null, "Berlin","12347", "D" , "010123457");
	}

	@Test
	public void einePersonZuEinerAdresse() {
		ap1 = new DepricatedWohnhaftIn(p1,a1);
		ap2 = new DepricatedWohnhaftIn(p2,a2);
		ap3 = new DepricatedWohnhaftIn(p1,a1);
		assertNotSame(ap1,ap2);
		assertEquals(ap1,ap3);
	}
	
	@Test
	public void einePersonZuVielenAdressen() {
		
	}

	@Test
	public void vielePersonenZuEinerAdresse() {
		
	}
	
	@Test
	public void vielePersonenZuVielenAdressen() {
		
	}
}
