/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelanceproposals.services;

import edu.esprit.freelanceproposals.entities.Proposal;
import edu.esprit.freelanceproposals.utils.MyDatabase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sirine
 */
public class ProposalService implements IProposalService {
    
    static Connection connection = MyDatabase.getInstance().getCnx();

    public ProposalService() {
        System.out.println("Connection réussie !");
    }

    @Override
    public void ajout(Proposal p) {
        String sql = "INSERT INTO proposal (titre, description, client, freelancer, budget, delai, statut, date_soummission, date_debut, date_fin, fichiers) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p.getTitre());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setString(3, p.getClient());
            preparedStatement.setString(4, p.getFreelancer());
            preparedStatement.setDouble(5, p.getBudget());
            preparedStatement.setDate(6, p.getDelai());
            preparedStatement.setString(7, p.getStatut());
            preparedStatement.setDate(8, p.getDateSoumission());
            preparedStatement.setDate(9, p.getDateDebut());
            preparedStatement.setDate(10, p.getDateFin());
            preparedStatement.setString(11, p.getFichiers());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void modifier(Proposal p) {
        String sql = "UPDATE proposal SET titre=?, description=?, client=?, freelancer=?, budget=?, delai=?, statut=?, " +
                     "date_soummission=?, date_debut=?, date_fin=?, fichiers=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, p.getTitre());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setString(3, p.getClient());
            preparedStatement.setString(4, p.getFreelancer());
            preparedStatement.setDouble(5, p.getBudget());
            preparedStatement.setDate(6, p.getDelai());
            preparedStatement.setString(7, p.getStatut());
            preparedStatement.setDate(8, p.getDateSoumission());
            preparedStatement.setDate(9, p.getDateDebut());
            preparedStatement.setDate(10, p.getDateFin());
            preparedStatement.setString(11, p.getFichiers());
            preparedStatement.setInt(12, p.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM proposal WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Proposal recherche(int id) {
        String sql = "SELECT * FROM proposal WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");
                String client = resultSet.getString("client");
                String freelancer = resultSet.getString("freelancer");
                double budget = resultSet.getDouble("budget");
                Date delai = resultSet.getDate("delai");
                String statut = resultSet.getString("statut");
                Date dateSoummission = resultSet.getDate("date_soummission");
                Date dateDebut = resultSet.getDate("date_debut");
                Date dateFin = resultSet.getDate("date_fin");
                String fichiers = resultSet.getString("fichiers");
                return new Proposal(id, titre, description, client, freelancer, budget, delai, statut,
                                     dateSoummission, dateDebut, dateFin, fichiers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Proposal> listeProposals() {
        List<Proposal> proposals = new ArrayList<>();
        String sql = "SELECT * FROM proposal";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");
                String client = resultSet.getString("client");
                String freelancer = resultSet.getString("freelancer");
                double budget = resultSet.getDouble("budget");
                Date delai = resultSet.getDate("delai");
                String statut = resultSet.getString("statut");
                Date dateSoummission = resultSet.getDate("date_soummission");
                Date dateDebut = resultSet.getDate("date_debut");
                Date dateFin = resultSet.getDate("date_fin");
                String fichiers = resultSet.getString("fichiers");
                Proposal proposal = new Proposal(id, titre, description, client, freelancer, budget, delai, statut,
                                                  dateSoummission, dateDebut, dateFin, fichiers);
                proposals.add(proposal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proposals;
    }
}