package application;

import java.io.IOException;

import application.MainApp;
import application.view.RootLayoutController;
import application.view.StartSeiteController;
import application.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	/**
	 * Definition der Variablen
	 */
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personDaten = FXCollections.observableArrayList();


    /**
     * Methode wird automatisch aufgerufen beim Launchen der Applikation
     */
    
    @Override
    public void start(Stage primaryStage) {
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

	public ObservableList<Person> getPersonDaten() {
		return personDaten;
	}
	
    /**
     * Shows the person overview inside the root layout.
     */
    public void showStartSeite() {
        try {
            // Load Startseite.
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

	public boolean showPersonUebersicht() {
		// TODO Auto-generated method stub
		return false;
	}
}