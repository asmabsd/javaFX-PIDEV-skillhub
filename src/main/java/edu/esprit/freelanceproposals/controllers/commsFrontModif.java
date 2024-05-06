package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Commentaire;
import edu.esprit.freelanceproposals.entities.Proposal;
import edu.esprit.freelanceproposals.services.CommentaireService;
import edu.esprit.freelanceproposals.services.ProposalService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
public class commsFrontModif
{
    @javafx.fxml.FXML
    private TextField auteurInput;
    @javafx.fxml.FXML
    private ComboBox noteInput;
    @javafx.fxml.FXML
    private TextArea commentaireInput;
    @javafx.fxml.FXML
    private Label btnListeComm;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private DatePicker datePublicationInput;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private Label btnListeProp;
    private Commentaire c; // Assume this is a property of the controller
    CommentaireService commentaireService = new CommentaireService();
    public void setObjectToSend(Commentaire obj) {
        this.c = obj;
        auteurInput.setText(c.getAuteur());
        commentaireInput.setText(c.getCommentaire());
        noteInput.setValue(c.getNote()); // Ensure the ComboBox is populated with appropriate values

        // Set date ensuring the value is not null
        if (c.getDatePublication() != null) {
            datePublicationInput.setValue(c.getDatePublication().toLocalDate());
        }
    }
    @javafx.fxml.FXML
    public void initialize() {
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
    public void saveCommentaire(ActionEvent actionEvent) {
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

        c.setAuteur(auteurInput.getText());
        c.setCommentaire(commentaireInput.getText());
        c.setDatePublication(Date.valueOf(datePublicationInput.getValue()));
        c.setNote((Integer) noteInput.getValue());

        commentaireService.modifier(c);  // Assume 'commentaireService' has an 'update' method similar to 'modifier' for Proposal

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Operation Completed");
        infoAlert.setHeaderText("Commentaire updated successfully.");
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