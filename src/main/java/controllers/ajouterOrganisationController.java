package controllers;


import entities.Organisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceOrganisation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;


public class ajouterOrganisationController {

    public Button buttonInsert;
    public Button buttonAffiche;
    @FXML
    private TextField nomTf;
    @FXML
    private TextField adresseTf;
    @FXML
    private TextField telephoneTf;
    @FXML
    private TextField emailTf;
    @FXML
    private TextField domaine_activiteTf;
    @FXML
    private TextField contactTf;


    // Expression régulière pour vérifier si le nom ne contient que des lettres
    private static final Pattern NOM_PATTERN = Pattern.compile("[a-zA-Z]+");

    // Expression régulière pour vérifier si l'email est valide
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    // Expression régulière pour vérifier si le téléphone a exactement 8 chiffres
    private static final Pattern TELEPHONE_PATTERN = Pattern.compile("\\d{8}");

    @FXML
    public void InsererOrganisation() {
        try {
            String nom = nomTf.getText().trim();
            String adresse = adresseTf.getText().trim();
            String telephone = telephoneTf.getText().trim();
            String email = emailTf.getText().trim();
            String contact = contactTf.getText().trim();
            String domaine_activite = domaine_activiteTf.getText().trim();

            // Vérifier si tous les champs sont remplis
            if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || email.isEmpty() || contact.isEmpty() || domaine_activite.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Champs Requis", "Veuillez remplir tous les champs.");
                return;
            }

            // Vérifier si le nom ne contient que des lettres
            if (!NOM_PATTERN.matcher(nom).matches()) {
                showAlert(Alert.AlertType.ERROR, "Nom Incorrect", "Le nom ne doit contenir que des lettres.");
                return;
            }

            // Vérifier si l'email est valide
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                showAlert(Alert.AlertType.ERROR, "Email Incorrect", "Veuillez saisir une adresse email valide.");
                return;
            }

            // Vérifier si le téléphone a exactement 8 chiffres
            if (!TELEPHONE_PATTERN.matcher(telephone).matches()) {
                showAlert(Alert.AlertType.ERROR, "Téléphone Incorrect", "Le numéro de téléphone doit avoir exactement 8 chiffres.");
                return;
            }

            int telephoneInt = Integer.parseInt(telephone);
            ServiceOrganisation so = new ServiceOrganisation();
            Organisation o = new Organisation(nom, adresse, telephoneInt, email, contact, domaine_activite);

            so.ajouter(o);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Organisation insérée avec succès");
            alert.show();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }



    // Méthode utilitaire pour afficher une boîte de dialogue d'alerte
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



   /* @FXML
    void InsererOrganisation(ActionEvent event) {
        try {

            String nom= nomTf.getText();
            String adresse = adresseTf.getText();


            int telephone= Integer.parseInt(telephoneTf.getText());
            String email= emailTf.getText();
            String contact =contactTf.getText();
            String domaine_activite = domaine_activiteTf.getText();


           ServiceOrganisation so=new ServiceOrganisation();
           Organisation o=new Organisation(nom,adresse,telephone,email,contact,domaine_activite);

            so.ajouter(o);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Organisation inséré avec succès");
            alert.show();
        } catch (SQLException var17) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(var17.getMessage());
            alert.show();
        }

    }*/
    @FXML
    void naviguez(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/afficherOrganisation.fxml"));
            Parent root = loader.load();
            afficherOrganisationController apc = loader.getController();
            apc.setData(this.nomTf.getText());
            this.nomTf.getScene().setRoot(root);
        } catch (IOException var5) {
            System.err.println(var5.getMessage());
        }

    }
}
