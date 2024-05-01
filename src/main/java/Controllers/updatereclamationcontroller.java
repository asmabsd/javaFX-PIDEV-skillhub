package Controllers;

//import com.sun.javafx.tk.quantum.PaintRenderJob;
//import com.sun.javafx.tk.quantum.SceneState;


import entities.Reclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.MyDatabase;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class updatereclamationcontroller {

    @FXML
    private TextField contenuelabel;

    @FXML
    private DatePicker datereclamationlabel;

    @FXML
    private TextField idlabel;

    @FXML
    private TextField objetlabel;

    @FXML
    private TextField statutlabel;


    @FXML
    void ajouterreclamation(ActionEvent event) {

    }
    public void initData(Reclamation rec) {
        idlabel.setText(String.valueOf(rec.getId()));
        objetlabel.setText(rec.getObjet());
        contenuelabel.setText(rec.getContenu());
        statutlabel.setText(rec.getStatut());
        Date dateDebut = rec.getDate_reclamtion();

        datereclamationlabel.setValue(LocalDate.parse(dateDebut.toString()));

    }

    @FXML
    java.sql.Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
     void updatereclamation(ActionEvent event) {
        String update = "update reclamation set id = ?, objet = ?, contenu = ?, statut = ?, date_reclamation = ? where id = ?" ;
        con = MyDatabase.getInstance().getConnection();

        try {
            st=con.prepareStatement(update);
            st.setInt(1, Integer.parseInt(idlabel.getText())); // Static value for user_id
            st.setString(2, objetlabel.getText()); // objet
            st.setString(3, contenuelabel.getText()); // contenu
            st.setString(4, statutlabel.getText()); // statut
            st.setDate(5, java.sql.Date.valueOf(datereclamationlabel.getValue())); // date_reclamation

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updaterdv(ActionEvent actionEvent) {
        String update = "update reclamation set id = ?, objet = ?, contenu = ?, statut = ?, date_reclamation = ? where id = ?" ;
        con = MyDatabase.getInstance().getConnection();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/FXML/listereclamation.fxml"));
        try {
            Stage stage =new Stage();
            Parent root =
                    FXMLLoader.load(getClass().getResource("/FXML/listereclamation.fxml"));


            contenuelabel.getScene().setRoot(root);
            st=con.prepareStatement(update);
            st.setInt(1, Integer.parseInt(idlabel.getText())); // Static value for user_id
            st.setString(2, objetlabel.getText()); // objet
            st.setString(3, contenuelabel.getText()); // contenu
            st.setString(4, statutlabel.getText()); // statut
            st.setDate(5, java.sql.Date.valueOf(datereclamationlabel.getValue())); // date_reclamation
            st.setInt(6, Integer.parseInt(idlabel.getText())); // Static value for user_id


            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




        }




