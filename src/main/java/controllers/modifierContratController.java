 package controllers;

 import entities.Contrat;
 import entities.Organisation;
 import entities.User;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.scene.control.*;
 import services.ServiceContrat;
 import services.ServiceOrganisation;
 import services.ServiceUser;

 import java.io.IOException;
 import java.sql.Date;
 import java.sql.SQLException;

public class modifierContratController {
    public Button buttonmiseajour;


    // @FXML
    //private TextField statutTf;
    
   // @FXML
    //private TextField organisation_idTf;
    //@FXML
    //private TextField user_idTf;
    

    @FXML
    private ComboBox<String> statutComboBox;

    public Button buttonUpdate;
    public Button buttonCancel;
    public Button buttonupdate;
    private Contrat selectedContrat;

    @FXML
    private TextField montantTf;
   // @FXML
    //private TextField statutTf;
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
    private Contrat currentContrat;
    private Contrat c;
    private final ServiceContrat sc = new ServiceContrat();
    private final ServiceOrganisation so=new ServiceOrganisation();
    @FXML
    private TableView<Contrat> tableView;
    private Label welcomeLBL;

    public modifierContratController() {
    }

    private boolean validateFields() {
        return !descriptionTf.getText().isEmpty() &&
                !freelancerTf.getText().isEmpty() &&
                //!statutTf.getText().isEmpty() &&
                !projetTf.getText().isEmpty() &&
                !montantTf.getText().isEmpty() &&
                date_finTf.getValue() != null &&
                date_debutTf.getValue() != null &&
                date_creationTf.getValue()!=null ;
    }

    ServiceUser su=new ServiceUser();

