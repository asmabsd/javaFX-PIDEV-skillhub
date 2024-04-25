package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import entities.SessionManager;
import entities.User;
import javafx.stage.Stage;
import services.ServiceUser;

public class AdminDashboardController implements Initializable {

    public Button btnSignout;
    @FXML
    private VBox pnItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Get users data from the SessionManager or your data source
        List<User> users = ServiceUser.getAllUsers();

        // Loop through the users and create nodes for each user
        for (User user : users) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/Item.fxml"));
                Node node = loader.load();
                ItemController controller = loader.getController();
                controller.initData(user); // Pass the user data to the controller

                // Add mouse hover effect
                node.setOnMouseEntered(event -> node.setStyle("-fx-background-color : #ffffff"));
                node.setOnMouseExited(event -> node.setStyle("-fx-background-color : #ffffff"));

                pnItems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleClicks(ActionEvent actionEvent) {
    }

    public void redirectToEditPage(ActionEvent actionEvent) {
    }
    public void signout(ActionEvent actionEvent) {
    SessionManager.clearSession();
    ToMainPage();
    }
    private void ToMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/HomePage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSignout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
