package application.view.stammdaten;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.MainApp;
import application.model.eMail.EMail;
import application.model.eMail.EMailDB;
import application.model.person.Person;
import application.model.stammdaten.StammdatenKategorie;
import application.model.stammdaten.StammdatenWert;
import application.model.stammdaten.StammdatenWertDB;
import application.tools.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * @author Lydia Pflug
 * @date 16.09.2016
 */

public class StammdatenUebersichtController {

	/**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zur Stammdaten ComboBox
		@FXML
	    private ComboBox<StammdatenKategorie> stammdatenComboBox;
	    private ObservableList<StammdatenKategorie> stammdatenComboBoxDaten = FXCollections.observableArrayList();
	    
	
	// Referenz zu FX Elementen
	@FXML
	private ListView<StammdatenWert> wertList;
	
	// Daten als ObservableList fuer Adressen
	private ObservableList<StammdatenWert> stammDaten = FXCollections.observableArrayList();
	
	// Ausgewaehlte Person fuer Adressansicht
	private StammdatenKategorie selectedKategorie;
	
//	private Stage dialogStage;
	
	// Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
	private ResultSet rs;
	private PreparedStatement ps;
	private MainApp mainApp;
	
	/**
	 * The constructor is called before the initialize() method.
	 */
	public StammdatenUebersichtController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * @throws SQLException 
	 */
	@FXML
	private void initialize() throws SQLException {
		
		 try {    	
	         // Execute query and store result in a resultset
	         rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM StammdatenKategorie");
	         while (rs.next()) {
	             //get string from db,whichever way and add some sample data in personComboBoxDaten
	        	 stammdatenComboBoxDaten.add(new StammdatenKategorie(
	        			 rs.getInt(1)		//KategorieID
	        			 ,rs.getString(2)	//Kategorie
	             		));   
	         }

         } catch (SQLException ex) {
             System.err.println("Error"+ex);
         } finally {
 			if (rs != null) rs.close();
         	DBConnect.close();
         }
    	   	
    	// Init ComboBox items.
		 stammdatenComboBox.setItems(stammdatenComboBoxDaten);
    	// Define rendering of the list of values in ComboBox drop down. 
		 stammdatenComboBox.setCellFactory((comboBox) -> {
    	    return new ListCell<StammdatenKategorie>() {
                @Override
                public void updateItem(StammdatenKategorie stdKategorie, boolean empty) {
                  super.updateItem(stdKategorie, empty);
                  if (stdKategorie != null) {
                    setText(stdKategorie.getKategorie());
                  } else {
                    setText(null);
                  }
                }
              };
    	});
    	
    	// Handle ComboBox event.
		 stammdatenComboBox.setOnAction((event) -> {
    	    StammdatenKategorie selectedKategorie = stammdatenComboBox.getSelectionModel().getSelectedItem();
    	    System.out.println("ComboBox Action selected: " + selectedKategorie.getKategorie() );
    	    try {
				showStammdaten(selectedKategorie);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    
    	    
    	});
		
		
	}
	
	public void showStammdaten(StammdatenKategorie stdKategorie) throws SQLException {
		
		// loescht Daten im ListView
		stammDaten.removeAll(stammDaten);
		
		try {
			// Execute query and store result in a resultset
	           ps = DBConnect.connect().prepareStatement(""
	        		   	+ "SELECT WertID "
	        		   	+ ",KategorieID "
	        		   	+ ",Wert "
	        		   	+ "FROM StammdatenWert "
	        		   	+ "WHERE KategorieID = ?");
	           ps.setInt(1, selectedKategorie.getKategorieID());
	           rs = ps.executeQuery();
	           
	           System.out.println(selectedKategorie);
	           
	           while (rs.next()) {
	               //get string from db, whichever way 
	               stammDaten.add(new StammdatenWert(
	            		   	rs.getInt(1) 		// WertID
							, rs.getObject(2) 	// KategorieID
							, rs.getString(3) 	// Wert
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

						wertList.setItems(stammDaten);
							
						wertList.setCellFactory(new Callback<ListView<StammdatenWert>, ListCell<StammdatenWert>>() {

							@Override
							public ListCell<StammdatenWert> call(ListView<StammdatenWert> e) {
								return new ListCell<StammdatenWert>() {
									@Override
									protected void updateItem(StammdatenWert stammdatenWert, boolean empty) {
										super.updateItem(stammdatenWert, empty);
										if (stammdatenWert != null) {
											setText(stammdatenWert.getWert());
										} else {
											setText(null);
										}
									}
								};
							}
						});
						
		
	}
	
    /**
	 * Wird aufgerufen, wenn User Neu anklickt. Oeffnet einen Dialog, um neue Adresse anzulegen.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@FXML
	public void handleNeu() throws SQLException, IOException {
	    StammdatenWert tempWert = new StammdatenWert();
	    boolean okClicked = mainApp.showStammdatenAnpassDialog(tempWert);
	    if (okClicked) {
	    	StammdatenWertDB.erstelleWert(tempWert);
	    	stammDaten.add(tempWert);
	    }	
	}
	
	/**
	 * Wird aufgerufen, wenn User Aendern anklickt. Oeffnet einen Dialog, um ausgewaehlte Person zu aendern.
	 * @throws SQLException 
     * @throws IOException 
	 */
	@FXML
	public void handleAendern() throws SQLException, IOException {
		StammdatenWert selectedStdWert = wertList.getSelectionModel().getSelectedItem();
	    if (selectedStdWert != null) {
	        boolean okClicked = mainApp.showStammdatenAnpassDialog(selectedStdWert);
	        if (okClicked) {
	        	StammdatenWertDB.aendereWert(selectedStdWert);
	            showStammdaten(selectedKategorie);
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
    	int selectedIndex = wertList.getSelectionModel().getSelectedIndex();
        StammdatenWert selectedStdWert = wertList.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
        	StammdatenWertDB.loescheEMail(selectedStdWert);
        	wertList.getItems().remove(selectedIndex);
        } else {
        	 // Nothing selected.
        	keineAdresseSelektiert();
        }
    }

    private void keineAdresseSelektiert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Keine Auswahl");
        alert.setHeaderText("Keine E-Mail selektiert");
        alert.setContentText("Bitte waehlen Sie eine E-Mail-Adresse in der Liste aus.");

        alert.showAndWait();
		
	}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     * @throws SQLException 
     */
    public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;
    }
		
	
}
