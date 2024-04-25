package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.ServiceUser;
import entities.User;

import javafx.event.ActionEvent;

import java.util.regex.Pattern;

public class SignUpFreeLancerController {

    public TextField JobDescriptiontextfield;
    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField jobTitleTextField;

    @FXML
    private TextField expertiseTextField;

    @FXML
    private TextField languageTextField;

    private ServiceUser userService = new ServiceUser();

    @FXML
    void handleSignUpButtonAction(ActionEvent event) {
        if (validateInputs()){
        // Create a new User object with the provided information
        User freelancer = new User();
        freelancer.setEmail(emailTextField.getText());
        freelancer.setPassword(passwordField.getText());
        freelancer.setFirstName(firstNameTextField.getText());
        freelancer.setLastName(lastNameTextField.getText());
        freelancer.setPhoneNumber(phoneNumberTextField.getText());
        freelancer.setJobTitle(jobTitleTextField.getText());
        freelancer.setProfessionalOverview(JobDescriptiontextfield.getText());
        freelancer.setFreelancerRole();
            System.out.println(freelancer);
            userService.addFreelancer(freelancer);
    }}
        // Call the service method to register the user




//////////////////////////////////////////
private boolean validateInputs() {
    String firstName = firstNameTextField.getText().trim();
    String lastName = lastNameTextField.getText().trim();
    String email = emailTextField.getText().trim();
    String password = passwordField.getText();
    String jobTitle = jobTitleTextField.getText().trim();
    String phoneNumber = phoneNumberTextField.getText().trim();

    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() ||
            jobTitle.isEmpty() || phoneNumber.isEmpty()) {
        showAlert("Please fill in all fields.");
        return false;
    }

    if (!Pattern.matches("[a-zA-Z]+", firstName) || !Pattern.matches("[a-zA-Z]+", lastName)) {
        showAlert("First name and last name should contain only alphabets.");
        return false;
    }

    if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
        showAlert("Invalid email address.");
        return false;
    }

    if (    ServiceUser.isEmailExists(email)) {
        showAlert("Email already exists. Please use a different email.");
        return false;
    }

    if (password.length() < 6) {
        showAlert("Password should be at least 6 characters long.");
        return false;
    }

    if (!Pattern.matches("[a-zA-Z0-9 ]+", jobTitle)) {
        showAlert("Job title should contain only alphabets, numbers, or spaces.");
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

}
