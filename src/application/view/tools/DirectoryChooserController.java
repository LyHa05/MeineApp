package application.view.tools;

import java.io.File;

import application.tools.AdressTextUmwandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DirectoryChooserController {
	
	@FXML
	private TextField speicherOrtField;
	
	private Stage dialogStage;
	private Stage chooserStage;
	
	private boolean okClicked = false;

	/**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // nur Pruefung auf Benutzername, da bei einigen DB's kein Passwort noetig
        if (speicherOrtField.getText() == null || speicherOrtField.getText().length() == 0) {
        	errorMessage += "Kein Speicherort angegeben!\n";
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {

            AdressTextUmwandler.setFile(new File(speicherOrtField.getText()));
    		
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
     * Called when the user clicks "..." .
     */
    @FXML
    private void handleAuswaehlen() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(chooserStage);
        speicherOrtField.setText(selectedDirectory.getAbsolutePath());
    }

}
