package tests;

import java.time.format.DateTimeFormatter;
import entities.Reclamation;
import services.ServiceReclamation;
import utils.MyDatabase;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        // Créer une instance de LocalDate avec la date spécifique
       // LocalDate dateReclamation = LocalDate.of(2024, 4, 16);

        // Formatter la date au format "YYYY-MM-DD"


    //MyDatabase myDataBase = new MyDatabase();
        ServiceReclamation serviceReclamation = new ServiceReclamation();
        System.out.println(serviceReclamation.afficher());


}}
