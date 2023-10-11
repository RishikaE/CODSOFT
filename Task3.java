package atmInterface;

import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankAccount{
	private double balance;
	
	public BankAccount(double  initialBalance) {
		balance = initialBalance;
	}
	
	public double getBalance() {
		return balance;
	}
	//Method to add deposit to the existing balance
	public boolean deposit(double amount) {
		if(amount > 0) 
			balance += amount;
		else 
	         throw new IllegalArgumentException("Invalid deposit amount.");
			return true;
		}
		
	//Method to remove the withdraw amount from the balance
	public boolean withdraw(double amount) throws InsufficientFundsException{
		if(amount > 0 && amount <= balance) 
			balance -= amount;
		
		else 
	           throw new InsufficientFundsException("Invalid withdrawal amount or insufficient balance.");
		return true;
		
	}    
}

class ATM{
	private BankAccount userAccount;
	
	public ATM(BankAccount account) {
		userAccount = account;
	}
	
	public void displayMenu() {
		//Displaying the user the choices available on the ATM
		System.out.println("Choose your option :");
		System.out.println("1. Check Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Exit");
	}
	
	public void processTrascation() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			displayMenu();
			//Asking user to enter their change
			System.out.print("Enter Your Choice : ");
			choice = scanner.nextInt();
		
		try {
			switch(choice) {
				case 1:
					//Displaying the balance
					System.out.println("Current balance : "+userAccount.getBalance());
					break;
				case 2:
					System.out.println("Enter deposit amount : ");
					double depositAmount = scanner.nextDouble();
					if(userAccount.deposit(depositAmount))
						System.out.println("Deposit successful. New Balance : "+userAccount.getBalance());
					else
						System.out.println("Invalid deposit amount.");
					
					break;
				case 3:
					System.out.println("Enter the withdrawal amount : ");
					double withdrawalAmount = scanner.nextDouble();
					if(userAccount.withdraw(withdrawalAmount))
						System.out.println("Withdrawal successful.. New Balance : "+userAccount.getBalance());
					else
						System.out.println("Invalid withdrawal amount or insufficient balance.");
					
					break;
				case 4:
					//Exit
					System.out.println("Thank You :) for using the ATM!");
					break;
				default:
					System.out.println("Invalid choice. Please select a valid option.");
			}	
		}catch (IllegalArgumentException | InsufficientFundsException e) {
             System.out.println("Error: " + e.getMessage());
            }
	}while(choice != 4);
}
}	
class Task3 {
	public static void main(String[] args) {
		System.out.println("Welcome To ATM :)");
		System.out.println("How we can help you ?");
		//Initial amount balance is 1000
		BankAccount userAccount = new BankAccount(1000);
		ATM atm = new ATM(userAccount);
		atm.processTrascation();
	}

}
