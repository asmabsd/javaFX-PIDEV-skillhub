package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Commentaire;
import edu.esprit.freelanceproposals.services.CommentaireService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class commsFrontAjout
{
    @javafx.fxml.FXML
    private VBox mainContainer;
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private VBox contentArea;
    @javafx.fxml.FXML
    private TextField auteurInput;
    @javafx.fxml.FXML
    private ComboBox noteInput;
    @javafx.fxml.FXML
    private Button gotoJobs;
    @javafx.fxml.FXML
    private TextArea commentaireInput;
    @javafx.fxml.FXML
    private DatePicker datePublicationInput;
    CommentaireService commentaireService = new CommentaireService();
    @javafx.fxml.FXML
    public void initialize() {
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
    public void gotoComments(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void saveCommentaire(ActionEvent event) {
        Map<String, String> validationErrors = verifyInputs();

        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (String error : validationErrors.values()) {
                errorMessage.append(error).append("\n");
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please correct the following errors:");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return;
        }

        String auteur = auteurInput.getText();
        String commentaire = commentaireInput.getText();
        LocalDate datePublication = datePublicationInput.getValue();
        Integer note = (Integer) noteInput.getValue();

        Commentaire newCommentaire = new Commentaire(0, auteur, commentaire, Date.valueOf(datePublication), note);
        commentaireService.ajout(newCommentaire);

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Operation Completed");
        infoAlert.setHeaderText("Comment saved successfully.");
        infoAlert.showAndWait();
    }

    public Map<String, String> verifyInputs() {
        Map<String, String> validationErrors = new HashMap<>();

        if (auteurInput.getText() == null || auteurInput.getText().isEmpty()) {
            validationErrors.put("auteur", "Please enter the author's name.");
        }

        if (commentaireInput.getText() == null || commentaireInput.getText().isEmpty()) {
            validationErrors.put("commentaire", "Please enter the comment text.");
        }

        if (datePublicationInput.getValue() == null) {
            validationErrors.put("datePublication", "Please select a publication date.");
        }

        if (noteInput.getValue() == null) {
            validationErrors.put("note", "Please select a note.");
        }

        return validationErrors;
    }
}