package Controllers;

import entities.actualite;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DetailsController {

    @FXML
    private Text des;

    @FXML
    private ImageView roua;

    @FXML
    private Text titre;

    @FXML
    private Text yu;

    public void initData(actualite actualite){
        titre.setText(actualite.getTitre());
        des.setText(actualite.getDescription());
    }

}

