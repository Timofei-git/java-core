       package ru.mentee.power.loop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

class NumberGuessingGameTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private NumberGuessingGame game;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        if (game != null) {
            try {
                game.close();
            } catch (Exception e) {
                // Игнорируем ошибки закрытия в тестах
            }
        }
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testPlayRoundGuessCorrectly() {
        String input = generateSafeInput("50\n25\n75\n60\n65\n62\n63\n61\n42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);

        assertThatNoException().isThrownBy(() -> game.startGame());

        String output = outputStream.toString();

        // Проверяем обязательные сообщения
        assertThat(output).contains("I guessed a number from 1 to 100");
        assertThat(output).containsPattern("Congratulations! You guessed the number \\d+ in \\d+ attempts");

        // Проверяем наличие подсказок или мгновенного угадывания
        assertThat(output).satisfiesAnyOf(
                o -> assertThat(o).contains("I guessed a number greater"),
                o -> assertThat(o).contains("I guessed a number less"),
                o -> assertThat(o).containsPattern("Congratulations! You guessed the number \\d+ in 1 attempts")
        );
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testInvalidInputHandling() {
        String input = generateSafeInput("abc\n-5\n200\n42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);

        assertThatNoException().isThrownBy(() -> game.startGame());

        String output = outputStream.toString();
        assertThat(output).contains("Please enter an integer");
        assertThat(output).contains("Please enter a number between 1 and 100");
        assertThat(output).containsPattern("Congratulations! You guessed the number \\d+");
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testBoundaryValueLow() {
        String input = generateSafeInput("1\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(1);
        game.startGame();

        String output = outputStream.toString();
        assertThat(output).contains("Congratulations! You guessed the number 1 in 1 attempts");
    }


    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testPlayAgainNo() {
        String input = generateSafeInput("42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);
        game.startGame();

        String output = outputStream.toString();
        assertThat(output).contains("Games played: 1");
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testHintsGreater() {
        String input = generateSafeInput("10\n42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);
        game.startGame();

        String output = outputStream.toString();
        assertThat(output).contains("I guessed a number greater");
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testMultipleInvalidInputs() {
        String input = generateSafeInput("abc\nxyz\n-10\n0\n101\n999\n42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);
        game.startGame();

        String output = outputStream.toString();
        assertThat(output).contains("Please enter an integer");
        assertThat(output).contains("Please enter a number between 1 and 100");
        assertThat(output).contains("Congratulations!");
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGameMessages() {
        String input = generateSafeInput("42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);
        game.startGame();

        String output = outputStream.toString();
        assertThat(output).contains("I guessed a number from 1 to 100");
        assertThat(output).contains("Enter a number:");
        assertThat(output).contains("Do you want to play again?");
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testStatisticsFormat() {
        String input = generateSafeInput("42\nno\n");
        setInput(input);

        game = new TestableNumberGuessingGame(42);
        game.startGame();

        String output = outputStream.toString();
        assertThat(output).containsPattern("Minimum number of attempts: \\d+");
        assertThat(output).containsPattern("Maximum number of attempts: \\d+");
        assertThat(output).containsPattern("Average number of attempts: \\d+\\.\\d+");
        assertThat(output).containsPattern("Games played: \\d+");
    }


    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testConstructor() {
        game = new NumberGuessingGame();
        assertThat(game).isNotNull();
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testCloseMethod() {
        game = new NumberGuessingGame();
        assertThatNoException().isThrownBy(() -> game.close());
        assertThatNoException().isThrownBy(() -> game.close()); // Повторный вызов
    }

    // Вспомогательные методы

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    private String generateSafeInput(String primaryInput) {
        StringBuilder safeInput = new StringBuilder(primaryInput);

        // Добавляем запасные данные для случая, если основного ввода недостаточно
        for (int i = 1; i <= 100; i++) {
            safeInput.append(i).append("\n");
        }

        // Добавляем множественные "no" для завершения любых циклов
        for (int i = 0; i < 10; i++) {
            safeInput.append("no\n");
        }

        return safeInput.toString();
    }

    private int extractNumber(String[] lines, String prefix) {
        for (String line : lines) {
            if (line.trim().startsWith(prefix)) {
                String numberPart = line.substring(line.indexOf(prefix) + prefix.length()).trim();
                // Извлекаем только цифры из строки
                StringBuilder digits = new StringBuilder();
                for (char c : numberPart.toCharArray()) {
                    if (Character.isDigit(c)) {
                        digits.append(c);
                    } else if (digits.length() > 0) {
                        break; // Прекращаем, если встретили не-цифру после цифр
                    }
                }
                if (digits.length() > 0) {
                    return Integer.parseInt(digits.toString());
                }
            }
        }
        throw new AssertionError("Could not find line with prefix: " + prefix);
    }

    // Тестовые классы

    static class TestableNumberGuessingGame extends NumberGuessingGame {
        private final int targetNumber;

        TestableNumberGuessingGame(int targetNumber) {
            this.targetNumber = targetNumber;
        }

        @Override
        protected Random createRandom() {
            return new PredictableRandom(targetNumber);
        }
    }

    static class PredictableRandom extends Random {
        private final int targetNumber;

        PredictableRandom(int targetNumber) {
            this.targetNumber = targetNumber;
        }

        @Override
        public int nextInt(int bound) {
            // Для диапазона 100 (числа от 1 до 100)
            if (bound == 100) {
                return Math.max(0, Math.min(targetNumber - 1, 99));
            }
            // Для других случаев возвращаем безопасное значение
            return 0;
        }

        @Override
        public int nextInt() {
            return targetNumber;
        }
    }
}