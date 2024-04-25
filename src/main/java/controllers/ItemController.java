package controllers;

import entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    @FXML
    private HBox itemC;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelEmail;

    public void setUsername(String username) {
        labelUsername.setText(username);
    }

    public void setEmail(String email) {
        labelEmail.setText(email);
    }

    public void initData(User user) {
        setUsername(user.getFirstName() + " " + user.getLastName());
        setEmail(user.getEmail());
    }

}
