package org.example;

import entities.event;
import entities.promotion;
import services.ServiceEvent;
import services.ServicePromotion;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // Date de début et de fin pour l'événement
        Date startDate = new Date(100, 7, 9); // 9 août 2000 (l'année est 1900 + 100, le mois commence à 0)
        Date endDate = new Date(124, 11, 10); // 10 décembre 2024 (l'année est 1900 + 124, le mois commence à 0)

        // Création d'un nouvel événement
        event newEvent = new event(1, 5, 50, "Event", "description", "rep", "Tunis", "bbb.jpg", startDate, endDate);

        // Service pour gérer les événements
        ServiceEvent serviceEvent = new ServiceEvent();

        // Ajout de l'événement
        try {
            serviceEvent.ajouter(newEvent);
            System.out.println("Connected successfully !");
        } catch (SQLException e) {
            System.out.println("Error adding event: " + e.getMessage());
        }

        // Création d'une nouvelle promotion
        promotion newPromotion = new promotion(1, 20, "ABCDE12345");

        // Service pour gérer les promotions
        ServicePromotion servicePromotion = new ServicePromotion();

        // Ajout de la promotion
        try {
            servicePromotion.ajouter(newPromotion);
        } catch (SQLException e) {
            System.out.println("Error adding promotion: " + e.getMessage());
        }
    }
}