package org.team.pada.unibook.service;

import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
import java.util.ArrayList; 
import java.util.Scanner; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import org.team.pada.unibook.data.Course; 
import org.team.pada.unibook.data.CourseAssignment; 
import org.team.pada.unibook.data.Enrollment; 
import org.team.pada.unibook.data.Professor; 
import org.team.pada.unibook.data.Student; 

/**
 * Services class handles all operations related to students, professors, courses, enrollments, and course assignments.
 * This includes adding, updating, deleting, and viewing these entities as well as loading and saving data from/to CSV files and validations.
 * 
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 * 
 */
public class Services {
    private static ArrayList<Student> students = new ArrayList<>(); // List to store Student objects
    private static ArrayList<Professor> professors = new ArrayList<>(); // List to store Professor objects
    private static ArrayList<Course> courses = new ArrayList<>(); // List to store Course objects
    private static ArrayList<Enrollment> enrollments = new ArrayList(); // List to store Enrollment objects
    private static ArrayList<CourseAssignment> courseAssignments = new ArrayList(); // List to store CourseAssignment objects

    /**
     * Constructor that loads data from CSV files upon initialization.
     */
    public Services() {
        loadData(); // Load data from CSV files when the Services object is created
    }
    
    /**
     * Displays a menu for interacting with the system and processes user input.
     * 
     * @param scanner Scanner object for reading user input
     */
    public void loadMenu(Scanner scanner){
        
        while (true) { // Infinite loop to display the menu until the user chooses to exit
            System.out.println("1. Add Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. Delete Student");
            System.out.println("4. View all Students");
            System.out.println("5. Add Professor");
            System.out.println("6. Update Professor Information");
            System.out.println("7. Delete Professor");
            System.out.println("8. View all Professors");
            System.out.println("9. Add Course");
            System.out.println("10. Update Course Information");
            System.out.println("11. Delete Course");
            System.out.println("12. View all Courses");
            System.out.println("13. Assign Course to Professor");
            System.out.println("14. Enroll Student in Course");
            System.out.println("15. Record Student Grades");
            System.out.println("16. Display Grade Statistics");
            System.out.println("17. Exit");
                    
            System.out.print("Give your choice: ");
            int choice = Integer.parseInt(validateChoice(scanner.next(),scanner)); // Read and validate the user's choice
            scanner.nextLine(); // Consume newline

            switch (choice) { // Switch statement to handle each menu option
                case 1:
                    addStudent(scanner); // Add a new student
                    break;
                case 2:
                    updateStudent(scanner); // Update existing student information
                    break;
                case 3:
                    deleteStudent(scanner); // Delete a student
                    break;
                case 4:
                    viewAllStudents(); // View all students
                    break;
                case 5:
                    addProfessor(scanner); // Add a new professor
                    break;
                case 6:
                    updateProfessor(scanner); // Update existing professor information
                    break;
                case 7:
                    deleteProfessor(scanner); // Delete a professor
                    break;
                case 8:
                    viewAllProfessors(); // View all professors
                    break;
                case 9:
                    addCourse(scanner); // Add a new course
                    break;
                case 10:
                    updateCourse(scanner); // Update existing course information
                    break;
                case 11:
                    deleteCourse(scanner); // Delete a course
                    break;
                case 12:
                    viewAllCourses(); // View all courses
                    break;
                case 13:
                    assignCourseToProfessor(scanner); // Assign a course to a professor
                    break;
                case 14:
                    enrollStudentToCourse(scanner); // Enroll a student in a course
                    break;
                case 15:
                    enterGradeForStudent(scanner); // Record a grade for a student
                    break;
                case 16:
                    viewStatistics(); // Display grade statistics
                    break;
                case 17:
                    saveChanges(); // Save changes to CSV files
                    System.out.println("Exiting the program");
                    scanner.close(); // Close the scanner
                    return; // Exit the menu loop
                default:
                    System.out.println("Invalid choice, please try again."); // Handle invalid menu choice
            }
        }
       
    }
       
