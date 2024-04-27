package utils;

import javax.swing.plaf.PanelUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {

    final String URL="jdbc:mysql://localhost:3306/freelance";

    final String USERNAME="root";
    final String PASSWORD="";

    Connection connection;

    static MyDatabase instance;

    public MyDatabase(){
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connexion etablie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public  static MyDatabase getInstance(){
        if(instance==null){
            instance= new MyDatabase();
        }
        return instance;
    }


    public Connection getConnection() {
        return connection;
    }
}

