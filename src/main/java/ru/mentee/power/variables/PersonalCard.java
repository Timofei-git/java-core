package ru.mentee.power.variables;

public class PersonalCard {
    public static void main(String[] args) {
        System.out.println("===Personal Card===");
        String name = "Tima";
        String surname = "Gridin";
        String city = "Mogilev";
        int age, height, weight;
        age = 18;
        weight = 85;
        height = 190;
        boolean student = true;
        char firstLetter = 'T';
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age + " y. o.");
        System.out.println("City: " + city);
        System.out.println("Height: " + height + " sm");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Student: " + student);
        System.out.println("First letter of my name: " + firstLetter);
    }
}
