package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.AssignedJobs;
import edu.esprit.freelancejobs.entities.PostedJobs;
import edu.esprit.freelancejobs.services.AssignedJobsService;
import edu.esprit.freelancejobs.services.PostedJobService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class assJobsModif
{
    @javafx.fxml.FXML
    private ComboBox noIdInput;
    @javafx.fxml.FXML
    private ComboBox statusInput;
    @javafx.fxml.FXML
    private Button btnSave;
    @javafx.fxml.FXML
    private DatePicker startDateInput;
    @javafx.fxml.FXML
    private Label btnListeComm;
    @javafx.fxml.FXML
    private Label btnAjoutProp;
    @javafx.fxml.FXML
    private Label btnAjoutComm;
    @javafx.fxml.FXML
    private DatePicker endDateInput;
    @javafx.fxml.FXML
    private Label btnListeProp;
    private AssignedJobs assignedJob;
    AssignedJobsService assignedJobsService = new AssignedJobsService();
    PostedJobService postedJobService = new PostedJobService();
    @javafx.fxml.FXML
    public void initialize() {
        ObservableList<PostedJobs> voyages = FXCollections.observableArrayList(postedJobService.getAll());
        noIdInput.setItems(voyages);
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

    public void setObjectToSend(AssignedJobs obj) {
        this.assignedJob = obj;
        noIdInput.setValue(postedJobService.getOneById(assignedJob.getNoId()));
        statusInput.setValue(assignedJob.getStatus());
        if (assignedJob.getStartDate() != null) {
            startDateInput.setValue(assignedJob.getStartDate().toLocalDate());
        }
        if (assignedJob.getEndDate() != null) {
            endDateInput.setValue(assignedJob.getEndDate().toLocalDate());
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

        assignedJob.setNoId(((PostedJobs)noIdInput.getValue()).getId());
        assignedJob.setStatus((String) statusInput.getValue());
        assignedJob.setStartDate(Date.valueOf(startDateInput.getValue()));
        assignedJob.setEndDate(Date.valueOf(endDateInput.getValue()));

        assignedJobsService.update(assignedJob);

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Operation Completed");
        infoAlert.setHeaderText("Assigned job updated successfully.");
        infoAlert.showAndWait();
    }
    public Map<String, String> verifyInputs() {
        Map<String, String> validationErrors = new HashMap<>();

        if (noIdInput.getValue() == null) {
            validationErrors.put("noId", "Please select a job.");
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