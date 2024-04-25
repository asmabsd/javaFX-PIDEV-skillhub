package tests;

import entities.User;

public class Main {
    public static void main(String[] args) {
        // Create a user with roles
        User user = new User();
        user.setRoles(new String[]{"ROLE_ADMIN"});

        // Test if the user has the role ROLE_ADMIN
        if (user.getRolesArrayAsJson().equals("[\"ROLE_ADMIN\"]")) {
            System.out.println("User has ROLE_ADMIN role.");
        } else {
            System.out.println("User does not have ROLE_ADMIN role.");
        }
    }
}
