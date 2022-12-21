package com.example.service_novigrad;

public class Employee extends Account{


    String firstName, lastName, email, role, username, password;

    //Create an employee from the register page
    public Employee(String firstName, String lastName, String email, String role, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    //Create an employee from the login page
    public Employee(String username, String password){
        this.username = username;
        this.password = password;
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
