package application.view.gs;

import application.model.gs.Geschenk;
import application.model.gs.GeschenkBestandteil;
import application.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GeschenkEinzelUebersichtController {

	// Referenz zur Person ComboBox
	@FXML
    private ComboBox<Person> personComboBox;
    private ObservableList<Person> personComboBoxDaten = FXCollections.observableArrayList();
    
    /**
	 * Definition der Instanzvariablen
	 */
	
	// Referenz zum TableView Anlass mit Geschenk
	@FXML
    private TableView<Geschenk> geschenkTable;
    @FXML
    private TableColumn<Geschenk, String> anlassColumn;
    @FXML
    private TableColumn<Geschenk, Integer> jahrColumn;
    @FXML
    private TableColumn<Geschenk, String> memoColumn;
    @FXML
    private TableColumn<Geschenk, Integer> preisColumn;
	
	// Referenz zum TableView Bestandteil des Geschenks
	@FXML
    private TableView<Geschenk> geschenkBestandteilTable;
    @FXML
    private TableColumn<GeschenkBestandteil, String> bestandteilColumn;
    @FXML
    private TableColumn<GeschenkBestandteil, String> beschreibungColumn;
    @FXML
    private TableColumn<GeschenkBestandteil, String> kategorieColumn;
    @FXML
    private TableColumn<GeschenkBestandteil, Integer> memoBestandteilColumn;
	
}