    /**
     * Registers a new student by prompting the user for details such as semester, first name, last name, phone, and email.
     * Validates the input and adds the student to the system if all details are valid.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void addStudent(Scanner scanner) {
        System.out.println("Student Registration:");
        int studentID = getNextStudentId(); // Get the next available student ID
        System.out.print("Semester: ");
        int semester = Integer.parseInt(validateNumber(scanner.next(), scanner)); // Read and validate semester
        scanner.nextLine(); // Consume newline
        System.out.print("First Name: ");
        String firstName = validateName(scanner.nextLine(), scanner); // Read and validate first name
        System.out.print("Last Name: ");
        String lastName = validateName(scanner.nextLine(), scanner); // Read and validate last name
        System.out.print("Phone: ");
        String phone = validatePhone(scanner.nextLine(), scanner); // Read and validate phone number
        System.out.print("E-mail: ");
        String email = validateEmail(scanner.nextLine(), scanner); // Read and validate email

        Student student = new Student(studentID, semester, firstName, lastName, phone, email); // Create a new Student object
        students.add(student); // Add the student to the list
        System.out.println("The student was added successfully.");
    }

    /**
     * Updates the information of an existing student based on their ID.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void updateStudent(Scanner scanner) {
        System.out.println("Update Student Information:");
        System.out.print("Student ID: ");
        int studentID = scanner.nextInt(); // Read student ID
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(studentID); // Find the student by ID
        if (student != null) {
            System.out.print("Enter new first name (leave blank to keep current): ");
            String newFirstName = validateName(scanner.nextLine(), scanner); // Read and validate new first name
            if (!newFirstName.isBlank()) {
                student.setFirstName(newFirstName); // Update first name if not blank
            }
            System.out.print("Enter new last name (leave blank to keep current): ");
            String newLastName = validateName(scanner.nextLine(), scanner); // Read and validate new last name
            if (!newLastName.isBlank()) {
                student.setLastName(newLastName); // Update last name if not blank
            }
            System.out.print("Enter new email (leave blank to keep current): ");
            String newEmail = validateEmail(scanner.nextLine(), scanner); // Read and validate new email
            if (!newEmail.isBlank()) {
                student.setEmail(newEmail); // Update email if not blank
            }
            System.out.print("Enter new phone (leave blank to keep current): ");
            String newPhone = validatePhone(scanner.nextLine(), scanner); // Read and validate new phone number
            if (!newPhone.isBlank()) {
                student.setPhone(newPhone); // Update phone number if not blank
            }
            System.out.print("Enter new semester (leave blank to keep current): ");
            String newSemesterString = validateNumber(scanner.nextLine(), scanner); // Read and validate new semester
            if (!newSemesterString.isBlank()) {
                int newSemester = Integer.parseInt(newSemesterString);
                student.setSemester(newSemester); // Update semester if not blank
            }
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found."); // Handle student not found
        }
    }

    /**
     * Deletes an existing student based on their ID.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void deleteStudent(Scanner scanner) {
        System.out.println("Delete Student:");
        System.out.print("Student ID: ");
        int studentID = scanner.nextInt(); // Read student ID
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(studentID); // Find the student by ID
        if (student != null) {
            deleteEnrollmentsByStudentId(student.getId()); // Delete all enrollments of the student
            students.remove(student); // Remove the student from the list
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found."); // Handle student not found
        }
    }

    /**
     * Displays all students in the system.
     */
    private void viewAllStudents() {
        System.out.println("All Students");
        for (Student student : students) { // Loop through each student
            System.out.println(student); // Print student details
        }
    }

    /**
     * Finds a student by their ID.
     * 
     * @param id the unique identifier of the student
     * @return the Student object if found, otherwise null
     */
    private Student findStudentById(int id) {
        for (Student student : students) { // Loop through each student
            if (student.getId() == id) { // Check if IDs match
                return student; // Return student if found
            }
        }
        return null; // Return null if not found
    }

    /**
     * Gets the next available student ID.
     * 
     * @return the next student ID
     */
    private int getNextStudentId() {
        int id = 0;
        for (Student student : students) { // Loop through each student
            id = student.getId(); // Get the ID of the last student
        }
        return id + 1; // Increment and return the next available ID
    }

    /**
     * Registers a new professor by prompting the user for details such as first name, last name, email, phone, and specialty.
     * Validates the input and adds the professor to the system if all details are valid.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void addProfessor(Scanner scanner) {
        System.out.println("Professor Registration:");
        int professorID = getNextProfessorId(); // Get the next available professor ID
        System.out.print("First Name: ");
        String firstName = validateName(scanner.nextLine(),scanner); // Read and validate first name
        System.out.print("Last Name: ");
        String lastName = validateName(scanner.nextLine(),scanner); // Read and validate last name
        System.out.print("Email: ");
        String email = validateEmail(scanner.nextLine(), scanner); // Read and validate email
        System.out.print("Phone: ");
        String phone = validatePhone(scanner.nextLine(),scanner); // Read and validate phone number
        System.out.print("Specialty: ");
        String specialty = scanner.nextLine(); // Read specialty

        Professor professor = new Professor(professorID, specialty, firstName, lastName, phone, email); // Create a new Professor object
        professors.add(professor); // Add the professor to the list
        System.out.println("Professor added successfully.");

    }

    /**
     * Updates the information of an existing professor based on their ID.
     * Prompts the user for the new values of first name, last name, email, phone, and specialty.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void updateProfessor(Scanner scanner) {
        System.out.println("Update professor information:");
        System.out.print("Professor ID: ");
        int professorID = scanner.nextInt(); // Read professor ID
        scanner.nextLine(); // Consume newline

        
        Professor professor = findProfessorById(professorID); // Find the professor by ID
        if (professor != null) {
            System.out.print("Enter new first name (leave blank to keep current): ");
            String newFirstName = validateName(scanner.nextLine(),scanner); // Read and validate new first name
            if (!newFirstName.isBlank()) {
                professor.setFirstName(newFirstName); // Update first name if not blank
            }
            System.out.print("Enter new last name (leave blank to keep current): ");
            String newLastName = validateName(scanner.nextLine(),scanner); // Read and validate new last name
            if (!newLastName.isBlank()) {
                professor.setLastName(newLastName); // Update last name if not blank
            }
            System.out.print("Enter new email (leave blank to keep current): ");
            String newEmail = validateEmail(scanner.nextLine(), scanner); // Read and validate new email
            if (!newEmail.isBlank()) {
                professor.setEmail(newEmail); // Update email if not blank
            }
            System.out.print("Enter new phone (leave blank to keep current): ");
            String newPhone = validatePhone(scanner.nextLine(),scanner); // Read and validate new phone number
            if (!newPhone.isBlank()) {
                professor.setPhone(newPhone); // Update phone number if not blank
            }
            System.out.print("Enter new specialty (leave blank to keep current): ");
            String newSpecialty = scanner.nextLine(); // Read new specialty
            if (!newSpecialty.isBlank()) {
                professor.setSpecialty(newSpecialty); // Update specialty if not blank
            }
            System.out.println("Professor updated successfully.");
            } else {
                System.out.println("Professor not found."); // Handle professor not found
            }
            
    }

    /**
     * Deletes an existing professor based on their ID.
     * Removes any course assignments associated with the professor.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void deleteProfessor(Scanner scanner) {
        System.out.println("Delete professor:");
        System.out.print("Professor ID: ");
        int professorID = scanner.nextInt(); // Read professor ID
        scanner.nextLine(); // Consume newline
        
        Professor professor = findProfessorById(professorID); // Find the professor by ID
        if (professor != null) {
            deleteAssignmentsByProfessorId(professor.getId()); // Delete all assignments of the professor
            professors.remove(professor); // Remove the professor from the list
            System.out.println("Professor deleted successfully.");
        } else {
            System.out.println("Professor not found."); // Handle professor not found
        }           
    }
    
    /**
     * Finds a professor by their ID.
     * 
     * @param id the unique identifier of the professor
     * @return the Professor object if found, otherwise null
     */
    private Professor findProfessorById(int id) {
        for (Professor professor : professors) { // Loop through each professor
            if (professor.getId() == id) { // Check if IDs match
                return professor; // Return professor if found
            }
        }
        return null; // Return null if not found
    }
    
