    package services;
    import com.google.gson.Gson;
    import org.mindrot.jbcrypt.BCrypt;
import entities.SessionManager;
    import entities.User;
    import utils.MyDatabase;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

    public class ServiceUser implements IService<User> {
        static Connection cnx = MyDatabase.getInstance().getCnx();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static List<User> getAllUsers() {
            List<User> userList = new ArrayList<>();
            String query = "SELECT * FROM user"; // Replace 'users' with your actual table name

            try (Connection connection = MyDatabase.getInstance().getCnx();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String firstname= resultSet.getString("first_name");
                    String lastname= resultSet.getString("last_name");

                    User user = new User(id, email,firstname,lastname);
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception
            }
            return userList;
        }
//        //////////////////////////////////////////////////////////////////////
        public void addFreelancer(User freelancer) {
            // Ensure freelancer is not null
            if (freelancer == null) {
                System.out.println("Freelancer data is null.");
                return;
            }

            // Validate user inputs
            if (!validateUserInputs(freelancer)) {
                System.out.println("Invalid freelancer data.");
                return;
            }

            // Add the freelancer to the database
            String insertQuery = "INSERT INTO user (email, password, first_name, last_name, phone, job_title,roles) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement ps = cnx.prepareStatement(insertQuery)) {

                ps.setString(1, freelancer.getEmail());
                ps.setString(2, hashPassword(freelancer.getPassword()));
                ps.setString(3, freelancer.getFirstName());
                ps.setString(4, freelancer.getLastName());
                ps.setString(5, freelancer.getPhoneNumber());
                ps.setString(6, freelancer.getJobTitle());
                ps.setString(7, freelancer.getRolesAsJson());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("sayye ");
                    System.out.println("Freelancer added successfully.");
                } else {
                    System.out.println("Failed to add freelancer.");
                }
            } catch (SQLException e) {
                System.out.println("Error adding freelancer: " + e.getMessage());
            }
        }
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        @Override
        public  void modifier(User user) {}
        public static void modify(User user) {
            String updateQuery = "UPDATE user SET email=?, password=?, roles=?, first_name=?, last_name=?, phone=?, company_name=?, company_description=?, industry=?, company_website=?, company_logo=? WHERE id=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(updateQuery);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRolesAsJson());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getLastName());
                ps.setString(6, user.getPhoneNumber());
                ps.setString(7, user.getCompanyName());
                ps.setString(8, user.getCompanyDescription());
                ps.setString(9, user.getIndustry());
                ps.setString(10, user.getCompanyWebsite());
                ps.setString(11, user.getCompanyLogo());
                ps.setInt(12, user.getId());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User updated successfully with ID: " + user.getId());
                    // Update user in session manager

                } else {
                    System.out.println("No user found with ID: " + user.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error updating user: " + e.getMessage());
            }
        }
