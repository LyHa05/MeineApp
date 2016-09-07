package application.view.tools;

import application.MainApp;
import application.tools.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Lydia Pflug
 * @date 07.09.2016
 */

public class DBAnmeldungController {
	
	// Referenz zu den Elementen
	@FXML
	private TextField benutzerNameField;
	@FXML
	private PasswordField passWortField;
	
	private boolean okClicked = false;
	
	private Stage dialogStage;
	
    // Reference to the main application.
    private MainApp mainApp;

	public DBAnmeldungController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	/**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {
    		
    		DBConnect.setUser(benutzerNameField.getText());
    		DBConnect.setPass(passWortField.getText());
    		
    		okClicked = true;
            dialogStage.close();
    	}
    }
    
    // TODO komplettes Programm beenden und schlieﬂen
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleSchliessen() {
        dialogStage.close();
        mainApp.getPrimaryStage().close();
    }
	
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // nur Pruefung auf Benutzername, da bei einigen DB's kein Passwort noetig
        if (benutzerNameField.getText() == null || benutzerNameField.getText().length() == 0) {
        	errorMessage += "Kein Benutzername eingegeben!\n";
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
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
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
