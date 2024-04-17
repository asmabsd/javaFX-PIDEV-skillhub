package Controllers;



import com.sun.jdi.connect.spi.Connection;
import entities.Reclamation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.MyDatabase;

import static java.sql.DriverManager.getConnection;

public class ReclamationController implements Initializable {

    Connection con=null;
    PreparedStatement st=null;
    ResultSet rs=null;
    @FXML
    private TextField contenuelabel;

    @FXML
    private DatePicker datereclamationlabel;

    @FXML
    private TextField idlabel;

    @FXML
    private TextField objetlabel;
    java.sql.Connection connection;

    @FXML
    private TextField statutlabel;

    @FXML
    private TableColumn<Reclamation, String> contenuecolumn;

    @FXML
    private TableColumn<Reclamation, String> datecolumn;

    @FXML
    private TableColumn<Reclamation, Integer> idcolumn;

    @FXML
    private TableColumn<Reclamation, String> objetcolumn;

    @FXML
    private TableColumn<Reclamation, String> reponsecolumn;

    @FXML
    private TableColumn<Reclamation, String> statutcolumn;

    @FXML
    private TableView<Reclamation> tablecredit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (url != null && url.getPath().contains("listereclamation.fxml")) {
            connection = MyDatabase.getInstance().getConnection();
            showreclamation();
        }

    }
    @FXML
    private Button deletebtn;
    int id=0;
    @FXML
    public Reclamation getData(javafx.scene.input.MouseEvent mouseEvent) {

        Reclamation rec=tablecredit.getSelectionModel().getSelectedItem();
        System.out.println(rec);
        id=rec.getId();
        return rec ;
    }
    public ObservableList<Reclamation> getReclamation() {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();
        String query = "SELECT id, objet, contenu, statut, date_reclamation, reponse FROM reclamation";
        connection = MyDatabase.getInstance().getConnection();
        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                Reclamation rec = new Reclamation(); // Use a different variable name here

                rec.setId(rs.getInt("id"));
                rec.setObjet(rs.getString("objet"));
                rec.setContenu(rs.getString("contenu"));
                rec.setStatut(rs.getString("statut"));
                rec.setDate_reclamtion(rs.getDate("date_reclamation"));
                rec.setReponse(rs.getString("reponse"));

                reclamations.add(rec);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reclamations;
    }

    public void showreclamation(){
        ObservableList<Reclamation> list= getReclamation();
        tablecredit.setItems(list);
        idcolumn.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
        objetcolumn.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("objet"));
        contenuecolumn.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("contenu"));
        statutcolumn.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("statut"));
        datecolumn.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Date date = cellData.getValue().getDate_reclamtion();
            if (date != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                property.setValue(dateFormat.format(date));
            }
            return property;
        });

    }
    private void setObjet(String objet) {
    }
    @FXML
    void deletereclamation(ActionEvent event) {


    }

    public void ajouterreclamation(javafx.event.ActionEvent actionEvent) {
        String insert = "insert into reclamation (id, objet, contenu, statut, date_reclamation) values (?, ?, ?, ?, ?)";
        connection = MyDatabase.getInstance().getConnection();
        try {
            st = connection.prepareStatement(insert);
            st.setInt(1, Integer.parseInt(idlabel.getText())); // id_client
            st.setString(2, objetlabel.getText()); // objet
            st.setString(3, contenuelabel.getText()); // contenu
            st.setString(4, statutlabel.getText()); // statut
            st.setDate(5, java.sql.Date.valueOf(datereclamationlabel.getValue())); // date_reclamation

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletereclamation(javafx.event.ActionEvent actionEvent) {
        String Delete="delete from reclamation where id = ?";

        connection = MyDatabase.getInstance().getConnection();
        try {
            st=connection.prepareStatement(Delete);
            st.setInt(1,id);
            st.executeUpdate();
            showreclamation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
