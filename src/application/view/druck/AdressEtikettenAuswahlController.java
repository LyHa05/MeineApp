package application.view.druck;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.MainApp;
import application.model.adresse.Adresse;
import application.model.adresse.WohnhaftIn;
import application.model.person.Person;
import application.tools.AdressTextUmwandler;
import application.tools.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdressEtikettenAuswahlController {
	
	// Referenz fuer Person mit Adresse
	@FXML
	private ListView<WohnhaftIn> personAdressList;
	@FXML
	private ListView<WohnhaftIn> personAdressAuswahlList;
	
	// Reference to the main application.
	private MainApp mainApp;
	
	// Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
	private ResultSet rs;
//	private PreparedStatement ps;
	
	// Daten als ObservableList fuer Personen
	private ObservableList<WohnhaftIn> personAdressDaten = FXCollections.observableArrayList();
	private ObservableList<WohnhaftIn> personAdressAuswahlDaten = FXCollections.observableArrayList();
	
	// List mit ausgewaehlten Datensaetzen
	private ArrayList<WohnhaftIn> selectedDaten = new ArrayList<>();
	
	private Stage dialogStage;
	
	/**
	 * The constructor is called before the initialize() method.
	 */
	public AdressEtikettenAuswahlController() {
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
	
        try {    	
            // Execute query and store result in a resultset
            rs = DBConnect.connect().createStatement().executeQuery(""
            		+ "SELECT Person.PersonID "
            		+ ",Person.Name "
            		+ ",Person.Vorname1 "
            		+ ",Person.Vorname2 "
            		+ ",Person.Geschlecht "
            		+ ",Person.Geburtsdatum "
            		+ ",Person.HandyNr1 "
            		+ ",Person.HandyNr2 "
            		+ ",Person.EMailAdresse1 "
            		+ ",Person.EMailAdresse2 "
            		+ ",Person.EMailAdresse3 "
            		+ ",Person.EMailAdresse4 "
            		+ ",Person.EMailAdresse5 "
            		+ ",Adresse.AdressID "
            		+ ",Adresse.Strasse "
            		+ ",Adresse.Zusatz "
            		+ ",Adresse.PLZ "
            		+ ",Adresse.Ort "
            		+ ",Adresse.Land "
            		+ ",Adresse.FestnetzNr "
            		+ "FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID "
            		+ "JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID");
            
            while (rs.next()) {
                //get string from db,whichever way   
            	
            	Person tempPerson = new Person(
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
                		,rs.getString(13));	//EMailAdresse5
            	
            	Adresse tempAdresse = new Adresse(
            			rs.getInt(14) 		// AdressID
						, rs.getString(15) 	// Strasse
						, rs.getString(16) 	// Zusatz
						, rs.getString(17) 	// PLZ
						, rs.getString(18) 	// Ort
						, rs.getString(19) 	// Land
						, rs.getString(20));// Festnetznr
            	
                personAdressDaten.add(new WohnhaftIn(tempPerson, tempAdresse));
                		
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
            System.out.println(ex);
        } finally {
			if (rs != null) rs.close();
        	DBConnect.close();
        }
        
        personAdressList.setItems(personAdressDaten);
        
        personAdressList.setCellFactory(new Callback<ListView<WohnhaftIn>, ListCell<WohnhaftIn>>() {

			@Override
			public ListCell<WohnhaftIn> call(ListView<WohnhaftIn> w) {
				return new ListCell<WohnhaftIn>() {
					@Override
					protected void updateItem(WohnhaftIn wohnhaftIn, boolean empty) {
						super.updateItem(wohnhaftIn, empty);
						if (wohnhaftIn != null) {
							setText(wohnhaftIn.getObjPerson().getVorname1() 
									+ " " 
									+ wohnhaftIn.getObjPerson().getName()
									+ ", "
									+ wohnhaftIn.getObjAdresse().getStrasse()
									+ ", "
									+ wohnhaftIn.getObjAdresse().getPlz()
									+ " "
									+ wohnhaftIn.getObjAdresse().getOrt());
						} else {
							setText(null);
						}
					}
				};
			}
		});
        
        personAdressAuswahlList.setCellFactory(new Callback<ListView<WohnhaftIn>, ListCell<WohnhaftIn>>() {

			@Override
			public ListCell<WohnhaftIn> call(ListView<WohnhaftIn> w) {
				return new ListCell<WohnhaftIn>() {
					@Override
					protected void updateItem(WohnhaftIn wohnhaftIn, boolean empty) {
						super.updateItem(wohnhaftIn, empty);
						if (wohnhaftIn != null) {
							setText(wohnhaftIn.getObjPerson().getVorname1() 
									+ " " 
									+ wohnhaftIn.getObjPerson().getName()
									+ ", "
									+ wohnhaftIn.getObjAdresse().getStrasse()
									+ ", "
									+ wohnhaftIn.getObjAdresse().getPlz()
									+ " "
									+ wohnhaftIn.getObjAdresse().getOrt());
						} else {
							setText(null);
						}
					}
				};
			}
		});
        
        personAdressAuswahlList.setItems(personAdressAuswahlDaten);
        
        
        personAdressList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        
        
        personAdressList.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                ObservableList<WohnhaftIn> selectedItems =  personAdressList.getSelectionModel().getSelectedItems();

                for(WohnhaftIn s : selectedItems){
                    System.out.println("selected item " + s);
                    personAdressAuswahlDaten.add(s);
                    personAdressDaten.remove(s);
                    
                }

            }

        });

        personAdressAuswahlList.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                ObservableList<WohnhaftIn> selectedItems =  personAdressAuswahlList.getSelectionModel().getSelectedItems();

                for(WohnhaftIn s : selectedItems){
                    System.out.println("selected item " + s);
                    personAdressDaten.add(s);
                    personAdressAuswahlDaten.remove(s);
                    
                }

            }

        });
        
	}
	
	/**
     * Called when the user clicks ok.
	 * @throws IOException 
     */
    @FXML
    private void handleUebernehmen() throws IOException {
        if (isInputValid()) {
        	
        	selectedDaten.addAll(personAdressAuswahlDaten);
        	System.out.println(selectedDaten);
            
        	mainApp.showDirectoryChooser();
        	AdressTextUmwandler.dateiErstellen();
        	AdressTextUmwandler.dateiSchreiben(selectedDaten);
        	mainApp.showStartSeite();
        	
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Etikettendruck");
            alert.setHeaderText("Die Datei fuer den Etikettendruck wurde erstellt");
//            alert.setContentText(AdressTextUmwandler.);

            alert.showAndWait();
        	
//            okClicked = true;
//            dialogStage.close();
        }
    }
      
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleSchliessen() {
        mainApp.showStartSeite();
    }
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}	
	
	/**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (personAdressAuswahlDaten.isEmpty()) {
        	errorMessage += "Keine Daten zum Drucken ausgewaehlt!\n";
        } 	
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ungueltige Felder");
            alert.setHeaderText("Bitte korrigieren Sie die ungueltigen Felder");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
