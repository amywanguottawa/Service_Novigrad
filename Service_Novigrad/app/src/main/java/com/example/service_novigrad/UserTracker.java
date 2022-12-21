package com.example.service_novigrad;

public class UserTracker {
    public static String role;
    public static String user;

    public static void setRole(String role) {
        UserTracker.role = role;
    }

    public static String getRole(){
        return role;
    }

    public static void setUser(String user) {
        UserTracker.user = user;
    }

    public static String getUser(){
        return user;
    }

}
