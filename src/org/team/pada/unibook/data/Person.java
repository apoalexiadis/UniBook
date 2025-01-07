/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.team.pada.unibook.data;

/**
 * Represents a person with a first name, last name, phone number, and email address.
 * This class serves as a base class for other entities such as students or professors.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class Person {
    // First name of the person
    protected String firstName;
    
    // Last name of the person
    protected String lastName;
    
    // Phone number of the person
    protected String phone;
    
    // Email address of the person
    protected String email;

    /**
     * Constructor to initialize the Person object with the specified firstName, lastName, phone, and email.
     * 
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param phone the phone number of the person
     * @param email the email address of the person
     */
    public Person(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the first name of the person.
     * 
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     * 
     * @param firstName the new first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     * 
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     * 
     * @param lastName the new last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the phone number of the person.
     * 
     * @return the phone number of the person
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the person.
     * 
     * @param phone the new phone number of the person
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email address of the person.
     * 
     * @return the email address of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person.
     * 
     * @param email the new email address of the person
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Person object.
     * The string consists of the first name, last name, phone number, and email address.
     * 
     * @return a string representation of the Person object
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + phone + " - " + email;
    }
}
