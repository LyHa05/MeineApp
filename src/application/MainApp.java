package application;

import java.io.IOException;
import java.sql.SQLException;

import application.model.adresse.Adresse;
import application.model.eMail.EMail;
import application.model.person.Person;
import application.model.stammdaten.StammdatenKategorie;
import application.model.stammdaten.StammdatenWert;
import application.view.adresse.AdressAnpassDialogController;
import application.view.adresse.AdressUebersichtController;
import application.view.adresse.AdressZuordnenDialogController;
import application.view.druck.AdressEtikettenAuswahlController;
import application.view.eMail.EMailAnpassDialogController;
import application.view.eMail.EMailUebersichtController;
import application.view.gs.GeschenkEinzelUebersichtController;
import application.view.person.PersonAnpassDialogController;
import application.view.person.PersonUebersichtController;
import application.view.root.RootLayoutController;
import application.view.root.StartSeiteController;
import application.view.stammdaten.StammdatenAnpassDialogController;
import application.view.stammdaten.StammdatenUebersichtController;
import application.view.tools.DBAnmeldungController;
import application.view.tools.DirectoryChooserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainApp extends Application {

	/**
	 * Definition der Variablen
	 */
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Window dialogStage;

	// Referenz zur Datenbankverbindnung.
	// private DBConnect dbc;
	// private Connection verbindung;

	/**
	 * Methode wird automatisch aufgerufen beim Launchen der Applikation
	 * 
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
	 * Initializes the root layout and tries to load the last opened person
	 * file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/root/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
			
			while (!showDBAnmeldung()) {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Fehler bei Anmeldung");
	            alert.setHeaderText("Bitte geben Sie die Daten erneut ein.");
	            alert.setContentText("Fehler!");

	            alert.showAndWait();
	            
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	// public ObservableList<Person> getPersonDaten() {
	// return personDaten;
	// }

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showStartSeite() {
		try {
			// Startseite laden.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/root/StartSeite.fxml"));
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
	
	public boolean showDBAnmeldung() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/tools/DBAnmeldung.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Datenbank Anmeldung");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			DBAnmeldungController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void showStammdatenUebersicht() throws SQLException {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/stammdaten/StammdatenUebersicht.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(page);

			// Give the controller access to the main app.
			StammdatenUebersichtController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPersonUebersicht() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/person/PersonUebersicht.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(page);

			// Give the controller access to the main app.
			PersonUebersichtController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showAdressUebersicht(boolean flagUebersicht, Person person) throws SQLException {

		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/adresse/AdressUebersicht.fxml"));
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
			controller.setAdressTable(person);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showEMailUebersicht(Person person) throws SQLException {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/eMail/EMailUebersicht.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("E-Mail-Adress-Uebersicht");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			EMailUebersichtController controller = loader.getController();
			controller.setSelectedPerson(person);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public boolean showStammdatenAnpassDialog(StammdatenWert stdWert, StammdatenKategorie stdKategorie) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/stammdaten/StammdatenAnpassDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
	
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Stammdaten Anpassen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
	
			// Set the person into the controller.
			StammdatenAnpassDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStammdatenKategorie(stdKategorie);
			controller.setStammdatenWert(stdWert);
			
	
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
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonAnpassDialog(Person person) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/person/PersonAnpassDialog.fxml"));
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

	public boolean showAdressAnpassDialog(Person person, Adresse adresse) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/adresse/AdressAnpassDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adresse Anpassen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AdressAnpassDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAdresse(adresse);
			controller.setSelectedPerson(person);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showEMailAnpassDialog(EMail eMail, Person person) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/eMail/EMailAnpassDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
	
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("E-Mail Anpassen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
	
			// Set the person into the controller.
			EMailAnpassDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEMail(eMail);
			controller.setSelectedPerson(person);
	
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
	
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showAdressZuordnenDialog(Person person, Adresse adresse, boolean flagUebersicht)
			throws SQLException {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/adresse/AdressZuordnenDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adresse Zuordnen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AdressZuordnenDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAdresse(adresse);
			controller.setPerson(person);
			controller.setFlagUebersicht(flagUebersicht);
			controller.showZuordnung();

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isZuordnenClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showAdressEtikettenAuswahlUebersicht() {

		try {
			// Laed Person und Adress Uebersicht.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/druck/AdressEtikettenAuswahl.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(page);

			// Give the controller access to the main app.
			AdressEtikettenAuswahlController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showGeschenkeEinzelUebersicht() {
		try {
			// Laed Person und Adress Uebersicht.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/gs/GeschenkEinzelUebersicht.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(page);

			// Give the controller access to the main app.
			GeschenkEinzelUebersichtController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	public void showGeschenkGesamtUebersicht() {
		// TODO Auto-generated method showGeschenkUebersicht
		
	}
	
	public boolean showDirectoryChooser() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/tools/DirectoryChooser.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Speicherort");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			DirectoryChooserController controller = loader.getController();
			controller.setDialogStage(dialogStage);

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
	 * 
	 * @return primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
