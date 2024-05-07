

package controllers;

import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import entities.Contrat;
import entities.Organisation;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import services.ServiceContrat;
import services.ServiceOrganisation;
import services.ServiceUser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.Label;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;

public class ajouterContratController {

    public Button buttonAffiche;
    public Button buttonInserer;
    public Button buttonpdf;
    @FXML
    private TextField montantTf;
    //@FXML
    //private TextField statutTf;
    @FXML
    private TextField projetTf;
    @FXML
    private TextField freelancerTf;
    //@FXML
    //private TextField organisation_idTf;
    //@FXML
   // private TextField user_idTf;
    @FXML
    private TextField descriptionTf;
    @FXML
    private DatePicker date_debutTf;
    @FXML
    private DatePicker date_finTf;
    @FXML
    private DatePicker date_creationTf;
    private Label wel6comeLBL;
    private Contrat contratAjoute;

    @FXML
    private ComboBox<String> statutComboBox;
    @FXML
    private ComboBox<Integer> userComboBox;
    @FXML
    private ComboBox<Integer> organisationComboBox;
    private Label welcomeLBL;


    public ajouterContratController() {
    }

    /*@FXML
    void InsererContrat(ActionEvent event) {
        try {
            if (!this.validateFields()) {



                return;
            }

            Date date_debut = Date.valueOf((LocalDate) this.date_debutTf.getValue());
            Date date_fin = Date.valueOf((LocalDate) this.date_finTf.getValue());
            int montant = Integer.parseInt(this.montantTf.getText());
            statutComboBox.getItems().addAll("En cours", "Terminé", "Annulé");
            String statut=statutComboBox.getValue();
            //String statut = this.statutTf.getText();
            String projet = this.projetTf.getText();
            String freelancer = this.freelancerTf.getText();


            ServiceUser su = new ServiceUser();
            ServiceOrganisation so = new ServiceOrganisation();

            organisationComboBox.getItems().addAll(so.getAllIds());
            int selected_id_o=organisationComboBox.getValue();
            Organisation organisation_id=so.getOneById(selected_id_o);
            userComboBox.getItems().addAll(su.getAllIds());
            User user_id=su.getOneById(userComboBox.getValue());

           // Organisation organisation_id = so.getOneById(Integer.parseInt(this.organisation_idTf.getText()));
            //User user_id = su.getOneById(Integer.parseInt(this.user_idTf.getText()));
            Date date_creation = Date.valueOf((LocalDate) this.date_creationTf.getValue());
            String description = this.descriptionTf.getText();
            Contrat contrat = new Contrat(date_debut, date_fin, montant, statut, projet, freelancer, organisation_id, user_id, date_creation, description);
            ServiceContrat serviceContrat = new ServiceContrat();
            serviceContrat.ajouter(contrat);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Contrat inséré avec succès");
            alert.show();
            //generatePDF(contrat);

            alert.setTitle("Succès");
            alert.setContentText("Contrat inséré avec succès. Le PDF a été généré.");
            alert.show();
            this.contratAjoute = contrat;

        } catch (SQLException var17) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(var17.getMessage());
            alert.show();
        }
    }
*/

