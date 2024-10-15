/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.team.pada.unibook.data;

/**
 * Represents the assignment of a professor to a course.
 * This class contains information about the assignment, including the professor's ID and the course ID.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class CourseAssignment {
    // Unique identifier for the professor
    private int professorID;
    
    // Unique identifier for the course
    private int courseID;

    /**
     * Constructor to initialize the CourseAssignment object with the specified professorID and courseID.
     * 
     * @param professorID the unique identifier of the professor
     * @param courseID the unique identifier of the course
     */
    public CourseAssignment(int professorID, int courseID) {
        this.professorID = professorID;
        this.courseID = courseID;
    }

    /**
     * Gets the unique identifier of the professor.
     * 
     * @return the professorID
     */
    public int getProfessorID() {
        return professorID;
    }

    /**
     * Sets the unique identifier of the professor.
     * 
     * @param professorID the new professorID
     */
    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }

    /**
     * Gets the unique identifier of the course.
     * 
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * Sets the unique identifier of the course.
     * 
     * @param courseID the new courseID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
