package ru.mentee.power.loop;

import java.util.Scanner; // Импортируем Scanner

public class DoWhileLoopExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        do {
            System.out.println("Doing important action...");
            System.out.print("Repeat?(Yes/No): ");
            answer = scanner.nextLine();
        } while (answer.equalsIgnoreCase("yes")); // Повторяем, пока ответ "да" (игнорируя регистр)

        System.out.println("Ending.");
        scanner.close(); // Не забываем закрыть Scanner
    }
}