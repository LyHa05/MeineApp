package application.view.root;

import java.sql.SQLException;

import application.MainApp;
import application.model.person.Person;
import javafx.fxml.FXML;

public class StartSeiteController {

	// Reference to the main application
	private MainApp mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Wird aufgerufen, wenn User Personenuebersicht anklickt. Oeffnet einen
	 * Dialog mit allen Personen in der Uebersicht.
	 * @throws SQLException 
	 */
	@FXML
	private void handleStammdatenUebersicht() throws SQLException {
		mainApp.showStammdatenUebersicht();
	}
	
	/**
	 * Wird aufgerufen, wenn User Personenuebersicht anklickt. Oeffnet einen
	 * Dialog mit allen Personen in der Uebersicht.
	 */
	@FXML
	private void handlePersonUebersicht() {
		mainApp.showPersonUebersicht();
	}

	/**
	 * Wird aufgerufen, wenn User Adressuebersicht anklickt. Oeffnet einen
	 * Dialog mit allen Adressen in der Uebersicht.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void handleAdressUebersicht() throws SQLException {
		Person leerePerson = new Person();
		mainApp.showAdressUebersicht(false, leerePerson);
	}
	
	/**
	 * Wird aufgerufen, wenn User Adressuebersicht anklickt. Oeffnet einen
	 * Dialog mit allen Adressen in der Uebersicht.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void handleGeschenke() throws SQLException {
		mainApp.showGeschenkeEinzelUebersicht();
	}
	
	/**
	 * Wird aufgerufen, wenn User Adressuebersicht anklickt. Oeffnet einen
	 * Dialog mit allen Adressen in der Uebersicht.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void handleGeschenkUebersicht() throws SQLException {
		mainApp.showGeschenkGesamtUebersicht();
	}
	
	/**
	 * Wird aufgerufen, wenn User Adressuebersicht anklickt. Oeffnet einen
	 * Dialog mit allen Adressen in der Uebersicht.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void handleAdressEtikettenAuswahl() throws SQLException {
		mainApp.showAdressEtikettenAuswahlUebersicht();
	}

}
