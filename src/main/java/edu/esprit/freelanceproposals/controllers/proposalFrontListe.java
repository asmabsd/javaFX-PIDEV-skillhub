package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Proposal;
import edu.esprit.freelanceproposals.services.ProposalService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class proposalFrontListe
{
    @javafx.fxml.FXML
    private VBox mainContainer;
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private VBox contentArea;
    @javafx.fxml.FXML
    private Button gotoJobs;
    @javafx.fxml.FXML
    private ListView proposalsListView;
    @javafx.fxml.FXML
    private Button addBtn;
    ProposalService proposalService = new ProposalService();
    @javafx.fxml.FXML
    private Button modifBtn;
    @javafx.fxml.FXML
    private Button suppBtn;

    @javafx.fxml.FXML
    public void initialize() {
        List<Proposal> proposals = proposalService.listeProposals();
        proposalsListView.setItems(FXCollections.observableArrayList(proposals));
        proposalsListView.setCellFactory(proposalListView -> new ListCell<Proposal>() {
            @Override
            protected void updateItem(Proposal proposal, boolean empty) {
                super.updateItem(proposal, empty);
                if (empty || proposal == null) {
                    setText(null);
                } else {
                    setText(proposal.getTitre() + " - " + proposal.getClient());
                }
            }
        });
    }

    @javafx.fxml.FXML
    public void gotoProposals(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoAjout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/AjoutFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoComments(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @javafx.fxml.FXML
    public void gotoModif(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/ModifFront.fxml"));
        Parent root = loader.load();

        proposalFrontModif newController = loader.getController();
        newController.setObjectToSend((Proposal) proposalsListView.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void supp(ActionEvent actionEvent) {
        Proposal v = (Proposal) proposalsListView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmer la suppression");
        alert.setContentText("Voulez cous supprimer la proposition à "+v.getTitre());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            proposalService.supprimer(v.getId());
            Alert alertDone = new Alert(Alert.AlertType.INFORMATION);
            alertDone.setTitle("Opération terminée");
            alertDone.setHeaderText("Proposition supprimée avec succès.");
            alertDone.showAndWait();
        }

    }
}