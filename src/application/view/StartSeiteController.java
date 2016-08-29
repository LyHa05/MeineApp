package application.view;

import application.MainApp;
import javafx.fxml.FXML;

public class StartSeiteController {

	// Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handlePersonUebersicht() {
            mainApp.showPersonUebersicht();
    }
    
}
