package ru.mentee.power.variables;

public class TypeCalculator {
    public static void main(String[] args) {
        byte num1 = 1;
        short num2 = 2;
        int num3 = 3;
        long num4 = 1000L;
        float num5 = 3f;
        double num6 = 6.5;

        System.out.println("byte (1) + short (2) = " + (num1 + num2)); // int
        System.out.println("int (3) * byte (1) = " + (num3 * num1)); // int
        System.out.println("double (6.5) + int (3) = " + (num6 + num3)); // doubleЫ
        System.out.println("int (7) / int (2) = " + (7 / 2)); // int = 3
        System.out.println("double (7) / int (2) = " + (7.0 / 2)); // double = 3.5
    }
}
