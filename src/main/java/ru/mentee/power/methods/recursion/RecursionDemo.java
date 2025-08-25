package ru.mentee.power.methods.recursion;

import java.util.Arrays;

public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println("=== Factorial ===");
        System.out.println("5! = " + RecursionExercises.factorial(5));

        System.out.println("\n=== Fibonacci Numbers ===");
        System.out.println("Naive implementation:");
        for (int i = 0; i <= 10; i++) {
            System.out.print(RecursionExercises.fibonacci(i) + " ");
        }
        long[] memo = new long[11];
        System.out.println("\nOptimized implementation:");
        // TODO: Вывести оптимизированные числа Фибоначчи от 0 до 10
        long[] result = new long[11];
        for (int i = 0;i <= 10;i++){
            result[i] = (int) RecursionExercises.fibonacciOptimized(i, memo);
        }
        System.out.println(Arrays.toString(result));

        System.out.println("\n\n=== Palindrome Check ===");
        // TODO: Создать массив тестовых строк и проверить, являются ли они палиндромами
        String[] array = {"madam", "apple", "145541"};
        boolean res;
        String resultInString= "";
        for (int i = 0; i < array.length;i++){
            res = RecursionExercises.isPalindrome(array[i]);
            if (res) {
                resultInString = resultInString + array[i] + " is palindrome.\n";
            } else {
                resultInString = resultInString + array[i] + " is not palindrome.\n";
            }
        }
        System.out.println(resultInString);

        System.out.println("\n=== Sum of Digits ===");
        // TODO: Вычислить и вывести сумму цифр числа 12345
        int sum = RecursionExercises.sumOfDigits(12345);
        System.out.println("Sum of 12345: " + sum);

        System.out.println("\n=== Power Function ===");
        // TODO: Вычислить и вывести результаты возведения числа 2 в степени 10 и -3
        double resultOfSqr = RecursionExercises.gcd(2, 10);
        System.out.println("Result of 2^10: " + resultOfSqr);
        resultOfSqr = RecursionExercises.gcd(2, -3);
        System.out.println("Result of 2^-3: " + resultOfSqr);

        System.out.println("\n=== Greatest Common Divisor ===");
        // TODO: Вычислить и вывести НОД чисел 48 и 36
        int resultOfNod = RecursionExercises.gcd(48, 36);
        System.out.println("The gcd of 48 and 36 is: " + resultOfNod);

        System.out.println("\n=== Array Reversal ===");
        // TODO: Создать массив, вывести его, обратить и снова вывести
        int[] arr = {1, 2 , 25, 48, 78, 95 };
        System.out.println("Start array: " + Arrays.toString(arr));
        RecursionExercises.reverseArray(arr, 0, arr.length - 1);
        System.out.println("Final array: " + Arrays.toString(arr));
    }
}
