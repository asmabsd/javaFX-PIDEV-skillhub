package Controllers;

import entities.actualite;
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
import services.Serviceactualite;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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


    public void initData(actualite actualite) {
        titreArtAdmin.setText(actualite.getTitre());
        Serviceactualite serviceactualite=new Serviceactualite();
        editArt.setId(String.valueOf(actualite.getId()));
        deleteArt.setId(String.valueOf(actualite.getId()));
        editArt.setOnMouseClicked(mouseEvent -> {
            Stage primaryStage = new Stage();
            try {
                ModifierArticleController modifierArticleController = new ModifierArticleController();
                modifierArticleController.initData(actualite);
                List<actualite> actualite1 = serviceactualite.getAllArticles();
               // actualite actualite2 =actualite1.stream().;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml")) ;

                Parent  parent = loader.load();
                Scene scene = new Scene(parent);
                primaryStage.setTitle("SKILLHUB");
                primaryStage.setScene(scene);
                primaryStage.show();

                //ModifierArticleController.initData(Integer.parseInt(Modifier.getId()));
               // AfficheOffreController afficheOffreController = new AfficheOffreController();

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteArt.setOnMouseClicked(mouseEvent -> {
            serviceactualite.supprimer(actualite);


        });

    }
}

