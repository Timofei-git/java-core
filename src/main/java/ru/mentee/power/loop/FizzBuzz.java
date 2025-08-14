package ru.mentee.power.loop;

import java.util.Scanner;

public class FizzBuzz {

    /**
     * Метод возвращает строковое представление чисел от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     * @return массив строк с результатами
     */
    public String[] generateFizzBuzz(int n) {
        String[] result = new String[n];
        for (int i = 1; i <= n; i++){
            if (i % 3 == 0 && i % 5 == 0){
                result[i-1] = "FizzBuzz";
            } else if (i % 5 == 0) {
                result[i-1] = "Buzz";
            } else if (i % 3 == 0 ) {
                result[i-1]="Fizz";
            }else result[i-1] = String.valueOf(i);
        }
        return result;
    }

    /**
     * Метод выводит на экран числа от 1 до n по правилам FizzBuzz
     *
     * @param n верхняя граница диапазона чисел
     */
    public void printFizzBuzz(int n) {

        String[] results = generateFizzBuzz(n);

        // Выводим каждый элемент в консоль
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();

        System.out.println("FizzBuzz for numbers 1 - 30:");
        fizzBuzz.printFizzBuzz(30);
    }
}