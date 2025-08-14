package ru.mentee.power.loop;

public class ForLoopExample {
    public static void main(String[] args) {
        String[] fruits = {"Apple", "Banana", "Orange"};

        System.out.println("Fruits(with index):");
        // Перебираем массив по индексам
        for (int index = 0; index < fruits.length; index++) {
            System.out.println("Index " + index + ": " + fruits[index]);
        }
    }
}