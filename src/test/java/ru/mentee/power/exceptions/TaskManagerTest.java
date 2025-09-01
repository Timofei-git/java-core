package ru.mentee.power.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class TaskManagerTest {

    private TaskManager account;
    private static final double INITIAL_BALANCE = 1000.00;
    private static final String ACCOUNT_ID = "ACC-123";

//    @BeforeEach
//    void setUp() {
//        // TODO: Создать новый экземпляр TaskManager с начальным балансом INITIAL_BALANCE и ID ACCOUNT_ID
//        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
//    }

    @Test
    @DisplayName("Constructor should correctly set initial balance and ID")
    void constructorShouldSetInitialBalanceAndId() {
        // TODO: Verify that ID and balance are correctly set when the object is created
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        assertThat(taskManager.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Constructor should throw IllegalArgumentException for negative balance")
    void constructorShouldThrowIllegalArgumentExceptionForNegativeBalance() {
        // TODO: Verify that creating an account with a negative balance throws IllegalArgumentException
        // TODO: Verify that the exception message contains the text "Initial balance cannot be negative"
        assertThatThrownBy(() -> new TaskManager(ACCOUNT_ID, -INITIAL_BALANCE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Initial balance cannot be negative");
    }

    // --- Tests for deposit ---
    @Test
    @DisplayName("Deposit method should increase balance for positive amount")
    void depositShouldIncreaseBalanceForPositiveAmount() {
        // TODO: Verify that calling deposit with a positive amount increases the balance by that amount
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        taskManager.deposit(1);
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE + 1);
    }

    @Test
    @DisplayName("Deposit method should allow zero amount")
    void depositShouldAllowZeroAmount() {
        // TODO: Verify that calling deposit with a zero amount does not change the balance
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        taskManager.deposit(0);
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Deposit method should throw IllegalArgumentException for negative amount")
    void depositShouldThrowIllegalArgumentExceptionForNegativeAmount() {
        // TODO: Verify that calling deposit with a negative amount throws IllegalArgumentException
        // TODO: Verify that the exception message contains the text "Deposit amount cannot be negative"
        // TODO: Verify that the balance did not change
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        assertThatThrownBy(() -> taskManager.deposit(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Deposit amount cannot be negative");
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    // --- Tests for withdraw ---
    @Test
    @DisplayName("Withdraw method should decrease balance for valid amount")
    void withdrawShouldDecreaseBalanceForValidAmount() throws TaskValidationException {
        // TODO: Verify that calling withdraw with a valid amount decreases the balance by that amount
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        taskManager.withdraw(1);
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE - 1);
    }

    @Test
    @DisplayName("Withdraw method should allow withdrawing full balance")
    void withdrawShouldAllowWithdrawingFullBalance() throws TaskValidationException {
        // TODO: Verify that withdrawing an amount equal to the current balance results in a new balance of zero
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        taskManager.withdraw(INITIAL_BALANCE);
        assertThat(taskManager.getBalance()).isEqualTo(0);
    }

    @Test
    @DisplayName("Withdraw method should allow zero amount")
    void withdrawShouldAllowZeroAmount() throws TaskValidationException {
        // TODO: Verify that calling withdraw with a zero amount does not change the balance
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        taskManager.withdraw(0);
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Withdraw method should throw IllegalArgumentException for negative amount")
    void withdrawShouldThrowIllegalArgumentExceptionForNegativeAmount() {
        // TODO: Verify that calling withdraw with a negative amount throws IllegalArgumentException
        // TODO: Verify that the exception message contains the text "Withdrawal amount cannot be negative"
        // TODO: Verify that the balance did not change
        TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
        assertThatThrownBy(() -> taskManager.withdraw(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Withdrawal amount cannot be negative");
        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE);
    }

    @Test
    @DisplayName("Withdraw method should throw TaskValidationException when amount exceeds balance")
    void withdrawShouldThrowTaskValidationExceptionWhenAmountExceedsBalance() {
        // TODO: Создать переменную excessiveAmount = INITIAL_BALANCE + 100.0 (сумма, превышающая баланс)
        // TODO: Проверить, что при вызове withdraw с суммой, превышающей баланс, выбрасывается TaskValidationException
        // TODO: Проверить, что сообщение исключения содержит корректный текст о нехватке средств
        // TODO: Проверить, что у исключения правильно установлены поля balance, withdrawAmount и defіcit
        // TODO: Проверить, что баланс не изменился
            TaskManager taskManager = new TaskManager(ACCOUNT_ID, INITIAL_BALANCE);
            double excessiveAmount = INITIAL_BALANCE + 100.00;
            assertThatThrownBy(() -> taskManager.withdraw(excessiveAmount))
                    .isInstanceOf(TaskValidationException.class)
                    .hasMessageContaining(
                            String.format("Insufficient funds in account %s for withdrawal of %.2f", ACCOUNT_ID, excessiveAmount)
                    );

        assertThat(taskManager.getBalance()).isEqualTo(INITIAL_BALANCE);
    }
}