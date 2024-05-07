package edu.esprit.freelancejobs.controllers;

import edu.esprit.freelancejobs.entities.PostedJobs;
import edu.esprit.freelancejobs.services.PostedJobService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostedFrontListe
{
    @javafx.fxml.FXML
    private VBox mainContainer;
    @javafx.fxml.FXML
    private ListView postedJobsListView;
    @javafx.fxml.FXML
    private Button modifBtn;
    @javafx.fxml.FXML
    private VBox contentArea;
    PostedJobService postedJobService = new PostedJobService();
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private Button gotoJobs;
    @javafx.fxml.FXML
    private Button addBtn;
    @javafx.fxml.FXML
    private TextField searchField;

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
                    // Create a button for generating QR code
                    Button generateQRButton = new Button("Generate QR");
                    generateQRButton.setOnAction(event -> {
                        try {
                            generateQRCode(job);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    // Add the button to the cell
                    setGraphic(new HBox(generateQRButton));
                }
            }
        });
    }

    private void generateQRCode(PostedJobs job) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/assignedJobs/QRFront.fxml"));
        Parent root = loader.load();
        QRFrontListe qrController = loader.getController();
        qrController.setObjectToSend(job);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoApply(ActionEvent actionEvent) {
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
    public void gotoAjouter(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/postedJobs/ajoutFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void search(Event event) {
        String searchTerm = searchField.getText().trim().toLowerCase();
        List<PostedJobs> jobs = postedJobService.getAll();
        List<PostedJobs> searchRes = new ArrayList<>();
        for (PostedJobs job:jobs){
            if (job.getTitle().toLowerCase().contains(searchTerm))
                searchRes.add(job);
        }
        postedJobsListView.getItems().clear();
        postedJobsListView.setItems(FXCollections.observableArrayList(searchRes));

        postedJobsListView.setCellFactory(postedJobsListView -> new ListCell<PostedJobs>() {
            @Override
            protected void updateItem(PostedJobs job, boolean empty) {
                super.updateItem(job, empty);
                if (empty || job == null) {
                    setText(null);
                } else {
                    // Display the details for the PostedJobs in your ListView
                    setText(job.getTitle() + " - Budget: " + job.getBudgetEstimate() + " - " + job.getStatus());
                    // Create a button for generating QR code
                    Button generateQRButton = new Button("Generate QR");
                    generateQRButton.setOnAction(event -> {
                        try {
                            generateQRCode(job);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    // Add the button to the cell
                    setGraphic(new HBox(generateQRButton));
                }
            }
        });
    }
}