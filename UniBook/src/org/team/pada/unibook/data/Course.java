/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.team.pada.unibook.data;

/**
 * Represents a course in the unibook app.
 * This class contains information about a course such as its ID, title, and semester.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class Course {
    // Unique identifier for the course
    private int id;
    
    // Title of the course
    private String title;
    
    // Semester in which the course is offered
    private int semester;

    /**
     * Constructor to initialize the Course object with the specified id, title, and semester.
     * 
     * @param id the unique identifier of the course
     * @param title the title of the course
     * @param semester the semester in which the course is offered
     */
    public Course(int id, String title, int semester) {
        this.id = id;
        this.title = title;
        this.semester = semester;
    }

    /**
     * Gets the unique identifier of the course.
     * 
     * @return the id of the course
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the course.
     * 
     * @param id the new id of the course
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the course.
     * 
     * @return the title of the course
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     * 
     * @param title the new title of the course
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the semester in which the course is offered.
     * 
     * @return the semester of the course
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets the semester in which the course is offered.
     * 
     * @param semester the new semester of the course
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Returns a string representation of the Course object.
     * The string consists of the id, title, and semester separated by hyphens.
     * 
     * @return a string representation of the Course object
     */
    @Override
    public String toString() {
        return id + " - " + title + " - " + semester;
    }
}
