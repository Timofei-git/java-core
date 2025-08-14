package ru.mentee.power.conditions;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDeterminerTest {

    @Test
    void testGetDayDescription_Monday() {
        assertThat(WeekdayDeterminer.getDayDescription(1))
                .isEqualTo("Monday - workday\nHard day");
    }

    @Test
    void testGetDayDescription_Wednesday() {
        assertThat(WeekdayDeterminer.getDayDescription(3))
                .isEqualTo("Wednesday - workday\nMidweek");
    }

    @Test
    void testGetDayDescription_Friday() {
        assertThat(WeekdayDeterminer.getDayDescription(5))
                .isEqualTo("Friday - workday\nWeekend is coming!");
    }

    @Test
    void testGetDayDescription_Saturday() {
        assertThat(WeekdayDeterminer.getDayDescription(6))
                .isEqualTo("Saturday - weekend");
    }

    @Test
    void testGetDayDescription_Sunday() {
        assertThat(WeekdayDeterminer.getDayDescription(7))
                .isEqualTo("Sunday - weekend");
    }

    @Test
    void testGetDayDescription_InvalidDay() {
        // TODO: Using assertThat, check that for invalid days (e.g., 0, 8),
        // the method returns "Invalid day of the week".
        assertThat(WeekdayDeterminer.getDayDescription(0)).isEqualTo("Invalid day of the week");
        assertThat(WeekdayDeterminer.getDayDescription(8)).isEqualTo("Invalid day of the week");
        assertThat(WeekdayDeterminer.getDayDescription(-5)).isEqualTo("Invalid day of the week");
    }
}

