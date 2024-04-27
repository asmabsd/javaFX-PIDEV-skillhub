//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tests;

import entities.Organisation;
import services.ServiceOrganisation;
import utils.MyDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        MyDatabase.getInstance().getConnection();
        Connection connection = MyDatabase.getInstance().getConnection();
        ServiceOrganisation serviceOrganisation = new ServiceOrganisation();
        Organisation o1 = new Organisation("esprit12", "Ariana soghra ", 44556677, "a@esprit.tn", "bouakline", "ensegnement superieure ");
        Organisation o2 = new Organisation("TEKUP", "ghazela", 44556677, "a@esprit.tn", "bouakline", "ensegnement superieure ");

        try {
            serviceOrganisation.ajouter(o1);
            serviceOrganisation.ajouter(o2);
        } catch (SQLException var8) {
            System.out.println(var8.getMessage());
        }

        try {
            serviceOrganisation.modifier(o1);
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
        }

        try {
            serviceOrganisation.supprimer(9);
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

    }
}
