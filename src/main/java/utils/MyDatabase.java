//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
    final String URL = "jdbc:mysql://localhost:3306/freelance";
    final String USERNAME = "root";
    final String PASSWORD = "";
    Connection connection;
    static MyDatabase instance;

    public MyDatabase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/freelance", "root", "");
            System.out.println("Connexion etablie");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public static MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabase();
        }

        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
