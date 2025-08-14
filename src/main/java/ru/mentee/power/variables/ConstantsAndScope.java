package ru.mentee.power.variables;

public class ConstantsAndScope {
    static final int age = 20;
    public static void main(String[] args) {
        int height = 190;
        final int weight = 85;
        {
            int num = 12;

        }
//age = 12;ошибка Невозможно присвоить значение конечной переменной
        System.out.println("Height is " + height);
        height = 180;
        System.out.println("Height is " + height);
        // num = 10;не видна переменная num
        // weight = 10;нельзя присвоить значение переменной weight с типом final
        someMethod();
    }
    public static void someMethod() {
        String name = "Tima";
        System.out.println("My name is " + name);
    }
}
