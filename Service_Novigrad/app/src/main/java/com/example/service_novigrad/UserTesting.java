package com.example.service_novigrad;



public class UserTesting {


    public static boolean testingAdmin(){
        Administrator admin = new Administrator("firstname","lastname","administrator@admin.com");
        return admin.getPassword().equals("admin") && admin.getUsername().equals("admin") && admin.getEmail().equals("administrator@admin.com") && admin.getFirstName().equals("firstname");
    }

    public static boolean testingCustomer(){
        Customer customer = new Customer("customer","customer","customer@customer.com","Customer","customer","password");
        return customer.getPassword().equals("password") && customer.getUsername().equals("customer") && customer.getEmail().equals("customer@customer.com") && customer.getFirstName().equals("customer") && customer.getRole().equals("Customer");


    }



    public static boolean testingEmployee(){
        Account employee = new Employee("a","a","a","a","a","a");
        System.out.println(employee.getPassword());
        return (employee.getPassword().equals("a")) && (employee.getUsername().equals("a")) && (employee.getEmail().equals("a")) && (employee.getFirstName().equals("a")) && (employee.getRole().equals("a"));
    }


    public static void main (String[] args ){
        System.out.println("hello world");
        boolean employeeTest = testingEmployee();
        boolean customerTest = testingCustomer();
        boolean adminTest = testingAdmin();
        System.out.println("Admin testing worked: "+adminTest);
        System.out.println("Employee testing worked: "+employeeTest);
        System.out.println("Customer testing worked: "+customerTest);

    }



}
