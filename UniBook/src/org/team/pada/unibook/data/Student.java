/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.team.pada.unibook.data;

/**
 * Represents a student, extending the Person class.
 * This class contains information about the student, including their ID and current semester.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class Student extends Person {
    // Unique identifier for the student
    private int id;
    
    // Current semester of the student
    private int semester;

    /**
     * Constructor to initialize the Student object with the specified id, semester, firstName, lastName, phone, and email.
     * 
     * @param id the unique identifier of the student
     * @param semester the current semester of the student
     * @param firstName the first name of the student
     * @param lastName the last name of the student
     * @param phone the phone number of the student
     * @param email the email address of the student
     */
    public Student(int id, int semester, String firstName, String lastName, String phone, String email) {
        super(firstName, lastName, phone, email);
        this.id = id;
        this.semester = semester;
    }

    /**
     * Gets the unique identifier of the student.
     * 
     * @return the id of the student
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the student.
     * 
     * @param id the new id of the student
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the current semester of the student.
     * 
     * @return the semester of the student
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets the current semester of the student.
     * 
     * @param semester the new semester of the student
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Returns a string representation of the Student object.
     * The string consists of the id, inherited person details, and current semester.
     * 
     * @return a string representation of the Student object
     */
    @Override
    public String toString() {
        return id + " - " + super.toString() + " - " + semester;
    }
}
