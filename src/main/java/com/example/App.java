package com.example;

import java.util.Scanner;

/**
 * BankAccount Class
 * Represents a user's bank account. It stores the balance and provides methods
 * to deposit, withdraw, and check the balance.
 */
class BankAccount {
    private double balance; // The current balance in the account

    /**
     * Constructor to create a bank account with an initial balance.
     * @param initialBalance The starting balance for the account.
     */
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0; // Default to 0 if initial balance is negative
        }
    }

    /**
     * Gets the current balance of the account.
     * @return The current balance.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Deposits a specified amount into the account.
     * @param amount The amount to deposit. Must be a positive number.
     * @return true if the deposit was successful, false otherwise.
     */
    public boolean deposit(double amount) {
        // A deposit is only valid if the amount is greater than 0
        if (amount > 0) {
            this.balance += amount; // Add the amount to the balance
            return true; // Indicate success
        }
        return false; // Indicate failure
    }

    /**
     * Withdraws a specified amount from the account.
     * @param amount The amount to withdraw. Must be positive and not exceed the balance.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        // A withdrawal is only valid if the amount is positive AND does not exceed the balance
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount; // Subtract the amount from the balance
            return true; // Indicate success
        }
        return false; // Indicate failure
    }
}

/**
 * ATM Class
 * Represents the ATM machine. It handles all user interactions, such as
 * displaying the menu and processing transactions on a BankAccount.
 */
class ATM {
    private BankAccount account; // The bank account currently being accessed
    private Scanner scanner;     // To read user input from the console

    /**
     * Constructor to create an ATM instance linked to a specific bank account.
     * @param account The bank account the ATM will operate on.
     */
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu of the ATM to the user.
     */
    public void showMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.println("====================");
    }

    /**
     * Starts the ATM, showing the menu and handling user choices in a loop.
     */
    public void run() {
        int choice;
        do {
            showMenu();
            System.out.print("Please enter your choice: ");
            choice = scanner.nextInt();

            // Use a switch statement to perform an action based on the user's choice
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-4).");
            }
        } while (choice != 4); // The loop continues until the user chooses to exit
    }

    /**
     * Handles the "Check Balance" transaction.
     */
    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
    }

    /**
     * Handles the "Deposit" transaction.
     */
    private void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        if (account.deposit(amount)) {
            System.out.printf("Successfully deposited $%.2f\n", amount);
            System.out.printf("Your new balance is: $%.2f\n", account.getBalance());
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        }
    }

    /**
     * Handles the "Withdraw" transaction.
     */
    private void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f\n", amount);
            System.out.printf("Your remaining balance is: $%.2f\n", account.getBalance());
        } else {
            // This could fail for two reasons: insufficient funds or an invalid amount.
            if (amount > account.getBalance()) {
                System.out.println("Error: Insufficient funds.");
            } else {
                System.out.println("Error: Invalid withdrawal amount. Please enter a positive number.");
            }
        }
    }
}

/**
 * Main Class (ATMSystem)
 * This is the entry point of the program. It creates the BankAccount and ATM
 * objects and starts the ATM simulation.
 */
public class App {
    public static void main(String[] args) {
        // Create a new bank account with an initial balance of $1000
        BankAccount userAccount = new BankAccount(1000.00);

        // Create an ATM instance linked to the user's account
        ATM atm = new ATM(userAccount);

        // Start the ATM simulation
        atm.run();
    }
}
