package ru.mentee.power.conditions;

import java.util.Scanner;

public class WeekdayDeterminer {


    public static String getDayDescription(int day) {
        String dayName;
        String dayType;
        String additionalInfo = "";

        switch (day) {
            case 1:
                dayName = "Monday";
                dayType = "workday";
                additionalInfo = "Hard day";
                break;
            case 2:
                dayName = "Tuesday";
                dayType = "workday";
                break;
            case 3:
                dayName = "Wednesday";
                dayType = "workday";
                additionalInfo = "Midweek";
                break;
            case 4:
                dayName = "Thursday";
                dayType = "workday";
                break;
            case 5:
                dayName = "Friday";
                dayType = "workday";
                additionalInfo = "Weekend is coming!";
                break;
            case 6:
                dayName = "Saturday";
                dayType = "weekend";
                break;
            case 7:
                dayName = "Sunday";
                dayType = "weekend";
                break;
            default:
                return "Invalid day of the week";
        }

        String result = dayName + " - " + dayType;
        if (!additionalInfo.isEmpty()) {
            result += "\n" + additionalInfo;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the day number (1-7): ");
        int day = scanner.nextInt();
        String description = getDayDescription(day);
        System.out.println(description);

        scanner.close();
    }
}