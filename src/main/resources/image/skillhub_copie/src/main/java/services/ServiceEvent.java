package services;

import entities.event;
import utils.MyDatabase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceEvent implements IService<event> {
    static Connection cnx = MyDatabase.getInstance().getCnx();

    public ServiceEvent() {
        System.out.println("Connected successfully !");
    }
    @Override
    public void ajouter(event event) throws SQLException {

        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            System.out.println("Error: Event title cannot be null or empty.");
            return;
        }

        String req = "INSERT INTO event (price, nb_participant, title, description, statut, location, image, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, event.getPrice());
            ps.setInt(2, event.getNb_participant());
            ps.setString(3, event.getTitle());
            ps.setString(4, event.getDescription());
            ps.setString(5, event.getStatut());
            ps.setString(6, event.getLocation());
            ps.setString(7, event.getImage());
            ps.setDate(8, new java.sql.Date(event.getStart_date().getTime()));
            ps.setDate(9, new java.sql.Date(event.getEnd_date().getTime()));
            ps.executeUpdate();
            System.out.println("Event added ");
        } catch (SQLException e) {
            throw new SQLException("Error adding event to the database: " + e.getMessage());
        }

    }

    @Override
    public void modifier(event event) {
        String req = "UPDATE event SET price=?, nb_participant=?, title=?, description=?, statut=?, location=?, image=?, start_date=?, end_date=? WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, event.getPrice());
            ps.setInt(2, event.getNb_participant());
            ps.setString(3, event.getTitle());
            ps.setString(4, event.getDescription());
            ps.setString(5, event.getStatut());
            ps.setString(6, event.getLocation());
            ps.setString(7, event.getImage());
            ps.setDate(8, new java.sql.Date(event.getStart_date().getTime()));
            ps.setDate(9, new java.sql.Date(event.getEnd_date().getTime()));
            ps.setInt(10, event.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Event updated successfully with ID: " + event.getId());
            } else {
                System.out.println("No event found with ID: " + event.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM event WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Event deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public event getOneById(int id) {
        String req = "SELECT * FROM event WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                int price = res.getInt("price");
                int nb_participant = res.getInt("nb_participant");
                String title = res.getString("title");
                String description = res.getString("description");
                String statut = res.getString("statut");
                String location = res.getString("location");
                String image = res.getString("image");
                Date start_date = res.getDate("start_date");
                Date end_date = res.getDate("end_date");
                event event = new event(id, price, nb_participant, title, description, statut, location, image, start_date, end_date);
                return event;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<event> getAll() {
        Set<event> events = new HashSet<>();
        String req = "SELECT * FROM event";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int id = res.getInt("id");
                int nb_participant = res.getInt("nb_participant");
                int price = res.getInt("price");

                String title = res.getString("title");
                String description = res.getString("description");
                String statut = res.getString("statut");
                String location = res.getString("location");
                String image = res.getString("image");
                Date start_date = res.getDate("start_date");
                Date end_date = res.getDate("end_date");
                event event = new event(id,nb_participant,price,title, description, statut, location, image, start_date, end_date);
                events.add(event);
                System.out.println("Event ID: " + id + ", Title: " + title);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }
}
