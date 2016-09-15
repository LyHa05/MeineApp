package application.view.adresse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.MainApp;
import application.model.adresse.AdressDB;
import application.model.adresse.Adresse;
import application.model.person.Person;
import application.tools.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * @author Lydia Pflug
 * @date 26.08.2016
 */

public class AdressUebersichtController {

	/**
	 * Definition der Instanzvariablen
	 */

	// Referenz zum TableView
	@FXML
	private TableView<Adresse> adressTable;
	@FXML
	private TableColumn<Adresse, String> adressIDColumn;
	@FXML
	private TableColumn<Adresse, String> strasseColumn;
	@FXML
	private TableColumn<Adresse, String> zusatzColumn;
	@FXML
	private TableColumn<Adresse, String> plzColumn;
	@FXML
	private TableColumn<Adresse, String> ortColumn;
	@FXML
	private TableColumn<Adresse, String> landColumn;
	@FXML
	private TableColumn<Adresse, String> festnetzNrColumn;

	// Referenz zum Labeln
	@FXML
	private Label ueberschriftLabel;

	// Referenz zum ListView fuer Bewohner
	@FXML
	private ListView<Person> personList;

	// Reference to the main application.
	private MainApp mainApp;

	// Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
	private ResultSet rs;
	private PreparedStatement ps;

	// Daten als ObservableList fuer Adressen
	private ObservableList<Adresse> adressDaten = FXCollections.observableArrayList();

	// Daten als ObservableList fuer Personen
	private ObservableList<Person> personDaten = FXCollections.observableArrayList();

	// Flag fuer verschiedene Uebersichten (true: personweise, false: gesamt)
	private boolean flagUebersicht;

	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedPerson;

