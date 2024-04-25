package services;

import entities.SessionManager;
import entities.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
    private static final Connection cnx = MyDatabase.getInstance().getCnx();
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Method to authenticate a user
    // Method to authenticate a user
    public User authenticateUser(String email, String password) {
        try {
            // Prepare SQL query to retrieve user by email
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, email);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Check if user exists
            if (resultSet.next()) {
                // Retrieve stored password hash
                String oldHashedPassword = resultSet.getString("password");

                // Rehash the password if necessary
                String hashedPassword = rehashPassword(oldHashedPassword);
                // Verify password using BCrypt





                int id = resultSet.getInt("id");
                String roles = resultSet.getString("roles");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String profilePicture = resultSet.getString("profile_picture");
                String jobTitle = resultSet.getString("job_title");
                String professionalOverview = resultSet.getString("professional_overview");
                String expertise = resultSet.getString("expertise");
                int phone = resultSet.getInt("phone");
                String companyName = resultSet.getString("company_name");
                String companyDescription = resultSet.getString("company_description");
                String industry = resultSet.getString("industry");
                String companyWebsite = resultSet.getString("company_website");
                String companyLogo = resultSet.getString("company_logo");


                if (BCrypt.checkpw(password, hashedPassword)) {
                    // Passwords match, return the authenticated user
                    return new User(
                            id,
                            email,
                            roles,
                            hashedPassword,
                            firstName,
                            lastName,
                            profilePicture,
                            jobTitle,
                            professionalOverview,
                            expertise,
                            phone,
                            companyName,
                            companyDescription,
                            industry,
                            companyWebsite,
                            companyLogo
                    );
                }
                }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        // Authentication failed
        return null;
    }


    // Method to logout user (clear session)
    public void logout() {
        SessionManager.clearSession();


    }







    public String rehashPassword(String oldHashedPassword) {
        // Check the version of the old hashed password
        if (oldHashedPassword.startsWith("$2y$")) {
            // Replace "$2y$" with "$2a$"
            String newHashedPassword = "$2a$" + oldHashedPassword.substring(4);
            return newHashedPassword;
        } else {
            // The old hashed password is already compatible with BCrypt.checkpw
            return oldHashedPassword;
        }
    }


}

