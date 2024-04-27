
package services;

import entities.Contrat;
import entities.Organisation;
import entities.User;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceContrat implements IService<Contrat> {
    Connection connection = MyDatabase.getInstance().getConnection();

    public ServiceContrat() {
    }

    public void ajouter(Contrat contrat) throws SQLException {

        String req = "INSERT INTO contrat (date_debut, date_fin, montant, statut, projet, freelancer, organisation_id, user_id, date_creation, description) VALUES ('" + contrat.getDate_debut() + "','" + contrat.getDate_fin() + "'," + contrat.getMontant() + ",'" + contrat.getStatut() + "','" + contrat.getProjet() + "','" + contrat.getFreelancer() + "'," + contrat.getOrganisation_id().getId() + "," + contrat.getUser_id().getId() + ",'" + contrat.getDate_creation() + "','" + contrat.getDescription() + "')";
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(req);
        System.out.println("Contrat ajouté avec succès.");
    }

    public void modifier(Contrat contrat) throws SQLException {
        String req = "UPDATE contrat SET date_debut=?, date_fin=?, montant=?, statut=?, projet=?, freelancer=?, organisation_id=?, user_id=?, date_creation=?, description=? WHERE id=?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(req);
        preparedStatement.setInt(1, contrat.getId());
        preparedStatement.setDate(2, contrat.getDate_debut());
        preparedStatement.setDate(3, contrat.getDate_fin());
        preparedStatement.setInt(4, contrat.getMontant());
        preparedStatement.setString(5, contrat.getStatut());
        preparedStatement.setString(6, contrat.getProjet());
        preparedStatement.setString(7, contrat.getFreelancer());
        preparedStatement.setInt(8, contrat.getOrganisation_id().getId());
        preparedStatement.setInt(9, contrat.getUser_id().getId());
        preparedStatement.setDate(10, contrat.getDate_creation());
        preparedStatement.setString(11, contrat.getDescription());
        preparedStatement.executeUpdate();
    }

    public void supprimer(int id) throws SQLException {
        String req = "delete from contrat where id=" + id;
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(req);
    }

    public List<Contrat> afficher() throws SQLException {
        List<Contrat> contrats = new ArrayList();
        String req = "SELECT * FROM contrat";
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while(rs.next()) {
            Contrat contrat = new Contrat();
            contrat.setId(rs.getInt("id"));
            contrat.setDate_debut(rs.getDate("date_debut"));
            contrat.setDate_fin(rs.getDate("date_fin"));
            contrat.setMontant(rs.getInt("montant"));
            contrat.setStatut(rs.getString("statut"));
            contrat.setProjet(rs.getString("projet"));
            contrat.setFreelancer(rs.getString("freelancer"));
            contrat.setOrganisation_id(new Organisation(rs.getInt("organisation_id")));
            contrat.setUser_id(new User(rs.getInt("user_id")));
            contrat.setDate_creation(rs.getDate("date_creation"));
            contrat.setDescription(rs.getString("description"));
            contrats.add(contrat);
        }

        return contrats;
    }
}
