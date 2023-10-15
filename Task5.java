package studentCourseRegistrationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Define the Course class for representing courses
class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;
    
 // Constructor for Course
    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }

 // Getter methods
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    // Register a student for the course
    public void registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
        } else {
            System.out.println("Course " + code + " is already full.");
        }
    }

    // Drop a student from the course
    public void dropStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }
}

//Define the Student class for representing students
class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    
 // Constructor for Student
    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    
 // Getter methods

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

 // Getter methods
    public void registerForCourse(Course course) {
        if (course.getAvailableSlots() >0) {
            course.registerStudent();
            registeredCourses.add(course);
        } else {
            System.out.println("Course " + course.getCode() + " is full. Cannot register.");
        }
    }

    // Drop a course
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
        }
    }
}

public class Task5 {
    public static void main(String[] args) {
        Course course1 = new Course("CSE-101", "Introduction to Programming and Algorithms", "Programming and Algorithm fundamentals", 30, "Mon/Wed/Fri 10:00 AM");
        Course course2 = new Course("MCA-201", "Blockchain", "Advanced Blockchain topics", 25, "Tue/Thu 2:00 PM");

        Student student1 = new Student(1, "Ram");
        Student student2 = new Student(2, "Priya");

        Scanner scanner = new Scanner(System.in);

     // Main program loop for user interactions
        while (true) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Register for a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View course listings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	// Register for a course
                    registerForCourse(scanner, student1, student2, course1, course2);
                    break;
                case 2:
                	// Drop a course
                    dropCourse(scanner, student1, student2, course1, course2);
                    break;
                case 3:
                	// View course listings
                    displayCourseListings(course1, course2);
                    break;
                case 4:
                	// Exit the program
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // Method to register a student for a course
    private static void registerForCourse(Scanner scanner, Student student1, Student student2, Course course1, Course course2) {
        System.out.print("Enter student name (Ram or Priya): ");
        String studentName = scanner.next();
        System.out.print("Enter course code (CSE-101 or MCA-201): ");
        String courseCode = scanner.next();

        Student selectedStudent = studentName.equalsIgnoreCase("Ram") ? student1 : studentName.equalsIgnoreCase("Priya") ? student2 : null;
        Course selectedCourse = courseCode.equalsIgnoreCase("CSE-101") ? course1 : courseCode.equalsIgnoreCase("MCA-201") ? course2 : null;

        if (selectedStudent != null && selectedCourse != null) {
            selectedStudent.registerForCourse(selectedCourse);
            System.out.println(selectedStudent.getName() + " is registered for " + selectedCourse.getTitle());
        } else {
            System.out.println("Invalid student name or course code.");
        }
    }

    // Method to drop a course for a student
    private static void dropCourse(Scanner scanner, Student student1, Student student2, Course course1, Course course2) {
        System.out.print("Enter student name (Ram or Priya): ");
        String studentName = scanner.next();
        System.out.print("Enter course code (CSE-101 or MCA-201): ");
        String courseCode = scanner.next();

        Student selectedStudent = studentName.equalsIgnoreCase("Ram") ? student1 : studentName.equalsIgnoreCase("Priya") ? student2 : null;
        Course selectedCourse = courseCode.equalsIgnoreCase("CSE-101") ? course1 : courseCode.equalsIgnoreCase("MCA-201") ? course2 : null;

        if (selectedStudent != null && selectedCourse != null) {
            if (selectedStudent.getRegisteredCourses().contains(selectedCourse)) {
                selectedStudent.dropCourse(selectedCourse);
                System.out.println(selectedStudent.getName() + " dropped " + selectedCourse.getTitle());
            } else {
                System.out.println(selectedStudent.getName() + " is not registered for " + selectedCourse.getTitle());
            }
        } else {
            System.out.println("Invalid student name or course code.");
        }
    }

    // Method to display course listings
    private static void displayCourseListings(Course course1, Course course2) {
        System.out.println("Course Listings:");
        System.out.println(course1.getCode() + " - " + course1.getTitle() + " (" + course1.getAvailableSlots() + " slots available)");
        System.out.println(course2.getCode() + " - " + course2.getTitle() + " (" + course2.getAvailableSlots() + " slots available)");
    }
}
