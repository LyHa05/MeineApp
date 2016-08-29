package application;

import java.io.IOException;
import java.sql.SQLException;
import application.model.Person;
import application.view.AdressUebersichtController;
import application.view.PersonAnpassDialogController;
import application.view.PersonUebersichtController;
import application.view.RootLayoutController;
import application.view.StartSeiteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	/**
	 * Definition der Variablen
	 */
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    // Referenz zur Datenbankverbindnung.
//    private DBConnect dbc;
//	private Connection verbindung;

    /**
     * Methode wird automatisch aufgerufen beim Launchen der Applikation
     * @throws SQLException 
     */
    @Override
    public void start(Stage primaryStage) throws SQLException {
    	   	
    	// Stage aufbauen
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/Address_Book.png"));
        
        initRootLayout();

        showStartSeite();
    }
    
    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}

//	public ObservableList<Person> getPersonDaten() {
//		return personDaten;
//	}
	
    /**
     * Shows the person overview inside the root layout.
     */
    public void showStartSeite() {
        try {
            // Startseite laden.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StartSeite.fxml"));
            AnchorPane StartSeite = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(StartSeite);
            
            // Give the controller access to the main app.
            StartSeiteController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

	public void showPersonUebersicht() {
		 try {
			    // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/PersonUebersicht.fxml"));   
	            AnchorPane PersonOverview = (AnchorPane) loader.load();            

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(PersonOverview);
	            
	            // Give the controller access to the main app.
	            PersonUebersichtController controller = loader.getController();
	            controller.setMainApp(this);
	               
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
	}

	public boolean showAdressUebersicht(boolean flagUebersicht, Person person) throws SQLException {
		
		try {
            // Load the fxml file and create a new stage for the popup dialog.
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdressUebersicht.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adressuebersicht");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
           AdressUebersichtController controller = loader.getController();
            controller.setSelectedPerson(person);
            controller.setFlagUebersicht(flagUebersicht);
            controller.setAdressTableMitPerson(person);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}

	/**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonAnpassDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonAnpassDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Person Anpassen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonAnpassDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }   
	
    /**
     * Gibt PrimaryStage zurueck.
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
