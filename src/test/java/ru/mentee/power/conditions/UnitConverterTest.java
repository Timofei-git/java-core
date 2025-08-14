package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class UnitConverterTest {

    private UnitConverter converter;
    private static final double DELTA = 0.01; // Acceptable tolerance
    private static final double ERROR = -1.0;  // Error code

    @BeforeEach
    void setUp() {
        converter = new UnitConverter();
    }

    @Test
    @DisplayName("Conversion from meters to centimeters")
    void convertMetresToCentimetres() {
        // Arrange
        double value = 1.0;
        String fromUnit = "Metres";
        String toUnit = "Centimeter";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // 1 meter contains 100 centimeters
        assertThat(convertedValue).isCloseTo(100.0, within(DELTA)); // Error in this line!
    }

    @Test
    @DisplayName("Conversion from centimeters to meters")
    void convertCentimetresToMetres() {
        // Arrange
        double value = 150.0;
        String fromUnit = "Centimeter";
        String toUnit = "Metres";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // 150 cm = 1.5 m
        assertThat(convertedValue).isCloseTo(1.50, within(DELTA)); // Error in this line!
    }

    @Test
    @DisplayName("Conversion from meters to feet")
    void convertMetresToFeet() {
        // Arrange
        double value = 2.0;
        String fromUnit = "Metres";
        String toUnit = "Foot";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // 1 meter = 3.28 feet, so 2 meters = 6.56 feet
        assertThat(convertedValue).isCloseTo(6.56, within(DELTA)); // Error in this line!
    }

    @Test
    @DisplayName("Conversion from kilograms to grams")
    void convertKilogramsToGrams() {
        // Arrange
        double value = 2.5;
        String fromUnit = "Kilo";
        String toUnit = "Gramme";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        assertThat(convertedValue).isCloseTo(2500.0, within(DELTA));
    }

    @Test
    @DisplayName("Conversion from pounds to ounces")
    void convertPoundsToOunces() {
        // Arrange
        double value = 1.0; // 1 pound
        String fromUnit = "Pound";
        String toUnit = "Ounce";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // 1 pound ≈ 16 ounces (via kg: 1 / 2.20462 * 35.274 ≈ 16)
        assertThat(convertedValue).isCloseTo(16.0, within(DELTA)); // Error in this line!
    }

    @Test
    @DisplayName("Conversion from Celsius to Fahrenheit")
    void convertCelsiusToFahrenheit() {
        // Arrange
        double value = 25.0;
        String fromUnit = "Celsius";
        String toUnit = "Fahrenheit";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // Formula: (C × 9/5) + 32 = (25 × 9/5) + 32 = 77.0
        double expectedValue = (25.0 * 9.0 / 5.0) + 32.0; // Error in formula here!
        assertThat(convertedValue).isCloseTo(expectedValue, within(DELTA));
    }

    @Test
    @DisplayName("Conversion from Fahrenheit to Kelvin")
    void convertFahrenheitToKelvin() {
        // Arrange
        double value = 32.0; // 32°F = 0°C = 273.15K
        String fromUnit = "Fahrenheit";
        String toUnit = "Kelvin";

        // Act
        double convertedValue = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // 32°F = 0°C = 273.15K
        assertThat(convertedValue).isCloseTo(273.15, within(DELTA)); // Error in this line!
    }

    @Test
    @DisplayName("Handling incompatible units")
    void handleIncompatibleUnits() {
        // Arrange
        double value = 10.0;
        String fromUnit = "Metres";
        String toUnit = "Kilo";

        // Act
        double result = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // For incompatible units, the method should return ERROR
        assertThat(result).isEqualTo(ERROR); // Error in this line!
    }

    @Test
    @DisplayName("Handling unsupported units (fromUnit)")
    void handleUnsupportedFromUnit() {
        // Arrange
        double value = 10.0;
        String fromUnit = "Mile"; // Unsupported unit
        String toUnit = "Metres";

        // Act
        double result = converter.convert(value, fromUnit, toUnit);

        // Assert
        // TODO: Fix the expected value error!
        // For unsupported unit, the method should return ERROR
        assertThat(result).isEqualTo(ERROR); // Error in this line!
    }

    @Test
    @DisplayName("Handling unsupported units (toUnit)")
    void handleUnsupportedToUnit() {
        // Arrange
        double value = 10.0;
        String fromUnit = "Metres";
        String toUnit = "Yard"; // Unsupported unit

        // Act
        double result = converter.convert(value, fromUnit, toUnit);

        // Assert
        assertThat(result).isEqualTo(ERROR);
    }

    @ParameterizedTest
    @CsvSource({ // value, fromUnit, toUnit, expected
            "1.0, Metres, Centimeter, 100.0",
            "100.0, Centimeter, Metres, 1.0",
            "1.0, Kilo, Pound, 2.20462",
            "1.0, Pound, Kilo, 0.45359", // 1 / 2.20462
            "0.0, Celsius, Fahrenheit, 32.0",
            "273.15, Kelvin, Celsius, 0.0",
            // TODO: Fix the expected values for the following cases!
            "5.0, Metres, Inch, 196.85", // Error here! 5 meters = 5 * 39.37 = 196.85 inches
            "10.0, Kilo, Ounce, 352.74" // Error here! 10 kg = 10 * 35.274 = 352.74 ounces
    })
    @DisplayName("Various conversions")
    void testVariousConversions(double value, String fromUnit, String toUnit, double expected) {
        double convertedValue = converter.convert(value, fromUnit, toUnit);
        assertThat(convertedValue).isCloseTo(expected, within(DELTA));
    }
}
