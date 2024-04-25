package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.ServiceUser;
import entities.User;

public class SignUpController {

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private CheckBox clientCheckBox;

    private ServiceUser userService = new ServiceUser();

    @FXML
    private void handleSignUpButtonAction() {
        // Retrieve data from the input fields
        String email = emailTextField.getText();
        String password = passwordField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        // Create a User object using the constructor
        User user = new User(email, password, firstName, lastName);

        // Call the service method to register the user
        userService.initialRegistration(user);
    }
}
