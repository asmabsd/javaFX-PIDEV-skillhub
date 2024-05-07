package edu.esprit.freelancejobs.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import edu.esprit.freelancejobs.entities.PostedJobs;
import edu.esprit.freelancejobs.services.QRGenerator;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class QRFrontListe  {

    @javafx.fxml.FXML
    private VBox mainContainer;
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private Button gotoJobs;
    @javafx.fxml.FXML
    private VBox content;
    PostedJobs job;
    private static final int QR_CODE_SIZE = 300;
    public void setObjectToSend(PostedJobs obj) {
        this.job = obj;
        QRGenerator qrGenerator;
        String CONTENT_TO_ENCODE = "http://localhost:8000/postedJobs/apply/"+job.getId();
        System.out.println(CONTENT_TO_ENCODE);
        try {
            qrGenerator = new QRGenerator(
                    CONTENT_TO_ENCODE,
                    BarcodeFormat.QR_CODE,
                    QR_CODE_SIZE, QR_CODE_SIZE,
                    BufferedImage.TYPE_INT_ARGB
            );
        } catch (WriterException e) {
            e.printStackTrace();
            return;
        }
        BufferedImage qrImage = qrGenerator.qrImage();
        try {
            qrGenerator.save("qrcode"+job.getTitle()+".png", "PNG");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(qrImage, null);
        ImageView imageView = new ImageView(image);

        content.getChildren().add(imageView);
        content.setAlignment(Pos.CENTER);
    }
    @javafx.fxml.FXML
    public void gotoAssignments(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/assignedJobs/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoJobs(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelancejobs/gui/postedJobs/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
