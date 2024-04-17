package services;

import entities.promotion;
import utils.MyDatabase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/*public class ServicePromotion implements IService<promotion> {
   /* static Connection cnx = MyDatabase.getInstance().getCnx();

    public ServicePromotion() {
        System.out.println("Connected successfully !");
    }

    @Override
    public void ajouter(promotion promotion) throws SQLException {
        if (promotion.getQr_code() == null || promotion.getQr_code().isEmpty()) {
            System.out.println("Error: Promotion QR code cannot be null or empty.");
            return;
        }

        String req = "INSERT INTO promotion (event_id, discount, qr_code) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, promotion.getEvent_id());
            ps.setInt(2, promotion.getDiscount());
            ps.setString(3, promotion.getQr_code());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Promotion added");
            } else {
                System.out.println("Failed to add promotion");
            }
        } catch (SQLException e) {
            throw new SQLException("Error adding promotion to the database: " + e.getMessage());
        }
    }

    @Override
    public void modifier(promotion promotion) {
        String req = "UPDATE promotion SET event_id=?, discount=?, qr_code=? WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, promotion.getEvent_id());
            ps.setInt(2, promotion.getDiscount());
            ps.setString(3, promotion.getQr_code());
            ps.setInt(4, promotion.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Promotion updated successfully with ID: " + promotion.getId());
            } else {
                System.out.println("No promotion found with ID: " + promotion.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM promotion WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Promotion deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public promotion getOneById(int id) {
        String req = "SELECT * FROM promotion WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                int event_id = res.getInt("event_id");
                String qr_code = res.getString("qr_code");
                int discount = res.getInt("discount");
                promotion promotion = new promotion(id, event_id, discount, qr_code);
                return promotion;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<promotion> getAll() {
        Set<promotion> promotions = new HashSet<>();
        String req = "SELECT * FROM promotion";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int id = res.getInt("id");
                int event_id = res.getInt("event_id");
                String qr_code = res.getString("qr_code");
                int discount = res.getInt("discount");
                promotion promotion = new promotion(id, event_id, discount, qr_code);
                promotions.add(promotion);
                System.out.println("Promotion ID: " + id + ", Event ID: " + event_id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return promotions;
    }
}*/
