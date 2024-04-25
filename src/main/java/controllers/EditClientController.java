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

public class EditClientController implements Initializable {

    public TextField ze;
    public Button btnSignout;
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField companyDescriptionTextField;

    @FXML
    private TextField companyNameTextField;

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
            companyDescriptionTextField.setText(user.getCompanyDescription());
            companyNameTextField.setText(user.getCompanyName());
        }
    }
    @FXML
    private void handleUpdateButtonAction(ActionEvent event) {
        // Retrieve user from session manager or any other source
        if (validateInput()) {

            User user = SessionManager.getCurrentUser();
        System.out.println(user);

        if (user != null) {
            // Get the new values from the text fields
            String newFirstName = firstNameTextField.getText();
            String newLastName = lastNameTextField.getText();
            String newEmail = emailTextField.getText();
            String newPhoneNumber = phoneNumberTextField.getText();
            String newCompanyDescription = companyDescriptionTextField.getText();
            String newCompanyName = companyNameTextField.getText();

            // Update the user object with new values
            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            user.setEmail(newEmail);
            user.setPhoneNumber(newPhoneNumber);
            user.setCompanyDescription(newCompanyDescription);
            user.setCompanyName(newCompanyName);
user.setClientRole();
            // Optionally, you can save the updated user object to the session manager or any other source
            // saveUser(user);

            // Display a message indicating successful update
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Successful");
            alert.setHeaderText(null);
            alert.setContentText("User information updated successfully!");
            alert.showAndWait();
            System.out.println(user);
            ServiceUser.modify(user);

        } else {
            // Display an error message if user is null
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update user information. User not found.");
            alert.showAndWait();
        }
    }}
    private boolean validateInput() {
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        // Validate email
        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
            showAlert("Invalid email address.");
            return false;
        }

        // Validate phone number
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