	/**
	 * The constructor is called before the initialize() method.
	 */
	public AdressUebersichtController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * @throws SQLException
	 * @throws IOException 
	 */
	@FXML
	private void initialize() throws SQLException, IOException {
		// Clear person List.
		showWohnendePersonen(null);

		// Listen for selection changes and show the person details when
		// changed.
		adressTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				showWohnendePersonen(newValue);
			} catch (SQLException | IOException e) {
				System.err.println("Error" + e);
			}
		});
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		adressTable.setItems(null);
		adressTable.setItems(adressDaten);
	}

	public void setFlagUebersicht(boolean fu) {
		this.flagUebersicht = fu;
	}

	// public ObservableList<Adresse> getAdressDaten() {
	// return adressDaten;
	// }

	/**
	 * @param sp
	 *            the selectedPerson to set
	 */
	public void setSelectedPerson(Person sp) {
		this.selectedPerson = sp;
		if (sp.getPersonID() != 0) {
			ueberschriftLabel.setText("Adressen von " + selectedPerson.getVorname1() + " " + selectedPerson.getName());
		} else {
			ueberschriftLabel.setText("Adressuebersicht");
		}
	}

	/**
	 * @param adressTable
	 *            the adressTable to set
	 * @throws SQLException
	 * @throws IOException 
	 */
	public void setAdressTable(Person sp) throws SQLException, IOException {
		try {
			if (flagUebersicht) {
				// Execute query and store result in a resultset
				ps = DBConnect.connect()
						.prepareStatement("SELECT Adresse.AdressID" + ", Adresse.Strasse" + ", Adresse.Zusatz"
								+ ", Adresse.PLZ" + ", Adresse.Ort" + ", Adresse.Land" + ", Adresse.FestnetzNr "
								+ "FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID "
								+ "JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID "
								+ "WHERE Person.PersonID = ?");
				ps.setInt(1, selectedPerson.getPersonID());
				rs = ps.executeQuery();
			} else {
				rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM Adresse");
			}

			while (rs.next()) {
				// get string from db,whichever way
				adressDaten.add(new Adresse(rs.getInt(1) // AdressID
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

		// Set cell value factory to tableview.
		// NB.PropertyValue Factory must be the same with the one set in model
		// class.
		adressIDColumn.setCellValueFactory(new PropertyValueFactory<>("adressID"));
		strasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));
		zusatzColumn.setCellValueFactory(new PropertyValueFactory<>("zusatz"));
		plzColumn.setCellValueFactory(new PropertyValueFactory<>("plz"));
		ortColumn.setCellValueFactory(new PropertyValueFactory<>("ort"));
		landColumn.setCellValueFactory(new PropertyValueFactory<>("land"));
		festnetzNrColumn.setCellValueFactory(new PropertyValueFactory<>("festnetzNr"));

	}

	private void showWohnendePersonen(Adresse selektierteAdresse) throws SQLException, IOException {
		
		// loescht Daten im ListView
			personDaten.removeAll(personDaten);
		
		// zeigt Person, die unter selektierter Adresse wohnen
		if (selektierteAdresse != null) {
			try {
				// Execute query and store result in a resultset
				ps = DBConnect.connect().prepareStatement("SELECT DISTINCT "
								+ "Person.PersonID "
								+ ", Person.Name "
								+ ", Person.Vorname1 "
								+ ", Person.Vorname2 "
								+ ", Person.Geschlecht "
								+ ", Person.Geburtsdatum "
								+ ", Person.HandyNr1 "
								+ ", Person.HandyNr2 "
								+ ", Person.EMailAdresse1 "
								+ ", Person.EMailAdresse2 "
								+ ", Person.EMailAdresse3 "
								+ ", Person.EMailAdresse4 "
								+ ", Person.EMailAdresse5 "
								+ "FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID "
								+ "JOIN Adresse ON WohnhaftIn.AdressID = ?");
				ps.setInt(1, selektierteAdresse.getAdressID());
				rs = ps.executeQuery();

				while (rs.next()) {
					// get string from db,whichever way
					personDaten.add(new Person(rs.getInt(1) // PersonID
							, rs.getString(2) // Name
							, rs.getString(3) // Vorname1
							, rs.getString(4) // Vorname2
							, rs.getString(5) // Geschlecht
							, rs.getDate(6) // Geburtsdatum
							, rs.getString(7) // HandyNr1
							, rs.getString(8) // HandyNr2
//							, rs.getString(9) // EMailAdresse1
//							, rs.getString(10) // EMailAdresse2
//							, rs.getString(11) // EMailAdresse3
//							, rs.getString(12) // EMailAdresse4
//							, rs.getString(13) // EMailAdresse5
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

			// TODO zeigt leeren ListView, wenn keine Adresse selektiert worden ist
		} else

		{

		}

	}
	
    /**
	 * Wird aufgerufen, wenn User Aendern anklickt. Oeffnet einen Dialog, um ausgewaehlte Person zu aendern.
	 * @throws SQLException 
     * @throws IOException 
	 */
	@FXML
	public void handleAendern() throws SQLException, IOException {
		Adresse selectedAdresse = adressTable.getSelectionModel().getSelectedItem();
	    if (selectedAdresse != null) {
	        boolean okClicked = mainApp.showAdressAnpassDialog(selectedPerson, selectedAdresse);
	        if (okClicked) {
	        	AdressDB.aendereAdresse(selectedAdresse);
	            showWohnendePersonen(selectedAdresse);
	        }
	
	    } else {
	        // Nothing selected.
	    	keineAdresseSelektiert();
	    }
	}

	/**
     * Wird aufgerufen, wenn User Loeschen anklickt. Loescht Persondaten.
     * @throws SQLException 
	 * @throws IOException 
     */
    @FXML
    public void handleLoeschen() throws SQLException, IOException {
        //TODO Adressauswahl und Indexauswahl zusammenfassen
    	int selectedIndex = adressTable.getSelectionModel().getSelectedIndex();
        Adresse selectedAdresse = adressTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
        	if (flagUebersicht) {AdressDB.loescheAdresseFuerPerson(selectedAdresse, selectedPerson);}
        	if (!flagUebersicht) {AdressDB.loescheAdresse(selectedAdresse);}
            adressTable.getItems().remove(selectedIndex);
        } else {
        	 // Nothing selected.
        	keineAdresseSelektiert();
        }
    }
    
    
    //TODO Aktualisierung der Adressansicht nach Zuordnung
    /**
     * Wird aufgerufen, wenn User Neu anklickt. Oeffnet einen Dialog, um neue Adresse anzulegen.
     * @throws SQLException 
     * @throws IOException 
     */
    @FXML
    public void handleZuordnen() throws SQLException, IOException {
    	// Aufruf von Personstammdaten
    	if (flagUebersicht) {
    		Adresse tempAdresse = new Adresse();
    		boolean okClicked = mainApp.showAdressZuordnenDialog(selectedPerson, tempAdresse, flagUebersicht);
        	if (okClicked) {
        		AdressDB.zuordnenAdresse(tempAdresse, selectedPerson);
        		showWohnendePersonen(tempAdresse);
        	}
	    // Aufruf von Adressgesamtuebersicht
    	} else {
    		 //TODO Adressauswahl und Indexauswahl zusammenfassen
	    	int selectedIndex = adressTable.getSelectionModel().getSelectedIndex();
	        Adresse selectedAdresse = adressTable.getSelectionModel().getSelectedItem();
	        Person tempPerson = new Person();
	        if (selectedIndex >= 0) {
	        	// in diesem Fall zuordnenClicked X) 
	        	boolean okClicked = mainApp.showAdressZuordnenDialog(tempPerson, selectedAdresse, flagUebersicht);
	        	if (okClicked) {
	        		showWohnendePersonen(selectedAdresse);
	        	}
	        } else {
	        	 // Nothing selected.
	        	keineAdresseSelektiert();
	        }
    	}
    }
    
    /**
     * Wird aufgerufen, wenn User Neu anklickt. Oeffnet einen Dialog, um neue Adresse anzulegen.
     * @throws SQLException 
     * @throws IOException 
     */
    @FXML
    public void handleNeu() throws SQLException, IOException {
        Adresse tempAdresse = new Adresse();
        boolean okClicked = mainApp.showAdressAnpassDialog(selectedPerson, tempAdresse);
        if (okClicked && flagUebersicht) {
        	AdressDB.erstelleAdresseFuerPerson(tempAdresse,selectedPerson);
    	} else  if (okClicked && !flagUebersicht) { 
        	AdressDB.erstelleAdresse(tempAdresse);	
        }
        	adressDaten.add(tempAdresse);
    }

	private void keineAdresseSelektiert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Keine Auswahl");
        alert.setHeaderText("Keine Adresse selektiert");
        alert.setContentText("Bitte waehlen Sie eine Adresse in der Tabelle aus.");

        alert.showAndWait();
		
	}

}
