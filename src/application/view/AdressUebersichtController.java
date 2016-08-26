package application.view;

import application.MainApp;
import application.model.Adresse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
	
	// Daten als ObservableList für Adressen
    private ObservableList<Adresse> adressDaten = FXCollections.observableArrayList();

    /**
     * The constructor is called before the initialize() method.
     */
    public AdressUebersichtController() {
    }
    
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}

}
