package ru.mentee.power.exceptions;

/**
 * Класс, представляющий банковский счет.
 */
public class TaskManager {

    private final String id;
    private double balance;

    /**
     * Создает новый счет.
     *
     * @param id             Идентификатор счета.
     * @param initialBalance Начальный баланс.
     * @throws IllegalArgumentException если начальный баланс отрицательный.
     */
    public TaskManager(String id, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative: " + initialBalance);
        }
        this.id = id;
        this.balance = initialBalance;
    }

    /**
     * Возвращает идентификатор счета.
     *
     * @return Идентификатор счета.
     */
    public String getId() {
        return id;
    }

    /**
     * Возвращает текущий баланс.
     *
     * @return Текущий баланс.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Вносит указанную сумму на счет.
     *
     * @param amount Сумма для внесения.
     * @throws IllegalArgumentException если сумма отрицательная.
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
        System.out.printf("Deposited %.2f to account %s. New balance: %.2f%n", amount, id, balance);
    }

    /**
     * Снимает указанную сумму со счета.
     *
     * @param amount Сумма для снятия.
     * @throws IllegalArgumentException   если сумма отрицательная.
     * @throws TaskValidationException если на счете недостаточно средств.
     */
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

