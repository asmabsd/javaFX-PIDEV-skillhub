package controllers;

import entities.SessionManager;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.AuthenticationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    public Button signUpAsClientButton;
    public Button btnSignin;
    @FXML
    private Button signUpAsFreelancerButton;
    @FXML
    private     TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblErrors;
    @FXML
    private void handleSignUpAsFreelancerButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/FreelancerSignUp.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) signUpAsFreelancerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignUpAsClientButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/ClientSignUp.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) signUpAsClientButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final AuthenticationService authService = new AuthenticationService();

    @FXML
    private void handleSignInButtonClick(ActionEvent event) {
        String email = txtUsername.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            lblErrors.setText("Please enter email and password.");
        } else {
            // Attempt to authenticate the user

            SessionManager sessionManager = new SessionManager();
            if (sessionManager.authenticate(email, password)) {
                // Authentication successful
                User currentUser = SessionManager.getCurrentUser();
                System.out.println(currentUser.getRolesAsJson());
                System.out.println(currentUser.trimRoleString());
                if (currentUser.trimRoleString().equals("[ROLE_ADMIN]")) {
                    System.out.println(currentUser.getRolesAsJson());
                    System.out.println("User is an admin. Redirecting to admin dashboard.");
                    // Redirect to admin dashboard
                    ToAdminDashboard();
                } else {
                    System.out.println("Authentication successful!");
                    ToMainPage();
                    System.out.println(sessionManager.getCurrentUser());
                }
            } else {
                // Authentication failed
                lblErrors.setText("Invalid email or password.");
            }
        }
    }

    private void ToAdminDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/AdminDashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSignin.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ToMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/Main.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSignin.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
