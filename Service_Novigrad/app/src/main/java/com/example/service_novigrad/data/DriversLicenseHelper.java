package com.example.service_novigrad.data;

public class DriversLicenseHelper {

    public static String firstName;
    public static String lastName;
    public static String birthday;
    public static String address;
    public static String residency;
    public static String status;
    public static String yourself;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResidency() {
        return residency;
    }

    public void setResidency(String residency) {
        this.residency = residency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setYourself(String yourself){
        this.yourself = yourself;
    }
    public String getYourself(){
        return yourself;
    }

    public DriversLicenseHelper(String firstName, String lastName, String birthday, String address, String residency, String status, String yourself) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.residency = residency;
        this.status = status;
        this.yourself = yourself;
    }
}
