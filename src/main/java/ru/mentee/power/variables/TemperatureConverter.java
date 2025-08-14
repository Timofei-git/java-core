package ru.mentee.power.variables;

public class TemperatureConverter {
    public static void main(String[] args) {
        final double temp_Kelvin = 273.15;
        double celsius1 = 15;
        double fahrenheit = celsius1*9/5 + 32;
        double celsius2 = (fahrenheit-32)*5/9;
        double celsius3 = temp_Kelvin -273.15;
        System.out.println("fahrenheit =  " + fahrenheit);
        System.out.println("celsius2 = " + celsius2);
        System.out.println("celsius3 = "+ celsius3);
    }
}
