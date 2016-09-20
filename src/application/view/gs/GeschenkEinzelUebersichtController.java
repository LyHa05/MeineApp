package application.view.gs;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.MainApp;
import application.model.gs.Geschenk;
import application.model.gs.GeschenkBestandteil;
import application.model.person.Person;
import application.tools.DBConnect;
import application.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GeschenkEinzelUebersichtController {

	// Referenz zur Person ComboBox
	@FXML
    private ComboBox<Person> personComboBox;
    private ObservableList<Person> personComboBoxDaten = FXCollections.observableArrayList();
    
    /**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zum TableView Anlass mit Geschenk
	@FXML
    private TableView<Geschenk> geschenkTable;
    @FXML
    private TableColumn<Geschenk, String> anlassColumn;
    @FXML
    private TableColumn<Geschenk, Integer> jahrColumn;
    @FXML
    private TableColumn<Geschenk, String> memoColumn;
    @FXML
    private TableColumn<Geschenk, Integer> preisColumn;
	
	// Referenz zum TableView Bestandteil des Geschenks
	@FXML
    private TableView<Geschenk> geschenkBestandteilTable;
    @FXML
    private TableColumn<GeschenkBestandteil, String> bestandteilColumn;
    @FXML
    private TableColumn<GeschenkBestandteil, String> beschreibungColumn;
    @FXML
    private TableColumn<GeschenkBestandteil, String> kategorieColumn;
    @FXML
    private TableColumn<GeschenkBestandteil, Integer> memoBestandteilColumn;
	
    // The data as an observable list of Geschenk bzw. GeschenkBestandteil.
    private ObservableList<Geschenk> geschenkDaten = FXCollections.observableArrayList();
    private ObservableList<GeschenkBestandteil> geschenkBestandteilDaten = FXCollections.observableArrayList();
        
    // Reference to the main application.
    private MainApp mainApp;
    
    // Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
    private ResultSet rs;
    
    /**
     * The constructor is called before the initialize() method.
     */
    public GeschenkEinzelUebersichtController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws SQLException 
     * @throws IOException 
     */
    @FXML
    private void initialize() throws SQLException, IOException {
    	    	
    	 try {    	
             // Execute query and store result in a resultset
             rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM Person");
             while (rs.next()) {
                 //get string from db,whichever way and add some sample data in personComboBoxDaten
            	 personComboBoxDaten.add(new Person(
                 		rs.getInt(1) 		//PersonID
                 		,rs.getString(2)	//Name
                 		,rs.getString(3)	//Vorname1
                 		,rs.getString(4)	//Vorname2
                 		,rs.getString(5)	//Geschlecht
                 		,rs.getDate(6)		//Geburtsdatum
                 		,rs.getString(7)	//HandyNr1
                 		,rs.getString(8)	//HandyNr2
//                 		,rs.getString(9)	//EMailAdresse1
//                 		,rs.getString(10)	//EMailAdresse2
//                 		,rs.getString(11)	//EMailAdresse3
//                 		,rs.getString(12)	//EMailAdresse4
//                 		,rs.getString(13)	//EMailAdresse5
                 		));   
             }

         } catch (SQLException ex) {
             System.err.println("Error"+ex);
             System.out.println(ex);
         } finally {
 			if (rs != null) rs.close();
         	DBConnect.close();
         }
    	   	
    	// Init ComboBox items.
    	personComboBox.setItems(personComboBoxDaten);
    	// Define rendering of the list of values in ComboBox drop down. 
    	personComboBox.setCellFactory((comboBox) -> {
    	    return new ListCell<Person>() {
                @Override
                public void updateItem(Person person, boolean empty) {
                  super.updateItem(person, empty);
                  if (person != null) {
                    setText(person.toString());
                  } else {
                    setText(null);
                  }
                }
              };
    	});
    	
    	// Handle ComboBox event.
    	personComboBox.setOnAction((event) -> {
    	    Person selectedPerson = personComboBox.getSelectionModel().getSelectedItem();
    	    System.out.println("ComboBox Action (selected: " + selectedPerson.toString() + ")");
    	    try {
				showGeschenkAnlass(selectedPerson);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    
    	});
    }
    
    private void showGeschenkAnlass(Person person) throws SQLException {
    	if (person != null) {
    		//TableView mit GeschenkDaten fuellen
    		
            try {    	
            // Execute query and store result in a resultset
            	rs = DBConnect.connect().createStatement().executeQuery(""
                		+ "	SELECT Geschenk.GeschenkID "
                		+ ",Geschenk.Jahr "
                		+ ",Geschenk.Anlass "
                		+ ",Geschenk.Memo "
                		+ ",Geschenk.Preis "
                		+ ",Geschenk.Erhaelt "
                		+ "FROM Geschenk "
                		+ "WHERE Geschenk.Erhaelt = ?"
            			);
            	
                while (rs.next()) {
                    //get string from db,whichever way 
                    geschenkDaten.add(new Geschenk(
                    		rs.getInt(1) 		//GeschenkID
                    		,rs.getInt(2)		//Jahr
                    		,rs.getString(3)	//Anlass
                    		,rs.getString(4)	//Memo
                    		,rs.getInt(5)		//Preis
                    		,rs.getObject(6)	//Erhaelt
                    		));   
                }

            } catch (SQLException ex) {
                System.err.println("Error"+ex);
            } finally {
    			if (rs != null) rs.close();
            	DBConnect.close();
            }	
    	}
	}

	/**
     * Wird aufgerufen, wenn User Menue anklickt. Oeffnet die Startseite. 
     */
    @FXML
    public void handleSchliessen() {
    	mainApp.showStartSeite();
    }
    
    /**
     * Wird aufgerufen, wenn User Neu anklickt. Oeffnet einen Dialog, um neue Person anzulegen.
     * @throws SQLException 
     */
    @FXML
    public void handleNeu() throws SQLException {
    	// TODO Methode handleNeu ergaenze
//        Person tempPerson = new Person();
//        boolean okClicked = mainApp.showPersonAnpassDialog(tempPerson);
//        if (okClicked) {
//        	PersonDB.erstellePerson(tempPerson);
//        	personDaten.add(tempPerson);
//        }
    }
       
    /**
     * Wird aufgerufen, wenn User Aendern anklickt. Oeffnet einen Dialog, um ausgewaehlte Person zu aendern.
     * @throws SQLException 
     */
    @FXML
    public void handleAendern() throws SQLException {
    	// TODO Methode handleAendern ergaenzen
    	
//    	Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonAnpassDialog(selectedPerson);
//            if (okClicked) {
//            	PersonDB.aenderePerson(selectedPerson);
//                showPersonDetails(selectedPerson);
//            }
//
//        } else {
//            // Nothing selected.
//        	keinePersonSelektiert();
//        }
    }
    
    /**
     * Wird aufgerufen, wenn User Loeschen anklickt. Loescht Persondaten.
     * @throws SQLException 
     */
    @FXML
    public void handleLoeschen() throws SQLException {
    // TODO Methode handleLoeschen ergaenzen
//        //TODO Personauswahl und Indexauswahl zusammenfassen
//    	int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
//        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
//        if (selectedIndex >= 0) {
//        	PersonDB.loeschePerson(selectedPerson);
//            personTable.getItems().remove(selectedIndex);
//        } else {
//        	 // Nothing selected.
//        	keinePersonSelektiert();
//        }
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    
}
