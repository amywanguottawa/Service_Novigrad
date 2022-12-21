package com.example.service_novigrad.data;

public class UserHelper {
    String firstName,lastName,email,userName,password,role,birthday,address,licenseType,phoneNumber;

    public UserHelper() {

    }

    public UserHelper(String firstName, String lastName, String email, String userName,String password ,String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.role = role;
        this.password = password;
    }

    public UserHelper(String firstName, String lastName, String address, String birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;

    }

    public UserHelper(String firstName, String lastName, String address, String birthday, String licenseType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.licenseType = licenseType;

    }

    public UserHelper(String address, String phoneNumber){
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address){this.address = address;}

    public String getAddress(){return address;}

    public void setBirthday(String birthday){this.birthday = birthday;}

    public String getBirthday(){return birthday;}

    public void setLicenseType(String licenseType){this.licenseType = licenseType;}

    public String getLicenseType(){return licenseType;}

    public String getPhoneNumber(){return phoneNumber;}
}
