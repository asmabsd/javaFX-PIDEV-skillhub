

package controllers;

import entities.Contrat;
import entities.Organisation;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServiceContrat;
import services.ServiceOrganisation;
import services.ServiceUser;

import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ajouterContratController {

    public Button buttonAffiche;
    public Button buttonInserer;
    @FXML
    private TextField montantTf;
    @FXML
    private TextField statutTf;
    @FXML
    private TextField projetTf;
    @FXML
    private TextField freelancerTf;
    @FXML
    private TextField organisation_idTf;
    @FXML
    private TextField user_idTf;
    @FXML
    private TextField descriptionTf;
    @FXML
    private DatePicker date_debutTf;
    @FXML
    private DatePicker date_finTf;
    @FXML
    private DatePicker date_creationTf;
    private Label welcomeLBL;

    public ajouterContratController() {
    }

    @FXML
    void InsererContrat(ActionEvent event) {
        try {
            if (!this.validateFields()) {
                return;
            }

            Date date_debut = Date.valueOf((LocalDate)this.date_debutTf.getValue());
            Date date_fin = Date.valueOf((LocalDate)this.date_finTf.getValue());
            int montant = Integer.parseInt(this.montantTf.getText());
            String statut = this.statutTf.getText();
            String projet = this.projetTf.getText();
            String freelancer = this.freelancerTf.getText();
            ServiceUser su = new ServiceUser();
            ServiceOrganisation so = new ServiceOrganisation();
            Organisation organisation_id = so.getOneById(Integer.parseInt(this.organisation_idTf.getText()));
            User user_id = su.getOneById(Integer.parseInt(this.user_idTf.getText()));
            Date date_creation = Date.valueOf((LocalDate)this.date_creationTf.getValue());
            String description = this.descriptionTf.getText();
            Contrat contrat = new Contrat(date_debut, date_fin, montant, statut, projet, freelancer, organisation_id, user_id, date_creation, description);
            ServiceContrat serviceContrat = new ServiceContrat();
            serviceContrat.ajouter(contrat);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Contrat inséré avec succès");
            alert.show();
        } catch (SQLException var17) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(var17.getMessage());
            alert.show();
        }

    }

    private boolean validateFields() {
        LocalDate debut = (LocalDate)this.date_debutTf.getValue();
        LocalDate fin = (LocalDate)this.date_finTf.getValue();
        if (debut != null && fin != null && !debut.isAfter(fin)) {
            try {
                int montant = Integer.parseInt(this.montantTf.getText());
                if (montant < 20 || montant > 300) {
                    this.showAlert("Le montant doit être compris entre 20 et 300");
                    return false;
                }
            } catch (NumberFormatException var5) {
                this.showAlert("Veuillez saisir un montant valide");
                return false;
            }

            String description = this.descriptionTf.getText();
            if (description.length() >= 5 && description.length() <= 50) {
                if (this.isEmpty(this.montantTf, this.statutTf, this.projetTf, this.freelancerTf, this.organisation_idTf, this.user_idTf, this.descriptionTf)) {
                    this.showAlert("Tous les champs sont obligatoires");
                    return false;
                } else {
                    return true;
                }
            } else {
                this.showAlert("La description doit contenir entre 5 et 50 caractères");
                return false;
            }
        } else {
            this.showAlert("La date de début doit être antérieure à la date de fin");
            return false;
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isEmpty(TextField... fields) {
        TextField[] var2 = fields;
        int var3 = fields.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            TextField field = var2[var4];
            if (field.getText().isEmpty()) {
                return true;
            }
        }

        return false;
    }

    private boolean isEmpty(DatePicker datePicker) {
        return datePicker.getValue() == null;
    }

    @FXML
    void naviguez(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherContrat.fxml"));
            Parent root = loader.load();

            afficherContratController apc = loader.getController();
            apc.setData(statutTf.getText());
            montantTf.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setData(String param) {
        this.welcomeLBL.setText("Welcome " + param);
    }
}
