package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmartThermostat {

    // TODO: Задайте значение константы для кода ошибки температуры
    private static final double ERROR_TEMP_CODE = -100.0; // Значение можно изменить

    // TODO: Заполните списки для рабочих дней и выходных
    private static final List<String> WEEKDAYS = Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday");
    private static final List<String> WEEKENDS = Arrays.asList("saturday", "sunday");

    /**
     * Определяет целевую температуру для термостата
     *
     * @param timeOfDay время суток в часах (0-23)
     * @param dayOfWeek день недели ("Понедельник", "Вторник", ..., "Воскресенье")
     * @param isOccupied есть ли кто-то дома
     * @param currentOutsideTemperature текущая температура на улице (в градусах Цельсия)
     * @return целевую температуру для установки или ERROR_TEMP_CODE в случае некорректных входных данных
     */
    public double getTargetTemperature(int timeOfDay, String dayOfWeek, boolean isOccupied,
                                       double currentOutsideTemperature) {
        if (timeOfDay > 23 || timeOfDay < 0){
            return ERROR_TEMP_CODE;
        }
        String category;
        String normalizedDay = dayOfWeek.toLowerCase();

        if (WEEKDAYS.contains(normalizedDay)) {
            category = "weekday";
        } else if (WEEKENDS.contains(normalizedDay)) {
            category = "weekend";
        } else {
            return ERROR_TEMP_CODE; // Invalid day
        }

        double targetTemperature;
        switch (category){
            case "weekday":
                if(isOccupied){
                    if((timeOfDay >= 6 && timeOfDay<=8)||(timeOfDay >=18 && timeOfDay <=22)){
                        targetTemperature = 22;
                    } else if (timeOfDay >=9 && timeOfDay<=17){
                        targetTemperature = 20;
                    } else  {
                        targetTemperature = 19;
                    }
                } else {
                    if(timeOfDay >= 6 && timeOfDay<=8){
                        targetTemperature = 18 ;
                    } else if (timeOfDay >=18 && timeOfDay <=22){
                        targetTemperature = 17;
                    } else  {
                        targetTemperature = 16;
                    }
                }
                break;
            case "weekend":
                if(isOccupied){
                    if(timeOfDay >= 7 && timeOfDay<=9){
                        targetTemperature = 23;
                    } else if (timeOfDay >=10 && timeOfDay<=23){
                        targetTemperature = 22;
                    } else  {
                        targetTemperature = 20;
                    }
                } else {
                    if(timeOfDay >= 7 && timeOfDay<=9){
                        targetTemperature = 18;
                    } else if (timeOfDay >=10 && timeOfDay<=23){
                        targetTemperature = 17;
                    } else  {
                        targetTemperature = 16;
                    }
                }
                break;
            default:
                System.out.println("Invalid day");
                return ERROR_TEMP_CODE;
        }
        if (currentOutsideTemperature > 25){
            targetTemperature +=1;
        } else if (currentOutsideTemperature < 0) {
            targetTemperature -=1;
        }
        return targetTemperature;
    }

    public static void main(String[] args) {
        SmartThermostat thermostat = new SmartThermostat();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the time: ");
        int timeOfDay = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the day: ");
        String dayOfWeek = scanner.nextLine().toLowerCase();
        System.out.println("If the house occupied(true/false): ");
        boolean isOccupied = scanner.nextBoolean();
        System.out.println("Enter the current outside temperature: ");
        int currentOutsideTemperature = scanner.nextInt();
        double targetTemp = thermostat.getTargetTemperature(timeOfDay, dayOfWeek, isOccupied, currentOutsideTemperature);
        if (targetTemp == ERROR_TEMP_CODE) {
            System.out.println("Error, some data is incorrect");
        } else {
            System.out.println("Recommended temperature: " + targetTemp + "C");
        }

        scanner.close();
    }
}
