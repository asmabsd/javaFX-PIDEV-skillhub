//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Connection c = MyDatabase.getInstance().getConnection();

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ajouterOrganisation.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SKILLHUB | FREELANCE APP ");
            primaryStage.show();
        } catch (IOException var6) {
            System.err.println(var6.getMessage());
        }

    }
}
