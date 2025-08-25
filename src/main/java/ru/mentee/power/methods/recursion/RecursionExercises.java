package ru.mentee.power.methods.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс с рекурсивными методами для решения различных задач
 */
public class RecursionExercises {

    final long ERROR = -1;
    /**
     * Вычисляет факториал числа n
     *
     * @param n Положительное целое число
     * @return Факториал числа n
     * @throws -1 если n < 0
     */
    public static long factorial(int n) {
        // TODO: Реализуйте рекурсивный метод вычисления факториала
        if (n < 0) {
            return -1;
        }

        if (n == 0 || n == 1){
            return 1;
        }

        return n * factorial(n - 1);
    }

    /**
     * Вычисляет n-ое число в последовательности Фибоначчи
     *
     * @param n Позиция в последовательности Фибоначчи (0-based)
     * @return Число Фибоначчи на позиции n
     * @throws -1 если n < 0
     */
    public static long fibonacci(int n) {
        // TODO: Реализуйте рекурсивный метод вычисления числа Фибоначчи
        if (n < 0) {
            throw new IllegalArgumentException("-1");
        }

        if (n == 0){
            return 0;
        }

        if (n == 1 || n == 2) return 1;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Оптимизированный метод для вычисления n-ого числа Фибоначчи
     *
     * @param n Позиция в последовательности Фибоначчи (0-based)
     * @return Число Фибоначчи на позиции n
     * @throws -1 если n < 0
     */
    public static long fibonacciOptimized(int n, long[] memo) {
        // TODO: Реализуйте оптимизированный метод вычисления числа Фибоначчи
        // Используйте мемоизацию или другой эффективный подход
        if (n < 0) {
            return -1;
        }

        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];

        memo[n] = fibonacciOptimized(n - 1, memo) + fibonacciOptimized(n - 2, memo);
        return memo[n];

    }

    /**
     * Проверяет, является ли строка палиндромом
     *
     * @param str Исходная строка
     * @return true, если строка является палиндромом, иначе false
     */
    public static boolean isPalindrome(String str) {
        // TODO: Реализуйте рекурсивный метод проверки, является ли строка палиндромом
        char[] string = str.trim().replaceAll("\\s", "").toLowerCase().toCharArray();
        for (int i = 0; i < str.length() / 2;i++) {
            if (!(string[string.length - 1 - i] == string[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Вычисляет сумму цифр числа
     *
     * @param n Целое число
     * @return Сумма цифр числа
     */
    public static int sumOfDigits(int n) {
        // TODO: Реализуйте рекурсивный метод для вычисления суммы цифр числа
        if (n == 0) return 0;
        if (n < 0) n *= -1;

        return (n % 10) + sumOfDigits(n / 10);
    }

    /**
     * Возводит число в степень
     *
     * @param base Основание
     * @param exponent Показатель степени
     * @return Результат возведения в степень
     */
    public static double power(double base, int exponent) {
        // TODO: Реализуйте рекурсивный метод возведения в степень
        if (base < 0) {
            return -1;
        }

        if (exponent < 0) {
            return 1.0 / power(base, -exponent);
        }

        if (exponent == 0) return 1;
        if (exponent == 1) return base;

        return base * power(base, exponent - 1);
    }

    /**
     * Находит наибольший общий делитель двух чисел
     *
     * @param a Первое число
     * @param b Второе число
     * @return Наибольший общий делитель
     */
    public static int gcd(int a, int b) {

        if (b == 0) {
            return Math.abs(a);
        }

        return gcd(b, a % b);
    }

    /**
     * Обращает порядок элементов в массиве
     *
     * @param array Исходный массив
     * @param start Начальный индекс для обработки
     * @param end Конечный индекс для обработки
     */
    public static void reverseArray(int[] array, int start, int end) {
        if (array == null) return;
        if (start >= end) return;
        // TODO: Реализуйте рекурсивный метод для обращения массива
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;

        reverseArray(array, start + 1, end - 1);

    }
}