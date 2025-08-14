package ru.mentee.power.loop;

public class ForEachLoopExample {
    public static void main(String[] args) {
        String[] fruits = {"Apple", "Banana", "Orange"};

        System.out.println("Fruits (without index):");
        // Перебираем массив с помощью for-each
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}