    /**
     * Gets the next available professor ID.
     * 
     * @return the next professor ID
     */
    private int getNextProfessorId(){
        int id = 0;
        
        for (Professor professor : professors) { // Loop through each professor
            id = professor.getId(); // Get the ID of the last professor
        }
        
        return id + 1; // Increment and return the next available ID
    }
    
    /**
     * Displays all professors in the system.
     */
    private void viewAllProfessors(){
        System.out.println("All Professors");
        for (Professor professor : professors) { // Loop through each professor
            System.out.println(professor); // Print professor details
        }
    }

    /**
     * Adds a new course by prompting the user for details such as title and semester.
     * Validates the input and adds the course to the system if all details are valid.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void addCourse(Scanner scanner) {
        System.out.println("Enter course details:");
        int courseID = getNextCourseId(); // Get the next available course ID
        System.out.print("Title: ");
        String title = validateName(scanner.nextLine(),scanner); // Read and validate course title
        System.out.print("Semester: ");
        int semester = Integer.parseInt(validateNumber(scanner.next(),scanner)); // Read and validate semester
        scanner.nextLine(); // Consume newline

        Course course = new Course(courseID, title, semester); // Create a new Course object
        courses.add(course); // Add the course to the list
        System.out.println("Course added successfully.");

    }  
    
    /**
     * Updates the information of an existing course based on its ID.
     * Prompts the user for the new values of title and semester.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void updateCourse(Scanner scanner) {
        System.out.println("Edit course details:");
        System.out.print("Course ID: ");
        int courseID = scanner.nextInt(); // Read course ID
        scanner.nextLine(); // Consume newline

        Course course = findCourseById(courseID); // Find the course by ID
        if (course != null) {
            System.out.print("Enter new title (leave blank to keep current): ");
            String newTitle = scanner.nextLine(); // Read new title
            if (!newTitle.isBlank()) {
                course.setTitle(newTitle); // Update title if not blank
            }
            System.out.print("Enter new semester (leave blank to keep current): ");
            String newSemesterString = validateNumber(scanner.nextLine(),scanner); // Read and validate new semester
            if (!newSemesterString.isBlank()) {
                int newSemester = Integer.parseInt(newSemesterString);
                course.setSemester(newSemester); // Update semester if not blank
            }
            System.out.println("Course updated successfully.");
            } else {
                System.out.println("Course not found."); // Handle course not found
            } 
    }

    /**
     * Deletes an existing course based on its ID.
     * Removes any enrollments and assignments associated with the course.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void deleteCourse(Scanner scanner) {
        System.out.println("Delete course:");
        System.out.print("Course ID: ");
        int courseID = scanner.nextInt(); // Read course ID
        scanner.nextLine(); // Consume newline

        Course course = findCourseById(courseID); // Find the course by ID
        
        if (course != null) {
            deleteEnrollmentsAndAssignmentsByCourseId(course.getId()); // Delete all enrollments and assignments of the course
            courses.remove(course); // Remove the course from the list
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course not found."); // Handle course not found
        }      
    }
    
    /**
     * Displays all courses in the system.
     */
    private void viewAllCourses(){
        System.out.println("All Courses");
        for (Course course : courses) { // Loop through each course
            System.out.println(course); // Print course details
        }
    }
    
    /**
     * Finds a course by its ID.
     * 
     * @param id the unique identifier of the course
     * @return the Course object if found, otherwise null
     */
    private Course findCourseById(int id) {
        for (Course course : courses) { // Loop through each course
            if (course.getId() == id) { // Check if IDs match
                return course; // Return course if found
            }
        }
        return null; // Return null if not found
    }
    
