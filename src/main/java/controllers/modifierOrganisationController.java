package controllers;

import entities.Organisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import services.ServiceOrganisation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class modifierOrganisationController {

    public TableView tableView;
    public Button buttonCancel;
    public Button buttonupdate;
    @FXML
    private TextField nomTf;
    @FXML
    private TextField adresseTf;
    @FXML
    private TextField telephoneTf;
    @FXML
    private TextField emailTf;
    @FXML
    private TextField contactTf;
    @FXML
    private TextField domaine_activiteTf;
    @FXML
    private Label welcomeLBL; // Assurez-vous d'importer javafx.scene.control.Label

    private Organisation currentOrganisation;
    private final ServiceOrganisation serviceOrganisation = new ServiceOrganisation();
    private int orgId;

    @FXML
    public void initData(Organisation o) {
        currentOrganisation = o;
        if (o != null) {
            nomTf.setText(o.getNom());
            adresseTf.setText(o.getAdresse());
            telephoneTf.setText(String.valueOf(o.getTelephone()));
            emailTf.setText(o.getEmail());
            contactTf.setText(o.getContact());
            domaine_activiteTf.setText(o.getDomaine_activite());
            orgId = o.getId();
        } else {
            System.out.println("L'organisation passée en paramètre est null.");
        }
    }

    @FXML
    public void setData(String text) {
        this.welcomeLBL.setText("Welcome to Skillhub - Your Freelance App, " + text);
    }








    private static final Pattern NOM_PATTERN = Pattern.compile("[a-zA-Z]+");

    // Expression régulière pour vérifier le format de l'email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");



    // Méthode pour afficher une alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Méthode pour vérifier si le nom est valide
    private boolean isValidNom(String nom) {
        return NOM_PATTERN.matcher(nom).matches();
    }

    // Méthode pour vérifier si l'email est valide
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Méthode pour vérifier si le téléphone est valide
    private boolean isValidTelephone(String telephone) {
        return telephone.matches("\\d{8}");
    }
    @FXML
    void updateOrganisation(ActionEvent event) {
        if (currentOrganisation != null) {
            // Vérification du nom
            if (!isValidNom(nomTf.getText())) {
                showAlert("Nom invalide", "Le nom doit contenir uniquement des lettres.");
                return;
            }

            // Vérification de l'email
            if (!isValidEmail(emailTf.getText())) {
                showAlert("Email invalide", "Veuillez entrer une adresse email valide.");
                return;
            }

            // Vérification du téléphone
            if (!isValidTelephone(telephoneTf.getText())) {
                showAlert("Téléphone invalide", "Le numéro de téléphone doit contenir 8 chiffres.");
                return;
            }

            try {
                currentOrganisation = new Organisation();
                // Mettre à jour les champs de l'organisation avec les valeurs des champs de texte
                currentOrganisation.setNom(nomTf.getText());
                currentOrganisation.setAdresse(adresseTf.getText());
                currentOrganisation.setTelephone(Integer.parseInt(telephoneTf.getText()));
                currentOrganisation.setEmail(emailTf.getText());
                currentOrganisation.setContact(contactTf.getText());
                currentOrganisation.setDomaine_activite(domaine_activiteTf.getText());
                currentOrganisation.setId(orgId);

                // Appelez la méthode de service pour mettre à jour l'organisation
                serviceOrganisation.modifier(currentOrganisation);

                // Affichez un message de succès ou utilisez un dialogue
                System.out.println("Organisation mise à jour avec succès.");

                // Vous pouvez également naviguer vers une autre vue après la mise à jour
            } catch (SQLException e) {
                // Gérez les erreurs de mise à jour de l'organisation
                System.err.println("Erreur lors de la mise à jour de l'organisation : " + e.getMessage());
            }
        } else {
            System.err.println("Aucune organisation sélectionnée pour la mise à jour.");
        }
    }

    @FXML
    void redirectToUpdatedOrganisation(ActionEvent event) {
        try {
            // Charger le fichier FXML de l'affichage des organisations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatedOrganisation.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de l'affichage des organisations
            afficherOrganisationController afficherOrganisationController = loader.getController();

            // Mettre à jour l'affichage en rechargeant les données des organisations
            afficherOrganisationController.loadData();

            // Changer la racine de la scène pour afficher l'interface d'affichage des organisations
            nomTf.getScene().setRoot(root);
        } catch (IOException e) {
            // Gérer les exceptions liées au chargement de l'interface d'affichage des organisations
            System.err.println(e.getMessage());
        }
    }


}

