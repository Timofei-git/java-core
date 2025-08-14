package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UnitConverter {

    private static final double ERROR_CODE = -1.0;

    private static final List<String> LENGTH_UNITS = Arrays.asList("inch", "foot", "centimeter", "metres");
    private static final List<String> WEIGHT_UNITS = Arrays.asList("ounce", "pound", "kilo", "gramme");
    private static final List<String> TEMP_UNITS = Arrays.asList("celsius", "fahrenheit", "kelvin");



    public double convert(double value, String fromUnit, String toUnit) {
        String category;
        String norm_fromUnit = fromUnit.toLowerCase();
        String norm_toUnit = toUnit.toLowerCase();
        if (!areSameCategory(norm_fromUnit, norm_toUnit)){
            return ERROR_CODE;}else{
            double value1;
            category = getCategory(norm_fromUnit);
            switch(category){
                case "length":
                    value1 = convertLength(value, norm_fromUnit,norm_toUnit);
                    break;
                case "weight":
                    value1 = convertWeight(value, norm_fromUnit,norm_toUnit);
                    break;
                case "temp":
                    value1 = convertTemperature(value, norm_fromUnit,norm_toUnit);
                    break;
                default:
                    return ERROR_CODE;
            }
            return value1;
        }
    }


    private boolean areSameCategory(String unit1, String unit2) {
        // TODO: Implement the category check
        // Use the getCategory method for both units and compare the results
        String str1 = getCategory(unit1.toLowerCase());
        String str2 = getCategory(unit2.toLowerCase());
        if (str1 != null && str1.equals(str2)) {
            return true;
        }else{
            return false;
        }
    }


    private String getCategory(String unit){
        // TODO: Implement the category determination
        // Check if the unit exists in the corresponding lists
        if (LENGTH_UNITS.contains(unit.toLowerCase())){
            return "length";
        } else if (WEIGHT_UNITS.contains(unit.toLowerCase())) {
            return "weight";
        }else if (TEMP_UNITS.contains(unit.toLowerCase())){
            return "temp";
        }else
            return null; // Temporary stub - replace with proper implementation
    }

    private double convertLength(double value, String fromUnit, String toUnit) {
        if (value < 0)return ERROR_CODE;
        double meters;

        // Переводим всё в метры
        switch (fromUnit.toLowerCase()) {
            case "inch": meters = value * 0.0254; break;
            case "foot": meters = value * 0.3048; break;
            case "centimeter": meters = value / 100; break;
            case "metres": meters = value; break;
            default: return ERROR_CODE;
        }

        // Из метров в нужную единицу
        switch (toUnit.toLowerCase()) {
            case "inch": return meters / 0.0254;
            case "foot": return meters / 0.3048;
            case "centimeter": return meters * 100;
            case "metres": return meters;
            default: return ERROR_CODE;
        }
    }

    private double convertWeight(double value, String fromUnit, String toUnit) {
        if (value < 0)return ERROR_CODE;
        double kg;

        // Перевод в килограммы
        switch (fromUnit.toLowerCase()) {
            case "ounce": kg = value * 0.0283495; break;
            case "pound": kg = value * 0.453592; break;
            case "kilo": kg = value; break;
            case "gramme": kg = value / 1000; break;
            default: return ERROR_CODE;
        }

        // Из килограммов в целевую
        switch (toUnit.toLowerCase()) {
            case "ounce": return kg / 0.0283495;
            case "pound": return kg / 0.453592;
            case "kilo": return kg;
            case "gramme": return kg * 1000;
            default: return ERROR_CODE;
        }
    }

    private double convertTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.toLowerCase().equals(toUnit.toLowerCase())) return value;

        double celsius;

        // Перевод в цельсии
        switch (fromUnit.toLowerCase()) {
            case "celsius": celsius = value; break;
            case "fahrenheit": celsius = (value - 32) * 5 / 9; break;
            case "kelvin": celsius = value - 273.15; break;
            default: return ERROR_CODE;
        }

        // Из цельсия в целевую
        switch (toUnit.toLowerCase()) {
            case "celsius": return celsius;
            case "fahrenheit": return celsius * 9 / 5 + 32;
            case "kelvin": return celsius + 273.15;
            default: return ERROR_CODE;
        }
    }

    public static void main(String[] args) {
        UnitConverter converter = new UnitConverter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the value:");
        double val = scanner.nextDouble();
        System.out.println("Enter the source unit: ");
        String from = scanner.next().toLowerCase();
        System.out.println("Enter the target unit:");
        String to = scanner.next().toLowerCase();
        double result = converter.convert(val, from, to);
        if (result == ERROR_CODE) {
            System.out.println("Conversion error!");
        } else {
            System.out.println("Result: " + result);
        }

        scanner.close();
    }
}