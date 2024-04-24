package Controllers;

import entities.actualite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.Serviceactualite;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class articleCardAdminController implements Initializable {

    @FXML
    private HBox action;

    @FXML
    private VBox backArt;

    @FXML
    private Text contenuArtFront;

    @FXML
    private Text datepubArt;

    @FXML
    private HBox deleteArt;

    @FXML
    private HBox editArt;

    @FXML
    private ImageView imgArtFront;

    @FXML
    private HBox open_productDetails;

    @FXML
    private Text titreArtFront;

    @FXML
    private HBox viewdetailArt;
    private actualite actualite;
    private ListArticleAdminController listArticleController;

    public void setListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }

//    public void initializeData(Article article) {
//
//
//        titreArtFront.setText(article.getTitre_art());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = article.getDate_pub_art().format(formatter);
//        datepubArt.setText(formattedDate);
//        contenuArtFront.setText(article.getContenu_art());
//
//    }
//    private Article article; // Article associé à cette carte d'article
//
//    public void setArticle(Article article) {
//        this.article = article;
//        // Remplir les éléments graphiques avec les données de l'article
//        if (article != null) {
//            // Assurez-vous que l'image de l'article n'est pas vide
////            if (article.getImage_art() != null && !article.getImage_art().isEmpty()) {
////                imgArtFront.setImage(new Image(article.getImage_art()));
////            }
//            titreArtFront.setText(article.getTitre_art());
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String formattedDate = article.getDate_pub_art().format(formatter);
//            datepubArt.setText(formattedDate);
//            contenuArtFront.setText(article.getContenu_art());
//        }
//    }
//    @FXML
//    void modifierArt(MouseEvent event) throws IOException {
////        // Charger la vue FXML du formulaire de modification d'article
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
////        Parent editArticleFormParent = loader.load();
////
////        // Créer une nouvelle scène
////        Scene editArticleFormScene = new Scene(editArticleFormParent);
////
////        // Obtenir la scène actuelle
////        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
////
////        // Créer une nouvelle fenêtre modale
////        Stage modalStage = new Stage();
////        modalStage.initModality(Modality.WINDOW_MODAL);
////        modalStage.initOwner(stage);
////        modalStage.setScene(editArticleFormScene);
////        modalStage.showAndWait();
//        // Charger la vue FXML de la boîte de dialogue modale personnalisée
//
//
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
//        Parent editArticlePopupParent = loader.load();
//
//        // Créer une nouvelle scène
//        Scene editArticlePopupScene = new Scene(editArticlePopupParent);
//
//        // Obtenir la scène actuelle
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        // Créer une nouvelle fenêtre modale
//        Stage modalStage = new Stage();
//        modalStage.initModality(Modality.WINDOW_MODAL);
//        modalStage.initOwner(stage);
//        modalStage.initStyle(StageStyle.TRANSPARENT); // Définir le style de la fenêtre comme transparent
//        modalStage.setScene(editArticlePopupScene);
//        modalStage.showAndWait();
//
//
//   }
public void initializeData(actualite actualite) {
    this.actualite = actualite;
    if (actualite!= null) {
        titreArtFront.setText(actualite.getTitre());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = actualite.getDate_publicaton().format(formatter);
        datepubArt.setText(formattedDate);
        contenuArtFront.setText(actualite.getDescription());
    }
}

    @FXML
    void deleteArtBtn(MouseEvent event) {
        if (actualite != null) {
            Serviceactualite sa = new Serviceactualite();
            sa.supprimer(actualite);
            Stage stage = (Stage) deleteArt.getScene().getWindow();
            stage.close();
        } else {
            // Affichez un message d'erreur ou faites une action appropriée si l'article est null
            System.err.println("L'article est null. Impossible de le supprimer.");
        }
    }
@FXML
void modifierArt(MouseEvent event) throws IOException {
////    ListArticleAdminController listArticleController = this;
// FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
//    Parent editArticlePopupParent = loader.load();
//
//    ModifierArticleController modifierArticleController = loader.getController();
//    modifierArticleController.initData(article); // Passer l'article à modifier
////    modifierArticleController.setListArticleController(this);
//
//    Stage stage = new Stage();
//    stage.initModality(Modality.WINDOW_MODAL);
//    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
//    stage.setScene(new Scene(editArticlePopupParent));
//    stage.showAndWait();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
    Parent editArticlePopupParent = loader.load();
    ModifierArticleController modifyArticleController = loader.getController();
    ModifierArticleController modifierArticleController = loader.getController();
    modifierArticleController.initData(actualite); // Passer l'article à modifier
//    modifyArticleController.initListArticleController(this); // Passer une référence au contrôleur ListArticleAdminController

    Stage stage = new Stage();
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
    stage.setScene(new Scene(editArticlePopupParent));
    stage.showAndWait();
}


//    @FXML
//    void deleteArtBtn(MouseEvent event) {
//
//        setArticle(article);
//        try {
//            if (article != null) {
//                ServiceArticle sa = new ServiceArticle();
//                sa.supprimer(article);
//                Stage stage = (Stage) deleteArt.getScene().getWindow();
//                stage.close();
//            } else {
//                // Affichez un message d'erreur ou faites une action appropriée si l'article est null
//                System.err.println("L'article est null. Impossible de le supprimer.");
//            }
//        } catch (SQLException e) {
//            // Gérer l'exception SQLException ici
//            e.printStackTrace();
//            // Vous pouvez également afficher un message d'erreur à l'utilisateur ici
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        deleteArt.setOnMouseClicked(event -> {
//            try {
//                deleteArtBtn(event);
//            } catch (SQLException e) {
//                // Gérer l'exception SQLException ici
//                e.printStackTrace(); // Afficher l'erreur dans la console
//                // Vous pouvez également afficher un message d'erreur à l'utilisateur ici
//            }
//        });

        editArt.setOnMouseClicked(event -> {
            // Rediriger vers l'interface "UpdateArticle.fxml" pour éditer l'article
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ModifierArticleController updateArticleController = loader.getController();
            updateArticleController.initData(actualite); // Passer l'article sélectionné
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });


    }

}


