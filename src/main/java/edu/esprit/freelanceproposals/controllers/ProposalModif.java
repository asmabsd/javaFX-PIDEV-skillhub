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
import java.util.HashMap;
import java.util.Map;

public class ProposalModif
{
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
    private Label btnListeComm;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private TextField freelancerInput;
    @javafx.fxml.FXML
    private Label btnListeProp;

    @javafx.fxml.FXML
    public void initialize() {
    }
    private Proposal p;
    ProposalService proposalService = new ProposalService();

    public void setObjectToSend(Proposal obj) {
        this.p = obj;
        titreInput.setText(p.getTitre());
        descriptionInput.setText(p.getDescription());
        clientInput.setText(p.getClient());
        freelancerInput.setText(p.getFreelancer());
        fichiersInput.setText(p.getFichiers());
        budgetInput.setText(String.format("%.2f", p.getBudget())); // Assuming budget is a double
        statutInput.setValue(p.getStatut());

        // Setting dates ensuring the values are not null
        if (p.getDateSoumission() != null) {
            dateSoumissionInput.setValue(p.getDateSoumission().toLocalDate());
        }
        if (p.getDateDebut() != null) {
            dateDebutInput.setValue(p.getDateDebut().toLocalDate());
        }
        if (p.getDateFin() != null) {
            dateFinInput.setValue(p.getDateFin().toLocalDate());
        }
        if (p.getDelai() != null) {
            delaiInput.setValue(p.getDelai().toLocalDate());
        }

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
        p.setTitre(titreInput.getText());
        p.setDescription(descriptionInput.getText());
        p.setClient(clientInput.getText());
        p.setFreelancer(freelancerInput.getText());
        p.setBudget(Double.parseDouble(budgetInput.getText())); // Handle NumberFormatException
        p.setStatut((String) statutInput.getValue());
        p.setDateSoumission(Date.valueOf(dateSoumissionInput.getValue()));
        p.setDateDebut(Date.valueOf(dateDebutInput.getValue()));
        p.setDateFin(Date.valueOf(dateFinInput.getValue()));

        proposalService.modifier(p);

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Operation Completed");
        infoAlert.setHeaderText("Proposition modifiée avec succès.");
        infoAlert.showAndWait();
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