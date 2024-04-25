package controllers;
//mta3 LmMainnormalement
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entities.SessionManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import entities.User;
public class Controller implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[2];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/user/Item.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #a8aac5");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #7a7f9f");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


            public void handleClicks(ActionEvent actionEvent) {
                if (actionEvent.getSource() == btnCustomers) {
                    pnlCustomer.setStyle("-fx-background-color : #1620A1");
                    pnlCustomer.toFront();
                }
                if (actionEvent.getSource() == btnMenus) {
                    pnlMenus.setStyle("-fx-background-color : #53639F");
                    pnlMenus.toFront();
                }
                if (actionEvent.getSource() == btnOverview) {
                    pnlOverview.setStyle("-fx-background-color : #02030A");
                    pnlOverview.toFront();
                }
                if (actionEvent.getSource() == btnOrders) {
                    pnlOrders.setStyle("-fx-background-color : #464F67");
                    pnlOrders.toFront();
                }
            }
    @FXML

    public void ToEditClient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/EditClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSettings.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ToEditFreelancer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../user/EditFreelancer.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSettings.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void redirectToEditPage() {
        User currentUser = SessionManager.getCurrentUser(); // Assuming you have a SessionManager class
        if (currentUser != null) {
            String rolesJson = currentUser.getRolesAsJson();
            if (rolesJson != null) {
                // Check if the roles contain "freelancer"
                if (rolesJson.contains("ROLE_FREELANCER")) {
                    // If the user has the role "freelancer", call the method to edit freelancer
                    ToEditFreelancer();
                    return;
                }
                // Check if the roles contain "client"
                if (rolesJson.contains("ROLE_CLIENT")) {
                    // If the user has the role "client", call the method to edit client
                    ToEditClient();
                    return;
                }
            }
            // If the roles do not contain "freelancer" or "client", display an error message or handle it accordingly
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid user role.");
            alert.showAndWait();
        } else {
            // Handle the case when there is no current user logged in
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No user logged in.");
            alert.showAndWait();
        }
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
