package quizApplicationWithTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    // Constructor for QuizQuestion class
    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    // Check if the user's answer is correct
    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }
}

public class Task4 {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;


    // Constructor for the main class
    public Task4() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;
        //timer = new Timer();
    }

    // Method to add a new question to the quiz
    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    // Start the quiz
    public void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        displayNextQuestion();
    }

    // Display the next question and start the timer
    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion question = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            startTimer(15); // 15 seconds to answer
        } else {
            endQuiz();
        }
    }

    // Start the timer for the current question
    private void startTimer(int seconds) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                displayNextQuestion();
            }
        }, seconds * 1000);
    }

    // Submit the user's answer
    public void submitAnswer(int userAnswer) {
        timer.cancel();// Cancel the timer
        QuizQuestion question = questions.get(currentQuestionIndex);
        if (question.isCorrect(userAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
        currentQuestionIndex++;
        displayNextQuestion();
    }


    // End the quiz and display the final score
    private void endQuiz() {
        System.out.println("Quiz ended.");
        System.out.println("Your score: " + score + " out of " + questions.size());
    }

    public static void main(String[] args) {
    	Task4 quizApp = new Task4();
    	
    	// Add 10 Java-related questions with options and correct answers
        // Each question is added using the addQuestion method
        // The options list should have the correct answer in the specified index (0 for the first option, 1 for the second, and so on)


    	List<String> options1 = List.of("10", "20", "30", "40");
    	quizApp.addQuestion(new QuizQuestion("What is 2 x 5?", options1, 0));

    	List<String> options2 = List.of("Java", "Python", "C++", " Ruby");
    	quizApp.addQuestion(new QuizQuestion("Which programming language is known for its 'Write Once, Run Anywhere' feature?", options2, 0));

    	List<String> options3 = List.of("Class", "Object", "Method", "Variable");
    	quizApp.addQuestion(new QuizQuestion("In Java, what is the blueprint for creating objects?", options3, 0));

    	List<String> options4 = List.of("byte", "short", "int", "long");
    	quizApp.addQuestion(new QuizQuestion("Which data type in Java is used to store a 64-bit signed integer?", options4, 3));

    	List<String> options5 = List.of("Polymorphism", "Inheritance", "Encapsulation", "Abstraction");
    	quizApp.addQuestion(new QuizQuestion("What is one of the four fundamental principles of Object-Oriented Programming (OOP) in Java?", options5, 1));

    	List<String> options6 = List.of("public", "private", "protected", "default");
    	quizApp.addQuestion(new QuizQuestion("In Java, what access modifier is used to restrict access to a class member within the same package?", options6, 3));

    	List<String> options7 = List.of("compile-time", "runtime", "design-time", "load-time");
    	quizApp.addQuestion(new QuizQuestion("When does Java perform exception checking?", options7, 0));

    	List<String> options8 = List.of("TreeSet", "HashSet", "ArrayList", "LinkedList");
    	quizApp.addQuestion(new QuizQuestion("Which Java data structure stores its elements in a sorted order?", options8, 0));

    	List<String> options9 = List.of("equals()", "toString()", " hashCode()", "getClass()");
    	quizApp.addQuestion(new QuizQuestion("Which method in Java is used to compare two objects for equality?", options9, 0));

    	List<String> options10 = List.of("int[]", "double[]", "String[]", "boolean[]");
    	quizApp.addQuestion(new QuizQuestion("Which data type can be used to declare an array of integers in Java?", options10, 0));
        // Start the quiz
        quizApp.startQuiz();

        Scanner scanner = new Scanner(System.in);
        while (quizApp.currentQuestionIndex < quizApp.questions.size()) {
            System.out.print("Enter your answer: ");
            int userAnswer = scanner.nextInt();
            quizApp.submitAnswer(userAnswer - 1);// Subtract 1 to match the user's input to the correct option index
        }
        scanner.close();
    }
}