    /**
     * Gets the next available course ID.
     * 
     * @return the next course ID
     */
    private int getNextCourseId(){
        int id = 0;
        
        for (Course course : courses) { // Loop through each course
            id = course.getId(); // Get the ID of the last course
        }
        
        return id + 1; // Increment and return the next available ID
    }
    
    /**
     * Assigns a course to a professor by prompting the user for the professor's ID and the course's ID.
     * Validates the input and creates a new course assignment if both the professor and the course exist.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void assignCourseToProfessor(Scanner scanner){
        System.out.print("Enter professor ID: ");
        int professorId = scanner.nextInt(); // Read professor ID
        scanner.nextLine(); // Consume the newline character
        
        Professor professor = findProfessorById(professorId);       
        if(professor == null){
            System.out.println("Professor not found");
            return;
        }
        
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt(); // Read course ID
        scanner.nextLine(); // Consume the newline character
        
        Course course = findCourseById(courseId);
        if(course == null){
            System.out.println("Course not found");
            return;
        }
        
        CourseAssignment checkCourseAssignment = findAssignmentById(course.getId(), professor.getId());      
        if(checkCourseAssignment == null){
            CourseAssignment newCourseAssignment = new CourseAssignment(professor.getId(),course.getId());
            courseAssignments.add(newCourseAssignment); // Add the course assignment to the list
            System.out.println("Course assigned to professor successfully.");
        } else{
            System.out.println("The course is assigned already to this professor.");
        }
               
    }
    
    /**
     * Finds a course assignment by course ID and professor ID.
     * 
     * @param courseId the unique identifier of the course
     * @param professorId the unique identifier of the professor
     * @return the CourseAssignment object if found, otherwise null
     */
    private CourseAssignment findAssignmentById(int courseId, int professorId) {
        for (CourseAssignment courseAssignment : courseAssignments) { // Loop through each course assignment
            if (courseAssignment.getCourseID() == courseId && courseAssignment.getProfessorID() == professorId) { // Check if IDs match
                return courseAssignment; // Return course assignment if found
            }
        }
        return null; // Return null if not found
    }
    
    /**
     * Enrolls a student in a course by prompting the user for the student's ID and the course's ID.
     * Validates the input and creates a new enrollment if both the student and the course exist and the student is not already enrolled in the course.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void enrollStudentToCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt(); // Read student ID
        
        Student student = findStudentById(studentId);
        if(student == null){
            System.out.println("Student not found");
            return;
        }
        
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt(); // Read course ID
        
        Course course = findCourseById(courseId);
        if(course == null){
            System.out.println("Course not found");
            return;
        }
        
        Enrollment enrollment = findEnrollmentById(course.getId(), student.getId());
        
        if(enrollment == null){
            enrollments.add(new Enrollment(studentId, courseId, -1.0)); // -1.0 indicates no grade yet
            System.out.println("Student enrolled to course successfully.");
        } else {
            System.out.println("Student is already enrolled to this course");
        }
        
        
    }
    
    /**
     * Finds an enrollment by course ID and student ID.
     * 
     * @param courseId the unique identifier of the course
     * @param studentId the unique identifier of the student
     * @return the Enrollment object if found, otherwise null
     */
    private Enrollment findEnrollmentById(int courseId, int studentId) {
        for (Enrollment enrollment : enrollments) { // Loop through each enrollment
            if (enrollment.getCourseID() == courseId && enrollment.getStudentID() == studentId) { // Check if IDs match
                return enrollment; // Return enrollment if found
            }
        }
        return null; // Return null if not found
    }
    
    /**
     * Records a grade for a student in a course by prompting the user for the student's ID, the course's ID, and the grade.
     * Validates the input and updates the enrollment with the new grade if both the student and the course exist and the student is enrolled in the course.
     * 
     * @param scanner Scanner object for reading user input
     */
    private void enterGradeForStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();       
        Student student = findStudentById(studentId);
        if(student == null){
            System.out.println("Student not found");
            return;
        }
        
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        Course course = findCourseById(courseId);
        if(course == null){
            System.out.println("Course not found");
            return;
        }
        
        Enrollment enrollment = findEnrollmentById(courseId, studentId);
        
