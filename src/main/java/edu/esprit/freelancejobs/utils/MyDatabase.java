/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelancejobs.utils;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Sirine
 */
public class MyDatabase {
    private String url="jdbc:mysql://localhost:3306/freelance";
    private String login="root";
    private String password="";


    Connection cnx;
    public static MyDatabase instance;
    private MyDatabase(){

        try{
            cnx= DriverManager.getConnection(url,login,password);
            System.out.println("Connexion etablie");
        }catch(SQLException e){
            System.out.println("Connexion non etablie"+e);
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
    public static MyDatabase getInstance(){
        if(instance == null){
            instance = new MyDatabase();

        }
        return instance;

    }
}
