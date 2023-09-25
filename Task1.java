package numberGame;

import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame{
	private final int minRange;
	private final int maxRange;
	private final int maxAttempts;
	private final Random random;
	private int rounds;
	private int totalScore;
	
	public NumberGuessingGame(int minRange,int maxRange,int maxAttempts) {
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.maxAttempts = maxAttempts;  //Limiting the Attempts.
		this.random = new Random();
		this.rounds = 0;
		this.totalScore = 0;
	}
	public void play() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the Number Guessing Game :) ");
		System.out.println("To predict the number between "+ minRange + " and "+ maxRange + ", you have "+ maxAttempts + " chances.");
		
		while(true) {
			//Generating the random numbers.
			int targetNumber = (int) (Math.random() * 100) + 1;
			int attempts = 0;
			
			while(attempts < maxAttempts) {
				//Asking the user to guess the number.
				System.out.println("Guess a Number? ");
				int userGuess = scanner.nextInt();
				attempts++;
				
				//Comparing the user's guess with randomly generated number and providing the feedback.
				if(userGuess < minRange || userGuess >maxRange) 
					System.out.println("Please provide a prediction that falls inside the given range.");
				else if(userGuess == targetNumber) {
					System.out.println("Congratulations! Within " + attempts + " attempts, you correctly predicted the number. :)");
					totalScore+=maxAttempts-attempts+100;
					break;
				}
				else if (userGuess < targetNumber)
					System.out.println("Your Guess is too low. Try it once again. :(");
				else
					System.out.println("Your Guess is too high. Try it once again. :(");
				
				//Intimating the user that they are left with 2 attempts.
				int remainingAttempts = maxAttempts-attempts;
				
				if(attempts == 4)
					System.out.println("Still Your left with "+remainingAttempts+" attempts.");
				
				if(attempts == maxAttempts)
					System.out.println("The allotted number of tries has been used. The right number was: " + targetNumber);
			}
			
				rounds++;
				//Allowing the user to play multiple rounds.
				System.out.println("Are you interested in playing another round? (yes/no):");
				String playAgain = scanner.next().toLowerCase();
			
				
				if(!playAgain.equals("yes"))
					break;
			}
			
			//Display the user's score,and the number of attempts taken.
			System.out.println("Thanks for participating :)");
			System.out.println("No of Rounds played : "+rounds);
			System.out.println("Total score : "+totalScore+" points");
	}

}

public class Task1{
	public static void main(String[] args) {
		NumberGuessingGame game = new NumberGuessingGame(1,100,6);
		game.play();
		
	}
}