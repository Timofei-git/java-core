package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CreditCalculatorTest {

    private CreditCalculator calculator;
    private static final double DELTA = 0.01; // Allowable delta for comparing floating-point numbers

    @BeforeEach
    void setUp() {
        calculator = new CreditCalculator();
    }

    @Test
    @DisplayName("Calculate mortgage payment with excellent credit score")
    void calculateMortgageWithExcellentCreditScore() {
        // Arrange
        double loanAmount = 5_000_000;
        int loanTermMonths = 240; // 20 years
        String creditType = "Mortgage";
        int creditScore = 800; // Excellent score

        // Act
        double monthlyPayment = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        // For excellent score (750-850) mortgage rate: 9% - 2% = 7%
        double monthlyRate = 7.0 / 12 / 100;
        double expectedPayment = loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths))
                / (Math.pow(1 + monthlyRate, loanTermMonths) - 1);

        assertThat(monthlyPayment).isCloseTo(expectedPayment, within(DELTA));
    }

    @Test
    @DisplayName("Calculate consumer loan payment with good credit score")
    void calculateConsumerLoanWithGoodCreditScore() {
        // Arrange
        double loanAmount = 500_000;
        int loanTermMonths = 36; // 3 years
        String creditType = "Consumer";
        int creditScore = 700; // Good score

        // Act
        double monthlyPayment = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        // TODO: Fix the rate calculation error!
        // For good score (650-749) consumer loan rate: 15% - 1% = 14%
        double monthlyRate = 14.0 / 12 / 100; // Error here!
        double expectedPayment = loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths))
                / (Math.pow(1 + monthlyRate, loanTermMonths) - 1);

        assertThat(monthlyPayment).isCloseTo(expectedPayment, within(DELTA));
    }

    @Test
    @DisplayName("Calculate auto loan payment with average credit score")
    void calculateAutoLoanWithAverageCreditScore() {
        // Arrange
        double loanAmount = 800_000;
        int loanTermMonths = 48; // 4 years
        String creditType = "Auto";
        int creditScore = 600; // Average score

        // Act
        double monthlyPayment = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        // TODO: Fix the rate calculation error!
        // For average score (500-649) base auto loan rate does not change (12%)
        double monthlyRate = 12.0 / 12 / 100; // Error here!
        double expectedPayment = loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths))
                / (Math.pow(1 + monthlyRate, loanTermMonths) - 1);

        assertThat(monthlyPayment).isCloseTo(expectedPayment, within(DELTA));
    }

    @Test
    @DisplayName("Calculate auto loan payment with poor credit score")
    void calculateAutoLoanWithPoorCreditScore() {
        // Arrange
        double loanAmount = 1_000_000;
        int loanTermMonths = 60; // 5 years
        String creditType = "Auto";
        int creditScore = 400; // Poor score

        // Act
        double monthlyPayment = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        // For poor score (300-499) auto loan rate: 12% + 3% = 15%
        double monthlyRate = 15.0 / 12 / 100;
        double expectedPayment = loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths))
                / (Math.pow(1 + monthlyRate, loanTermMonths) - 1);

        assertThat(monthlyPayment).isCloseTo(expectedPayment, within(DELTA));
    }

    @Test
    @DisplayName("Handle too small loan amount")
    void handleTooSmallLoanAmount() {
        // Arrange
        double loanAmount = 5_000; // Less than minimum (10,000)
        int loanTermMonths = 60;
        String creditType = "Auto";
        int creditScore = 700;

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("Handle too large loan amount")
    void handleTooLargeLoanAmount() {
        // Arrange
        double loanAmount = 11_000_000; // More than maximum (10,000,000)
        int loanTermMonths = 120;
        String creditType = "Mortgage";
        int creditScore = 750;

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("Handle too short loan term")
    void handleTooShortLoanTerm() {
        // Arrange
        double loanAmount = 1_000_000;
        int loanTermMonths = 0; // Less than minimum (1)
        String creditType = "Consumer";
        int creditScore = 680;

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        // TODO: Fix the result check error!
        // For invalid input, the method should return -1
        assertThat(result).isEqualTo(-1.0); // Error here!
    }

    @Test
    @DisplayName("Handle too long loan term")
    void handleTooLongLoanTerm() {
        // Arrange
        double loanAmount = 2_000_000;
        int loanTermMonths = 400; // More than maximum (360)
        String creditType = "Auto";
        int creditScore = 720;

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("Handle unknown credit type")
    void handleUnknownCreditType() {
        // Arrange
        double loanAmount = 500_000;
        int loanTermMonths = 60;
        String creditType = "Educational"; // Unknown type
        int creditScore = 800;

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("Handle too low credit score")
    void handleTooLowCreditScore() {
        // Arrange
        double loanAmount = 300_000;
        int loanTermMonths = 36;
        String creditType = "Consumer";
        int creditScore = 250; // Below minimum (300)

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("Handle too high credit score")
    void handleTooHighCreditScore() {
        // Arrange
        double loanAmount = 4_000_000;
        int loanTermMonths = 180;
        String creditType = "Mortgage";
        int creditScore = 900; // Above maximum (850)

        // Act
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);

        // Assert
        // TODO: Fix the result check error!
        // When credit score > 850, method should return -1
        assertThat(result).isEqualTo(-1.0); // Error here!
    }
}
