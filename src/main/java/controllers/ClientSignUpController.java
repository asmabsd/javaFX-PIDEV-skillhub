package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUser;
import entities.User;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.regex.Pattern;

public class ClientSignUpController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private TextField companyDescriptionTextField;

    private ServiceUser userService = new ServiceUser();

    @FXML
    void handleSignUpButtonAction(ActionEvent event) {
        if (validateInputs()) {

        User client = new User();
        client.setFirstName(firstNameTextField.getText());
        client.setLastName(lastNameTextField.getText());
        client.setEmail(emailTextField.getText());
        client.setPassword(passwordField.getText());
        client.setPhoneNumber(phoneNumberTextField.getText());
        client.setCompanyName(companyNameTextField.getText());
        client.setCompanyDescription(companyDescriptionTextField.getText());
            client.setClientRole();
                    // Add the client to the database
            System.out.println(client);
        userService.addClient(client);
        loadHomePage();
    }}
    private void loadHomePage() {
        try {
            // Load the FXML file of the home page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/HomePage.fxml"));
            Parent root = loader.load();

            // Set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) emailTextField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean validateInputs() {
        // Validate first name
        String firstName = firstNameTextField.getText().trim();
        if (firstName.isEmpty() || !Pattern.matches("[a-zA-Z]+", firstName)) {
            showAlert("First name must be non-empty and contain only letters.");
            return false;
        }

        // Validate last name
        String lastName = lastNameTextField.getText().trim();
        if (lastName.isEmpty() || !Pattern.matches("[a-zA-Z]+", lastName)) {
            showAlert("Last name must be non-empty and contain only letters.");
            return false;
        }

        // Validate email
        String email = emailTextField.getText().trim();
        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
            showAlert("Invalid email address.");
            return false;
        }

        // Validate password
        String password = passwordField.getText().trim();
        if (password.length() < 6) {
            showAlert("Password must be at least 6 characters long.");
            return false;
        }

        // Validate phone number
        String phoneNumber = phoneNumberTextField.getText().trim();
        if (!Pattern.matches("[0-9]+", phoneNumber)) {
            showAlert("Phone number must contain only digits.");
            return false;
        }
        if (ServiceUser.isEmailExists(email)) {
            showAlert("Email already exists. Please use a different email.");
            return false;
        }
        // Add more validation rules for other fields as needed

        return true;
    }

    private void signUp() {
        // Implement sign-up process here
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
