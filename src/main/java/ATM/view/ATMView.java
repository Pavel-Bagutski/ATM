package ATM.view;

import java.util.Scanner;

public class ATMView {
    private Scanner scanner;

    public ATMView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the ATM Simulator!");
    }

    public void displayGoodbyeMessage() {
        System.out.println("Thank you for using the ATM Simulator. Goodbye!");
    }

    public String promptCardNumber() {
        System.out.print("Enter your card number (XXXX-XXXX-XXXX-XXXX) or 'exit' to quit: ");
        return scanner.nextLine();
    }

    public int promptPIN() {
        System.out.print("Enter your PIN: ");
        return scanner.nextInt();
    }

    public int promptAccountAction() {
        System.out.println("\nAccount actions:");
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public double promptWithdrawAmount() {
        System.out.print("Enter the amount to withdraw: ");
        return scanner.nextDouble();
    }

    public double promptDepositAmount() {
        System.out.print("Enter the amount to deposit: ");
        return scanner.nextDouble();
    }

    public void displayBalance(double balance) {
        System.out.println("Your current balance is: $" + balance);
    }

    public void displayWithdrawalSuccess(double balance) {
        System.out.println("Withdrawal successful. New balance: $" + balance);
    }

    public void displayWithdrawalFailure() {
        System.out.println("Withdrawal failed. Insufficient funds or exceeded maximum withdrawal limit.");
    }

    public void displayDepositSuccess(double balance) {
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    public void displayDepositFailure() {
        System.out.println("Deposit failed. Deposit amount exceeded the maximum balance limit.");
    }

    public void displayInvalidPINMessage() {
        System.out.println("Invalid PIN. Please try again.");
    }

    public void displayCardNotFoundMessage() {
        System.out.println("Card number not found. Please try again.");
    }

    public void displayInvalidCardNumberMessage() {
        System.out.println("Invalid card number format. Please try again.");
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice. Please try again.");
    }
}