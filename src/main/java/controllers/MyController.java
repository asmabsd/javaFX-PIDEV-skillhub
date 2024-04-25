package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MyController {

    @FXML
    private Label myLabel;

    @FXML
    public void initialize() {
        myLabel.setText("Hello, JavaFX!");
    }
}
