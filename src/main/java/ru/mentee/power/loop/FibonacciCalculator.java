package ru.mentee.power.loop;

import java.util.HashMap;
import java.util.Map;

public class FibonacciCalculator {

    // Кэш для хранения уже вычисленных чисел Фибоначчи
    private final Map<Integer, Long> cache = new HashMap<>();
    /**
     * Вычисляет число Фибоначчи с заданным индексом, используя рекурсивный подход
     * Внимание: этот метод имеет экспоненциальную сложность и не рекомендуется для больших чисел!
     *
     * @param n индекс числа Фибоначчи (n >= 0)
     * @return число Фибоначчи с индексом n
     */
    public long fibonacciRecursive(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Вычисляет число Фибоначчи с заданным индексом, используя итеративный подход
     *
     * @param n индекс числа Фибоначчи (n >= 0)
     * @return число Фибоначчи с индексом n
     */
    public long fibonacciIterative(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long prev = 0;
        long current = 1;

        for (int i = 2; i <= n; i++) {
            long next = prev + current;
            prev = current;
            current = next;
        }

        return current;
    }

    /**
     * Вычисляет число Фибоначчи с заданным индексом, используя кэширование
     *
     * @param n индекс числа Фибоначчи (n >= 0)
     * @return число Фибоначчи с индексом n
     */
    public long fibonacciWithCache(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        // Проверяем, есть ли значение в кэше
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        // Вычисляем значение и сохраняем его в кэше
        long result = fibonacciWithCache(n - 1) + fibonacciWithCache(n - 2);
        cache.put(n, result);

        return result;
    }

    /**
     * Генерирует последовательность первых n чисел Фибоначчи
     *
     * @param n количество чисел Фибоначчи для генерации
     * @return массив с числами Фибоначчи
     */
    public long[] generateFibonacciSequence(int n) {
        if (n <= 0) {
            return new long[0]; // пустой массив для некорректного n
        }
        long[] numbers = new long[n];


        numbers[0] = 0;
        numbers[1] = 1;
        long prev = 0;
        long current = 1;

        for (int i = 2; i < n; i++) {
            long next = prev + current;
            prev = current;
            current = next;
            numbers[i] = current;
        }

        return numbers;
    }

    /**
     * Проверяет, является ли число числом Фибоначчи
     *
     * @param number проверяемое число
     * @return true, если число является числом Фибоначчи, иначе false
     */
    public boolean isFibonacciNumber(long number) {

        if (number == 0||number == 1)return true;
        long prev = 0;
        long current = 1;
        while (current < number){
            long next = prev + current;
            prev = current;
            current = next;
            if ( current == number)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FibonacciCalculator calculator = new FibonacciCalculator();

        System.out.println("Fibonacci sequence (first 10 numbers):");
        long[] sequence = calculator.generateFibonacciSequence(10);
        for (int i = 0; i < sequence.length; i++) {
            System.out.println("F(" + i + ") = " + sequence[i]);
        }

        System.out.println("\nTesting different methods of calculating F(10):");
        System.out.println("Recursive method: " + calculator.fibonacciRecursive(10));
        System.out.println("Iterative method: " + calculator.fibonacciIterative(10));
        System.out.println("Method with caching: " + calculator.fibonacciWithCache(10));

        System.out.println("\nChecking if a number is a Fibonacci number:");
        long[] testNumbers = {8, 10, 13, 15, 21};
        for (long num : testNumbers) {
            System.out.println(num + ": " + calculator.isFibonacciNumber(num));
        }
    }
}
