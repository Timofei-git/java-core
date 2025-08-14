package ru.mentee.power.conditions;

import java.util.Scanner;
import java.util.Random;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Генерируем случайное число от 1 до 100
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean hasGuessed = false;

        System.out.println("Я загадал число от 1 до 100. Попробуй угадать!");

        while (!hasGuessed && attempts < 7) {
            System.out.print("Введи свой вариант: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == secretNumber) {
                hasGuessed = true;
                System.out.println("Поздравляю! Ты угадал число за " + attempts + " попыток!");
            } else if (guess < secretNumber) {
                System.out.println("Мое число больше.");
            } else {
                System.out.println("Мое число меньше.");
            }
        }

        if (!hasGuessed) {
            System.out.println("К сожалению, ты не угадал. Моё число было: " + secretNumber);
        }

        scanner.close();
    }
}