    @FXML
    void InsererContrat(ActionEvent event) {
        try {
            if (!this.validateFields()) {
                return;
            }

            // Récupération des valeurs des champs de saisie
            Date date_debut = Date.valueOf( this.date_debutTf.getValue());
            Date date_fin = Date.valueOf( this.date_finTf.getValue());
            int montant = Integer.parseInt(this.montantTf.getText());
            String statut = statutComboBox.getValue();
            String projet = this.projetTf.getText();
            String freelancer = this.freelancerTf.getText();

            // Récupération des valeurs sélectionnées dans les ComboBox
            ServiceUser su = new ServiceUser();
            ServiceOrganisation so = new ServiceOrganisation();
            int selected_id_o = organisationComboBox.getValue();
            Organisation organisation_id = so.getOneById(selected_id_o);
            User user_id = su.getOneById(userComboBox.getValue());

            Date date_creation = Date.valueOf( this.date_creationTf.getValue());
            String description = this.descriptionTf.getText();
            Contrat contrat = new Contrat(date_debut,date_fin , montant, statut, projet, freelancer, organisation_id, user_id, date_creation, description);

            // Ajout du contrat à la base de données
            ServiceContrat serviceContrat = new ServiceContrat();
            serviceContrat.ajouter(contrat);
            this.contratAjoute = contrat;
            // Envoi de l'e-mail de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Voulez-vous envoyer un e-mail de confirmation ?");
            ButtonType sendEmailButton = new ButtonType("Envoyer un e-mail");
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationAlert.getButtonTypes().setAll(sendEmailButton, cancelButton);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == sendEmailButton) {
                // Afficher la boîte de dialogue pour saisir l'adresse e-mail
                TextInputDialog emailDialog = new TextInputDialog();
                emailDialog.setTitle("Confirmation de l'envoi d'e-mail");
                emailDialog.setHeaderText("Entrez votre adresse e-mail pour recevoir une confirmation :");
                emailDialog.setContentText("E-mail :");

                Optional<String> emailResult = emailDialog.showAndWait();
                emailResult.ifPresent(email -> {
                    try {
                        sendEmail(email);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        // Gérer les erreurs d'envoi d'e-mail
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Erreur");
                        errorAlert.setHeaderText("Erreur lors de l'envoi de l'e-mail de confirmation");
                        errorAlert.setContentText("Veuillez réessayer plus tard.");
                        errorAlert.showAndWait();
                    }
                });
            }

            // Afficher une confirmation que le contrat a été inséré avec succès
            Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText("Contrat inséré avec succès");
            successAlert.showAndWait();

            // Remarque : Vous pouvez également générer un PDF ici si nécessaire

            // Réinitialiser les champs de saisie après l'insertion du contrat

        } catch (SQLException var17) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setContentText(var17.getMessage());
            errorAlert.showAndWait();
        }
    }

    private boolean validateFields() {
        LocalDate debut = (LocalDate) this.date_debutTf.getValue();
        LocalDate fin = (LocalDate) this.date_finTf.getValue();
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
                if (this.isEmpty(this.montantTf, this.projetTf, this.freelancerTf, this.descriptionTf)) {
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

        for (int var4 = 0; var4 < var3; ++var4) {
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
            apc.setData(descriptionTf.getText());
            montantTf.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    /*private void genererPDF(Contrat contrat) throws FileNotFoundException, DocumentException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, new FileOutputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/" + contrat.getNumero_commande() + ".pdf"));
        doc.open();
        // Ajoutez le contenu du PDF ici en utilisant iText
        doc.close();
    }*/

    public void setData(String param) {
        this.welcomeLBL.setText("Welcome " + param);
    }





    public void addParagraph(PdfContentByte canvas, String text, float x, float y, Font font) throws DocumentException {
        Phrase phrase = new Phrase(text, font);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, x, y, 0);
    }
ServiceContrat so=new ServiceContrat();
    @FXML
    public void telechargerPDF(ActionEvent actionEvent) {
        if (contratAjoute != null) {
            try {
                // Chemin vers le modèle PDF
                String templatePath = "src/template.pdf";
                PdfReader reader = new PdfReader(templatePath);

                // Chemin vers le PDF final
                String outputPath = "contrat.pdf";
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputPath));
                Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);

                // Récupérer le contenu PDF
                PdfContentByte canvas = stamper.getOverContent(1); // Page 1

                // Position des attributs sur la page
                float x = 100; // Coordonnée x
                float y = 600; // Coordonnée y

                // Ajouter les attributs du contrat au PDF
                addParagraph(canvas, "Date de début : " + contratAjoute.getDate_debut(), x, y, titleFont);
                addParagraph(canvas, "Date de fin : " + contratAjoute.getDate_fin(), x, y - 20, titleFont);
                addParagraph(canvas, "Montant : " + contratAjoute.getMontant(), x, y - 40, titleFont);
                addParagraph(canvas, "Statut : " + contratAjoute.getStatut(), x, y - 60, titleFont);
                addParagraph(canvas, "Projet : " + contratAjoute.getProjet(), x, y - 80, titleFont);
                addParagraph(canvas, "Freelancer : " + contratAjoute.getFreelancer(), x, y - 100, titleFont);
                addParagraph(canvas, "Organisation ID : " + contratAjoute.getOrganisation_id(), x, y - 120, titleFont);
                addParagraph(canvas, "User ID : " + contratAjoute.getUser_id(), x, y - 140, titleFont);
                addParagraph(canvas, "Date de création : " + contratAjoute.getDate_creation(), x, y - 160, titleFont);
                addParagraph(canvas, "Description : " + contratAjoute.getDescription(), x, y - 180, titleFont);

                // Fermer le PDF
                stamper.close();
                reader.close();
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        } else {
            // Si aucun contrat n'a été ajouté, affichez un message d'erreur
            System.out.println("Aucun contrat n'a été ajouté.");
        }

    }
   @FXML
   private void sendEmail(String email)throws MessagingException {
        if (contratAjoute == null) {
            System.out.println("Aucun contrat n'a été ajouté.");
            return;
        }

        System.out.println("Envoi d'un e-mail à " + email);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("atouanirana@gmail.com", "eafczhobtosqclvw");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bsdasma13@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Contrat marqué comme complet");
            message.setText("Votre contrat a été marqué comme complet : " + contratAjoute.getDescription());

            Transport.send(message);

            // Afficher un message de confirmation
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText("E-mail envoyé avec succès");
            successAlert.setContentText("L'e-mail de confirmation a été envoyé à " + email);
            successAlert.showAndWait();

            System.out.println("E-mail envoyé avec succès à " + email);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Gérer les erreurs d'envoi d'e-mail et afficher un message à l'utilisateur
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText("Erreur lors de l'envoi de l'e-mail de confirmation");
            errorAlert.setContentText("Veuillez réessayer plus tard.");
            errorAlert.showAndWait();
        }
    }





}



