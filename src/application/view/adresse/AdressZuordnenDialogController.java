package application.view.adresse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.MainApp;
import application.controller.DBConnect;
import application.model.adresse.AdressDB;
import application.model.adresse.Adresse;
import application.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private MainApp mainApp;

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
           ps = DBConnect.connect().prepareStatement(""
        		   	+"SELECT DISTINCT WohnhaftIn.AdressID "
        		   	+",Adresse.Strasse "
	       			+",Adresse.Zusatz "
	       			+",Adresse.PLZ "
	       			+",Adresse.Ort "
	       			+",Adresse.Land "
	       			+",Adresse.FestnetzNr "
	       			+"FROM Adresse JOIN WohnhaftIn ON Adresse.AdressID = WohnhaftIn.AdressID "
	       			+"WHERE WohnhaftIn.AdressID Not In (Select Adresse.AdressID FROM Adresse JOIN WohnhaftIn ON WohnhaftIn.AdressID = Adresse.AdressID "
       														+"JOIN Person ON Person.PersonID = WohnhaftIn.PersonID "
       														+"WHERE Person.PersonID = ?)");
           ps.setInt(1, person.getPersonID());
           rs = ps.executeQuery();
           
           System.out.println(person);
           
           while (rs.next()) {
               //get string from db, whichever way 
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
		
		// loescht Daten im ListView --> pruefen, ob später noch nötig
		personDaten.removeAll(personDaten);
		
		try {
			 // Execute query and store result in a resultset
            ps = DBConnect.connect().prepareStatement(""
	        	+"SELECT DISTINCT WohnhaftIn.PersonID "
				+",Person.Name "
				+",Person.Vorname1 "
				+",Person.Vorname2 "
				+",Person.Geschlecht "
				+",Person.Geburtsdatum "
				+",Person.HandyNr1 "
				+",Person.HandyNr2 "
				+",Person.EMailAdresse1 "
				+",Person.EMailAdresse2 "
				+",Person.EMailAdresse3 "
				+",Person.EMailAdresse4 "
				+",Person.EMailAdresse5 "
				+"FROM WohnhaftIn JOIN Person ON WohnhaftIn.PersonID = Person.PersonID "
				+"WHERE WohnhaftIn.PersonID Not In (Select Person.PersonID FROM Person JOIN WohnhaftIn ON WohnhaftIn.PersonID = Person.PersonID "
														+"JOIN Adresse ON Adresse.AdressID = WohnhaftIn.AdressID "
														+"WHERE Adresse.AdressID = ?)");
     
            ps.setInt(1, adresse.getAdressID());
            rs = ps.executeQuery();
            
            System.out.println(adresse);
            
            System.out.println(rs);
            
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
            System.out.println(personDaten);
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

	public void handleZuordnen() throws SQLException {
        if (isInputValid()) {
//            okClicked = true;
        	
        	if (flagUebersicht) {
        		
        		// leeren tempAdresse müssen Daten der Auswahl zugeordnet werden
        		
        		Adresse selectedAdresse = adressList.getSelectionModel().getSelectedItem();
        		
        		adresse.setAdressID(selectedAdresse.getAdressID());
        		adresse.setStrasse(selectedAdresse.getStrasse());
        		adresse.setZusatz(selectedAdresse.getZusatz());
        		adresse.setPlz(selectedAdresse.getPlz());
        		adresse.setLand(selectedAdresse.getLand());
        		adresse.setFestnetzNr(selectedAdresse.getFestnetzNr());       		
        		
        		AdressDB.zuordnenAdresse(adresse, person);
        		
        	} else {
        		
        		// leeren tempPerson müssen Daten der Auswahl zugeordnet werden
        		
        		Person selectedPerson = personList.getSelectionModel().getSelectedItem();
        		
        		person.setPersonID(selectedPerson.getPersonID());
        		person.setName(selectedPerson.getName());
        		person.setVorname1(selectedPerson.getVorname1());
        		person.setVorname2(selectedPerson.getVorname2());
        		person.setGeschlecht(selectedPerson.getGeschlecht());
        		person.setGeburtsdatum(selectedPerson.getGeburtsdatum());
        		person.setHandyNr1(selectedPerson.getHandyNr1());
        		person.seteMailAdresse1(selectedPerson.geteMailAdresse1());
        	
        		AdressDB.zuordnenAdresse(adresse, person);
        		
        	}
        	
            dialogStage.close();
        }
	}

	private boolean isInputValid() {
		int selectedIndex;
		if (flagUebersicht) {
			selectedIndex = adressList.getSelectionModel().getSelectedIndex();
		} else {
			selectedIndex = personList.getSelectionModel().getSelectedIndex();
		}
		if (selectedIndex >= 0) {
			return true;
		} else {
			// Nothing selected.
			keineAdresseSelektiert();
		}
		
		return false;
	}
	
	private void keineAdresseSelektiert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Keine Auswahl");
        alert.setHeaderText("Keine Adresse/Person selektiert");
        alert.setContentText("Bitte waehlen Sie eine Adresse/Person in der Tabelle aus.");

        alert.showAndWait();
		
	}
}
