package ru.mentee.power.datatypes;

public class VariablesOperations {
    public static void main(String[] args) {
        byte num1 = 1;
        short num2 = 2;
        int num3 = 3;
        int num4 = 4;
        String str1 = "Hello";
        String str2 = "World";
        int[] array = {1, 2, 3};
        num3 = (int) num1;
        System.out.println("num3 = " + num3);
        num3 = num2;
        System.out.println("num3 = " + num3);
        System.out.println("num3 + num4 = " +(num3+num4) );
    }
}
