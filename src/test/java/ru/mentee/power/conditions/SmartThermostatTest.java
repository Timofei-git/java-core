package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SmartThermostatTest {

    private SmartThermostat thermostat;
    private static final double ERROR_TEMP = -100.0; // Error code

    @BeforeEach
    void setUp() {
        thermostat = new SmartThermostat();
    }

    @Test
    @DisplayName("Weekday morning with people present")
    void getTargetTemperatureForWeekdayMorningOccupied() {
        int timeOfDay = 7; // Morning (6–8)
        String dayOfWeek = "Tuesday";
        boolean isOccupied = true;
        double outsideTemp = 15.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        assertThat(targetTemp).isEqualTo(22.0); // This line contains an error!
    }

    @Test
    @DisplayName("Weekday morning with no one home")
    void getTargetTemperatureForWeekdayMorningUnoccupied() {
        int timeOfDay = 7;
        String dayOfWeek = "Tuesday";
        boolean isOccupied = false;
        double outsideTemp = 15.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        // TODO: Fix expected value!
        // Expected: 18.0°C for empty home on weekday morning
        assertThat(targetTemp).isEqualTo(18.0); // This line contains an error!
    }

    @Test
    @DisplayName("Weekday daytime with people present")
    void getTargetTemperatureForWeekdayDayOccupied() {
        int timeOfDay = 14; // Day (9–17)
        String dayOfWeek = "Wednesday";
        boolean isOccupied = true;
        double outsideTemp = 20.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        // TODO: Fix expected value!
        // Expected: 20.0°C for weekday daytime with people present
        assertThat(targetTemp).isEqualTo(20.0); // This line contains an error!
    }

    @Test
    @DisplayName("Weekday evening with people present")
    void getTargetTemperatureForWeekdayEveningOccupied() {
        int timeOfDay = 20; // Evening (18–22)
        String dayOfWeek = "Thursday";
        boolean isOccupied = true;
        double outsideTemp = 15.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        assertThat(targetTemp).isEqualTo(22.0);
    }

    @Test
    @DisplayName("Weekend night with no one home")
    void getTargetTemperatureForWeekendNightUnoccupied() {
        int timeOfDay = 2; // Night (0–6)
        String dayOfWeek = "Sunday";
        boolean isOccupied = false;
        double outsideTemp = 10.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        assertThat(targetTemp).isEqualTo(16.0);
    }

    @Test
    @DisplayName("Energy saving rule in hot weather")
    void applyEnergyRuleForHotWeather() {
        int timeOfDay = 14; // Weekday daytime
        String dayOfWeek = "Monday";
        boolean isOccupied = true;
        double outsideTemp = 28.0; // Hot (above 25°C)

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        // TODO: Fix expected value!
        // Base temp is 20°C, +1 for hot weather = 21.0°C
        assertThat(targetTemp).isEqualTo(21.0); // This line contains an error!
    }

    @Test
    @DisplayName("Energy saving rule in cold weather")
    void applyEnergyRuleForColdWeather() {
        int timeOfDay = 23; // Night
        String dayOfWeek = "Saturday"; // Weekend
        boolean isOccupied = true;
        double outsideTemp = -5.0; // Cold (below 0°C)

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        // TODO: Fix expected value!
        // Base: 22°C, -1°C for cold weather = 21.0°C
        assertThat(targetTemp).isEqualTo(21.0); // This line contains an error!
    }

    @Test
    @DisplayName("Handle invalid time of day (less than 0)")
    void handleInvalidTimeOfDayTooLow() {
        int timeOfDay = -1; // Invalid time
        String dayOfWeek = "Monday";
        boolean isOccupied = true;
        double outsideTemp = 15.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        // TODO: Fix expected value!
        assertThat(targetTemp).isEqualTo(ERROR_TEMP); // This line contains an error!
    }

    @Test
    @DisplayName("Handle invalid time of day (greater than 23)")
    void handleInvalidTimeOfDayTooHigh() {
        int timeOfDay = 24; // Invalid time
        String dayOfWeek = "Monday";
        boolean isOccupied = true;
        double outsideTemp = 15.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        assertThat(targetTemp).isEqualTo(ERROR_TEMP);
    }

    @Test
    @DisplayName("Handle invalid day of week")
    void handleInvalidDayOfWeek() {
        int timeOfDay = 10;
        String dayOfWeek = "Friddday"; // Invalid day
        boolean isOccupied = false;
        double outsideTemp = 18.0;

        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);

        // TODO: Fix expected value!
        assertThat(targetTemp).isEqualTo(ERROR_TEMP); // This line contains an error!
    }

    @ParameterizedTest
    @CsvSource({ // timeOfDay, dayOfWeek, isOccupied, outsideTemp, expectedTemp
            "7, Monday, true, 15.0, 22.0",    // Weekday, morning, occupied, normal weather
            "7, Monday, false, 15.0, 18.0",   // Weekday, morning, unoccupied, normal weather
            "12, Wednesday, true, 28.0, 21.0",// Weekday, daytime, hot
            "20, Friday, false, -2.0, 16.0",  // Weekday, evening, unoccupied, cold
            "8, Saturday, true, 10.0, 23.0",  // Weekend, morning, occupied
            "15, Sunday, false, 30.0, 18.0",  // Weekend, daytime, unoccupied, hot
            // TODO: Fix expected values for the following:
            "23, Saturday, true, 15.0, 22.0", // Weekend, evening, occupied — expected: 22.0
            "3, Sunday, true, -5.0, 19.0"     // Weekend, night, occupied, cold — expected: 19.0
    })
    @DisplayName("Various combinations of time, day, and occupancy")
    void getTargetTemperatureForVariousCombinations(int timeOfDay, String dayOfWeek,
                                                    boolean isOccupied, double outsideTemp,
                                                    double expectedTemp) {
        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, outsideTemp);
        assertThat(targetTemp).isEqualTo(expectedTemp);
    }
}

