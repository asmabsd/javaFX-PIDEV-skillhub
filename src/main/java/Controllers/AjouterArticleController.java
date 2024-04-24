package Controllers;

import entities.actualite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.Serviceactualite;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AjouterArticleController implements Initializable {
    @FXML
    private Text CatArtInputError;

    @FXML
    private TextArea ContenuArt;

    @FXML
    private Text ContenuArtInputError;

    @FXML
    private HBox ContenuHboxErreur;

    @FXML
    private Button addArticleBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox categorieErrorHbox;

    @FXML
    private ComboBox<String> categoriechoice;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pieceJInputErrorHbox;

    @FXML
    private Text piecejointeInputError;


    @FXML
    private TextField titreInput;

    @FXML
    private Text titreInputError;

    @FXML
    private HBox titreInputErrorHbox;
    @FXML
    private Pane content_area;

    @FXML
    private ScrollPane scrollPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        categorieErrorHbox.setVisible(false);
        ContenuHboxErreur.setVisible(false);

        titreInputErrorHbox.setVisible(false);
//        SpinnerValueFactory<Integer> valueFactory =
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // Valeur initiale, valeur maximale, valeur minimale
//        dureeArt.setValueFactory(valueFactory);
//
//        // Autoriser la saisie directe des valeurs dans le Spinner
//        dureeArt.setEditable(true);
//
//        // Limiter la saisie à des nombres entiers
//        dureeArt.getEditor().setTextFormatter(new TextFormatter<>(c -> {
//            if (c.getControlNewText().matches("\\d*")) {
//                return c;
//            } else {
//                return null;
//            }
//        }));
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        categoriechoice.setItems(categories);


    }

    @FXML
    void ajouter_article(MouseEvent event) throws SQLException, IOException {
        Serviceactualite sa = new Serviceactualite();
        boolean champsVides = false;
        if (ContenuArt.getText().isEmpty()) {
            ContenuHboxErreur.setVisible(true);
            champsVides = true;
        } else {
            ContenuHboxErreur.setVisible(false);
        }

        // Vérification des autres champs similaires...

        if (champsVides) {
            return;
        }

        LocalDateTime datePublication = LocalDateTime.now(); // Utilisation de la date actuelle

        // Autres validations et récupération des valeurs des champs...

        String image = imageInput.getImage().getUrl();
        String selectedCategory = categoriechoice.getSelectionModel().getSelectedItem();
        actualite actualite = new actualite(titreInput.getText(), datePublication, ContenuArt.getText(), selectedCategory, image);

        if (sa.ajouter(actualite)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListArticleAdmin.fxml"));
            Pane listArtAdminPane = loader.load();
            content_area.getChildren().setAll(listArtAdminPane);
        } else {
            // Gérer l'échec de l'ajout d'article (afficher un message d'erreur, par exemple)
        }
    }

    @FXML
    void returnbackarticle(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void importerImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedImageFile = fileChooser.showOpenDialog(choose_photoBtn.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);
        }
    }

    public void ajouterPiece(MouseEvent mouseEvent) {


            }
    }

