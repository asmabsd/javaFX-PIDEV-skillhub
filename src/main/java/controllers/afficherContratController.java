
package controllers;

import entities.Contrat;
import entities.Organisation;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceContrat;
import services.ServiceOrganisation;
import services.ServiceUser;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class afficherContratController {
    public Button delete;
    public Button update;
    ServiceContrat sc = new ServiceContrat();
    @FXML
    private TableColumn<Contrat, Date> date_debutCol;
    @FXML
    private TableColumn<Contrat, Date> date_finCol;
    @FXML
    private TableColumn<Contrat, Integer> montantCol;
    @FXML
    private TableColumn<Contrat, String> statutCol;
    @FXML
    private TableColumn<Contrat, String> projetCol;
    @FXML
    private TableColumn<Contrat, String> freelancerCol;
    @FXML
    private TableColumn<Contrat, Organisation> organisation_idCol;
    @FXML
    private TableColumn<Contrat, User> user_idCol;
    @FXML
    private TableColumn<Contrat, Date> date_creationCol;
    @FXML
    private TableColumn<Contrat, String> descriptionCol;
    @FXML
    private TableView<Contrat> tableView;
    @FXML
    private Label welcomeLBL;
    ObservableList<Contrat> observableList;
    @FXML
    private TextField montantTf;
    @FXML
    private TextField statutTf;
    @FXML
    private TextField projetTf;
    @FXML
    private TextField freelancerTf;
    @FXML
    private TextField organisation_idTf;
    @FXML
    private TextField user_idTf;
    @FXML
    private TextField descriptionTf;
    @FXML
    private DatePicker date_debutp;
    @FXML
    private DatePicker date_finp;
    @FXML
    private DatePicker date_creationp;
    private Contrat selectedContrat;

    public afficherContratController() {
    }

    @FXML
    void initialize() {
        try {
            List<Contrat> contratList = this.sc.afficher();
            this.observableList = FXCollections.observableList(contratList);
            this.tableView.setItems(this.observableList);
            this.date_debutCol.setCellValueFactory(new PropertyValueFactory("date_debut"));
            this.date_finCol.setCellValueFactory(new PropertyValueFactory("date_fin"));
            this.montantCol.setCellValueFactory(new PropertyValueFactory("montant"));
            this.statutCol.setCellValueFactory(new PropertyValueFactory("statut"));
            this.projetCol.setCellValueFactory(new PropertyValueFactory("projet"));
            this.freelancerCol.setCellValueFactory(new PropertyValueFactory("freelancer"));
            this.organisation_idCol.setCellValueFactory(new PropertyValueFactory("organisation_id"));
            this.user_idCol.setCellValueFactory(new PropertyValueFactory("user_id"));
            this.date_creationCol.setCellValueFactory(new PropertyValueFactory("date_creation"));
            this.descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        } catch (SQLException var2) {
            System.err.println(var2.getMessage());
        }

    }

    @FXML
    void delete(ActionEvent event) {
        try {
            Contrat c = this.tableView.getSelectionModel().getSelectedItem();
            this.sc.supprimer(c.getId());
            this.observableList.remove(c);
        } catch (SQLException var3) {
            System.err.println(var3.getMessage());
        }

    }

    @FXML
    void setData(String param) {
        this.welcomeLBL.setText("Liste des contrats  " + param);
    }

    public class ButtonTableCellFactory implements Callback<TableColumn<Contrat, String>, TableCell<Contrat, String>> {

        @Override
        public TableCell<Contrat, String> call(TableColumn<Contrat, String> param) {
            return new TableCell<>() {
                final Button btn = new Button("Edit");

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setGraphic(btn);
                        btn.setOnAction(event -> {
                            Contrat selectedContrat = getTableView().getItems().get(getIndex());
                            populateFormWithEvent(selectedContrat);
                        });
                    }
                }
            };
        }


    }


    @FXML
    private void populateFormWithEvent(Contrat selectedEvent) {

        statutTf.setText(selectedEvent.getStatut());

        projetTf.setText(selectedContrat.getProjet());
        freelancerTf.setText(selectedContrat.getFreelancer());
        montantTf.setText(String.valueOf(selectedEvent.getMontant()));
        descriptionTf.setText(selectedContrat.getDescription());
        date_debutp.setValue(selectedContrat.getDate_debut().toLocalDate());
        date_finp.setValue(selectedContrat.getDate_fin().toLocalDate());
        date_creationp.setValue(selectedContrat.getDate_creation().toLocalDate());
        user_idTf.setText(String.valueOf(selectedContrat.getUser_id()));
        organisation_idTf.setText(String.valueOf(selectedContrat.getOrganisation_id()));

    }

    ServiceOrganisation so = new ServiceOrganisation();
    ServiceUser su = new ServiceUser();

    private void updateSelectedContratWithFormData(Contrat selectedContrat) {
        selectedContrat.setMontant(Integer.parseInt(montantTf.getText()));
        selectedContrat.setFreelancer(freelancerTf.getText());
        selectedContrat.setStatut(statutTf.getText());
        selectedContrat.setDescription(descriptionTf.getText());
        selectedContrat.setProjet(projetTf.getText());
        selectedContrat.setDate_debut(Date.valueOf(date_debutp.getValue()));
        selectedContrat.setDate_fin(Date.valueOf(date_finp.getValue()));
        selectedContrat.setDate_creation(Date.valueOf(date_creationp.getValue()));
        selectedContrat.setUser_id(su.getOneById(Integer.parseInt(user_idTf.getText())));

        selectedContrat.setOrganisation_id(so.getOneById(Integer.parseInt(organisation_idTf.getText())));
    }

    private void clearFormFields() {
        freelancerTf.clear();
        statutTf.clear();
        descriptionTf.clear();
        projetTf.clear();
        //  statutComboBox.getSelectionModel().clearSelection();
        user_idTf.clear();
        organisation_idTf.clear();
        montantTf.clear();
        date_debutp.setValue(null);
        date_finp.setValue(null);
        date_finp.setValue(null);
    }

    @FXML
    void naviguez(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherContrat.fxml"));
            Parent root = loader.load();
            afficherContratController apc = loader.getController();
            apc.setData(statutTf.getText());
            montantTf.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void editContrat() {
        // Récupérer le contrat sélectionné dans le TableView
        Contrat selectedContrat = tableView.getSelectionModel().getSelectedItem();

        if (selectedContrat != null) {
            try {
                // Charger la vue de modification de contrat depuis le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateContrat.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé à la vue de modification de contrat
                modifierContratController modifierContratController = loader.getController();

                // Passer le contrat sélectionné au contrôleur de la vue de modification
                modifierContratController.initData(selectedContrat);

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

}

