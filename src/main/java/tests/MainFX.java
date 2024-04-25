package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entities.SessionManager;
import entities.User;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a user

        // Set the user in the session manager
      User user = new User("John", "Doe", "john.doe@example.com", 123456789, "Software Company", "ABC Ltd.");

        // Set the user in the session manager
    SessionManager.setCurrentUser(user);
        // Load the FXML file with the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/HomePage.fxml"));
        Parent root = loader.load();

        // Set the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Client");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
