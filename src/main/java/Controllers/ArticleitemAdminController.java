package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ArticleitemAdminController {

    @FXML
    private HBox action;

    @FXML
    private Label categoryArticle;

    @FXML
    private HBox deleteArt;

    @FXML
    private HBox editArt;

    @FXML
    private ImageView imgArtAdmin;

    @FXML
    private Text nbCommentaireArt;

    @FXML
    private HBox offerProduit;

    @FXML
    private HBox priceAfterOfferHbox;

    @FXML
    private HBox priceHbox;

    @FXML
    private Text stockProduit;

    @FXML
    private Text titreArtAdmin;

    @FXML
    private HBox viewdetailArt;


}

