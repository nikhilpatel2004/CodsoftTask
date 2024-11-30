import java.util.Scanner;

class bankAccount {
    private double balance;

    public bankAccount(double initialBalance) {
        if (initialBalance > 0) {
            balance = initialBalance;
        } else {
            balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}

class ATM {
    private bankAccount account;

    public ATM(bankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Withdrawal of " + amount + " was successful.");
        } else {
            System.out.println("Insufficient balance. Your current balance is " + account.getBalance());
        }
    }

    private void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        account.deposit(amount);
        System.out.println("Deposit of " + amount + " was successful.");
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        bankAccount account = new bankAccount(500); // Initial balance of 500
        ATM atm = new ATM(account);
        atm.displayMenu();
    }
}