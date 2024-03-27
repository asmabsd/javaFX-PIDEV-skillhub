package utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/freelance";
    private static final String USER = "root";
    private static final String PWD = "";
    private Connection cnx;

    private static MyDatabase instance;

    private MyDatabase(){
        try {
            cnx = DriverManager.getConnection(URL,USER,PWD);
            System.out.println("Connected successfully !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static MyDatabase getInstance(){
        if(instance == null)
            instance = new MyDatabase();
        return instance;
    }

    public Connection getCnx(){
        return this.cnx;
    }

}