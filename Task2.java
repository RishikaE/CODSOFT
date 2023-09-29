package studentGradeCalc;

import java.util.Scanner;

//custom exception class
class InvalidMarksException extends Exception{
	public InvalidMarksException(String message) {
		super(message);
	}
}

class Student{
	private String name;
	private int noOfSubjects;
	private int[] marks;
	
	public Student(String name, int noOfSubjects) {
		this.name = name;
		this.noOfSubjects = noOfSubjects;
		this.marks = new int[noOfSubjects];
	}
	
	public void inputMarks(Scanner scanner) {
		for(int i = 0;i < noOfSubjects; i++) {
			boolean validInput = false;
			while(!validInput) {
				try {
					System.out.println("Enter marks obtained in each subjects "+(i+1)+" (out of 100): ");
					int mark = scanner.nextInt();
					
					validateMarks(mark);
					marks[i] = mark;
					validInput = true;
				}
				catch(InvalidMarksException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}


//Validating the marks that mark should not be less than 0 and greater than 100.
private void validateMarks(int mark) throws InvalidMarksException{
	if(mark < 0 || mark >100) {
		throw new InvalidMarksException("Invalid Marks. Marks shoulde be between 0 and 100.");
		
	}
}

//calculate Percentage
public double calculateAveragePercentage() {
	int totalMarks = 0;
	for(int mark : marks) 
		totalMarks += mark;
	
	return (double)totalMarks / noOfSubjects;
}

//Assigning grades based on the percentage.
public String calculateGrade() {
	double averagePercentage = calculateAveragePercentage();
	if(averagePercentage >= 90)
		return "O";
	else if(averagePercentage >= 80)
		return "A";
	else if(averagePercentage >= 70)
		return "B";
	else if(averagePercentage >= 60)
		return "C";
	else if(averagePercentage >= 50)
		return "D";
	else
		return "F";
}

//Displaying the results.
public void displayResults() {
	System.out.println("Student Name : "+name);
	System.out.println("Total Marks : "+calculateTotalMarks());
	System.out.println("Average Percentage : "+calculateAveragePercentage()+"%");
	System.out.println("Grade : "+calculateGrade());
}

//Sum up the marks obtained in all subjects.
private int calculateTotalMarks() {
	int totalMarks = 0;
	for(int mark : marks)
		totalMarks += mark;
	return totalMarks;
}

public class Task2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//Getting input from user.
		System.out.println("Enter student Name : ");
		String studentName = scanner.nextLine();
		
		System.out.println("Enter the number of subjects : ");
		int noOfSubjects = scanner.nextInt();
		
		Student student = new Student(studentName,noOfSubjects);
		
		student.inputMarks(scanner);
		student.displayResults();
	}
}
}

