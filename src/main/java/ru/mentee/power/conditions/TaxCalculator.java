package ru.mentee.power.conditions;

import java.util.Scanner;

public class TaxCalculator {


    public static double calculateTax(double income) {
        if (income < 0) {
            return -1;
        }

        double taxAmount;

        if (income <= 100000) {
            taxAmount = income * 0.1; // 10% налог
        } else if (income <= 500000) {
            taxAmount = income * 0.15; // 15% налог
        } else if (income <= 1000000) {
            taxAmount = income * 0.2; // 20% налог
        } else {
            taxAmount = income * 0.25; // 25% налог
        }
        return taxAmount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your annual income in rubles: ");
        double income = scanner.nextDouble();

        if (income < 0) {
            System.out.println("Income cannot be negative.");
        } else {
            double taxAmount = calculateTax(income);
            double netIncome = income - taxAmount;
            double effectiveTaxRate = (income == 0) ? 0 : (taxAmount / income) * 100;

            System.out.printf("Your tax: %.2f RUB%n", taxAmount);
            System.out.printf("Net income after tax: %.2f RUB%n", netIncome);
            System.out.printf("Effective tax rate: %.1f%%%n", effectiveTaxRate);
        }

        scanner.close();
    }
}
