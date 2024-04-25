package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.PostedJobs;
import edu.esprit.freelancejobs.services.PostedJobService;
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

public class PostedFrontAjout
{
    @javafx.fxml.FXML
    private VBox mainContainer;
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private ComboBox statusInput;
    @javafx.fxml.FXML
    private TextField titleInput;
    @javafx.fxml.FXML
    private TextArea requiredSkillsInput;
    @javafx.fxml.FXML
    private VBox contentArea;
    @javafx.fxml.FXML
    private Button btnSave;
    @javafx.fxml.FXML
    private TextField budgetEstimateInput;
    @javafx.fxml.FXML
    private Button gotoJobs;
    @javafx.fxml.FXML
    private DatePicker startDateInput;
    @javafx.fxml.FXML
    private TextArea descriptionInput;
    @javafx.fxml.FXML
    private DatePicker endDateInput;
    PostedJobService postedJobService = new PostedJobService();

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void gotoAssignments(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/assignedJobs/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoJobs(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/postedJobs/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void save(ActionEvent event) {
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

        String title = titleInput.getText();
        String description = descriptionInput.getText();
        String requiredSkills = requiredSkillsInput.getText();
        double budgetEstimate = Double.parseDouble(budgetEstimateInput.getText());
        LocalDate startDate = startDateInput.getValue();
        LocalDate endDate = endDateInput.getValue();
        String status = (String) statusInput.getValue();

        PostedJobs newJob = new PostedJobs(0, title, description, requiredSkills, budgetEstimate, Date.valueOf(startDate), Date.valueOf(endDate), status);
        postedJobService.add(newJob);

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Operation Completed");
        infoAlert.setHeaderText("Job posted successfully.");
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