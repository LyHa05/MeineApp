package application.view.stammdaten;

import application.model.eMail.EMail;
import application.model.person.Person;
import application.model.stammdaten.StammdatenKategorie;
import application.model.stammdaten.StammdatenWert;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Lydia Pflug
 * @date 16.09.2016
 */

public class StammdatenAnpassDialogController {
	
	/**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zu FX Elementen
	@FXML
	private Label ueberschriftLabel;
	@FXML
	private TextField stdWertField;
	
	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedPerson;
	
	private EMail eMail;
	
	private Stage dialogStage;
	// TODO Pruefen, ob entfernt werden kann
	private boolean okClicked = false;
	private StammdatenWert stammdatenWert;
	private StammdatenKategorie stammdatenKategorie;
	
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
        	
        	stammdatenWert.setWert(stdWertField.getText());
        	stammdatenWert.setKategorieID(stammdatenKategorie);

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

        if (stdWertField.getText() == null || stdWertField.getText().length() == 0) {
        	errorMessage += "Kein Wert angegeben!\n";
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
	 * Selektierter Stammdatenwert wird in Dialog gesetzt.
	 * 
	 * @param stdWert
	 */
	public void setStammdatenWert(StammdatenWert stdWert) {
		this.stammdatenWert = stdWert;
		stdWertField.setText(stdWert.getWert());
	}

	/**
	 * Selektierte Adresse wird in Dialog gesetzt.
	 * 
	 * @param stdKategorie
	 */
	public void setStammdatenKategorie(StammdatenKategorie stdKategorie) {
		this.stammdatenKategorie = stdKategorie;
		ueberschriftLabel.setText("Kategorie " + stammdatenKategorie.getKategorie());
		
	}
	
	
	
}
