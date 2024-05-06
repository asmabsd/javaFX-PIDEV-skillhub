
package controllers;

import entities.Contrat;
import entities.Organisation;
import entities.User;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceContrat;
import services.ServiceOrganisation;
import services.ServiceUser;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class afficherContratController {
    public Button delete;
    public Button update;
    public Button stats;
    public Button stats1;
    public Button stats2;
    public Button stats3;
    public Button stats4;
    ServiceContrat sc = new ServiceContrat();
    @FXML
    private TableColumn<Contrat, Date> date_debutCol;
    @FXML
    private TableColumn<Contrat, Date> date_finCol;
    @FXML
    private TableColumn<Contrat, Integer> montantCol;
    @FXML
    private TableColumn<Contrat, String> statutCol;
    @FXML
    private TableColumn<Contrat, String> projetCol;
    @FXML
    private TableColumn<Contrat, String> freelancerCol;
    @FXML
    private TableColumn<Contrat, Organisation> organisation_idCol;
    @FXML
    private TableColumn<Contrat, User> user_idCol;
    @FXML
    private TableColumn<Contrat, Date> date_creationCol;
    @FXML
    private TableColumn<Contrat, String> descriptionCol;
    @FXML
    private TableView<Contrat> tableView;
    @FXML
    private Label welcomeLBL;
    ObservableList<Contrat> observableList;
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
    private DatePicker date_debutp;
    @FXML
    private DatePicker date_finp;
    @FXML
    private DatePicker date_creationp;
    private Contrat selectedContrat;

    public afficherContratController() {
    }

    @FXML
    void initialize() {
        try {
            List<Contrat> contratList = this.sc.afficher();
            this.observableList = FXCollections.observableList(contratList);
            this.tableView.setItems(this.observableList);
            this.date_debutCol.setCellValueFactory(new PropertyValueFactory("date_debut"));
            this.date_finCol.setCellValueFactory(new PropertyValueFactory("date_fin"));
            this.montantCol.setCellValueFactory(new PropertyValueFactory("montant"));
            this.statutCol.setCellValueFactory(new PropertyValueFactory("statut"));
            this.projetCol.setCellValueFactory(new PropertyValueFactory("projet"));
            this.freelancerCol.setCellValueFactory(new PropertyValueFactory("freelancer"));
           // this.organisation_idCol.setCellValueFactory(new PropertyValueFactory("organisation_id"));
            organisation_idCol.setCellValueFactory(new PropertyValueFactory<>("organisation_id"));
            user_idCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));




            this.date_creationCol.setCellValueFactory(new PropertyValueFactory("date_creation"));
            this.descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        } catch (SQLException var2) {
            System.err.println(var2.getMessage());
        }

    }

    @FXML
    void delete(ActionEvent event) {
        try {
            Contrat c = this.tableView.getSelectionModel().getSelectedItem();
            this.sc.supprimer(c.getId());
            this.observableList.remove(c);
        } catch (SQLException var3) {
            System.err.println(var3.getMessage());
        }

    }

    @FXML
    void setData(String param) {
        this.welcomeLBL.setText("Liste des contrats  " + param);
    }



    public class ButtonTableCellFactory implements Callback<TableColumn<Contrat, String>, TableCell<Contrat, String>> {

        @Override
        public TableCell<Contrat, String> call(TableColumn<Contrat, String> param) {
            return new TableCell<>() {
                final Button btn = new Button("Edit");

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        setGraphic(btn);
                        btn.setOnAction(event -> {
                            Contrat selectedContrat = getTableView().getItems().get(getIndex());
                            populateFormWithEvent(selectedContrat);
                        });
                    }
                }
            };
        }


    }


    @FXML
    private void populateFormWithEvent(Contrat selectedEvent) {

        statutTf.setText(selectedEvent.getStatut());

        projetTf.setText(selectedContrat.getProjet());
        freelancerTf.setText(selectedContrat.getFreelancer());
        montantTf.setText(String.valueOf(selectedEvent.getMontant()));
        descriptionTf.setText(selectedContrat.getDescription());
        date_debutp.setValue(selectedContrat.getDate_debut().toLocalDate());
        date_finp.setValue(selectedContrat.getDate_fin().toLocalDate());
        date_creationp.setValue(selectedContrat.getDate_creation().toLocalDate());
        user_idTf.setText(String.valueOf(selectedContrat.getUser_id()));
        organisation_idTf.setText(String.valueOf(selectedContrat.getOrganisation_id()));

    }

    ServiceOrganisation so = new ServiceOrganisation();
    ServiceUser su = new ServiceUser();

    private void updateSelectedContratWithFormData(Contrat selectedContrat) {
        selectedContrat.setMontant(Integer.parseInt(montantTf.getText()));
        selectedContrat.setFreelancer(freelancerTf.getText());
        selectedContrat.setStatut(statutTf.getText());
        selectedContrat.setDescription(descriptionTf.getText());
        selectedContrat.setProjet(projetTf.getText());
        selectedContrat.setDate_debut(Date.valueOf(date_debutp.getValue()));
        selectedContrat.setDate_fin(Date.valueOf(date_finp.getValue()));
        selectedContrat.setDate_creation(Date.valueOf(date_creationp.getValue()));
        selectedContrat.setUser_id(su.getOneById(Integer.parseInt(user_idTf.getText())));

        selectedContrat.setOrganisation_id(so.getOneById(Integer.parseInt(organisation_idTf.getText())));
    }

    private void clearFormFields() {
        freelancerTf.clear();
        statutTf.clear();
        descriptionTf.clear();
        projetTf.clear();
        //  statutComboBox.getSelectionModel().clearSelection();
        user_idTf.clear();
        organisation_idTf.clear();
        montantTf.clear();
        date_debutp.setValue(null);
        date_finp.setValue(null);
        date_finp.setValue(null);
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

    @FXML
    void editContrat() {
        // Récupérer le contrat sélectionné dans le TableView
        Contrat selectedContrat = tableView.getSelectionModel().getSelectedItem();

        if (selectedContrat != null) {
            try {
                // Charger la vue de modification de contrat depuis le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateContrat.fxml"));
                Parent root = loader.load();

                // Récupérer le contrôleur associé à la vue de modification de contrat
                modifierContratController modifierContratController = loader.getController();

                // Passer le contrat sélectionné au contrôleur de la vue de modification
                modifierContratController.initData(selectedContrat);

                // Afficher la scène de modification de contrat
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Afficher une alerte si aucun contrat n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun contrat sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un contrat à modifier.");
            alert.showAndWait();
        }
    }
    @FXML
    private PieChart pieChart;

    @FXML
    void afficherChartecirculaire(ActionEvent event) {
        // Créer un graphique circulaire
        PieChart pieChart = new PieChart();
        Map<Integer, Integer> contratsParAnnee = new HashMap<>();

        // Agréger les données
        for (Contrat contrat : observableList) {
            Date dateDebut = contrat.getDate_debut();
            int annee = dateDebut.getYear();
            contratsParAnnee.put(annee, contratsParAnnee.getOrDefault(annee, 0) + 1);
        }

        // Calculer le total des contrats sur toutes les années
        int totalContrats = contratsParAnnee.values().stream().mapToInt(Integer::intValue).sum();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        // Ajouter les données au graphique
        for (Map.Entry<Integer, Integer> entry : contratsParAnnee.entrySet()) {
            double pourcentage = (double) entry.getValue() / totalContrats * 100;
            String label = entry.getKey().toString() + " (" + String.format("%.2f", pourcentage) + "%)";
            PieChart.Data slice = new PieChart.Data(label, entry.getValue());
            slice.setName(dateFormat.format(new Date(entry.getKey(), 0, 1)));
            pieChart.getData().add(slice);
        }

        // Créer une nouvelle fenêtre pour afficher le graphique
        Stage stage = new Stage();
        stage.setScene(new Scene(new AnchorPane(pieChart)));
        stage.setTitle("Graphique nbr des contrats par année");
        stage.show();

        // Configurer l'étiquetage des tranches pour afficher les pourcentages
        for (final PieChart.Data data : pieChart.getData()) {
            data.nameProperty().bind(
                    Bindings.concat(
                            data.getName(), " (",
                            String.format("%.1f%%", 100 * data.getPieValue() / totalContrats),
                            ")"
                    )
            );
        }
    }


    @FXML
    void afficherChartecirculaire2(ActionEvent event) {
        // Créer un graphique circulaire
        PieChart pieChart = new PieChart();

        // Définir les intervalles de montant
        int[] intervals = {20, 50, 100, 150, 200, 250, 300};
        // Initialiser le compteur pour chaque intervalle
        Map<String, Integer> contratParIntervalle = new HashMap<>();
        for (int i = 0; i < intervals.length - 1; i++) {
            String intervalLabel = intervals[i] + "-" + intervals[i+1];
            contratParIntervalle.put(intervalLabel, 0);
        }

        // Agréger les données
        for (Contrat contrat : observableList) {
            int montant = contrat.getMontant();
            for (int i = 0; i < intervals.length - 1; i++) {
                if (montant >= intervals[i] && montant < intervals[i + 1]) {
                    String intervalLabel = intervals[i] + "-" + intervals[i + 1];
                    contratParIntervalle.put(intervalLabel, contratParIntervalle.get(intervalLabel) + 1);
                    break;
                }
            }
        }

        // Calculer le total des contrats sur toutes les intervalles
        int totalContrats = contratParIntervalle.values().stream().mapToInt(Integer::intValue).sum();

        // Ajouter les données au graphique
        for (Map.Entry<String, Integer> entry : contratParIntervalle.entrySet()) {
            double pourcentage = (double) entry.getValue() / totalContrats * 100;
            String label = entry.getKey() + " (" + String.format("%.2f", pourcentage) + "%)";
            pieChart.getData().add(new PieChart.Data(label, entry.getValue()));
        }

        // Créer une nouvelle fenêtre pour afficher le graphique
        Stage stage = new Stage();
        stage.setScene(new Scene(new AnchorPane(pieChart)));
        stage.setTitle("Graphique nbr des contrats par intervalle de montant");
        stage.show();
    }


    @FXML
    void afficherChartecirculaire3(ActionEvent event) {
        // Créer un graphique circulaire
        PieChart pieChart = new PieChart();

        // Initialiser le compteur pour chaque statut
        Map<String, Integer> contratsParStatut = new HashMap<>();

        // Agréger les données
        for (Contrat contrat : observableList) {
            String statut = contrat.getStatut();
            contratsParStatut.put(statut, contratsParStatut.getOrDefault(statut, 0) + 1);
        }

        // Calculer le total des contrats
        int totalContrats = contratsParStatut.values().stream().mapToInt(Integer::intValue).sum();

        // Ajouter les données au graphique
        for (Map.Entry<String, Integer> entry : contratsParStatut.entrySet()) {
            double pourcentage = (double) entry.getValue() / totalContrats * 100;
            String label = entry.getKey() + " (" + String.format("%.2f", pourcentage) + "%)";
            pieChart.getData().add(new PieChart.Data(label, entry.getValue()));
        }

        // Créer une nouvelle fenêtre pour afficher le graphique
        Stage stage = new Stage();
        stage.setScene(new Scene(new AnchorPane(pieChart)));
        stage.setTitle("Répartition des contrats par statut");
        stage.show();
    }

    @FXML
    void afficherChartecirculaire4(ActionEvent event) {
        // Créer un graphique circulaire
        PieChart pieChart = new PieChart();

        // Initialiser le compteur pour chaque organisation
        Map<String, Integer> contratsParOrganisation = new HashMap<>();

        // Agréger les données
        for (Contrat contrat : observableList) {
            String nomOrganisation = contrat.getOrganisation_id().getNom(); // Supposons que "getNom()" retourne le nom de l'organisation
            contratsParOrganisation.put(nomOrganisation, contratsParOrganisation.getOrDefault(nomOrganisation, 0) + 1);
        }

        // Calculer le total des contrats
        int totalContrats = contratsParOrganisation.values().stream().mapToInt(Integer::intValue).sum();

        // Ajouter les données au graphique
        for (Map.Entry<String, Integer> entry : contratsParOrganisation.entrySet()) {
            double pourcentage = (double) entry.getValue() / totalContrats * 100;
            String label = entry.getKey() + " (" + String.format("%.2f", pourcentage) + "%)";
            pieChart.getData().add(new PieChart.Data(label, entry.getValue()));
        }

        // Créer une nouvelle fenêtre pour afficher le graphique
        Stage stage = new Stage();
        stage.setScene(new Scene(new AnchorPane(pieChart)));
        stage.setTitle("Répartition des contrats par organisation");
        stage.show();
    }


 /*   @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        ServiceContrat sc =new ServiceContrat();

        Contrat selected = .getSelectionModel().getSelectedItem();
        PdfPTable table = new PdfPTable(3);
        PdfPCell c1 = new PdfPCell(new Phrase("produit"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("quantite"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("prix"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);




        for(int i=0;i<OrderService.recupererOrderDetails(selected).size();i++){
            List<orderdetail> list=OrderService.recupererOrderDetails(selected);
            table.addCell(list.get(i).getProduit().getNom_produit()+"");
            table.addCell(list.get(i).getQuantity()+"");
            table.addCell(list.get(i).getPrix()+"");


        }



        Document doc=new Document();
        PdfWriter.getInstance(doc,new FileOutputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+selected.getNumero_commande()+".pdf"));
        doc.open();
        doc.addAuthor("Easy Fit");
        Image img=  Image.getInstance("C:/Users/PC/Downloads/logo.png");
        doc.add(img);

        doc.addTitle("Facture n°"+selected.getNumero_commande());

        doc.add(new Paragraph("Nom client:  Line Kazdaghli \n Num° commande"+selected.getNumero_commande()+"\n Date:"+selected.getDate()+"\n\n\n\n"));
        doc.add(table);
        doc.add(new Paragraph("votre total est  "+selected.getTotal()+"   dt"));
        doc.close();
        Desktop.getDesktop().open(new File("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+selected.getNumero_commande()+".pdf"));

    }*/
    //stat peut etre considéré les 2
//itext for pdf done
    //twilio sms
    //api : email ,sms,pdf
    //

}

