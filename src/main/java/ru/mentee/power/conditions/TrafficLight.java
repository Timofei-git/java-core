package ru.mentee.power.conditions;

import java.util.Scanner;

public class TrafficLight {

    public static String getRecommendation(String signal) {
        // TODO: Реализуйте логику с if-else или switch-case здесь, используя строки

        if (signal == null) {
            return "Invalid signal!";
        }
        switch (signal.toLowerCase()) {
            case "red":
                return "Don't move!";
            case "yellow":
                return "Be ready!";
            case "green":
                return "You can go!";
            default:
                return "Invalid signal!";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What's the color of the traffic light?");
        System.out.print("Enter the color of the signal: ");

        String signal = scanner.nextLine();
        String recommendation = getRecommendation(signal);
        System.out.println(recommendation);

        scanner.close();
    }
}