    @FXML
    void updateContrat() {
        if (validateFields()) {
            try {
                // Récupérer les valeurs des champs
                int montant = Integer.parseInt(montantTf.getText());
                String freelancer = freelancerTf.getText();
                String description = descriptionTf.getText();
               statutComboBox.getItems().addAll("Terminé", "En cours", "Annulé");
                String statut=statutComboBox.getValue();
                String projet = projetTf.getText();
                //Organisation organisation_id=so.getOneById(organisation_idTf);

Integer user_id=Integer.parseInt(user_idTf.getText());
Integer organisation_id=Integer.parseInt(organisation_idTf.getText());
User user =su.getOneById(user_id);
                Organisation organisation=so.getOneById(organisation_id);


                Date date_debut = null;
                Date date_fin = null;
                Date date_creation = null;

                // Vérifier si les champs de date ne sont pas nuls avant de les convertir
                if (date_debutTf.getValue() != null) {
                    date_debut = Date.valueOf(date_debutTf.getValue());
                }

                if (date_finTf.getValue() != null) {
                    date_fin = Date.valueOf(date_finTf.getValue());
                }

                if (date_creationTf.getValue() != null) {
                    date_creation = Date.valueOf(date_creationTf.getValue());
                }

                // Vérifier les contraintes de saisie
                if (date_debut != null && date_fin != null && date_creation != null) {
                    if (date_fin.before(date_debut)) {
                        showAlert(Alert.AlertType.WARNING, "Erreur", "La date de fin doit être après la date de début.");
                        return;
                    }

                    if (date_creation.after(date_fin)) {
                        showAlert(Alert.AlertType.WARNING, "Erreur", "La date de création doit être avant la date de fin.");
                        return;
                    }
                }

                if (montant < 20 || montant > 300) {
                    showAlert(Alert.AlertType.WARNING, "Erreur", "Le montant doit être compris entre 20 et 300.");
                    return;
                }

                if (description.length() < 5) {
                    showAlert(Alert.AlertType.WARNING, "Erreur", "La description doit contenir au moins 5 caractères.");
                    return;
                }
                Date dateActuelle = new Date(System.currentTimeMillis());
                if (date_fin.before(dateActuelle)) {
                    // Désactiver la modification du statut
                    statutComboBox.setDisable(true);
                }
                // Mettre à jour l'objet Contrat actuel avec les nouvelles valeurs
                currentContrat.setMontant(montant);
                currentContrat.setFreelancer(freelancer);
                currentContrat.setDescription(description);
                currentContrat.setStatut(statut);
                currentContrat.setProjet(projet);
                currentContrat.setDate_debut(date_debut);
                currentContrat.setDate_fin(date_fin);
                currentContrat.setDate_creation(date_creation);
                  currentContrat.setUser_id(user);
                  currentContrat.setOrganisation_id(organisation);
                // Mettre à jour les champs dans l'interface graphique
                montantTf.setText(String.valueOf(currentContrat.getMontant()));
                freelancerTf.setText(currentContrat.getFreelancer());
                descriptionTf.setText(currentContrat.getDescription());
                statutComboBox.setValue(currentContrat.getStatut());
                statutComboBox.getItems().addAll("Terminé", "En cours", "Annulé");
                projetTf.setText(currentContrat.getProjet());
                date_debutTf.setValue(currentContrat.getDate_debut().toLocalDate());
                date_finTf.setValue(currentContrat.getDate_fin().toLocalDate());
                date_creationTf.setValue(currentContrat.getDate_creation().toLocalDate());
                  organisation_idTf.setText(String.valueOf(currentContrat.getOrganisation_id()));
                user_idTf.setText(String.valueOf(currentContrat.getUser_id()));
                // Appeler la méthode modifier du service Contrat
                sc.modifier(currentContrat);
                showAlert(Alert.AlertType.INFORMATION, "Contrat Mis à Jour", "Le contrat a été mis à jour avec succès.");
            }  catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Champs Requis", "Veuillez remplir tous les champs requis.");
        }
    }






    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void backToContratList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherContrat.fxml"));
            Parent root = loader.load();
            afficherContratController acc = loader.getController();
            acc.setData(projetTf.getText()); // Vous pouvez passer des données si nécessaire
            montantTf.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }





    @FXML
    private void edit(ActionEvent event) {

        Contrat selectedContrat = tableView.getSelectionModel().getSelectedItem();

        initData(selectedContrat);
    }







   /* @FXML
        void updateContrat () {
            if (validateFields()) {

                int montant= Integer.parseInt(modifiemontantTf.getText());

                String freelancer = modifiefreelancerTf.getText();

                String description = modifiedescriptionTf.getText();
                String statut = modifiestatutTf.getText();
                String projet = modifieprojetTf.getText();


                Date date_debut = Date.valueOf(modifiedate_debutTf.getValue());
                Date date_fin= Date.valueOf(modifiedate_finTf.getValue());
Date date_creation =Date.valueOf(modifiedate_creationTf.getValue());
                ServiceUser su = new ServiceUser();
                ServiceOrganisation so = new ServiceOrganisation();
                Organisation organisation_id = so.getOneById(Integer.parseInt(this.modifieorganisation_idTf.getText()));
                User user_id = su.getOneById(Integer.parseInt(this.modifieuser_idTf.getText()));





                try {
                    sc.modifier(currentContrat);
                    showAlert(Alert.AlertType.INFORMATION, "Event Updated", "Event updated successfully.");
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Required Fields", "Please fill in all required fields.");
            }
        }

*/

    @FXML
    public void initData(Contrat c) {
        currentContrat = c;
        if (c != null) {
            // Assurez-vous que montantTf est initialisé avant d'appeler setText()
            if (montantTf != null) {
                // Utilisez setText() une fois que l'objet est correctement initialisé
                montantTf.setText(String.valueOf(c.getMontant()));
            } else {
                System.out.println("montantTf est null, vérifiez l'initialisation.");
            }

            // Vérifiez chaque champ avant de l'utiliser


            if (projetTf != null) {
                projetTf.setText(c.getProjet());
            } else {
                System.out.println("projetTf est null, vérifiez l'initialisation.");
            }

            if (freelancerTf != null) {
                freelancerTf.setText(c.getFreelancer());
            } else {
                System.out.println("freelancerTf est null, vérifiez l'initialisation.");
            }
            if (statutComboBox != null) {
                // Désactiver la modification du statut si la date de fin est antérieure à la date actuelle
                Date dateActuelle = new Date(System.currentTimeMillis());
                if (c.getDate_fin().before(dateActuelle)) {
                    statutComboBox.setDisable(true);
                    statutComboBox.setValue(c.getStatut()); // Utiliser le statut du contrat sélectionné
                } else {
                    statutComboBox.setDisable(false);
                    statutComboBox.getItems().addAll("Terminé", "En cours", "Annulé");
                }
            } else {
                System.out.println("statutComboBox est null, vérifiez l'initialisation.");
            }


            if (organisation_idTf != null) {
                organisation_idTf.setText(String.valueOf(c.getOrganisation_id().getId()));
            } else {
                System.out.println("organisation_idTf est null, vérifiez l'initialisation.");
            }
                        if (user_idTf != null) {
                            user_idTf.setText(String.valueOf(c.getUser_id().getId()));
                        } else {
                            System.out.println("user_idTf est null, vérifiez l'initialisation.");
                        }




//organisationComboBox.setDisable(false);
               // organisationComboBox.setValue(c.getOrganisation_id().getId());
                //statutComboBox.getItems().addAll("Terminé", "En cours", "Annulé");







            /* if (organisation_idTf != null) {
                organisation_idTf.setText(String.valueOf(c.getOrganisation_id()));
            } else {
                System.out.println("organisation_idTf est null, vérifiez l'initialisation.");
            }

            if (user_idTf != null) {
                user_idTf.setText(String.valueOf(c.getUser_id()));
            } else {
                System.out.println("user_idTf est null, vérifiez l'initialisation.");
            }*/

            if (descriptionTf != null) {
                descriptionTf.setText(c.getDescription());
            } else {
                System.out.println("descriptionTf est null, vérifiez l'initialisation.");
            }

            Date date_debut = (Date) c.getDate_debut();
            if (date_debut != null && date_debutTf != null) {
                date_debutTf.setValue(date_debut.toLocalDate());
            } else {
                System.out.println("date_debut ou date_debutTf est null, vérifiez l'initialisation.");
            }

            Date date_fin = (Date) c.getDate_fin();
            if (date_fin != null && date_finTf != null) {
                date_finTf.setValue(date_fin.toLocalDate());
            } else {
                System.out.println("date_fin ou date_finTf est null, vérifiez l'initialisation.");
            }

            Date date_creation = (Date) c.getDate_creation();
            if (date_creation != null && date_creationTf != null) {
                date_creationTf.setValue(date_creation.toLocalDate());
            } else {
                System.out.println("date_creation ou date_creationTf est null, vérifiez l'initialisation.");
            }
        } else {
            System.out.println("Le contrat passé en paramètre est null.");
        }
    }



    public void setData(String text) {
        this.welcomeLBL.setText("Welcome " + text);
    }




}