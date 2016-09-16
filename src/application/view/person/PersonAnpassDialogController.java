package application.view.person;

import java.sql.SQLException;

import application.model.person.Person;
import application.model.stammdaten.StammdatenDB;
import application.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;

public class PersonAnpassDialogController {

	/**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zu den Labeln
    @FXML
    private Label personIDLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField vname1Field;
    @FXML
    private TextField vname2Field;
    @FXML
    private TextField gDatumField;
    @FXML
    private TextField handyNr1Field;
    @FXML
    private TextField handyNr2Field;
	
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
 
    @FXML
    private ComboBox<String> geschlComboBox;
    private ObservableList<String> geschlComboBoxDaten = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws SQLException 
     */
    @FXML
    private void initialize() throws SQLException {
    	
    	// Add some sample data in geschlComboBox.
    	geschlComboBoxDaten.addAll(StammdatenDB.comboBoxDatenLesen("Geschlecht"));
    	// Init ComboBox items.
    	geschlComboBox.setItems(geschlComboBoxDaten);
    	// Define rendering of the list of values in ComboBox drop down. 
    	geschlComboBox.setCellFactory((comboBox) -> {
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
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param selectedPerson
     */
    public void setPerson(Person selectedPerson) {
        this.person = selectedPerson;

		personIDLabel.setText(Integer.toString(person.getPersonID()));
		nameField.setText(person.getName());
		vname1Field.setText(person.getVorname1());
		vname2Field.setText(person.getVorname2());
		geschlComboBox.setValue(person.getGeschlecht());
		gDatumField.setText(DateUtil.format(person.getGeburtsdatum()));
		gDatumField.setPromptText("dd.mm.yyyy");
		handyNr1Field.setText(person.getHandyNr1());
		handyNr2Field.setText(person.getHandyNr2());

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
        	
            person.setName(nameField.getText());
            person.setVorname1(vname1Field.getText());
            person.setVorname2(vname2Field.getText());
            person.setGeschlecht(geschlComboBox.getValue());
            person.setGeburtsdatum(DateUtil.parse(gDatumField.getText()));
            person.setHandyNr1(handyNr1Field.getText());
            person.setHandyNr2(handyNr2Field.getText());
                        
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

        // Auskommentiert, da nicht von allen Personen der Nachname bekannt ist
//        if (nameField.getText() == null || nameField.getText().length() == 0) {
//            errorMessage += "Kein gueltiger Name!\n"; 
//        }
        if (vname1Field.getText() == null || vname1Field.getText().length() == 0) {
            errorMessage += "Kein gueltiger Vorname!\n"; 
        }
        if (geschlComboBox.getValue() != "Männlich" && geschlComboBox.getValue() != "Weiblich") {
            errorMessage += "Kein Geschlecht angegeben!\n"; 
        } 
        // Auskommentiert, da nicht von allen Personen Geburtsdatum bekannt ist
//        if ((gDatumField.getText() != null || gDatumField.getText().length() != 0) && (!DateUtil.validDate(gDatumField.getText()))) {
//            errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
//        }
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
    
}
