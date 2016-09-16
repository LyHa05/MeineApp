package application.view.eMail;

import application.model.eMail.EMail;
import application.model.person.Person;
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

public class EMailAnpassDialogController {
	
	/**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zu FX Elementen
	@FXML
	private Label ueberschriftLabel;
	@FXML
	private TextField eMailField;
	
	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedPerson;
	
	private EMail eMail;
	
	private Stage dialogStage;
	// TODO Pruefen, ob entfernt werden kann
	private boolean okClicked = false;
	
   
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
        	
        	eMail.setEMailAdresse(eMailField.getText());
        	eMail.setGehoert(selectedPerson);

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

        if (eMailField.getText() == null || eMailField.getText().length() == 0) {
        	errorMessage += "Kein E-Mail angegeben!\n";
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
	 * @param sp
	 *            the selectedPerson to set
	 */
	public void setSelectedPerson(Person sp) {
		this.selectedPerson = sp;
		ueberschriftLabel.setText("E-Mail-Adresse von " + selectedPerson.toString());
	}

	/**
	 * Selektierte Adresse wird in Dialog gesetzt.
	 * 
	 * @param selectedAdresse
	 */
	public void setEMail(EMail selectedEMail) {
		this.eMail = selectedEMail;
		
		eMailField.setText(eMail.getEMailAdresse());
		
	}
	
}
