package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ArrayStatisticsTest {

    final int ERROR = -1;

    @Test
    void testFindMinMax() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.findMin()).isEqualTo(1);
        assertThat(stats.findMax()).isEqualTo(9);
    }

    @Test
    void testCalculateSumAndAverage() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.calculateSum()).isEqualTo(51);
        assertThat(stats.calculateAverage()).isEqualTo(5.1);
    }

    @Test
    void testCalculateMedian() {
        // Подготовка
        int[] testData1 = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6}; // Четное количество элементов
        int[] testData2 = {5, 7, 2, 9, 3, 5, 1, 8, 5}; // Нечетное количество элементов

        ArrayStatistics stats1 = new ArrayStatistics(testData1);
        ArrayStatistics stats2 = new ArrayStatistics(testData2);

        // Проверка
        assertThat(stats1.calculateMedian()).isEqualTo(5.0);
        assertThat(stats2.calculateMedian()).isEqualTo(5.0);
    }

    @Test
    void testFindMode() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.findMode()).isEqualTo(5); // 5 встречается 3 раза
    }

    @Test
    void testCalculateStandardDeviation() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка (с округлением до 2 знаков после запятой)
        assertThat(Math.round(stats.calculateStandardDeviation() * 100) / 100.0).isEqualTo(2.43);
    }

    @Test
    void testCountGreaterAndLessThan() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.countGreaterThan(5)).isEqualTo(4); // 7, 9, 8, 6
        assertThat(stats.countLessThan(5)).isEqualTo(3); // 2, 3, 1
    }

    @Test
    void testContains() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.contains(7)).isTrue();
        assertThat(stats.contains(10)).isFalse();
    }

    @Test
    void testEmptyArray() {
        // TODO: Реализовать тест для проверки работы методов с пустым массивом
        int[] testData = {};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.findMin()).isEqualTo(ERROR);
        assertThat(stats.countGreaterThan(5)).isEqualTo(ERROR);
    }

    @Test
    void testSingleElementArray() {
        // TODO: Реализовать тест для проверки работы методов с массивом из одного элемента
        int[] testData = {1};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.calculateStandardDeviation()).isEqualTo(ERROR);
    }

    @Test
    void testArrayWithDuplicates() {
        // TODO: Реализовать тест для проверки работы методов с массивом, содержащим только дубликаты
        int[] testData = {5, 5, 5, 5, 5};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.findMode()).isEqualTo(5);


    }

    @Test
    void testArrayWithNegativeValues() {
        // TODO: Реализовать тест для проверки работы методов с массивом, содержащим отрицательные значения

        int[] testData = {-5, -1, -2};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.calculateStandardDeviation()).isEqualTo(ERROR);
        assertThat(stats.findMin()).isEqualTo(ERROR);
    }
}
