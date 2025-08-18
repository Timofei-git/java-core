package ru.mentee.power.methods;

public class StringUtilsDemo {
    public static void main(String[] args) {
        // Example of using the countChars method
        String text = "Hello, World!";
        char target = 'l';
        int count = StringUtils.countChars(text, target);
        System.out.println("The character '" + target + "' appears " + count + " times in the string \"" + text + "\"");

        // Example of using the truncate method
        String longText = "This is a long text that needs to be truncated";
        String truncated = StringUtils.truncate(longText, 10);
        System.out.println("Original text: \"" + longText + "\"");
        System.out.println("Truncated text: \"" + truncated + "\"");

        // Example of using the isPalindrome method
        String palindrome = "A rose fell on Azora's paw"; // adapted to English meaning
        boolean isPal = StringUtils.isPalindrome(palindrome);
        System.out.println("The string \"" + palindrome + "\" " + (isPal ? "is" : "is not") + " a palindrome");

        // Example of using the normalizeSpaces method
        String withExtraSpaces = "   Too    many     extra spaces   ";
        String normalized = StringUtils.normalizeSpaces(withExtraSpaces);
        System.out.println("Original string: \"" + withExtraSpaces + "\"");
        System.out.println("After normalization: \"" + normalized + "\"");

        // Example of using the join method
        String[] words = {"Apple", "Banana", "Pear", "Orange"};
        String joined = StringUtils.join(words, ", ");
        System.out.println("Joined words: " + joined);
    }
}
