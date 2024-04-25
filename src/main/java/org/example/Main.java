package org.example;

import entities.User;
import services.ServiceUser;

import java.lang.reflect.Array;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        User user = new User();
        user.setEmail("kazk@aze.com");
        user.setPassword("000000");
    user.setClientRole();

        user.setFirstName("omar");
        user.setLastName("Doe");
user.setId(84);
        // Ajout de l'utilisateur
        ServiceUser userService = new ServiceUser();
        userService.initialRegistration(user);
        System.out.println("//////////////////////////////////////////");
        userService.FreelancerAdditionalInformation(user, "profile_picture.jpg", "Software Engineer", "Professional overview", "Software Development");


    }
}