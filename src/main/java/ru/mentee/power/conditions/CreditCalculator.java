package ru.mentee.power.conditions;

import java.util.Scanner;

public class CreditCalculator {

    final String Type_Mortgage = "mortgage";
    final String Type_Consumer = "consumer";
    final String Type_Auto = "auto";

    final double PERCENT_MORTGAGE = 9;
    final double PERCENT_CONSUMER = 15;
    final double PERCENT_AUTO = 12;

    final int MAX_loanAmount = 10_000_000;
    final int MIN_loanAmount = 10_000;

    final int MAX_creditRate = 850;
    final int MIN_creditRate = 300;

    final int MIN_creditLength = 1;
    final int MAX_creditLength = 360;

    public double calculateMonthlyPayment(double loanAmount, int loanTermMonths, String creditType, int creditScore) {
        double monthlyPayment, percentRate;
        if ((loanAmount > MAX_loanAmount || loanAmount < MIN_loanAmount)||(loanTermMonths > MAX_creditLength || loanTermMonths < MIN_creditLength)
                || (creditScore > MAX_creditRate || creditScore < MIN_creditRate)) {
            return -1;
        }// Временная заглушка
        switch(creditType.toLowerCase()){
            case Type_Mortgage:
                percentRate = PERCENT_MORTGAGE;
                break;
            case Type_Consumer:
                percentRate = PERCENT_CONSUMER;
                break;
            case Type_Auto:
                percentRate = PERCENT_AUTO;
                break;
            default:
                System.out.println("You have entered incorrect type.");
                return -1;
        }
        if (creditScore >= 750) {
            percentRate -= 2.0;
        } else if (creditScore >= 650) {
            percentRate -= 1;
        } else if (creditScore >= 500) {
            // ставка без изменений
        } else {
            percentRate += 3.0;
        }

        double monthlyRate = percentRate / 100 / 12;
        monthlyPayment = loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths))
                / (Math.pow(1 + monthlyRate, loanTermMonths) - 1);
        return monthlyPayment;
    }

    public static void main(String[] args) {
        CreditCalculator calculator = new CreditCalculator();
        Scanner scanner = new Scanner(System.in);
        double loanAmount;
        int loanTermMonths, creditScore;
        System.out.println("Enter the loan amount: ");
        loanAmount = scanner.nextDouble();
        System.out.println("Enter the loan term in months: ");
        loanTermMonths = scanner.nextInt();
        System.out.println("Enter the credit score:");
        creditScore = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the credit type: ");
        String creditType = scanner.nextLine();

        double sum1 = calculator.calculateMonthlyPayment( loanAmount,  loanTermMonths,  creditType,  creditScore);
        int sum = (int)sum1;
        System.out.println("Your monthly payment is " + sum);

        scanner.close();
    }
}