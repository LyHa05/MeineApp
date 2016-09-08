package application.view.tools;

import application.MainApp;
import application.tools.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
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
	@FXML
    private ComboBox<String> datenbankComboBox;
    private ObservableList<String> datenbankComboBoxDaten = FXCollections.observableArrayList();
   
	private boolean okClicked = false;
	
	private Stage dialogStage;
	
    // Reference to the main application.
    private MainApp mainApp;

	public DBAnmeldungController() {

	}
	
	@FXML
	private void initialize() {
		// Add some sample data in datenbankComboBox.
    	datenbankComboBoxDaten.addAll("Oracle SQL","Microsoft SQL");
    	// Init ComboBox items.
    	datenbankComboBox.setItems(datenbankComboBoxDaten);
    	// Define rendering of the list of values in ComboBox drop down. 
    	datenbankComboBox.setCellFactory((comboBox) -> {
    	    return new ListCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                  super.updateItem(item, empty);
                  if (item != null) {
                    setText(item);
                    if (item.contains("Oracle SQL")) {
                      setText("Oracle SQL");
                    } else if (item.contains("Microsoft SQL")) {
                      setText("Microsoft SQL");
                    } 
                  } else {
                    setText(null);
                  }
                }
              };
    	});
	}
	
	/**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	if (isInputValid()) {
    		
    		DBConnect.setUser(benutzerNameField.getText());
    		DBConnect.setPass(passWortField.getText());
    		
    		if (datenbankComboBox.getValue() == "Oracle SQL") {
    			DBConnect.setUrl("jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14");
    			DBConnect.setDriver("oracle.jdbc.driver.OracleDriver");
    		} else if (datenbankComboBox.getValue() == "Microsoft SQL") {
    			DBConnect.setUrl("jdbc:sqlserver://localhost;databasename=test4");
    			DBConnect.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		}
    		
    		okClicked = true;
            dialogStage.close();
    	}
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleSchliessen() {
    	okClicked = true;
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
        if (datenbankComboBox.getValue() != "Oracle SQL" && datenbankComboBox.getValue() != "Microsoft SQL") {
            errorMessage += "Keine Datenbank ausgewaehlt!\n"; 
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
