package application.controller;

import application.util.DateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import application.MainApp;
import application.model.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class PersonUebersichtController {

	/**
	 * Definition der Instanzvariablen
	 */
	
	//Referenz zum TableView
	@FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
	
    //Referenz zu den Labeln
    @FXML
    private Label personIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label vname1Label;
    @FXML
    private Label vname2Label;
    @FXML
    private Label geschlLabel;
    @FXML
    private Label gDatumLabel;
    @FXML
    private Label handyNr1Label;
    @FXML
    private Label eMail1Label;
    
    // Reference to the main application.
    private MainApp mainApp;
    
    // Referenz zur Datenbankverbindnung.
    private DBConnect dbc;
    
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personDaten = FXCollections.observableArrayList();

    
    /**
     * The constructor is called before the initialize() method.
     */
    public PersonUebersichtController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws SQLException 
     */
    @FXML
    private void initialize() throws SQLException {
    	 
        try {
        	//Serververbindung hergestellt
        	dbc = new DBConnect();
        	Connection verbindung = dbc.connect();
        	
            // Execute query and store result in a resultset
            ResultSet rs = verbindung.createStatement().executeQuery("SELECT * FROM Person");
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

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("vorname1"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        

    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
    	if (person != null) {
    		// Fill the labels with info from the person object. LocalDate vorab konvertieren
    		personIDLabel.setText(Integer.toString(person.getPersonID()));
    		nameLabel.setText(person.getName());
    		vname1Label.setText(person.getVorname1());
    		vname2Label.setText(person.getVorname2());
    		geschlLabel.setText(person.getGeschlecht());
    		gDatumLabel.setText(DateUtil.format(person.getGeburtsdatum()));
    		handyNr1Label.setText(person.getHandyNr1());
    		eMail1Label.setText(person.geteMailAdresse1());
    		
    	} else {
    		// Person is null, remove all the text.
    		personIDLabel.setText("");
    		nameLabel.setText("");
    		vname1Label.setText("");
    		vname2Label.setText("");
    		geschlLabel.setText("");
    		gDatumLabel.setText("");
    		handyNr1Label.setText("");
    		eMail1Label.setText("");
    	}
    }
    
    
    /**
     * Wird aufgerufen, wenn User Zurueck anklickt. Oeffnet die Startseite. 
     */
    @FXML
    public void handleZurueck() {
    	mainApp.showStartSeite();
    }
    
    /**
     * Wird aufgerufen, wenn User Neu anklickt. Oeffnet einen Dialog, um neue Person anzulegen.
     */
    @FXML
    public void handleNeu() {
    	
    }
    
    /**
     * Wird aufgerufen, wenn User Aendern anklickt. Oeffnet einen Dialog, um ausgewaehlte Person zu aendern.
     */
    @FXML
    public void handleAendern() {
    	
    }
    
    /**
     * Wird aufgerufen, wenn User Loeschen anklickt. Loescht Persondaten.
     */
    @FXML
    public void handleLoeschen() {
    	
    }
	
    public void loadPersonDataFromDatabase() {
        try {

        	dbc = new DBConnect();
        	
        	
        	
//        	personDaten.clear();
        	

        	



        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Daten konnten nicht geladen werden");
//            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
	public ObservableList<Person> getPersonDaten() {
		return personDaten;
	}
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
//        personTable.setItems(mainApp.getPersonDaten());
        personTable.setItems(null);
        personTable.setItems(personDaten);
    }
}
