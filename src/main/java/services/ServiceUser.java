package services;

import entities.User;
import utils.MyDatabase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceUser implements IService<User> {
    static Connection cnx = MyDatabase.getInstance().getCnx();

    @Override
    public void ajouter(User user) {
        String req = "INSERT INTO user (`email`, `password`, `first_name`, `last_name`) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.executeUpdate();
            System.out.println("User added ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(User user) {
        String req = "UPDATE user SET `email`=?, `password`=?, `roles`=?, `first_name`=?, `last_name`=? WHERE `id`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, String.join(",", user.getRoles()));
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setInt(6, user.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User updated successfully with ID: " + user.getId());
            } else {
                System.out.println("No user found with ID: " + user.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM user WHERE `id`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("User deleted !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getOneById(int id) {
        String req = "SELECT * FROM user WHERE `id`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                String email = res.getString("email");
                String password = res.getString("password");
                String[] roles = res.getString("roles").split(",");
                String firstName = res.getString("first_name");
                String lastName = res.getString("last_name");
                User user = new User(id, email, password, firstName, lastName);
                user.setRoles(roles);
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<User> getAll() {
        Set<User> users = new HashSet<>();
        String req = "SELECT * FROM user";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                int id = res.getInt("id");
                String email = res.getString("email");
                String password = res.getString("password");
                String[] roles = res.getString("roles").split(",");
                String firstName = res.getString("first_name");
                String lastName = res.getString("last_name");
                User user = new User(id, email, password, firstName, lastName);
                user.setRoles(roles);
                users.add(user);
                System.out.println("User ID: " + id + ", Email: " + email + ", Roles: " + String.join(",", roles));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
