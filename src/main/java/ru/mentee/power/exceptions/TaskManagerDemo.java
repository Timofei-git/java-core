package ru.mentee.power.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManagerDemo {

   public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            TaskManager account = null;

            while (account == null) {
                try {
                    System.out.println("Enter the id: ");
                    String id = scanner.nextLine();

                    System.out.println("Enter the balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    account = new TaskManager(id, balance);
                    System.out.println("Account was successfully added. ");
                } catch (InputMismatchException e) {
                    System.out.println("Error, enter the correct number");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error, enter the positive balance");
                    scanner.nextLine();
                }
            }
            boolean running = true;

            while (running) {
                printMenu();
                int choice = -1;

                try {
                    System.out.println("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Input error: Please enter a number for the action.");
                    scanner.nextLine();
                    continue;
                }

                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Enter the amount of deposit: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            account.deposit(amount);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Enter the amount of withdraw: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine(); // очистка буфера
                            account.withdraw(amount);
                        } catch (TaskValidationException e) {
                            System.out.println("Error: " + e.getMessage() +
                                    ". Deficit: " + e.getDeficit());
                        } catch (InputMismatchException e) {
                            System.out.println("Input error: Please enter a valid number.");
                            scanner.nextLine(); // очистка ввода
                        }
                        break;
                    case 3:
                        double balance = account.getBalance();
                        System.out.println("Balance: " + balance);
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting the program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select between 1 and 5.");
                }
            }
        }
}

    private static void printMenu() {
       System.out.println("===== MENU =====");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check the balance");
        System.out.println("4. Exit");
        System.out.println("==============");
    }
}