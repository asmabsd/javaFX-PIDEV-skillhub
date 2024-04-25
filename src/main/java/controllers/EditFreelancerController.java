package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import entities.SessionManager;
import entities.User;
import javafx.stage.Stage;
import services.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EditFreelancerController implements Initializable {

    public TextField Job_OverviewTextfield;
    public TextField JobtitleTextfield;
    public Button btnSignout;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField companyDescriptionTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Retrieve user from session manager
        User user = SessionManager.getCurrentUser();
        System.out.println(user);
        if (user != null) {
            firstNameTextField.setText(user.getFirstName());
            lastNameTextField.setText(user.getLastName());
            emailTextField.setText(user.getEmail());
            phoneNumberTextField.setText(user.getPhoneNumber());
            Job_OverviewTextfield.setText(user.getProfessionalOverview());
            JobtitleTextfield.setText(user.getJobTitle());
        }
    }

    @FXML
    private void handleUpdateButtonAction(ActionEvent event) {
        if (validateInput()) {
            User user = SessionManager.getCurrentUser();
            System.out.println(user);

            if (user != null) {
                String newFirstName = firstNameTextField.getText();
                String newLastName = lastNameTextField.getText();
                String newEmail = emailTextField.getText();
                String newPhoneNumber = phoneNumberTextField.getText();
                String jobOverview = Job_OverviewTextfield.getText();
                String jobTitle = JobtitleTextfield.getText();

                user.setFirstName(newFirstName);
                user.setLastName(newLastName);
                user.setEmail(newEmail);
                user.setPhoneNumber(newPhoneNumber);
                user.setCompanyDescription(jobOverview);
                user.setJobTitle(jobTitle);
                user.setFreelancerRole();
                user.setProfessionalOverview(jobOverview);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Successful");
                alert.setHeaderText(null);
                alert.setContentText("User information updated successfully!");
                alert.showAndWait();

                System.out.println(user);
                ServiceUser.FreelancerUpdate(user);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update user information. User not found.");
                alert.showAndWait();
            }
        }
    }

    private boolean validateInput() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        if (firstName.isEmpty()) {
            showAlert("First name cannot be empty.");
            return false;
        }

        if (lastName.isEmpty()) {
            showAlert("Last name cannot be empty.");
            return false;
        }

        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
            showAlert("Invalid email address.");
            return false;
        }

        if (!Pattern.matches("[0-9]+", phoneNumber)) {
            showAlert("Phone number should contain only numbers.");
            return false;
        }

        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleClicks(ActionEvent actionEvent) {
        // Handle button clicks if needed
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
