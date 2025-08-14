package ru.mentee.power.conditions;

import java.util.Scanner;

public class TheaterTickets {

    public static final int BASE_TICKET_PRICE = 1000;

    public static double calculateTicketPrice(int age, boolean isStudent, boolean isWeekend) {
        double discount = 0.0;

        if (age < 6) {
            discount = 1.0;
        } else {
            if (age <= 18) {
                discount = Math.max(discount, 0.50);
            }
            if (age > 65) {
                discount = Math.max(discount, 0.30);
            }
            if (isStudent && !isWeekend) {
                discount = Math.max(discount, 0.25);
            }
        }
        return BASE_TICKET_PRICE * (1.0 - discount);
    }

    public static double getAppliedDiscountPercentage(int age, boolean isStudent, boolean isWeekend) {
        double finalPrice = calculateTicketPrice(age, isStudent, isWeekend);
        return ((double) (BASE_TICKET_PRICE - finalPrice) / BASE_TICKET_PRICE) * 100;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Do you have a student ID? (true/false): ");
        boolean isStudent = scanner.nextBoolean();

        System.out.print("Is today a weekend? (true/false): ");
        boolean isWeekend = scanner.nextBoolean();

        double finalPrice = calculateTicketPrice(age, isStudent, isWeekend);
        double discountPercentage = getAppliedDiscountPercentage(age, isStudent, isWeekend);
        double discountAmount = BASE_TICKET_PRICE - finalPrice;

        System.out.printf("Base ticket price: %d RUB%n", BASE_TICKET_PRICE);
        if (discountPercentage > 0) {
            System.out.printf("Your discount: %.0f%% (%.0f RUB)%n", discountPercentage, discountAmount);
        } else {
            System.out.println("No discount applied.");
        }
        System.out.printf("Final ticket price: %.0f RUB%n", finalPrice);

        scanner.close();
    }
}
