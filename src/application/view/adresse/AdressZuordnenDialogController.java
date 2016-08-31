package application.view.adresse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.MainApp;
import application.controller.DBConnect;
import application.model.adresse.Adresse;
import application.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdressZuordnenDialogController {
	
	/**
	 * Definition der Instanzvariablen
	 */
	
	 // Referenz zu dem Label
	@FXML
	private Label ueberschriftLabel;
	@FXML
	private ListView<Person> personList;
	@FXML
	private ListView<Adresse> adressList;
	
	// Daten als ObservableList fuer Adressen
		private ObservableList<Adresse> adressDaten = FXCollections.observableArrayList();

	// Daten als ObservableList fuer Personen
	private ObservableList<Person> personDaten = FXCollections.observableArrayList();

	// Flag fuer verschiedene Uebersichten (true: personweise, false: gesamt)
	private boolean flagUebersicht;
	
    private Stage dialogStage;
    
    // Ausgewaehlte Adresse bzw. Person fuer Zuordnung
    private Person person;
    private Adresse adresse;
    
    private boolean zuordnenClicked = false;
    
	// Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
	private ResultSet rs;
	private PreparedStatement ps;

	/**
	 * The constructor is called before the initialize() method.
	 */
	public AdressZuordnenDialogController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void initialize() throws SQLException {
		
	}
		
		

	public void showZuordnung() throws SQLException {
		
		if (flagUebersicht) {
			
			showAdressen();
			
			adressList.setVisible(true);
			personList.setVisible(false);
			
		} else if (!flagUebersicht){
			
			showPersonen();
				
			personList.setVisible(true);
			adressList.setVisible(false);
		}
	}

	private void showAdressen() throws SQLException {
		try {
			 // Execute query and store result in a resultset
           rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM Adresse");
           while (rs.next()) {
               //get string from db,whichever way 
               adressDaten.add(new Adresse(
            		   rs.getInt(1) // AdressID
						, rs.getString(2) // Strasse
						, rs.getString(3) // Zusatz
						, rs.getString(4) // PLZ
						, rs.getString(5) // Ort
						, rs.getString(6) // Land
						, rs.getString(7) // Festnetznr
   		));   
       }

		} catch (SQLException e) {
			System.err.println("Error" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			DBConnect.close();

		}

					adressList.setItems(adressDaten);
						
					adressList.setCellFactory(new Callback<ListView<Adresse>, ListCell<Adresse>>() {

						@Override
						public ListCell<Adresse> call(ListView<Adresse> a) {
							return new ListCell<Adresse>() {
								@Override
								protected void updateItem(Adresse adresse, boolean empty) {
									super.updateItem(adresse, empty);
									if (adresse != null) {
										setText(adresse.getStrasse() + ", " + adresse.getPlz() + ", "
												+ adresse.getOrt());
									} else {
										setText(null);
									}
								}
							};
						}
					});
					
	}

	private void showPersonen() throws SQLException {
		
//		// loescht Daten im ListView --> pruefen, ob später noch nötig
//		personDaten.removeAll(personDaten);
		
		try {
			 // Execute query and store result in a resultset
            rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM Person");
            while (rs.next()) {
                //get string from db,whichever way 
                personDaten.add(new Person(
                		rs.getInt(1) 		//PersonID
                		,rs.getString(2)	//Name
                		,rs.getString(3)	//Vorname1
                		,rs.getString(4)	//Vorname2
                		,rs.getString(5)	//Geschlecht
                		,rs.getDate(6)		//Geburtsdatum
                		,rs.getString(7)	//HandyNr1
                		,rs.getString(8)	//HandyNr2
                		,rs.getString(9)	//EMailAdresse1
                		,rs.getString(10)	//EMailAdresse2
                		,rs.getString(11)	//EMailAdresse3
                		,rs.getString(12)	//EMailAdresse4
                		,rs.getString(13)	//EMailAdresse5
    		));   
        }

		} catch (SQLException e) {
			System.err.println("Error" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			DBConnect.close();

		}

					personList.setItems(personDaten);
						
					personList.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {

						@Override
						public ListCell<Person> call(ListView<Person> p) {
							return new ListCell<Person>() {
								@Override
								protected void updateItem(Person person, boolean empty) {
									super.updateItem(person, empty);
									if (person != null) {
										setText(person.getVorname1() + " " + person.getName());
									} else {
										setText(null);
									}
								}
							};
						}
					});
					
	}
	
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isZuordnenClicked() {
        return zuordnenClicked;
    }
		
	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
	    this.dialogStage = dialogStage;
	}

	/**
	 * Selektierte Adresse wird in Dialog gesetzt.
	 * 
	 * @param selectedAdresse
	 */
	public void setAdresse(Adresse a) {
		this.adresse = a;
		
//		adressIDLabel.setText(Integer.toString(adresse.getAdressID()));
//		strasseField.setText(adresse.getStrasse());
//		zusatzField.setText(adresse.getZusatz());
//		plzField.setText(adresse.getPlz());
//		ortField.setText(adresse.getOrt());
//		landField.setText(adresse.getLand());
//		festnetzNrField.setText(adresse.getFestnetzNr());
		
	}

	public void setFlagUebersicht(boolean fu) {
		this.flagUebersicht = fu;
	}

	/**
	 * @param sp
	 *            the selectedPerson to set
	 */
	public void setPerson(Person p) {
		this.person = p;
		if (p.getPersonID() != 0) {
			ueberschriftLabel.setText("Adresse von " + person.getVorname1() + " " + person.getName());
			setFlagUebersicht(true);
		} else {
			ueberschriftLabel.setText("Adresse");
			setFlagUebersicht(false);
		}
	}

}
