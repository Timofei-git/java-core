package ru.mentee.power.loop;

import java.util.Arrays;

public class ArrayStatistics {

    // Массив с данными для анализа
    private final int[] data;

    private static final int ERROR = -1;

    /**
     * Конструктор класса.
     *
     * @param data массив целых чисел для анализа
     */
    public ArrayStatistics(int[] data) {
        // Создаем копию массива, чтобы избежать изменений извне
        this.data = Arrays.copyOf(data, data.length);
    }

    /**
     * Возвращает минимальное значение в массиве.
     *
     * @return минимальное значение или Integer.MAX_VALUE, если массив пуст
     */
    public int findMin() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int min = data[0];
        for (int value : data) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    /**
     * Возвращает максимальное значение в массиве.
     *
     * @return максимальное значение или Integer.MIN_VALUE, если массив пуст
     */
    public int findMax() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int max = data[0];
        for (int value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Вычисляет сумму всех элементов массива.
     *
     * @return сумма элементов
     */
    public int calculateSum() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    /**
     * Вычисляет среднее арифметическое элементов массива.
     *
     * @return среднее арифметическое или 0, если массив пуст
     */
    public double calculateAverage() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int sum = calculateSum();
        return (double) sum / data.length;
    }

    /**
     * Вычисляет медиану массива (среднее значение после сортировки).
     *
     * @return медиана или 0, если массив пуст
     */
    public double calculateMedian() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

        int mid = data.length / 2;
        if (data.length % 2 == 0) {
            return (data[mid - 1] + data[mid]) / 2.0;
        } else {
            return data[mid];
        }
    }

    /**
     * Находит моду массива (наиболее часто встречающееся значение).
     * Если таких значений несколько, возвращает наименьшее из них.
     *
     * @return мода или 0, если массив пуст
     */
    public int findMode() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int count, maxCount = 0, maxNumber = Integer.MAX_VALUE;
        for (int i = 0; i < data.length; i++) {
            count = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[i] == data[j]) {
                    count++;
                }
                if (maxCount < count) {
                    maxCount = count;
                    maxNumber = data[i];
                } else if (maxCount == count) {
                    if (maxNumber > data[i]) {
                        maxNumber = data[i];
                    }
                }
            }
        }
        return maxNumber;
    }

    /**
     * Вычисляет стандартное отклонение элементов массива.
     *
     * @return стандартное отклонение или ERROR, если массив пуст или содержит менее 2 элементов
     */
    public double calculateStandardDeviation() {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }
        if (data.length < 2) {
            return ERROR;
        }

        double average = calculateAverage();
        double sum = 0.0;
        for (int value : data) {
            double diff = value - average;
            sum += diff * diff;
        }
        return Math.sqrt(sum / data.length);
    }

    /**
     * Подсчитывает количество элементов, больших заданного значения.
     *
     * @param value значение для сравнения
     * @return количество элементов, больших value
     */
    public int countGreaterThan(int value) {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int count = 0;
        for (int num : data) {
            if (num > value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Подсчитывает количество элементов, меньших заданного значения.
     *
     * @param value значение для сравнения
     * @return количество элементов, меньших value
     */
    public int countLessThan(int value) {
        int check = check();
        if (check == ERROR) {
            return ERROR;
        }

        int count = 0;
        for (int num : data) {
            if (num < value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Проверяет, содержит ли массив заданное значение.
     *
     * @param value искомое значение
     * @return true, если значение найдено, иначе false
     */
    public boolean contains(int value) {
        int check = check();
        if (check == ERROR) {
            return false;
        }

        for (int num : data) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

    private int check() {
        if (data.length == 0) {
            return ERROR;
        }
        int count = 0;
        for (int value : data) {
            if (value < 0) {
                count++;
            }
        }
        if (count == data.length) {
            return ERROR;
        }
        return 1;
    }

    /** Выводит статистический отчет по массиву. */
    public void printStatisticsReport() {
        System.out.println("===== Statistical Report =====");
        System.out.println("Array size: " + data.length);
        System.out.println("Minimum value: " + findMin());
        System.out.println("Maximum value: " + findMax());
        System.out.println("Sum of elements: " + calculateSum());
        System.out.println("Average value: " + calculateAverage());
        System.out.println("Median: " + calculateMedian());
        System.out.println("Mode: " + findMode());
        System.out.println("Standard deviation: " + calculateStandardDeviation());
        System.out.println("================================");
    }

    public static void main(String[] args) {
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        System.out.println("Initial array: " + Arrays.toString(testData));
        stats.printStatisticsReport();

        System.out.println("Elements greater than 5: " + stats.countGreaterThan(5));
        System.out.println("Elements less than 5: " + stats.countLessThan(5));
        System.out.println("Array contains 7: " + stats.contains(7));
        System.out.println("Array contains 10: " + stats.contains(10));
    }
}
