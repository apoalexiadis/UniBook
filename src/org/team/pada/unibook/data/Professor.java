/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.team.pada.unibook.data;

/**
 * Represents a professor, extending the Person class.
 * This class contains information about the professor, including their ID and specialty.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class Professor extends Person {
    // Unique identifier for the professor
    private int id;
    
    // Specialty of the professor
    private String specialty;

    /**
     * Constructor to initialize the Professor object with the specified id, specialty, firstName, lastName, phone, and email.
     * 
     * @param id the unique identifier of the professor
     * @param specialty the specialty of the professor
     * @param firstName the first name of the professor
     * @param lastName the last name of the professor
     * @param phone the phone number of the professor
     * @param email the email address of the professor
     */
    public Professor(int id, String specialty, String firstName, String lastName, String phone, String email) {
        super(firstName, lastName, phone, email);
        this.id = id;
        this.specialty = specialty;
    }

    /**
     * Gets the unique identifier of the professor.
     * 
     * @return the id of the professor
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the professor.
     * 
     * @param id the new id of the professor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the specialty of the professor.
     * 
     * @return the specialty of the professor
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Sets the specialty of the professor.
     * 
     * @param specialty the new specialty of the professor
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Returns a string representation of the Professor object.
     * The string consists of the id, inherited person details, and specialty.
     * 
     * @return a string representation of the Professor object
     */
    @Override
    public String toString() {
        return id + " - " + super.toString() + " - " + specialty;
    }
}
