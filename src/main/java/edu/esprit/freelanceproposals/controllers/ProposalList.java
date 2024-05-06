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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProposalList
{
    @javafx.fxml.FXML
    private Label btnListeComm;
    @javafx.fxml.FXML
    private ListView proposalListView;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private Label btnListeProp;

    ProposalService proposalService = new ProposalService();
    @javafx.fxml.FXML
    private Button modifBtn;
    @javafx.fxml.FXML
    private Button suppBtn;

    @javafx.fxml.FXML
    public void initialize() {
        List<Proposal> proposals = proposalService.listeProposals();
        proposalListView.setItems(FXCollections.observableArrayList(proposals));
        proposalListView.setCellFactory(proposalListView -> new ListCell<Proposal>() {
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
    public void gotoListeProp(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/liste.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoListeComm(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/liste.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoAjoutProp(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/ajout.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoAjoutComm(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/ajout.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void supp(ActionEvent actionEvent) {
        Proposal v = (Proposal) proposalListView.getSelectionModel().getSelectedItem();
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

    @javafx.fxml.FXML
    public void gotoModif(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/modifier.fxml"));
        Parent root = loader.load();

        ProposalModif newController = loader.getController();
        newController.setObjectToSend((Proposal) proposalListView.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}