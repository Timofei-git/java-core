package ru.mentee.power.conditions;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class LogicalTrainer {

    public static Map<Integer, Boolean> evaluateLogic(boolean likeProgramming, boolean likeMath, boolean likeReading) {
        Map<Integer, Boolean> results = new HashMap<>();

        results.put(1, likeProgramming && likeMath);
        results.put(2, likeProgramming || likeReading);
        results.put(3, likeMath && !likeReading);
        results.put(4, !likeProgramming && !likeMath);
        results.put(5, likeProgramming || likeMath || likeReading);

        int dislikes = 0;
        if (!likeProgramming) dislikes++;
        if (!likeMath) dislikes++;
        if (!likeReading) dislikes++;
        results.put(6, dislikes == 2);

        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Logical Trainer!");
        System.out.println("Please answer a few questions:");

        System.out.print("Do you like programming? (true/false): ");
        boolean likeProgramming = scanner.nextBoolean();

        System.out.print("Do you like math? (true/false): ");
        boolean likeMath = scanner.nextBoolean();

        System.out.print("Do you like reading books? (true/false): ");
        boolean likeReading = scanner.nextBoolean();

        Map<Integer, Boolean> results = evaluateLogic(likeProgramming, likeMath, likeReading);

        System.out.println("\nEvaluation results:");
        System.out.println("Statement 1: You like both programming and math: " + results.get(1));
        System.out.println("Statement 2: You like programming or reading: " + results.get(2));
        System.out.println("Statement 3: You like math but do not like reading: " + results.get(3));
        System.out.println("Statement 4: You do not like programming nor math: " + results.get(4));
        System.out.println("Statement 5: You like at least one of the three: " + results.get(5));
        System.out.println("Statement 6: You dislike exactly two out of three: " + results.get(6));

        scanner.close();
    }
}
