package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.PostedJobs;
import edu.esprit.freelancejobs.services.PostedJobService;
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

public class posJobsModif
{
    @javafx.fxml.FXML
    private ComboBox statusInput;
    @javafx.fxml.FXML
    private TextField titleInput;
    @javafx.fxml.FXML
    private TextArea requiredSkillsInput;
    @javafx.fxml.FXML
    private Button btnSave;
    @javafx.fxml.FXML
    private TextField budgetEstimateInput;
    @javafx.fxml.FXML
    private DatePicker startDateInput;
    @javafx.fxml.FXML
    private Label btnListeComm;
    @javafx.fxml.FXML
    private TextArea descriptionInput;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private DatePicker endDateInput;
    @javafx.fxml.FXML
    private Label btnListeProp;

    PostedJobs job;
    PostedJobService postedJobService = new PostedJobService();
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void gotoListePosJob(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/postedJobs/liste.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoAjoutAssJob(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/assignedJobs/ajout.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoAjoutPosJob(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/postedJobs/ajout.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoListeAssJob(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/assignedJobs/liste.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void setObjectToSend(PostedJobs obj) {
        this.job = obj;
        titleInput.setText(job.getTitle());
        descriptionInput.setText(job.getDescription());
        requiredSkillsInput.setText(job.getRequiredSkills());
        budgetEstimateInput.setText(String.format("%.2f", job.getBudgetEstimate()));
        statusInput.setValue(job.getStatus());

        // Setting dates ensuring the values are not null
        if (job.getStartDate() != null) {
            startDateInput.setValue(job.getStartDate().toLocalDate());
        }
        if (job.getEndDate() != null) {
            endDateInput.setValue(job.getEndDate().toLocalDate());
        }
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

        job.setTitle(titleInput.getText());
        job.setDescription(descriptionInput.getText());
        job.setRequiredSkills(requiredSkillsInput.getText());
        job.setBudgetEstimate(Double.parseDouble(budgetEstimateInput.getText()));
        job.setStatus((String) statusInput.getValue());
        job.setStartDate(Date.valueOf(startDateInput.getValue()));
        job.setEndDate(Date.valueOf(endDateInput.getValue()));

        postedJobService.update(job);

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Operation Completed");
        infoAlert.setHeaderText("Job updated successfully.");
        infoAlert.showAndWait();
    }
    public Map<String, String> verifyInputs() {
        Map<String, String> validationErrors = new HashMap<>();

        if (titleInput.getText() == null || titleInput.getText().isEmpty()) {
            validationErrors.put("title", "Please enter the job title.");
        }
        if (descriptionInput.getText() == null || descriptionInput.getText().isEmpty()) {
            validationErrors.put("description", "Please enter the job description.");
        }
        if (requiredSkillsInput.getText() == null || requiredSkillsInput.getText().isEmpty()) {
            validationErrors.put("requiredSkills", "Please enter the required skills for the job.");
        }
        if (budgetEstimateInput.getText() == null || budgetEstimateInput.getText().isEmpty()) {
            validationErrors.put("budgetEstimate", "Please enter the budget estimate.");
        } else {
            try {
                Double.parseDouble(budgetEstimateInput.getText());
            } catch (NumberFormatException e) {
                validationErrors.put("budgetEstimate", "Budget estimate is not a valid number.");
            }
        }
        if (startDateInput.getValue() == null) {
            validationErrors.put("startDate", "Please select a start date.");
        }
        if (endDateInput.getValue() == null) {
            validationErrors.put("endDate", "Please select an end date.");
        }
        if (statusInput.getValue() == null) {
            validationErrors.put("status", "Please select the job status.");
        }

        return validationErrors;
    }
}