package edu.esprit.freelanceproposals.controllers;

import edu.esprit.freelanceproposals.entities.Proposal;
import edu.esprit.freelanceproposals.services.ProposalService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

public class deadlineCalendar implements Initializable
{
    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @javafx.fxml.FXML
    private Text year;

    @javafx.fxml.FXML
    private Text month;

    @javafx.fxml.FXML
    private FlowPane calendar;

    ProposalService proposalService = new ProposalService();
    @javafx.fxml.FXML
    private Button btnAssignments;
    @javafx.fxml.FXML
    private Button gotoJobs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @javafx.fxml.FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @javafx.fxml.FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<Proposal>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<Proposal> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<Proposal> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            Proposal p = calendarActivities.get(k);
            Button text = new Button(p.getTitre());
            text.setAlignment(Pos.CENTER);
            text.setPrefWidth(50);
            VBox.setMargin(text, new Insets(0, 0, 5, 0));
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(p.getTitre());
                alert.setHeaderText(p.getClient());
                alert.setContentText("Statut: "+p.getStatut()+", Avant le: "+p.getDelai()+", pour: "+p.getBudget()+"DT");
                alert.showAndWait();
            });
        }
        calendarActivityBox.setPadding(new javafx.geometry.Insets(10));
        ScrollPane dateActivities = new ScrollPane();
        dateActivities.setContent(calendarActivityBox);
        dateActivities.setMaxWidth(rectangleWidth);
        dateActivities.setPrefHeight(rectangleHeight);
        calendarActivityBox.setStyle("-fx-background-color:RED");
        stackPane.getChildren().add(dateActivities);
    }

    private Map<Integer, List<Proposal>> createCalendarMap(List<Proposal> calendarActivities) {
        Map<Integer, List<Proposal>> calendarActivityMap = new HashMap<>();

        for (Proposal activity: calendarActivities) {
            int activityDate = activity.getDelai().getDate();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<Proposal> OldListByDate = calendarActivityMap.get(activityDate);

                List<Proposal> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    private Map<Integer, List<Proposal>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Proposal> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        List<Proposal> proposals = proposalService.listeProposals();
        for (Proposal proposal:proposals){
            if (proposal.getDelai().getMonth()+1==month && proposal.getDelai().getYear()+1900==year){
                calendarActivities.add(proposal);
            }
        }
        return createCalendarMap(calendarActivities);
    }

    @javafx.fxml.FXML
    public void gotoProposals(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/proposals/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void gotoComments(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/freelanceproposals/gui/comms/ListeFront.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}