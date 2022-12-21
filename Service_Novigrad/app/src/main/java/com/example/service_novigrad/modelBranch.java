package com.example.service_novigrad;

public class modelBranch {

    String address, phoneNumber,name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public modelBranch() {
    }

    public modelBranch(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
