package application.view.adresse;

import java.sql.SQLException;
import application.model.adresse.Adresse;
import application.model.person.Person;
import application.model.stammdaten.StammdatenDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdressAnpassDialogController {
	
	/**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zu den Labeln
	@FXML
	private Label ueberschriftLabel;
	@FXML
	private Label adressIDLabel;
	@FXML
	private TextField strasseField;
	@FXML
	private TextField zusatzField;
	@FXML
	private TextField plzField;
	@FXML
	private TextField ortField;
	@FXML
	private TextField festnetzNrField;
	
    @FXML
    private ComboBox<String> landComboBox;
    private ObservableList<String> landComboBoxDaten = FXCollections.observableArrayList();
	
	// Flag fuer verschiedene Uebersichten (true: personweise, false: gesamt)
	private boolean flagUebersicht;

	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedPerson;
	
	private Stage dialogStage;
	private Adresse adresse;
	// TODO Pruefen, ob entfernt werden kann
	private boolean okClicked = false;
//	private MainApp mainApp;
	
	@FXML
	private void initialize() throws SQLException {
		
		// Strings für landComboBoxDaten
		landComboBoxDaten.addAll(StammdatenDB.comboBoxDatenLesen("Land"));
    	// Init ComboBox items.
    	landComboBox.setItems(landComboBoxDaten);
    	// Define rendering of the list of values in ComboBox drop down. 
    	landComboBox.setCellFactory((comboBox) -> {
    	    return new ListCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item != null) {
                    setText(item);
                  } else {
                    setText(null);
                  }
                }
              };
    	});
	}
	
	// TODO Pruefen, ob entfernt werden kann
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	
        	adresse.setStrasse(strasseField.getText());
        	adresse.setZusatz(zusatzField.getText());
        	adresse.setPlz(plzField.getText());
        	adresse.setOrt(ortField.getText());
        	adresse.setLand(landComboBox.getValue());
        	adresse.setFestnetzNr(festnetzNrField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleSchliessen() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (strasseField.getText() == null || strasseField.getText().length() == 0) {
        	errorMessage += "Keine gueltige Strasse!\n";
        }
        if (plzField.getText() == null || plzField.getText().length() == 0) {
        	errorMessage += "Keine gueltige PLZ!\n";
        }
        if (ortField.getText() == null || ortField.getText().length() == 0) {
            errorMessage += "Kein gueltiger Ort!\n"; 
        }
        if (landComboBox.getValue() == null) {
            errorMessage += "Kein Land angegeben!\n"; 
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

	/**
	 * Selektierte Adresse wird in Dialog gesetzt.
	 * 
	 * @param selectedAdresse
	 */
	public void setAdresse(Adresse selectedAdresse) {
		this.adresse = selectedAdresse;
		
		adressIDLabel.setText(Integer.toString(adresse.getAdressID()));
		strasseField.setText(adresse.getStrasse());
		zusatzField.setText(adresse.getZusatz());
		plzField.setText(adresse.getPlz());
		ortField.setText(adresse.getOrt());
		landComboBox.setValue(adresse.getLand());
		festnetzNrField.setText(adresse.getFestnetzNr());
		
	}

	public void setFlagUebersicht(boolean fu) {
		this.flagUebersicht = fu;
	}

	/**
	 * @param sp
	 *            the selectedPerson to set
	 */
	public void setSelectedPerson(Person sp) {
		this.selectedPerson = sp;
		if (sp.getPersonID() != 0) {
			ueberschriftLabel.setText("Adresse von " + selectedPerson.toString());
		} else {
			ueberschriftLabel.setText("Adresse");
		}
	}

//	public void setMainApp(MainApp mainApp) {
//		this.mainApp = mainApp;
//		
//	}

}
