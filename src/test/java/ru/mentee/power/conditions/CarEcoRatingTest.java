package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class CarEcoRatingTest {

    private CarEcoRating ratingCalculator;
    private static final int ERROR = -1;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 100;

    @BeforeEach
    void setUp() {
        ratingCalculator = new CarEcoRating();
    }

    @Test
    @DisplayName("Calculate rating for a modern electric car")
    void calculateRatingForModernElectricCar() {
        // Arrange
        String fuelType = "electric";
        double engineVolume = 0.0;
        double fuelConsumption = 15.0; // kWh/100km
        int yearOfManufacture = 2023;
        boolean isEuroCompliant = false; // Not applicable

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix the expected rating error!
        // Base rating 90, penalty 15*0.5=7.5, year penalty 0.
        // Total = 90 - 7.5 = 82.5 -> 83 (rounded)
        int expectedRating = 83; // This line has an error!
        assertThat(rating).isEqualTo(expectedRating);
    }

    @Test
    @DisplayName("Calculate rating for an efficient Euro-6 hybrid")
    void calculateRatingForEfficientEuro6Hybrid() {
        // Arrange
        String fuelType = "hybrid";
        double engineVolume = 1.5;
        double fuelConsumption = 4.0; // l/100km - less than 5, bonus +15
        int yearOfManufacture = 2021;
        boolean isEuroCompliant = true; // Bonus +10

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix the expected rating error!
        // Base 70. Engine -7.5. Consumption -8. Year 2021 (>2020) -0. Euro-6 +10. Consumption<5 +15.
        // Total = 70 - 7.5 - 8 + 10 + 15 = 79.5 -> 80 (rounded)
        int expectedRating = 80; // This line has an error!
        assertThat(rating).isEqualTo(expectedRating);
    }

    @Test
    @DisplayName("Calculate rating for an old diesel car not Euro-6 compliant")
    void calculateRatingForOldDieselCarNotEuro6() {
        // Arrange
        String fuelType = "diesel";
        double engineVolume = 2.5;
        double fuelConsumption = 8.0;
        int yearOfManufacture = 2015;
        boolean isEuroCompliant = false;

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix the expected rating error!
        // Base 40. Engine -12.5. Consumption -16. Year (2020-2015=5) -5. No Euro.
        // Total = 40 - 12.5 - 16 - 5 = 6.5 -> 7 (rounded)
        assertThat(rating).isEqualTo(7); // This line has an error!
    }

    @Test
    @DisplayName("Upper rating limit (max 100)")
    void ensureMaximumRatingIs100() {
        // Arrange - create an ideal future electric car
        String fuelType = "electric";
        double engineVolume = 0.0;
        double fuelConsumption = 0.1; // Almost zero consumption
        int yearOfManufacture = Year.now().getValue(); // Current year
        boolean isEuroCompliant = true; // Assume argument applies to electric

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix the rating check error!
        // We must ensure rating does not exceed max allowed (100)
        assertThat(rating).isLessThanOrEqualTo(MAX_RATING);
        // This line has an error!
    }

    @Test
    @DisplayName("Lower rating limit (min 1)")
    void ensureMinimumRatingIs1() {
        // Arrange - create an extremely inefficient old car
        String fuelType = "petrol";
        double engineVolume = 7.0; // Huge volume
        double fuelConsumption = 25.0; // Huge consumption
        int yearOfManufacture = 1980; // Very old
        boolean isEuroCompliant = false;

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix the expected rating error!
        // Base 30. Engine -35. Consumption -50. Year (2020-1980=40) -40. No Euro.
        // Total = 30 - 35 - 50 - 40 = -95. Should be clamped to MIN_RATING (1).
        assertThat(rating).isEqualTo(MIN_RATING); // This line has an error!
    }

    @Test
    @DisplayName("Handle unknown fuel type")
    void handleUnknownFuelType() {
        // Arrange
        String fuelType = "hydrogen"; // Unknown fuel type
        double engineVolume = 0.0;
        double fuelConsumption = 10.0;
        int yearOfManufacture = 2020;
        boolean isEuroCompliant = false;

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix expected result error!
        // For unknown fuel type method should return error code
        assertThat(rating).isEqualTo(ERROR); // This line has an error!
    }

    @Test
    @DisplayName("Handle negative engine volume")
    void handleNegativeEngineVolume() {
        // Arrange
        String fuelType = "petrol";
        double engineVolume = -2.0; // Negative engine volume
        double fuelConsumption = 10.0;
        int yearOfManufacture = 2020;
        boolean isEuroCompliant = false;

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        assertThat(rating).isEqualTo(ERROR);
    }

    @Test
    @DisplayName("Handle negative fuel consumption")
    void handleNegativeFuelConsumption() {
        // Arrange
        String fuelType = "diesel";
        double engineVolume = 2.0;
        double fuelConsumption = -5.0; // Negative consumption
        int yearOfManufacture = 2018;
        boolean isEuroCompliant = true;

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        // TODO: Fix expected result error!
        // Negative fuel consumption should return error code
        assertThat(rating).isEqualTo(ERROR); // This line has an error!
    }

    @Test
    @DisplayName("Handle future year of manufacture")
    void handleFutureYearOfManufacture() {
        // Arrange
        String fuelType = "hybrid";
        double engineVolume = 1.6;
        double fuelConsumption = 5.5;
        int yearOfManufacture = Year.now().getValue() + 5; // Future year
        boolean isEuroCompliant = true;

        // Act
        int rating = ratingCalculator.calculateEcoRating(
                fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);

        // Assert
        assertThat(rating).isEqualTo(ERROR);
    }
}
