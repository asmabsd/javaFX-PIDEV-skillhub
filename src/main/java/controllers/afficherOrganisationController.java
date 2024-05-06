

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceOrganisation;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class afficherOrganisationController {
    public Button delete;

    public Button update;
    public Button stats1;
    public Label welcomeLBL1;

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
    @FXML
    void afficherCharte(ActionEvent event) {
        // Créez un axe de catégorie pour les villes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Ville");

        // Créez un axe numérique pour le nombre d'organisations dans chaque ville
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre d'organisations");

        // Créez le graphique à barres
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Répartition géographique des organisations par ville");

        // Ajoutez les données au graphique
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Nombre d'organisations");

        try {
            // Connexion à la base de données
            Connection connection = MyDatabase.getInstance().getConnection();

            // Requête SQL pour compter le nombre d'organisations pour chaque ville
            String sql = "SELECT adresse, COUNT(*) AS nombre_organisations FROM organisation GROUP BY adresse";

            // Création de l'instruction préparée
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // Exécution de la requête SQL
                try (ResultSet rs = stmt.executeQuery()) {
                    // Parcours des résultats de la requête
                    while (rs.next()) {
                        String ville = rs.getString("adresse");
                        int nombreOrganisations = rs.getInt("nombre_organisations");
                        // Ajoutez la ville et le nombre d'organisations correspondant à la série de données
                        dataSeries.getData().add(new XYChart.Data<>(ville, nombreOrganisations));
                    }
                }
            }

            // Ajoutez la série de données au graphique
            barChart.getData().add(dataSeries);

            // Ajoutez le graphique à votre interface utilisateur
            AnchorPane chartPane = new AnchorPane(barChart);
            // Positionnez le graphique comme vous le souhaitez dans votre interface utilisateur
            // Par exemple, chartPane.setLayoutX(xValue); et chartPane.setLayoutY(yValue);

            // Créez une nouvelle fenêtre pour afficher le graphique
            Stage stage = new Stage();
            stage.setScene(new Scene(chartPane));
            stage.setTitle("Répartition géographique des organisations par ville");
            stage.show();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception selon vos besoins
        }
    }


}
