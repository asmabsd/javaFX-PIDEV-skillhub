package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Proposal;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ProposalController
{
    ProposalService proposalService = new ProposalService();
    @javafx.fxml.FXML
    private Label btnListeComm;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private Label btnListeProp;
    @javafx.fxml.FXML
    private TextField titreInput;
    @javafx.fxml.FXML
    private DatePicker dateDebutInput;
    @javafx.fxml.FXML
    private DatePicker delaiInput;
    @javafx.fxml.FXML
    private ComboBox statutInput;
    @javafx.fxml.FXML
    private TextArea descriptionInput;
    @javafx.fxml.FXML
    private DatePicker dateFinInput;
    @javafx.fxml.FXML
    private TextField clientInput;
    @javafx.fxml.FXML
    private Button btnSave;
    @javafx.fxml.FXML
    private TextField budgetInput;
    @javafx.fxml.FXML
    private TextField fichiersInput;
    @javafx.fxml.FXML
    private DatePicker dateSoumissionInput;
    @javafx.fxml.FXML
    private TextField freelancerInput;

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
    public void save(ActionEvent actionEvent) {
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
        String titre = titreInput.getText();
        String description = descriptionInput.getText();
        String client = clientInput.getText();
        String freelancer = freelancerInput.getText();
        double budget = Double.parseDouble(budgetInput.getText()); // Ensure this text field contains a valid double before parsing
        LocalDate delai = delaiInput.getValue();
        LocalDate dateSoumission = dateSoumissionInput.getValue();
        LocalDate dateDebut = dateDebutInput.getValue();
        LocalDate dateFin = dateFinInput.getValue();
        String statut = statutInput.getSelectionModel().getSelectedItem().toString();
        String fichiers = fichiersInput.getText();

        Proposal proposal = new Proposal(titre, description, client, freelancer, budget, Date.valueOf(delai), statut, Date.valueOf(dateSoumission), Date.valueOf(dateDebut), Date.valueOf(dateFin), fichiers);
        proposalService.ajout(proposal);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Opération terminée");
        alert.setHeaderText("Proposition ajouté avec succès.");
        alert.showAndWait();
    }

    public Map<String, String> verifyInputs() {
        Map<String, String> validationErrors = new HashMap<>();

        // Check if title is empty
        if (titreInput.getText() == null || titreInput.getText().isEmpty()) {
            validationErrors.put("titre", "Please enter a title.");
        }

        // Check if description is empty
        if (descriptionInput.getText() == null || descriptionInput.getText().isEmpty()) {
            validationErrors.put("description", "Please enter a description.");
        }

        // Check if client name is empty
        if (clientInput.getText() == null || clientInput.getText().isEmpty()) {
            validationErrors.put("client", "Please enter a client name.");
        }

        // Check if freelancer name is empty
        if (freelancerInput.getText() == null || freelancerInput.getText().isEmpty()) {
            validationErrors.put("freelancer", "Please enter a freelancer name.");
        }

        // Validate budget input
        if (budgetInput.getText() == null || budgetInput.getText().isEmpty()) {
            validationErrors.put("budget", "Please enter a budget.");
        } else {
            try {
                Double.parseDouble(budgetInput.getText());
            } catch (NumberFormatException e) {
                validationErrors.put("budget", "Budget is not a valid number.");
            }
        }

        // Validate date fields
        if (dateDebutInput.getValue() == null) {
            validationErrors.put("dateDebut", "Please select a start date.");
        }
        if (dateFinInput.getValue() == null) {
            validationErrors.put("dateFin", "Please select an end date.");
        }
        if (dateSoumissionInput.getValue() == null) {
            validationErrors.put("dateSoumission", "Please select a submission date.");
        }
        if (delaiInput.getValue() == null) {
            validationErrors.put("delai", "Please select a deadline date.");
        }

        // Check if status is selected
        if (statutInput.getSelectionModel().getSelectedItem() == null) {
            validationErrors.put("statut", "Please select a status.");
        }

        return validationErrors;
    }
}