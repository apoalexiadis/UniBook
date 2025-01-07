/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.team.pada.unibook.data;

/**
 * Represents the enrollment of a student in a course.
 * This class contains information about the enrollment, including the student's ID, course ID, and grade.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class Enrollment {
    // Unique identifier for the student
    private int studentID;
    
    // Unique identifier for the course
    private int courseID;
    
    // Grade of the student in the course
    private double grade;

    /**
     * Constructor to initialize the Enrollment object with the specified studentID, courseID, and grade.
     * 
     * @param studentID the unique identifier of the student
     * @param courseID the unique identifier of the course
     * @param grade the grade of the student in the course
     */
    public Enrollment(int studentID, int courseID, double grade) {        
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    /**
     * Gets the unique identifier of the student.
     * 
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Sets the unique identifier of the student.
     * 
     * @param studentID the new studentID
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    /**
     * Gets the grade of the student in the course.
     * 
     * @return the grade
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Sets the grade of the student in the course.
     * 
     * @param grade the new grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
}