////////////////////////////////FREELANCER UPDATE

        public static void FreelancerUpdate(User user) {
            String updateQuery = "UPDATE user SET email=?, password=?, first_name=?, last_name=?, phone=?, professional_overview=?, job_title=? WHERE id=?";
            try {
                PreparedStatement ps = cnx.prepareStatement(updateQuery);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getPhoneNumber());
                ps.setString(6, user.getProfessionalOverview());
                ps.setString(7, user.getJobTitle());
                ps.setInt(8, user.getId());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    user.setJobTitle("omar");
                    System.out.println(user +"hedha l user li bech yedzed ");
                    System.out.println("Freelancer updated successfully with ID: " + user.getId());
                    SessionManager.setCurrentUser(user);
                } else {
                    System.out.println("No freelancer found with ID: " + user.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error updating freelancer: " + e.getMessage());
            }
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        ///////////////////////////////////////////////////////////////////////////////////////////////
        // Method for initial user registration
        public void initialRegistration(User user) {
            if (validateUserInputs(user)) {
                String insertQuery = "INSERT INTO user (email, password, first_name, last_name, roles) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = cnx.prepareStatement(insertQuery)) {
                    ps.setString(1, user.getEmail());
                    ps.setString(2, hashPassword(user.getPassword())); // Hashing the password
                    ps.setString(3, user.getFirstName());
                    ps.setString(4, user.getLastName());
                    ps.setString(5, user.getRolesAsJson()); // Set the role

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("User registered successfully.");
                    } else {
                        System.out.println("Failed to register user.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error registering user: " + e.getMessage());
                }

            }
        }

        private boolean validateUserInputs(User user) {
            // Check if any of the fields are empty
            if (user.getEmail() == null || user.getEmail().isEmpty() ||
                    user.getPassword() == null || user.getPassword().isEmpty() ||
                    user.getFirstName() == null || user.getFirstName().isEmpty() ||
                    user.getLastName() == null || user.getLastName().isEmpty()) {
                System.out.println("All fields are mandatory.");
                return false;
            }

            // Check if the email format is valid
            if (!isValidEmail(user.getEmail())) {
                System.out.println("Invalid email format.");
                return false;
            }

            // Check if the password is at least 6 characters long
            if (user.getPassword().length() < 6) {
                System.out.println("Password should be at least 6 characters long.");
                return false;
            }

            // Check if the email is already registered
            if (getUserByEmail(user.getEmail()) != null) {
                System.out.println("User with email  " + user.getEmail() + " already exists.");
                return false;
            }

            // Additional validations can be added here if needed

            // All validations passed
            return true;
        }

        private boolean isValidEmail(String email) {
            // Implement email format validation logic here
            // This is a basic example, you can use a regex or library for more comprehensive validation
            return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        }


        private User getUserByEmail(String email) {
            String query = "SELECT * FROM user WHERE email = ?";
            try (PreparedStatement ps = cnx.prepareStatement(query)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    // Retrieve user data from the result set
                    int id = rs.getInt("id");
                    String password = rs.getString("password");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    // You can retrieve other fields as needed

                    // Create and return the User object
                    User user = new User(id, email, password, firstName, lastName);
                    // Set additional fields if needed
                    return user;
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving user: " + e.getMessage());
            }
            return null; // Return null if user not found or an error occurred
        }

        ///////////////////////////////////////////////
        public void FreelancerAdditionalInformation(User user, String profilePicture, String jobTitle, String professionalOverview, String expertise) {
            // Update database with the provided information
            String updateQuery = "UPDATE user SET profile_picture = ?, job_title = ?, professional_overview = ?, expertise = ? WHERE id = ?";
            try (PreparedStatement ps = cnx.prepareStatement(updateQuery)) {
                ps.setString(1, profilePicture);
                ps.setString(2, jobTitle);
                ps.setString(3, professionalOverview);
                ps.setString(4, expertise);
                ps.setInt(5, user.getId());


                System.out.println("SQL Query: " + ps.toString());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Freelancer additional information updated successfully.");
                } else {
                    System.out.println("Failed to update freelancer additional information.");
                }
            } catch (SQLException e) {
                System.out.println("Error updating freelancer additional information: " + e.getMessage());
            }
        }



        /////////////////////////////////////////////////////////
    public void ClientAdditionalInformation(User user, String companyName, String companyDescription, String industry, String companyWebsite, String companyLogo) {
        // Update database with the provided information
        String updateQuery = "UPDATE user SET company_name = ?, company_description = ?, industry = ?, company_website = ?, company_logo = ? WHERE id = ?";
        try (PreparedStatement ps = cnx.prepareStatement(updateQuery)) {
            ps.setString(1, companyName);
            ps.setString(2, companyDescription);
            ps.setString(3, industry);
            ps.setString(4, companyWebsite);
            ps.setString(5, companyLogo);
            ps.setInt(6, user.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client additional information updated successfully.");
            } else {
                System.out.println("Failed to update client additional information.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating client additional information: " + e.getMessage());
        }
    }

        // Method to hash the password
        private String hashPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

    //    /////////////////////////////////////    /////////////////////////////////////    /////////////////////////////////////
    public void addClient(User client) {
        String insertQuery = "INSERT INTO user (email, password, first_name, last_name, phone, company_name, company_description,roles) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement ps = cnx.prepareStatement(insertQuery)) {
            ps.setString(1, client.getEmail());
            ps.setString(2, hashPassword(client.getPassword()));
            ps.setString(3, client.getFirstName());
            ps.setString(4, client.getLastName());
            ps.setString(5, client.getPhoneNumber());
            ps.setString(6, client.getCompanyName());
            ps.setString(7, client.getCompanyDescription());
            ps.setString(8, client.getRolesAsJson());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client registered successfully.");
            } else {
                System.out.println("Failed to register client.");
            }
        } catch (SQLException e) {
            System.out.println("Error registering client: " + e.getMessage());
        }
    }



        /////////////////////////////////////
        public static boolean isEmailExists(String email) {
            String query = "SELECT COUNT(*) FROM user WHERE email = ?";
            try {
                Connection connection = MyDatabase.getInstance().getCnx();
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    resultSet.close(); // Close the ResultSet
                    statement.close(); // Close the PreparedStatement
                    // Don't close the connection here
                    return count > 0; // True if email already exists
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

    }

