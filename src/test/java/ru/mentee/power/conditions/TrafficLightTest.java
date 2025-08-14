package ru.mentee.power.conditions;

import org.junit.jupiter.api.Test;
// Импортируем статический метод assertThat из AssertJ
import static org.assertj.core.api.Assertions.assertThat;


class TrafficLightTest {

    @Test
    void testGetRecommendation_RedSignal() {
        assertThat(TrafficLight.getRecommendation("Red")).isEqualTo("Don't move!");
        assertThat(TrafficLight.getRecommendation("red")).isEqualTo("Don't move!"); // Проверка регистра
    }

    @Test
    void testGetRecommendation_YellowSignal() {
        assertThat(TrafficLight.getRecommendation("Yellow")).isEqualTo("Be ready!");
        assertThat(TrafficLight.getRecommendation("YELLOW")).isEqualTo("Be ready!");
    }

    @Test
    void testGetRecommendation_GreenSignal() {
        assertThat(TrafficLight.getRecommendation("Green")).isEqualTo("You can go!");
        assertThat(TrafficLight.getRecommendation("green")).isEqualTo("You can go!");
    }

    @Test
    void testGetRecommendation_InvalidSignal() {
        assertThat(TrafficLight.getRecommendation("Blue")).isEqualTo("Invalid signal!");
        assertThat(TrafficLight.getRecommendation("")).isEqualTo("Invalid signal!");
        assertThat(TrafficLight.getRecommendation(null)).isEqualTo("Invalid signal!");
    }
}
