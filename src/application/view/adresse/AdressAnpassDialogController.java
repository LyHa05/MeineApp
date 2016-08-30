package application.view.adresse;

import application.model.adresse.Adresse;
import application.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
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
	private TextField landField;
	@FXML
	private TextField festnetzNrField;
	
	// Flag fuer verschiedene Uebersichten (true: personweise, false: gesamt)
	private boolean flagUebersicht;

	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedPerson;
	
	private Stage dialogStage;
	private Adresse adresse;
	private boolean okClicked = false;
	
	
	@FXML
	private void initialize() {
		
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
		landField.setText(adresse.getLand());
		festnetzNrField.setText(adresse.getFestnetzNr());
		
	}
	
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
        	adresse.setLand(landField.getText());
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
        // TODO Combobox mit D und AT sowie ggf. Möglichkeit selbst etwas einzutragen und Beschränkung auf 3 Zeichen!
        if (landField.getText() != null || landField.getText().length() == 0) {
            errorMessage += "Kein gültiges Land!\n";
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
			ueberschriftLabel.setText("Adressen von " + selectedPerson.getVorname1() + " " + selectedPerson.getName());
		} else {
			ueberschriftLabel.setText("Adressuebersicht");
		}
	}

}
