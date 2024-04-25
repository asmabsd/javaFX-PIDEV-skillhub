package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.AssignedJobs;
import edu.esprit.freelancejobs.services.AssignedJobsService;
import edu.esprit.freelancejobs.services.PostedJobService;
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

public class AssignedListe
{
    @javafx.fxml.FXML
    private ListView assignedJobsListView;
    @javafx.fxml.FXML
    private Button modifBtn;
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

    AssignedJobsService assignedJobsService = new AssignedJobsService();
    PostedJobService postedJobService = new PostedJobService();
    @javafx.fxml.FXML
    public void initialize() {
        List<AssignedJobs> assignedJobsList = assignedJobsService.getAll(); // Replace 'getAll' with your actual method
        assignedJobsListView.setItems(FXCollections.observableArrayList(assignedJobsList));

        assignedJobsListView.setCellFactory(assignedJobsListView -> new ListCell<AssignedJobs>() {
            @Override
            protected void updateItem(AssignedJobs job, boolean empty) {
                super.updateItem(job, empty);
                if (empty || job == null) {
                    setText(null);
                } else {
                    // Format the text as you need, using the AssignedJobs object properties
                    setText("Job: " + postedJobService.getOneById(job.getNoId()).getTitle()
                            + ": Status: " + job.getStatus()+" from "+job.getStartDate()+" to "+job.getEndDate());
                }
            }
        });
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

    @javafx.fxml.FXML
    public void supp(ActionEvent actionEvent) {
        AssignedJobs job = (AssignedJobs) assignedJobsListView.getSelectionModel().getSelectedItem();
        if (job != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete this assignment with ID: " + job.getId() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                assignedJobsService.delete(job.getId()); // Replace 'delete' with your actual method to delete the job
                Alert alertDone = new Alert(Alert.AlertType.INFORMATION);
                alertDone.setTitle("Operation Completed");
                alertDone.setHeaderText("Assignment deleted successfully.");
                alertDone.showAndWait();
            }
        }
    }

    @javafx.fxml.FXML
    public void gotoModif(ActionEvent actionEvent) throws IOException {
        AssignedJobs selectedJob = (AssignedJobs) assignedJobsListView.getSelectionModel().getSelectedItem();
        if (selectedJob != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/assignedJobs/modifier.fxml"));
            Parent root = loader.load();

            assJobsModif modifyController = loader.getController();
            modifyController.setObjectToSend(selectedJob);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}