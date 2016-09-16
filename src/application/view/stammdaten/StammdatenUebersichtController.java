package application.view.stammdaten;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.MainApp;
import application.model.eMail.EMail;
import application.model.eMail.EMailDB;
import application.model.person.Person;
import application.tools.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

/**
 * @author Lydia Pflug
 * @date 16.09.2016
 */

public class StammdatenUebersichtController {

	/**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zu FX Elementen
	@FXML
	private Label ueberschriftLabel;
	@FXML
	private ListView<String> wertList;
	
	// Daten als ObservableList fuer Adressen
	private ObservableList<EMail> wertDaten = FXCollections.observableArrayList();
	
	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedKategorie;
	
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
	 */
	@FXML
	private void initialize() {
	}
	
	public void showEMailAdressen() throws SQLException {
		
		// loescht Daten im ListView
		eMailDaten.removeAll(eMailDaten);
		
		try {
			// Execute query and store result in a resultset
	           ps = DBConnect.connect().prepareStatement(""
	        		   	+ "SELECT EMailID "
	        		   	+ ",EMailAdresse "
	        		   	+ ",Gehoert "
	        		   	+ "FROM EMail "
	        		   	+ "WHERE Gehoert = ?");
	           ps.setInt(1, selectedPerson.getPersonID());
	           rs = ps.executeQuery();
	           
	           System.out.println(selectedPerson);
	           
	           while (rs.next()) {
	               //get string from db, whichever way 
	               eMailDaten.add(new EMail(
	            		   	rs.getInt(1) 		// eMailID
							, rs.getString(2) 	// EMailAdresse
							, rs.getObject(3) 	// Gehoert
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

						eMailList.setItems(eMailDaten);
							
						eMailList.setCellFactory(new Callback<ListView<EMail>, ListCell<EMail>>() {

							@Override
							public ListCell<EMail> call(ListView<EMail> e) {
								return new ListCell<EMail>() {
									@Override
									protected void updateItem(EMail eMail, boolean empty) {
										super.updateItem(eMail, empty);
										if (eMail != null) {
											setText(eMail.getEMailAdresse());
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
	    EMail tempEMail = new EMail();
	    boolean okClicked = mainApp.showEMailAnpassDialog(tempEMail, selectedPerson);
	    if (okClicked) {
	    	EMailDB.erstelleEMail(selectedPerson, tempEMail);
	    	eMailDaten.add(tempEMail);
	    }	
	}
	
	/**
	 * Wird aufgerufen, wenn User Aendern anklickt. Oeffnet einen Dialog, um ausgewaehlte Person zu aendern.
	 * @throws SQLException 
     * @throws IOException 
	 */
	@FXML
	public void handleAendern() throws SQLException, IOException {
		EMail selectedEMail = eMailList.getSelectionModel().getSelectedItem();
	    if (selectedEMail != null) {
	        boolean okClicked = mainApp.showEMailAnpassDialog(selectedEMail, selectedPerson);
	        if (okClicked) {
	        	EMailDB.aendereEMail(selectedPerson, selectedEMail);
	            showEMailAdressen();
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
    	int selectedIndex = eMailList.getSelectionModel().getSelectedIndex();
        EMail selectedEMail = eMailList.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
        	EMailDB.loescheEMail(selectedEMail);
        	eMailList.getItems().remove(selectedIndex);
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
	 * @param sp
	 *            the selectedPerson to set
	 */
	public void setSelectedPerson(Person sp) {
		this.selectedPerson = sp;
		ueberschriftLabel.setText("E-MailAdressen von " + selectedPerson.toString());
	}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     * @throws SQLException 
     */
    public void setMainApp(MainApp mainApp) throws SQLException {
        this.mainApp = mainApp;
		showEMailAdressen();
    }
		
	
}
