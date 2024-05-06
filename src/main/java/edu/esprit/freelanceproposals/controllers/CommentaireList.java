package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Commentaire;
import edu.esprit.freelanceproposals.entities.Proposal;
import edu.esprit.freelanceproposals.services.CommentaireService;
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

public class CommentaireList {
    @javafx.fxml.FXML
    private Button modifBtn;
    @javafx.fxml.FXML
    private ListView commListView;
    @javafx.fxml.FXML
    private Button suppBtn;
    @javafx.fxml.FXML
    private Label btnListeComm;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private Label btnListeProp;

    CommentaireService commentaireService = new CommentaireService();
    @javafx.fxml.FXML
    public void initialize() {
        List<Commentaire> commentaires = commentaireService.listeCommentaires();
        commListView.setItems(FXCollections.observableArrayList(commentaires));
        commListView.setCellFactory(commentaireListView -> new ListCell<Commentaire>() {
            @Override
            protected void updateItem(Commentaire commentaire, boolean empty) {
                super.updateItem(commentaire, empty);
                if (empty || commentaire == null) {
                    setText(null);
                } else {
                    setText(commentaire.getAuteur() + ": " + commentaire.getCommentaire() + " - Note: " + commentaire.getNote());
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
        Commentaire v = (Commentaire) commListView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmer la suppression");
        alert.setContentText("Voulez cous supprimer le commentaire à "+v.getCommentaire());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            commentaireService.supprimer(v.getId());
            Alert alertDone = new Alert(Alert.AlertType.INFORMATION);
            alertDone.setTitle("Opération terminée");
            alertDone.setHeaderText("Commentaire supprimé avec succès.");
            alertDone.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void gotoModif(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/modifier.fxml"));
        Parent root = loader.load();

        CommentaireModif newController = loader.getController();
        newController.setObjectToSend((Commentaire) commListView.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}