package services;

import entities.actualite;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Serviceactualite implements IServiceactualite<actualite> {
    private Connection connection;

    public Serviceactualite() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(actualite actualite) {
        String req = "INSERT INTO actualite (titre, date_publication, description, categorie, image) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, actualite.getTitre());
            preparedStatement.setObject(2, actualite.getDate_publicaton());
            preparedStatement.setString(3, actualite.getDescription());
            preparedStatement.setString(4, actualite.getCategorie());
            preparedStatement.setString(5, actualite.getImage());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Nombre de lignes affectées : " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
            return false;
        }
    }

    @Override
    public void supprimer(actualite article) {
        String req = "DELETE FROM article WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, article.getId());
            preparedStatement.executeUpdate();
            System.out.println("Article supprimé avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de l'article : " + ex.getMessage());
        }
    }

    @Override
    public void modifier(actualite article) {
        String req = "UPDATE article SET titre=?, date_publication=?, description=?, categorie=?, image=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, article.getTitre());
            preparedStatement.setObject(2, article.getDate_publicaton());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setString(4, article.getCategorie());
            preparedStatement.setString(5, article.getImage());
            preparedStatement.setInt(6, article.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Nombre de lignes modifiées : " + rowsAffected);
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de l'article : " + ex.getMessage());
        }
    }

    @Override
    public List<actualite> afficher() {
        List<actualite> articles = new ArrayList<>();
        String req = "SELECT * FROM article";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                actualite article = new actualite();
                article.setId(rs.getInt("id"));
                article.setTitre(rs.getString("titre"));
                article.setDate_publicaton(rs.getTimestamp("date_publication").toLocalDateTime());
                article.setDescription(rs.getString("description"));
                article.setCategorie(rs.getString("categorie"));
                article.setImage(rs.getString("image"));
                articles.add(article);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des articles : " + e.getMessage());
        }
        return articles;
    }
    public List<actualite> getAllArticles() throws SQLException {
        List<actualite> articles = new ArrayList<>();
        String query = "SELECT * FROM article"; // Assurez-vous que "article" est le nom de votre table d'articles dans la base de données
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    actualite article = new actualite();
                    article.setId(resultSet.getInt("id"));
                    article.setTitre(resultSet.getString("titre"));
                    article.setDescription(resultSet.getString("description"));
                    article.setDate_publicaton(resultSet.getTimestamp("date_publication").toLocalDateTime());
                    // Assurez-vous que la colonne "date_pub_art" dans votre table de base de données est de type TIMESTAMP
                    // Convertissez-le en LocalDateTime en utilisant toLocalDateTime()
                    // Ajoutez d'autres attributs d'article de la même manière si nécessaire
                    articles.add(article);
                }
            }
        }
        return articles;
    }

}
