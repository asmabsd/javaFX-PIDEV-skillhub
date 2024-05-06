package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Commentaire;
import edu.esprit.freelanceproposals.services.CommentaireService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class commsFrontListe
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
    private Button addBtn;
    @javafx.fxml.FXML
    private ListView commsListView;
    CommentaireService commentaireService = new CommentaireService();
    @javafx.fxml.FXML
    private Button modifBtn;
    @javafx.fxml.FXML
    private Button suppBtn;

    @javafx.fxml.FXML
    public void initialize() {
        List<Commentaire> commentaires = commentaireService.listeCommentaires();
        commsListView.setItems(FXCollections.observableArrayList(commentaires));
        commsListView.setCellFactory(commentaireListView -> new ListCell<Commentaire>() {
            @Override
            protected void updateItem(Commentaire commentaire, boolean empty) {
                super.updateItem(commentaire, empty);
                if (empty || commentaire == null) {
                    setText(null);
                } else {
                    Label text = new Label(commentaire.getAuteur() + ": " + commentaire.getCommentaire() + " - Note: " + commentaire.getNote());
                    text.setPrefWidth(630);
                    Button likeButton = new Button("\uD83D\uDC4D");
                    likeButton.setStyle("-fx-background-color: GREEN; -fx-text-fill: white;");
                    likeButton.setOnAction(event -> {
                        commentaireService.ajoutReaction(commentaire,true);
                        initialize();
                    });
                    Button dislikeButton = new Button("\uD83D\uDC4E");
                    dislikeButton.setStyle("-fx-background-color: RED; -fx-text-fill: white;");
                    dislikeButton.setOnAction(event -> {
                        commentaireService.ajoutReaction(commentaire,false);
                        initialize();
                    });

                    List<Boolean> reactions = commentaireService.getReactionsByComment(commentaire.getId());
                    int trueCount = (int) reactions.stream().filter(Boolean::booleanValue).count();
                    int falseCount = reactions.size() - trueCount;
                    Label pos = new Label("("+trueCount+")");
                    Label fal = new Label("("+falseCount+")");

                    HBox buttonBox = new HBox(text, likeButton,pos, dislikeButton,fal);
                    setGraphic(buttonBox);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/AjoutFront.fxml"));
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
    public void supp(ActionEvent actionEvent) {
        Commentaire v = (Commentaire) commsListView.getSelectionModel().getSelectedItem();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/ModifFront.fxml"));
        Parent root = loader.load();

        CommentaireModif newController = loader.getController();
        newController.setObjectToSend((Commentaire) commsListView.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}