package com.example.service_novigrad;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HealthCardTest extends TestCase {

    //Validating name format for first name input, returns true
    public void testValidateFirstName() {
        String firstName = "customerFirst";
        Boolean output;
        Boolean expected = true;

        HealthCard myObject = new HealthCard();
        output = myObject.validateFirstName(firstName);

        assertThat(output, is(expected));
    }

    //Validating name format for last name input, returns true
    public void testValidateLastName() {
        String lastName = "customerLast";
        Boolean output;
        Boolean expected = true;

        HealthCard myObject = new HealthCard();
        output = myObject.validateLastName(lastName);

        assertThat(output, is(expected));
    }

    //Validating birthday format length for birthday input, returns true
    public void testValidateBirthday() {
        String birthday = "20010823";
        Boolean output;
        Boolean expected = true;

        HealthCard myObject = new HealthCard();
        output = myObject.validateBirthday(birthday);

        assertThat(output, is(expected));
    }
}