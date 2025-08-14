package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ShapeDrawerTest {

    final String ERROR = "error";
    private final ShapeDrawer drawer = new ShapeDrawer();

    @Test
    void testDrawSquare() {
        // Подготовка ожидаемого результата для квадрата 3x3
        String expected = "***\n***\n***";

        // Вызов тестируемого метода
        String result = drawer.drawSquare(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawEmptySquare() {
        // Подготовка ожидаемого результата для пустого квадрата 3x3
        String expected = "***\n* *\n***";

        // Вызов тестируемого метода
        String result = drawer.drawEmptySquare(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawTriangle() {
        // Подготовка ожидаемого результата для треугольника высотой 3
        String expected = "*\n**\n***";

        // Вызов тестируемого метода
        String result = drawer.drawTriangle(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testDrawRhombus() {
        // Подготовка ожидаемого результата для ромба размером 3
        String expected = " * \n***\n * ";

        // Вызов тестируемого метода
        String result = drawer.drawRhombus(3);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testWithZeroOrNegativeSize() {
        // TODO: Дополнить тест для проверки поведения методов при передаче нулевого или отрицательного размера
        String expected = ERROR;

        String result = drawer.drawRhombus(-5);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testWithLargeSize() {
        // TODO: Дополнить тест для проверки работы методов с большим размером фигур (например, 10)
        String expected = "*\n**\n***\n****\n*****\n******\n*******\n********\n*********\n**********";

        // Вызов тестируемого метода
        String result = drawer.drawTriangle(10);

        // Проверка результата
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testRhombusWithEvenSize() {
        // TODO: Дополнить тест для проверки поведения метода drawRhombus при передаче четного размера

        String expected = " * \n***\n * ";

        String result = drawer.drawRhombus(2);

        assertThat(result).isEqualTo(expected);
    }
}