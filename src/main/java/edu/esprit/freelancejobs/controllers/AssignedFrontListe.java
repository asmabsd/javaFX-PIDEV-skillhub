package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.AssignedJobs;
import edu.esprit.freelancejobs.services.AssignedJobsService;
import edu.esprit.freelancejobs.services.PostedJobService;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AssignedFrontListe
{
    @javafx.fxml.FXML
    private VBox mainContainer;
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private ListView assignedJobsListView;
    @javafx.fxml.FXML
    private VBox contentArea;
    @javafx.fxml.FXML
    private Button gotoJobs;
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
}