

package controllers;

import entities.Organisation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceOrganisation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class afficherOrganisationController {
    public Button delete;

    public Button update;

    ServiceOrganisation serviceOrganisation = new ServiceOrganisation();
    @FXML
    private TableColumn<Organisation, String> nomCol;
    @FXML
    private TableColumn<Organisation, String> adresseCol;
    @FXML
    private TableColumn<Organisation, Integer> telephoneCol;
    @FXML
    private TableColumn<Organisation, String> emailCol;
    @FXML
    private TableColumn<Organisation, String> domaine_activiteCol;
    @FXML
    private TableColumn<Organisation, String> contactCol;
    @FXML
    private TableView<Organisation> tableView;
    @FXML
    private Label welcomeLBL;
    @FXML
    private ObservableList<Organisation> observableList;

    public afficherOrganisationController() {
    }

    @FXML
    void initialize() {
        try {
            List<Organisation> organisationList = this.serviceOrganisation.afficher();
            this.observableList = FXCollections.observableList(organisationList);
            this.tableView.setItems(this.observableList);
            this.nomCol.setCellValueFactory(new PropertyValueFactory("nom"));
            this.adresseCol.setCellValueFactory(new PropertyValueFactory("adresse"));
            this.telephoneCol.setCellValueFactory(new PropertyValueFactory("telephone"));
            this.emailCol.setCellValueFactory(new PropertyValueFactory("email"));
            this.contactCol.setCellValueFactory(new PropertyValueFactory("contact"));
            this.domaine_activiteCol.setCellValueFactory(new PropertyValueFactory("domaine_activite"));
           // loadData();
        } catch (SQLException var2) {
            System.err.println(var2.getMessage());
        }

    }
    public void loadData() {
        // Récupérer la liste des organisations depuis la source de données (par exemple, base de données)
        Set<Organisation> organisationList = serviceOrganisation.getAll();

        // Effacer toutes les données actuellement affichées dans la TableView
        tableView.getItems().clear();

        // Ajouter les nouvelles données à la TableView
        tableView.getItems().addAll(organisationList);
    }
    @FXML
    void delete(ActionEvent event) {
        try {
            Organisation organisation = this.tableView.getSelectionModel().getSelectedItem();
            if (organisation != null) {
                this.serviceOrganisation.supprimer(organisation.getId());
                this.observableList.remove(organisation);
            }
        } catch (SQLException var3) {
            System.err.println("Erreur lors de la suppression : " + var3.getMessage());
        }

    }

    @FXML
    public void setData(String param) {
        this.welcomeLBL.setText("Welcome " + param);
    }



    @FXML
    void editOrganisation() {
        // Récupérer le contrat sélectionné dans le TableView
        Organisation selectedOrganisation = tableView.getSelectionModel().getSelectedItem();

        if (selectedOrganisation != null) {
            try {
                // Charger la vue de modification de contrat depuis le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateOrganisation.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé à la vue de modification de contrat
                modifierOrganisationController moc = loader.getController();

                // Passer le contrat sélectionné au contrôleur de la vue de modification
                moc.initData(selectedOrganisation);

                // Afficher la scène de modification de contrat
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Afficher une alerte si aucun contrat n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun contrat sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un contrat à modifier.");
            alert.showAndWait();
        }
    }
    @FXML
    void showAllData(ActionEvent event) {
        try {
            // Charger le fichier FXML de la vue affichant toutes les données de la base
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatedOrganisation.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la racine chargée à partir du fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle à partir de n'importe quel nœud de la scène
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène dans la fenêtre
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la vue de toutes les données : " + e.getMessage());
        }
    }


}
