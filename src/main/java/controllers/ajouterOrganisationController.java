package controllers;

import entities.Organisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceOrganisation;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;


public class ajouterOrganisationController {

    public Button buttonInsert;
    public Button buttonAffiche;
    public Button buttonConfirmerparsms;
    public Button buttonsendemail;
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
Organisation organisationajoutee;

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
            this.organisationajoutee =o;
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

    // Le numéro Twilio à partir duquel vous envoyez le SMS
    public static final String TWILIO_NUMBER = "+13345186510";




   /* @FXML
    public void sendEmail(ActionEvent event) {
        // Paramètres de connexion au serveur de messagerie
        String host = "smtp.gmail.com"; // Serveur SMTP Gmail
        int port = 587; // Port SMTP pour Gmail
        String username = "votre_adresse@gmail.com"; // Adresse e-mail Gmail
        String password = "votre_mot_de_passe"; // Mot de passe Gmail

        // Récupération des données de l'e-mail
        String toEmail = "destinataire@example.com"; // Adresse e-mail du destinataire
        String subject = "Sujet de l'e-mail";
        String body = "Contenu de l'e-mail";

        // Paramètres de propriétés
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Création d'une session de messagerie
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Création d'un objet MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Définition des détails de l'e-mail
            message.setFrom(new InternetAddress(username));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Envoi de l'e-mail
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès !");
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }*/


    @FXML
    public void start(Stage primaryStage) {
        sendEmail("asmaboussaada0@gmail.com", "Confirmation", "Votre email a été confirmé avec succès.");
    }



   @FXML
    public static void sendEmail(String to, String subject, String body) {

        // Paramètres SMTP de Gmail
        String username = "asmaboussaada0@gmail.com";
        String password = "asma55147032";

        // Configuration des propriétés
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Création de la session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Création du message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(MimeMessage.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Envoi du message
            Transport.send(message);

            // Affichage d'une confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Email envoyé avec succès !");
            alert.showAndWait();

        } catch (Exception e) {
            // En cas d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'envoi de l'email : " + e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private void handleSendButtonClick(ActionEvent event) {
        sendEmail("bsdasma13@gmail.com", "Confirmation", "Votre email a été confirmé avec succès.");
    }



}
