
package services;


import entities.Organisation;
import utils.MyDatabase;

import java.sql.*;
import java.util.*;

public class ServiceOrganisation implements IService<Organisation> {
    Connection connection = MyDatabase.getInstance().getConnection();

    public ServiceOrganisation() {
    }

    public void ajouter(Organisation organisation) throws SQLException {
        String req = "INSERT INTO organisation (nom, adresse, telephone, email, contact, domaine_activite) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(req);
        preparedStatement.setString(1, organisation.getNom());
        preparedStatement.setString(2, organisation.getAdresse());
        preparedStatement.setInt(3, organisation.getTelephone());
        preparedStatement.setString(4, organisation.getEmail());
        preparedStatement.setString(5, organisation.getContact());
        preparedStatement.setString(6, organisation.getDomaine_activite());

        preparedStatement.executeUpdate();
        System.out.println("Organisation ajoutée avec succès.");
    }

    public void modifier(Organisation organisation) throws SQLException {
        String req = "UPDATE organisation SET nom=?, adresse=?, telephone=?, email=?, contact=?, domaine_activite=? WHERE id=?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(req);
        preparedStatement.setString(1, organisation.getNom());
        preparedStatement.setString(2, organisation.getAdresse());
        preparedStatement.setInt(3, organisation.getTelephone());
        preparedStatement.setString(4, organisation.getEmail());
        preparedStatement.setString(5, organisation.getContact());
        preparedStatement.setString(6, organisation.getDomaine_activite());
        preparedStatement.setInt(7, organisation.getId());

        preparedStatement.executeUpdate();
    }

    public void supprimer(int id) throws SQLException {
        String req = "delete from organisation where id=" + id;
        Statement statement = this.connection.createStatement();
        statement.executeUpdate(req);
    }

    public List<Organisation> afficher() throws SQLException {
        List<Organisation> organisations = new ArrayList();
        String req = "SELECT * FROM organisation";
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while(rs.next()) {
            Organisation organisation = new Organisation();
            organisation.setId(rs.getInt("id"));
            organisation.setNom(rs.getString("nom"));
            organisation.setAdresse(rs.getString("adresse"));
            organisation.setTelephone(rs.getInt("telephone"));
            organisation.setEmail(rs.getString("email"));
            organisation.setContact(rs.getString("contact"));
            organisation.setDomaine_activite(rs.getString("domaine_activite"));
             organisations.add(organisation);
        }

        return organisations;
    }



    public Organisation getOneById(int id) {
        String req = "SELECT * FROM organisation WHERE `id`=?";

        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                String nom= res.getString("nom");
                String adresse = res.getString("adresse");
                Integer telephone=res.getInt("telephone");
                String email = res.getString("email");
                String contact = res.getString("contact");
                String domaine_activite = res.getString("domaine_activite");




                Organisation organisation= new Organisation(id,nom ,adresse,telephone,email,contact,domaine_activite);

                return organisation;
            }
        } catch (SQLException var11) {
            System.out.println(var11.getMessage());
        }

        return null;
    }

    public Set<Organisation> getAll() {
        Set<Organisation> organisations = new HashSet();
        String req = "SELECT * FROM organisation";

        try {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(req);

            while(res.next()) {
                int id = res.getInt("id");
                String nom= res.getString("email");
                String adresse = res.getString("email");
                String domaine_activite = res.getString("email");
                String email = res.getString("email");
                Integer telephone=res.getInt("telephone");
                String contact = res.getString("contact");

                Organisation organisation= new Organisation(id,nom ,adresse,telephone,email,contact,domaine_activite);


                organisations.add(organisation);
                System.out.println(" ID: " + id + ", nom: " + nom + ", adresse: " + adresse+"telephone"+telephone+"email"+email+"contact"+contact+"domaine"+domaine_activite);
            }
        } catch (SQLException var12) {
            System.out.println(var12.getMessage());
        }

        return organisations;
    }
    public List<Integer> getAllIds() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        Set<Organisation> organisations = getAll();
        for (Organisation organisation : organisations) {
            ids.add(organisation.getId());
        }
        return ids;
    }

}