        if(enrollment != null)
        {
            System.out.print("Enter grade: ");
            double grade = validateGrade(scanner.nextDouble(),scanner);
            enrollment.setGrade(grade); // Update grade
            System.out.println("Grade entered successfully.");
            
        } else{
            System.out.println("Enrollment not found");
        }
           
    }
   
    /**
     * Deletes all enrollments and course assignments associated with a specific course ID.
     * 
     * @param courseId the unique identifier of the course
     */
    private void deleteEnrollmentsAndAssignmentsByCourseId(int courseId){
        int counterEnrollments = 0;
        int counterAssignments = 0;
        ArrayList<Enrollment> enrollmentsToRemove = new ArrayList<>();
        ArrayList<CourseAssignment> courseAssignmentsToRemove = new ArrayList<>();
        for (Enrollment enrollment : enrollments){
            if(enrollment.getCourseID() == courseId){
                counterEnrollments += 1;
                enrollmentsToRemove.add(enrollment);
             
            }
        } 
        
        enrollments.removeAll(enrollmentsToRemove);
        
        for (CourseAssignment courseAssignment : courseAssignments){
            if(courseAssignment.getCourseID() == courseId){
                counterAssignments +=1;
                courseAssignmentsToRemove.add(courseAssignment);
            }
        }
        
        courseAssignments.removeAll(courseAssignmentsToRemove);
        
        System.out.println(counterEnrollments + " of Enrollments were deleted.");
        System.out.println(counterAssignments + " of Course assignments were deleted.");
    }
    
    /**
     * Deletes all enrollments associated with a specific student ID.
     * 
     * @param studentId the unique identifier of the student
     */
    private void deleteEnrollmentsByStudentId(int studentId){
        int counterEnrollments = 0;
        ArrayList<Enrollment> enrollmentsToRemove = new ArrayList<>();
        for (Enrollment enrollment : enrollments){
            if(enrollment.getStudentID() == studentId){
                counterEnrollments += 1;
                enrollmentsToRemove.add(enrollment);
                              
            }
        }          
        enrollments.removeAll(enrollmentsToRemove);
        System.out.println(counterEnrollments + " of Enrollments were deleted.");
    }
    
    /**
     * Deletes all course assignments associated with a specific professor ID.
     * 
     * @param professorId the unique identifier of the professor
     */
    private void deleteAssignmentsByProfessorId(int professorId){
        int counterAssignments = 0;
        ArrayList<CourseAssignment> courseAssignmentsToRemove = new ArrayList<>();        
        for (CourseAssignment courseAssignment : courseAssignments){
            if(courseAssignment.getProfessorID()== professorId){
                counterAssignments +=1;
                courseAssignmentsToRemove.add(courseAssignment);
            }
        }
        
        courseAssignments.removeAll(courseAssignmentsToRemove);
        System.out.println(counterAssignments + " of Course assignments were deleted.");
    }
    
    /**
     * Displays statistical information, including the average grade per student and per course.
     */
    private void viewStatistics() {
        System.out.println("Statistics:");

        // Average grade per student
        for (Student student : students) {
            double averageGrade = enrollments.stream()
                    .filter(e -> e.getStudentID() == student.getId() && e.getGrade() >= 0)
                    .mapToDouble(e -> e.getGrade())
                    .average()
                    .orElse(Double.NaN);
            System.out.println("Student " + student.getFirstName() + " " + student.getLastName() + " (ID: " + student.getId() + ") - Average Grade: " + (Double.isNaN(averageGrade) ? "N/A" : averageGrade));
        }

        // Average grade per course
        for (Course course : courses) {
            double averageGrade = enrollments.stream()
                    .filter(e -> e.getCourseID() == course.getId() && e.getGrade() >= 0)
                    .mapToDouble(e -> e.getGrade())
                    .average()
                    .orElse(Double.NaN);
            System.out.println("Course " + course.getTitle() + " (Course ID: " + course.getId() + ") - Average Grade: " + (Double.isNaN(averageGrade) ? "N/A" : averageGrade));
        }
    }
    

    /**
     * Loads course data from a CSV file and populates the courses list.
     * Each line in the CSV file should contain the course ID, title, and semester separated by semicolons.
     */
    private void loadCourses () {     
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Courses.csv"; // File name for courses CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
              
        String Line = null; // Variable to store each line read from the file
        String[] FMat; // Array to store fields split by semicolon
        int courseId; // Variable to store course ID
        String title; // Variable to store course title
        int semester; // Variable to store course semester
       
        try (BufferedReader Br = new BufferedReader (new InputStreamReader (new FileInputStream (FullFn)))) // Try with resources to read the file
        {
            while ((Line = Br.readLine ()) != null) // Read each line until end of file
            {
                FMat = Line.split (";"); // Split the line by semicolon
                courseId = Integer.parseInt(FMat[0]); // Parse course ID
                title = FMat[1]; // Get course title
                semester = Integer.parseInt(FMat[2]); // Parse course semester
                Course course = new Course (courseId, title, semester); // Create a new Course object
                courses.add (course); // Add course to the list
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot Open File...");
            System.exit (1); // Exit if file not found
        }
        catch (IOException ex)
        {
            System.out.println ("Severe IO Error...");
            System.exit (1); // Exit on IO error
        }
        catch (NumberFormatException ex)
        {
            System.out.println ("Data Error...: " + Line);
            System.exit (1); // Exit on number format error
        }     
    }
    
    /**
     * Loads student data from a CSV file and populates the students list.
     * Each line in the CSV file should contain the student ID, semester, first name, last name, phone, and email separated by semicolons.
     */
    private void loadStudents () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Students.csv"; // File name for students CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
    
        String Line = null; // Variable to store each line read from the file
        String[] FMat; // Array to store fields split by semicolon
        int id; // Variable to store student ID
        int semester; // Variable to store semester
        String firstName; // Variable to store first name
        String lastName; // Variable to store last name
        String phone; // Variable to store phone number
        String email; // Variable to store email
       
        try (BufferedReader Br = new BufferedReader (new InputStreamReader (new FileInputStream (FullFn)))) // Try with resources to read the file
        {
            while ((Line = Br.readLine ()) != null) // Read each line until end of file
            {
                FMat = Line.split (";"); // Split the line by semicolon
                id = Integer.parseInt(FMat[0]); // Parse student ID
                semester = Integer.parseInt(FMat[1]); // Parse semester
                firstName = FMat[2]; // Get first name
                lastName = FMat[3]; // Get last name
                phone = FMat[4]; // Get phone number
                email = FMat[5]; // Get email
                Student student = new Student (id, semester, firstName, lastName, phone, email); // Create a new Student object
                students.add (student); // Add student to the list
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot Open File...");
            System.exit (1); // Exit if file not found
        }
        catch (IOException ex)
        {
            System.out.println ("Severe IO Error...");
            System.exit (1); // Exit on IO error
        }
        catch (NumberFormatException ex)
        {
            System.out.println ("Data Error...: " + Line);
            System.exit (1); // Exit on number format error
        }
       
    }
    
    /**
     * Loads professor data from a CSV file and populates the professors list.
     * Each line in the CSV file should contain the professor ID, specialty, first name, last name, phone, and email separated by semicolons.
     */
    private void loadProfessors () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Professors.csv"; // File name for professors CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line read from the file
        String[] FMat; // Array to store fields split by semicolon
        int id; // Variable to store professor ID
        String specialty; // Variable to store specialty
        String firstName; // Variable to store first name
        String lastName; // Variable to store last name
        String phone; // Variable to store phone number
        String email; // Variable to store email
       
        try (BufferedReader Br = new BufferedReader (new InputStreamReader (new FileInputStream (FullFn)))) // Try with resources to read the file
        {
            while ((Line = Br.readLine ()) != null) // Read each line until end of file
            {
                FMat = Line.split (";"); // Split the line by semicolon
                id = Integer.parseInt(FMat[0]); // Parse professor ID
                specialty = FMat[1]; // Get specialty
                firstName = FMat[2]; // Get first name
                lastName = FMat[3]; // Get last name
                phone = FMat[4]; // Get phone number
                email = FMat[5]; // Get email
                Professor professor = new Professor (id, specialty, firstName, lastName, phone, email); // Create a new Professor object
                professors.add (professor); // Add professor to the list
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot Open File...");
            System.exit (1); // Exit if file not found
        }
        catch (IOException ex)
        {
            System.out.println ("Severe IO Error...");
            System.exit (1); // Exit on IO error
        }
        catch (NumberFormatException ex)
        {
            System.out.println ("Data Error...: " + Line);
            System.exit (1); // Exit on number format error
        }
       
    }
    
    /**
     * Loads enrollment data from a CSV file and populates the enrollments list.
     * Each line in the CSV file should contain the student ID, course ID, and grade separated by semicolons.
     */
    private void loadEnrollments () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Enrollments.csv"; // File name for enrollments CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line read from the file
        String[] FMat; // Array to store fields split by semicolon
        int studentId; // Variable to store student ID
        int courseId; // Variable to store course ID
        Double grade; // Variable to store grade

        try (BufferedReader Br = new BufferedReader (new InputStreamReader (new FileInputStream (FullFn)))) // Try with resources to read the file
        {
            while ((Line = Br.readLine ()) != null) // Read each line until end of file
            {
                FMat = Line.split (";"); // Split the line by semicolon
                studentId = Integer.parseInt(FMat[0]); // Parse student ID
                courseId = Integer.parseInt(FMat[1]); // Parse course ID
                grade = Double.valueOf(FMat[2]); // Parse grade
                Enrollment enrollment = new Enrollment (studentId, courseId, grade); // Create a new Enrollment object
                enrollments.add (enrollment); // Add enrollment to the list
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot Open File...");
            System.exit (1); // Exit if file not found
        }
        catch (IOException ex)
        {
            System.out.println ("Severe IO Error...");
            System.exit (1); // Exit on IO error
        }
        catch (NumberFormatException ex)
        {
            System.out.println ("Data Error...: " + Line);
            System.exit (1); // Exit on number format error
        }
       
    }
    
    /**
     * Loads course assignment data from a CSV file and populates the courseAssignments list.
     * Each line in the CSV file should contain the professor ID and course ID separated by semicolons.
     */
    private void loadCourseAssignments () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "CourseAssignments.csv"; // File name for course assignments CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line read from the file
        String[] FMat; // Array to store fields split by semicolon
        int professorId; // Variable to store professor ID
        int courseId; // Variable to store course ID

        try (BufferedReader Br = new BufferedReader (new InputStreamReader (new FileInputStream (FullFn)))) // Try with resources to read the file
        {
            while ((Line = Br.readLine ()) != null) // Read each line until end of file
            {
                FMat = Line.split (";"); // Split the line by semicolon
                professorId = Integer.parseInt(FMat[0]); // Parse professor ID
                courseId = Integer.parseInt(FMat[1]); // Parse course ID
                CourseAssignment courseAssignment = new CourseAssignment (professorId, courseId); // Create a new CourseAssignment object
                courseAssignments.add(courseAssignment); // Add course assignment to the list
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot Open File...");
            System.exit (1); // Exit if file not found
        }
        catch (IOException ex)
        {
            System.out.println ("Severe IO Error...");
            System.exit (1); // Exit on IO error
        }
        catch (NumberFormatException ex)
        {
            System.out.println ("Data Error...: " + Line);
            System.exit (1); // Exit on number format error
        }
       
    }
    
    /**
     * Loads all data from CSV files, including courses, students, professors, enrollments, and course assignments.
     */
    private void loadData(){
        loadCourses(); // Load courses from CSV
        loadStudents(); // Load students from CSV
        loadProfessors(); // Load professors from CSV
        loadEnrollments(); // Load enrollments from CSV
        loadCourseAssignments(); // Load course assignments from CSV
    }
    
    /**
     * Saves the current list of courses to a CSV file.
     * Each line in the CSV file contains the course ID, title, and semester separated by semicolons.
     */
    private void saveCoursesCSV () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Courses.csv"; // File name for courses CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line to be written to the file

        try (PrintWriter Pr = new PrintWriter (new FileWriter (FullFn))) // Try with resources to write to the file
        {
            for (Course course: courses) // Loop through each course
            {
                Line = course.getId() + ";" + course.getTitle() + ";" + course.getSemester(); // Format the course data
                Pr.println (Line); // Write the line to the file
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot open output file");
            System.exit (0); // Exit if file not found
        } catch (IOException ex) {
            // Handle IO exception
        }
        
    }
    
    /**
     * Saves the current list of students to a CSV file.
     * Each line in the CSV file contains the student ID, semester, first name, last name, phone, and email separated by semicolons.
     */
    private void saveStudentsCSV () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Students.csv"; // File name for students CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line to be written to the file

        try (PrintWriter Pr = new PrintWriter (new FileWriter (FullFn))) // Try with resources to write to the file
        {
            for (Student student: students) // Loop through each student
            {
                Line = student.getId() + ";" + student.getSemester() + ";" + student.getFirstName() + ";" + student.getLastName() + ";" + student.getPhone() + ";" + student.getEmail(); // Format the student data
                Pr.println (Line); // Write the line to the file
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot open output file");
            System.exit (0); // Exit if file not found
        } catch (IOException ex) {
            // Handle IO exception
        }
        
    }
    
    /**
     * Saves the current list of professors to a CSV file.
     * Each line in the CSV file contains the professor ID, specialty, first name, last name, phone, and email separated by semicolons.
     */
    private void saveProfessorsCSV () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Professors.csv"; // File name for professors CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        String Line = null; // Variable to store each line to be written to the file

        try (PrintWriter Pr = new PrintWriter (new FileWriter (FullFn))) // Try with resources to write to the file
        {
            for (Professor professor: professors) // Loop through each professor
            {
                Line = professor.getId() + ";" + professor.getSpecialty() + ";" + professor.getFirstName() + ";" + professor.getLastName() + ";" + professor.getPhone() + ";" + professor.getEmail(); // Format the professor data
                Pr.println (Line); // Write the line to the file
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot open output file");
            System.exit (0); // Exit if file not found
        } catch (IOException ex) {
            // Handle IO exception
        }
        
    }
    
    /**
     * Saves the current list of enrollments to a CSV file.
     * Each line in the CSV file contains the student ID, course ID, and grade separated by semicolons.
     */
    private void saveEnrollmentsCSV () {
         final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "Enrollments.csv"; // File name for enrollments CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line to be written to the file
      
        try (PrintWriter Pr = new PrintWriter (new FileWriter (FullFn))) // Try with resources to write to the file
        {
            for (Enrollment enrollment: enrollments) // Loop through each enrollment
            {
                Line = enrollment.getStudentID()+ ";" + enrollment.getCourseID() + ";" + enrollment.getGrade(); // Format the enrollment data
                Pr.println (Line); // Write the line to the file
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot open output file");
            System.exit (0); // Exit if file not found
        } catch (IOException ex) {
            // Handle IO exception
        }
    }
    
    /**
     * Saves the current list of course assignments to a CSV file.
     * Each line in the CSV file contains the professor ID and course ID separated by semicolons.
     */
    private void saveCourseAssignmentsCSV () {
        final String Dir = "dataCsv" + File.separator; // Directory for CSV files
        final String Fn = "CourseAssignments.csv"; // File name for course assignments CSV
        String FullFn = "." + File.separator + Dir + Fn; // Full file path
        
        File file = new File(FullFn); // Create a File object
        if (!file.exists()) { // Check if file exists
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            System.exit(1); // Exit if file does not exist
        }
        
        String Line = null; // Variable to store each line to be written to the file

        try (PrintWriter Pr = new PrintWriter (new FileWriter (FullFn))) // Try with resources to write to the file
        {
            
            for (CourseAssignment courseAssignment: courseAssignments) // Loop through each course assignment
            {             
                Line = courseAssignment.getProfessorID()+ ";" + courseAssignment.getCourseID(); // Format the course assignment data
                Pr.println (Line); // Write the line to the file
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println ("Cannot open output file");
            System.exit (0); // Exit if file not found
        } catch (IOException ex) {
            System.out.println ("IOException");
            System.exit (0); // Exit if file not found
        }
    }
    
    /**
     * Saves all data to their respective CSV files, including courses, students, professors, enrollments, and course assignments.
     */
    private void saveChanges(){
        saveCoursesCSV(); // Save courses to CSV
        saveStudentsCSV(); // Save students to CSV
        saveProfessorsCSV(); // Save professors to CSV
        saveEnrollmentsCSV(); // Save enrollments to CSV
        saveCourseAssignmentsCSV(); // Save course assignments to CSV
    }
    
    /**
     * Validates an email address.
     * Allows blank emails for updates. Checks if the email matches a regular expression pattern.
     * 
     * @param email the email address to validate
     * @return true if the email is valid or blank, false otherwise
     */
    private boolean isValidEmail(String email) {
        
        //For blank updates
        if(email.isBlank())
            return true;    
        
        // Regular expression pattern for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[a-z]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches(); // Return true if email matches the pattern
    }
    
    /**
     * Prompts the user to enter a valid email address until a valid one is provided.
     * 
     * @param email the initial email address
     * @param scanner Scanner object for reading user input
     * @return the validated email address
     */
    private String validateEmail(String email, Scanner scanner) {
        while (!isValidEmail(email)) { // Loop until a valid email is entered
            System.out.print("Invalid email. Please enter a valid email: ");
            email = scanner.next();
        }
        return email; // Return the validated email
    }
    
    /**
     * Validates a name.
     * Allows blank names for updates. Checks if the name starts with an uppercase letter followed by lowercase letters.
     * 
     * @param name the name to validate
     * @return true if the name is valid or blank, false otherwise
     */
    private boolean isValidName(String name) {
        
        //For blank updates
        if(name.isBlank())
            return true;
        
        
        return name.matches("[A-Z][a-z]*"); // Return true if name matches the pattern
    }
    
    /**
     * Prompts the user to enter a valid name until a valid one is provided.
     * 
     * @param name the initial name
     * @param scanner Scanner object for reading user input
     * @return the validated name
     */
    private String validateName(String name, Scanner scanner) {
        
        while (!isValidName(name)) { // Loop until a valid name is entered
                System.out.print("Invalid name. Please enter a valid name: ");
                name = scanner.next() + scanner.nextLine();
            }  
        
        return name; // Return the validated name
    }
    
    /**
     * Validates a phone number.
     * Allows blank phone numbers for updates. Checks if the phone number has 10 digits and contains only numbers.
     * 
     * @param phone the phone number to validate
     * @return true if the phone number is valid or blank, false otherwise
     */
    private boolean isValidPhone(String phone) {
        
        //For blank updates
        if(phone.isBlank())
            return true;
        
        //Phone number must have 10 digits
        if (phone.length() != 10)
            return false;

        //Phone number must have contain only numbers
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true; // Return true if phone is valid
    }
    
    /**
     * Prompts the user to enter a valid phone number until a valid one is provided.
     * 
     * @param phone the initial phone number
     * @param scanner Scanner object for reading user input
     * @return the validated phone number
     */
    private String validatePhone(String phone, Scanner scanner) {
           
        while (!isValidPhone(phone)) { // Loop until a valid phone number is entered
            System.out.print("Invalid phone. Please enter a valid number: ");
            phone = scanner.next();
        }
        return phone; // Return the validated phone number
    }
    
    /**
     * Validates a number.
     * Allows blank numbers for updates. Checks if the number matches a regular expression pattern.
     * 
     * @param number the number to validate
     * @return true if the number is valid or blank, false otherwise
     */
    private boolean isValidNumber(String number){
        
        //For blank updates
        if(number.isBlank())
            return true;
        
        String integerRegex = "^-?\\d+$"; // Regular expression pattern for numbers
        return Pattern.matches(integerRegex, number); // Return true if number matches the pattern
       
    }
    
    /**
     * Prompts the user to enter a valid number until a valid one is provided.
     * 
     * @param number the initial number
     * @param scanner Scanner object for reading user input
     * @return the validated number
     */
    private String validateNumber(String number, Scanner scanner){
        
        while (!isValidNumber(number)){ // Loop until a valid number is entered
            System.out.print("Invalid number. Please enter a valid number: ");
            number = scanner.next();
        }
        return number; // Return the validated number
            
    }
    
    /**
     * Validates a choice.
     * Checks if the choice matches a regular expression pattern for numbers.
     * 
     * @param choice the choice to validate
     * @return true if the choice is valid, false otherwise
     */
    private boolean isValidChoice(String choice){
        
        String integerRegex = "^-?\\d+$"; // Regular expression pattern for numbers
        return Pattern.matches(integerRegex, choice); // Return true if choice matches the pattern    
    }
    
    /**
     * Prompts the user to enter a valid choice until a valid one is provided.
     * 
     * @param choice the initial choice
     * @param scanner Scanner object for reading user input
     * @return the validated choice
     */
    private String validateChoice(String choice, Scanner scanner){
        
        while (!isValidChoice(choice)){ // Loop until a valid choice is entered
            System.out.print("Invalid choice. Please enter a valid number: ");
            choice = scanner.next();
        }
        return choice; // Return the validated choice
            
    }
    
    /**
     * Validates a grade.
     * Checks if the grade is between 0 and 10 inclusive.
     * 
     * @param grade the grade to validate
     * @return true if the grade is valid, false otherwise
     */
    private boolean isValidGrade(double grade){
               
        if(grade < 0 || grade > 10)
            return false;
        
        return true; // Return true if grade is valid
    }
    
    /**
     * Prompts the user to enter a valid grade until a valid one is provided.
     * 
     * @param grade the initial grade
     * @param scanner Scanner object for reading user input
     * @return the validated grade
     */
    private double validateGrade(double grade, Scanner scanner){
        
        while(!isValidGrade(grade)){ // Loop until a valid grade is entered
            System.out.print("Invalid grade. Please enter a valid grade: ");
            grade = scanner.nextDouble();
        }
        return grade; // Return the validated grade
    }
    

}
