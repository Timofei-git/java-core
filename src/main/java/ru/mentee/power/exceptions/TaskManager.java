package ru.mentee.power.exceptions;

public class TaskManager {

    private final String id;
    private double balance;


    public TaskManager(String id, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative: " + initialBalance);
        }
        this.id = id;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
        System.out.printf("Deposited %.2f to account %s. New balance: %.2f%n", amount, id, balance);
    }

    public void withdraw(double amount) throws TaskValidationException {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative: " + amount);
        }
        if (amount > this.balance) {
            throw new TaskValidationException(
                    String.format("Insufficient funds in account %s for withdrawal of %.2f", id, amount),
                    this.balance,
                    amount
            );
        }
        this.balance -= amount;
        System.out.printf("Withdrew %.2f from account %s. New balance: %.2f%n", amount, id, balance);
    }
}

