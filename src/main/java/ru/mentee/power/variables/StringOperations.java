package ru.mentee.power.variables;

public class StringOperations {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "World";
        char letter = 'A';
        int number = 10;
        String numberStr = "89";

        System.out.println("Concatenation: \"" + s1 + "\" + \"" + s2 + "\" = \"" + s1 + s2 + "\"");
        System.out.println("Char to string: '" + letter + "' -> \"" + Character.toString(letter) + "\"");
        System.out.println("Number to string: " + number + " -> \"" + Integer.toString(number) + "\"");
        System.out.println("String to number: \"" + numberStr + "\" -> " + Integer.parseInt(numberStr));
        System.out.println("Character from string \"Java\": index 2 -> '" + "Java".charAt(2) + "'");
    }
}
