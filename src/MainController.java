//package votre.package;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainController {
    @FXML
    private TextField numeroCompteField;
    @FXML
    private TextField nomTitulaireField;
    @FXML
    private TextField soldeField;
    @FXML
    private TableView<CompteBancaire> accountTable;
    @FXML
    private TableColumn<CompteBancaire, String> numeroCompteColumn;
    @FXML
    private TableColumn<CompteBancaire, String> nomTitulaireColumn;
    @FXML
    private TableColumn<CompteBancaire, Double> soldeColumn;

    private ObservableList<CompteBancaire> accountData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        numeroCompteColumn.setCellValueFactory(cellData -> cellData.getValue().numeroCompteProperty());
        nomTitulaireColumn.setCellValueFactory(cellData -> cellData.getValue().nomTitulaireProperty());
        soldeColumn.setCellValueFactory(cellData -> cellData.getValue().soldeProperty().asObject());

        accountTable.setItems(accountData);
    }

    @FXML
    private void handleAddAccount() {
        String numeroCompte = numeroCompteField.getText();
        String nomTitulaire = nomTitulaireField.getText();
        double solde = Double.parseDouble(soldeField.getText());

        CompteBancaire newAccount = new CompteCourant(numeroCompte, nomTitulaire, solde, 0); // Exemple avec CompteCourant
        accountData.add(newAccount);

        DatabaseManager.ajouterCompteBancaire(newAccount);
    }
}