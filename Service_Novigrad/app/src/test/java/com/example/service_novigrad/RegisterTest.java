package com.example.service_novigrad;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterTest extends TestCase {

    //Validating email format for email input, returns true
    public void testValidateEmail() {
        String email = "student@gmail.com";
        Boolean output;
        Boolean expected = true;

        Register myObject = new Register();
        output = myObject.validateEmail(email);

        assertThat(output, is(expected));
    }

    //Validating name format for first name input, returns true
    public void testValidateFirstName() {
        String firstName = "studentFirst";
        Boolean output;
        Boolean expected = true;

        Register myObject = new Register();
        output = myObject.validateFirstName(firstName);

        assertThat(output, is(expected));
    }

    //Validating name format for last name input, returns true
    public void testValidateLastName() {
        String lastName = "studentLast";
        Boolean output;
        Boolean expected = true;

        Register myObject = new Register();
        output = myObject.validateLastName(lastName);

        assertThat(output, is(expected));
    }


}