package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    public void testFizzBuzzForFirst15Numbers() {
        // Подготовка
        FizzBuzz fizzBuzz = new FizzBuzz();

        // Действие
        String[] result = fizzBuzz.generateFizzBuzz(30);

        // Проверка
        assertThat(result).isNotNull();
        assertThat(result).hasSize(30);

        // Проверяем конкретные значения
        assertThat(result[0]).isEqualTo("1");    // 1
        assertThat(result[1]).isEqualTo("2");    // 2
        assertThat(result[2]).isEqualTo("Fizz"); // 3
        assertThat(result[4]).isEqualTo("Buzz"); // 5
        assertThat(result[14]).isEqualTo("FizzBuzz"); // 15
    }

    @Test
    public void testFizzBuzzWithZeroInput() {
        // Подготовка
        FizzBuzz fizzBuzz = new FizzBuzz();

        // Действие
        String[] result = fizzBuzz.generateFizzBuzz(0);

        // Проверка
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    public void testAllFizzValuesAreDivisibleBy3() {
        // TODO: Дополнить тест, проверяющий, что все значения "Fizz"
        // соответствуют числам, делящимся на 3 (и не делящимся на 5)
        FizzBuzz fizzBuzz = new FizzBuzz();



        String[] result = fizzBuzz.generateFizzBuzz(30);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(30);
        assertThat(result[2]).isEqualTo("Fizz");
        assertThat(result[5]).isEqualTo("Fizz");
        assertThat(result[8]).isEqualTo("Fizz");
        assertThat(result[11]).isEqualTo("Fizz");
        assertThat(result[17]).isEqualTo("Fizz");
        assertThat(result[20]).isEqualTo("Fizz");
        assertThat(result[23]).isEqualTo("Fizz");
        assertThat(result[26]).isEqualTo("Fizz");

    }

    @Test
    public void testAllBuzzValuesAreDivisibleBy5() {
        // TODO: Дополнить тест, проверяющий, что все значения "Buzz"
        // соответствуют числам, делящимся на 5 (и не делящимся на 3)
        FizzBuzz fizzBuzz = new FizzBuzz();

        String[] result = fizzBuzz.generateFizzBuzz(30);

        assertThat(result[4]).isEqualTo("Buzz");
        assertThat(result[9]).isEqualTo("Buzz");
        assertThat(result[19]).isEqualTo("Buzz");
        assertThat(result[24]).isEqualTo("Buzz");

    }

    @Test
    public void testAllFizzBuzzValuesAreDivisibleBy3And5() {
        // TODO: Дополнить тест, проверяющий, что все значения "FizzBuzz"
        // соответствуют числам, делящимся и на 3, и на 5
        FizzBuzz fizzBuzz = new FizzBuzz();

        String[] result = fizzBuzz.generateFizzBuzz(30);

        assertThat(result[14]).isEqualTo("FizzBuzz");
        assertThat(result[29]).isEqualTo("FizzBuzz");
    }
}