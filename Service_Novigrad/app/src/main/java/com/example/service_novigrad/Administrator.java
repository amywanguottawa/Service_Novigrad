package com.example.service_novigrad;

public class Administrator extends Account {

    String firstName, lastName, email, role;
    static String username = "admin";
    static String password = "admin";

    //Create an employee from the register page
    public Administrator(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = "Administrator";
        this.username = "admin";
        this.password = "admin";
    }

    //Create an employee from the login page
    public Administrator(String firstname, String lastname, String username, String password){
        this.username = "admin";
        this.password = "admin";
    }
    // Getter for the first name of the person linked to the account
    public String getFirstName(){
        return firstName;
    }

    // Getter for the last name of the person linked to the account
    public String getLastName(){
        return lastName;
    }

    // Getter for the email address linked to the account
    public String getEmail(){
        return email;
    }

    // Getter for the role linked to the account
    public String getRole(){
        return role;
    }

    // Getter for the username linked to the account
    public String getUsername(){
        return username;
    }

    // Getter for the password linked to the account
    public String getPassword(){
        return password;
    }







}
