/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelanceproposals.services;

import edu.esprit.freelanceproposals.entities.Commentaire;
import edu.esprit.freelanceproposals.utils.MyDatabase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sirine
 */
public class CommentaireService implements ICommentaireService{
    
    static Connection connection = MyDatabase.getInstance().getCnx();

    public CommentaireService() {
        System.out.println("Connection réussie !");
    }

    @Override
    public void ajout(Commentaire c) {
        String sql = "INSERT INTO commentaire (auteur, commentaire, date_publication, note) VALUES (?, ?, ?, ?)"; 
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getAuteur());
            preparedStatement.setString(2, c.getCommentaire());
            preparedStatement.setDate(3, c.getDatePublication());
            
            preparedStatement.setInt(4, c.getNote());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void modifier(Commentaire c) {
        String sql = "UPDATE commentaire SET auteur=?, commentaire=?, date_publication=?, note=? WHERE id=?";
        try  
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getAuteur());
            preparedStatement.setString(2, c.getCommentaire());
            preparedStatement.setDate(3, c.getDatePublication());
            preparedStatement.setInt(4, c.getNote());
            preparedStatement.setInt(5, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM commentaire WHERE id=?";
        try  
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Commentaire recherche(int id) {
        String sql = "SELECT id, auteur, commentaire, date_publication, note FROM commentaire WHERE id=?";
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String auteur = resultSet.getString("auteur");
                String commentaire = resultSet.getString("commentaire");
                Date datePublication = resultSet.getDate("date_publication");
                int note = resultSet.getInt("note");
                return new Commentaire(id, auteur, commentaire, datePublication, note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Commentaire> listeCommentaires() {
        List<Commentaire> commentaires = new ArrayList<>();
        String sql = "SELECT id, auteur, commentaire, date_publication, note FROM commentaire";
        try 
        {                      
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String auteur = resultSet.getString("auteur");
                String commentaire = resultSet.getString("commentaire");
                Date datePublication = resultSet.getDate("date_publication");
                int note = resultSet.getInt("note");
                Commentaire newComment = new Commentaire(id, auteur, commentaire, datePublication, note);
                commentaires.add(newComment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }

    @Override
    public List<Boolean> getReactionsByComment(int id) {
        List<Boolean> reactions = new ArrayList<>();
        String sql = "SELECT feeling FROM commentaire_reviews WHERE commentaire_id=?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Boolean reaction = resultSet.getBoolean("feeling");
                reactions.add(reaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reactions;
    }

    public void ajoutReaction(Commentaire c,Boolean reaction) {
        String sql = "INSERT INTO commentaire_reviews (user_id, commentaire_id, feeling) VALUES (?, ?, ?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 65);
            preparedStatement.setInt(2, c.getId());
            preparedStatement.setBoolean(3, reaction);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
