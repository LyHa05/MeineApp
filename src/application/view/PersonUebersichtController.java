package application.view;

import application.util.DateUtil;
import application.MainApp;
import application.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonUebersichtController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().vorname1Property());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    
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
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonDaten());
    }
}
