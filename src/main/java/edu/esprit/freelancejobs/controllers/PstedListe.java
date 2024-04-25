package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.PostedJobs;
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

public class PstedListe
{
    @javafx.fxml.FXML
    private ListView postedJobsListView;
    @javafx.fxml.FXML
    private Button modifBtn;

    PostedJobService postedJobService = new PostedJobService();
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

    @javafx.fxml.FXML
    public void initialize() {
        List<PostedJobs> postedJobs = (List<PostedJobs>) postedJobService.getAll();
        postedJobsListView.setItems(FXCollections.observableArrayList(postedJobs));

        postedJobsListView.setCellFactory(postedJobsListView -> new ListCell<PostedJobs>() {
            @Override
            protected void updateItem(PostedJobs job, boolean empty) {
                super.updateItem(job, empty);
                if (empty || job == null) {
                    setText(null);
                } else {
                    // Display the details for the PostedJobs in your ListView
                    setText(job.getTitle() + " - Budget: " + job.getBudgetEstimate() + " - " + job.getStatus());
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
        PostedJobs job = (PostedJobs) postedJobsListView.getSelectionModel().getSelectedItem();
        if (job != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Do you want to delete the job titled: " + job.getTitle() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                postedJobService.delete(job.getId());
                Alert alertDone = new Alert(Alert.AlertType.INFORMATION);
                alertDone.setTitle("Operation Completed");
                alertDone.setHeaderText("Job deleted successfully.");
                alertDone.showAndWait();
                // Refresh the list view if necessary
            }
        }
    }

    @javafx.fxml.FXML
    public void gotoModif(ActionEvent actionEvent) throws IOException {
        PostedJobs selectedJob = (PostedJobs) postedJobsListView.getSelectionModel().getSelectedItem();
        if (selectedJob != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/postedJobs/modifier.fxml"));
            Parent root = loader.load();

            posJobsModif modifyController = loader.getController();
            modifyController.setObjectToSend(selectedJob);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Deprecated
    public void gotoApply(ActionEvent actionEvent) {
    }
}