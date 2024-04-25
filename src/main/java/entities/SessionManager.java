package entities;

import services.AuthenticationService;

public class SessionManager {
    private static User currentUser;
    private static final AuthenticationService authService = new AuthenticationService();

    // Set the current user after successful login
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Get the current user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Clear the session after logout
    public static void clearSession() {
        currentUser = null;
    }

    // Check if a user is authenticated
    public static boolean isAuthenticated() {
        return currentUser != null;
    }

    // Authenticate a user based on email and password
    public static boolean authenticate(String email, String password) {
        User user = authService.authenticateUser(email, password);
        if (user != null) {
            setCurrentUser(user);
            return true;
        } else {
            return false;
        }
    }

}
