package application.view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.MainApp;
import application.controller.DBConnect;
import application.model.Adresse;
import application.model.Person;
import application.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Lydia Pflug
 * @date 26.08.2016
 */

public class AdressUebersichtController {

	/**
	 * Definition der Instanzvariablen
	 */

	// Referenz zum TableView
	@FXML
	private TableView<Adresse> adressTable;
	@FXML
	private TableColumn<Adresse, String> adressIDColumn;
	@FXML
	private TableColumn<Adresse, String> strasseColumn;
	@FXML
	private TableColumn<Adresse, String> zusatzColumn;
	@FXML
	private TableColumn<Adresse, String> plzColumn;
	@FXML
	private TableColumn<Adresse, String> ortColumn;
	@FXML
	private TableColumn<Adresse, String> landColumn;
	@FXML
	private TableColumn<Adresse, String> festnetzNrColumn;

	// Referenz zum Labeln
	@FXML
	private Label ueberschriftLabel;

	// Reference to the main application.
	private MainApp mainApp;

	// Referenz für ResultSet (zum Garantieren des Schliessen des ResultSets)
	private ResultSet rs;
	private PreparedStatement ps;

	// Daten als ObservableList für Adressen
	private ObservableList<Adresse> adressDaten = FXCollections.observableArrayList();

	// Flag fuer verschiedene Uebersichten (true: personweise, false: gesamt)
	private boolean flagUebersicht;

	// Ausgewaehlte Person fuer Adressansicht
	private Person selectedPerson;

	// TODO Pruefen, ob wirklich noetig fuer Problembehebung :-)
	private Stage dialogStage;
	private boolean okClicked = false;

	/**
	 * The constructor is called before the initialize() method.
	 */
	public AdressUebersichtController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * @throws SQLException
	 */
	@FXML
	private void initialize() throws SQLException {

//		try {
//			if (flagUebersicht) {
//				// Execute query and store result in a resultset
//				ps = DBConnect.connect()
//						.prepareStatement("SELECT Adresse.AdressID" + ", Adresse.Strasse" + ", Adresse.Zusatz"
//								+ ", Adresse.PLZ" + ", Adresse.Ort" + ", Adresse.Land" + ", Adresse.FestnetzNr "
//								+ "FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID "
//								+ "JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID "
//								+ "WHERE Person.PersonID = ?");
//				ps.setInt(1, selectedPerson.getPersonID());
//				rs = ps.executeQuery();
//			} else {
//				rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM Adresse");
//			}
//
//			while (rs.next()) {
//				// get string from db,whichever way
//				adressDaten.add(new Adresse(rs.getInt(1) // AdressID
//						, rs.getString(2) // Strasse
//						, rs.getString(3) // Zusatz
//						, rs.getString(4) // PLZ
//						, rs.getString(5) // Ort
//						, rs.getString(6) // Land
//						, rs.getString(7) // Festnetznr
//				));
//				System.out.println("Pruefung der gesetzten Instanzveriablen:");
//				System.out.println(selectedPerson);
//				System.out.println(flagUebersicht);
//			}
//
//		} catch (SQLException ex) {
//			System.err.println("Error" + ex);
//			System.out.println(ex);
//		} finally {
//			if (rs != null)
//				rs.close();
//			if (ps != null)
//				ps.close();
//			DBConnect.close();
//
//		}
//
//		// Set cell value factory to tableview.
//		// NB.PropertyValue Factory must be the same with the one set in model
//		// class.
//		adressIDColumn.setCellValueFactory(new PropertyValueFactory<>("adressID"));
//		strasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));
//		zusatzColumn.setCellValueFactory(new PropertyValueFactory<>("zusatz"));
//		plzColumn.setCellValueFactory(new PropertyValueFactory<>("plz"));
//		ortColumn.setCellValueFactory(new PropertyValueFactory<>("ort"));
//		landColumn.setCellValueFactory(new PropertyValueFactory<>("land"));
//		festnetzNrColumn.setCellValueFactory(new PropertyValueFactory<>("festnetzNr"));

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		adressTable.setItems(null);
		adressTable.setItems(adressDaten);

	}

	public void setFlagUebersicht(boolean fu) {
		this.flagUebersicht = fu;
		System.out.println("flagUebersicht: " + flagUebersicht);

	}

	public ObservableList<Adresse> getPersonDaten() {
		return adressDaten;
	}

	/**
	 * @param sp
	 *            the selectedPerson to set
	 */
	public void setSelectedPerson(Person sp) {
		this.selectedPerson = sp;
		ueberschriftLabel.setText("Adressen von " + selectedPerson.getVorname1() + " " + selectedPerson.getName());
		System.out.println("Person fuer Adresse gesetzt");
		System.out.println("selected Person: " + selectedPerson);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * @param adressTable
	 *            the adressTable to set
	 * @throws SQLException
	 */
	public void setAdressTableMitPerson(Person sp) throws SQLException {
		try {
			if (flagUebersicht) {
				// Execute query and store result in a resultset
				ps = DBConnect.connect()
						.prepareStatement("SELECT Adresse.AdressID" + ", Adresse.Strasse" + ", Adresse.Zusatz"
								+ ", Adresse.PLZ" + ", Adresse.Ort" + ", Adresse.Land" + ", Adresse.FestnetzNr "
								+ "FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID "
								+ "JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID "
								+ "WHERE Person.PersonID = ?");
				ps.setInt(1, selectedPerson.getPersonID());
				rs = ps.executeQuery();
			} else {
				rs = DBConnect.connect().createStatement().executeQuery("SELECT * FROM Adresse");
			}

			while (rs.next()) {
				// get string from db,whichever way
				adressDaten.add(new Adresse(rs.getInt(1) // AdressID
						, rs.getString(2) // Strasse
						, rs.getString(3) // Zusatz
						, rs.getString(4) // PLZ
						, rs.getString(5) // Ort
						, rs.getString(6) // Land
						, rs.getString(7) // Festnetznr
				));
				System.out.println("Pruefung der gesetzten Instanzveriablen:");
				System.out.println(selectedPerson);
				System.out.println(flagUebersicht);
			}

		} catch (SQLException ex) {
			System.err.println("Error" + ex);
			System.out.println(ex);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			DBConnect.close();

		}

		System.out.println("Dieser Code wird ausgefuehrt.");
		// Set cell value factory to tableview.
		// NB.PropertyValue Factory must be the same with the one set in model
		// class.
		adressIDColumn.setCellValueFactory(new PropertyValueFactory<>("adressID"));
		strasseColumn.setCellValueFactory(new PropertyValueFactory<>("strasse"));
		zusatzColumn.setCellValueFactory(new PropertyValueFactory<>("zusatz"));
		plzColumn.setCellValueFactory(new PropertyValueFactory<>("plz"));
		ortColumn.setCellValueFactory(new PropertyValueFactory<>("ort"));
		landColumn.setCellValueFactory(new PropertyValueFactory<>("land"));
		festnetzNrColumn.setCellValueFactory(new PropertyValueFactory<>("festnetzNr"));

	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
