package services;

import entities.Reclamation;
import utils.MyDatabase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServiceReclamation implements IService<Reclamation> {
    Connection connection;

    public ServiceReclamation() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        String req = "insert into reclamation (objet,contenu,statut,reponse,/*date_reclamtion*/)" +
                "values('" + reclamation.getObjet() + "','" + reclamation.getContenu() + "','" + reclamation.getStatut() + "','" + reclamation.getReponse() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(req);
        System.out.println("reclamtion ajoute");

    }

    @Override
    public void modifier(Reclamation reclamation) throws SQLException {
        String req = "modifer reclamtion set objet=?,contenu=?,statut=?,reponse=? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, reclamation.getObjet());
        preparedStatement.setString(2, reclamation.getContenu());
        preparedStatement.setString(3, reclamation.getStatut());
        preparedStatement.setString(4, reclamation.getReponse());
        preparedStatement.setInt(5, reclamation.getId());

        preparedStatement.executeUpdate();
        System.out.println("modifie");

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public Reclamation getOneById(int id) {
        return null;
    }

    @Override
    public Set<Reclamation> getAll() {
        return null;
    }

    @Override
    public List<Reclamation> afficher() throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        try {
            String req = "select * from reclamation";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"),
                        rs.getString("objet"),
                        rs.getString("contenu"),
                        rs.getString("statut"),
                        rs.getString("reponse")));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Reclamation i : list) {
            System.out.println(i.getId());
        }
        return list;
    }
}
