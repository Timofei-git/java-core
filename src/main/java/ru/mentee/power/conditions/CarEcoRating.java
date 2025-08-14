package ru.mentee.power.conditions;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CarEcoRating {

    // TODO: Set the constant values for the error code, minimum and maximum ratings
    public static final int ERROR_CODE = -1;
    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 100;

    // TODO: Set the year from which the age penalty begins (e.g., 2020)
    private static final int EURO_STANDARD_YEAR_THRESHOLD = 2020; // Replace with a valid value

    // TODO: Set the constant values for the base ratings of different fuel types
    private static final int BASE_RATING_ELECTRIC = 90; // Replace with a valid value
    private static final int BASE_RATING_HYBRID = 70; // Replace with a valid value
    private static final int BASE_RATING_DIESEL = 40; // Replace with a valid value
    private static final int BASE_RATING_PETROL = 30; // Replace with a valid value

    // TODO: Fill in the list of valid fuel types
    private static final List<String> VALID_FUEL_TYPES = Arrays.asList("petrol", "diesel", "hybrid", "electric");

    /**
     * Calculates the environmental rating of a car based on its characteristics
     *
     * @param fuelType          fuel type ("Petrol", "Diesel", "Hybrid", "Electric")
     * @param engineVolume      engine displacement in liters
     * @param fuelConsumption   fuel consumption in l/100km or kWh/100km
     * @param yearOfManufacture year the car was manufactured
     * @param isEuroCompliant   whether the car complies with Euro-6 standard
     * @return eco-rating of the car from MIN_RATING to MAX_RATING or ERROR_CODE in case of error
     */
    public int calculateEcoRating(String fuelType, double engineVolume,
                                  double fuelConsumption, int yearOfManufacture,
                                  boolean isEuroCompliant) {
        // TODO: Implement the method according to the requirements
        // 1. Validate the input data using validateInput
        // 2. Determine the base rating using getBaseFuelTypeRating
        // 3. Apply modifiers using applyRatingModifiers
        // 4. Clamp the final rating using clampRating

        if (validateInput(fuelType, engineVolume,
                fuelConsumption, yearOfManufacture)) {
            int baseRating = getBaseFuelTypeRating(fuelType);
            int rating = applyRatingModifiers(baseRating, fuelType, engineVolume, fuelConsumption, yearOfManufacture, isEuroCompliant);
            rating = clampRating(rating);
            return rating;


        }
        return ERROR_CODE; // Temporary stub - replace with proper implementation
    }

    /**
     * Validates the input data
     *
     * @param fuelType          fuel type
     * @param engineVolume      engine displacement
     * @param fuelConsumption   fuel consumption
     * @param yearOfManufacture year of manufacture
     * @return true if all data is valid, false otherwise
     */
    private boolean validateInput(String fuelType, double engineVolume,
                                  double fuelConsumption, int yearOfManufacture) {
        if (!VALID_FUEL_TYPES.contains(fuelType.toLowerCase()) || engineVolume < 0 || fuelConsumption < 0 || yearOfManufacture > Year.now().getValue() || (fuelType.equals("electric") && engineVolume != 0)) {
            return false;
        } else
            return true;
    }

    /**
     * Determines the base rating depending on the fuel type
     *
     * @param fuelType fuel type
     * @return base rating or ERROR_CODE if the fuel type is unknown
     */
    private int getBaseFuelTypeRating(String fuelType) {
        // TODO: Implement base rating determination using a switch statement
        // For each fuel type, return the corresponding constant
        switch (fuelType.toLowerCase()) {
            case "petrol":
                return BASE_RATING_PETROL;
            case "diesel":
                return BASE_RATING_DIESEL;
            case "hybrid":
                return BASE_RATING_HYBRID;
            case "electric":
                return BASE_RATING_ELECTRIC;
            default:
                return ERROR_CODE;
        }
    }

    /**
     * Applies rating modifiers based on the characteristics of the car
     *
     * @param baseRating        base rating
     * @param fuelType          fuel type
     * @param engineVolume      engine displacement
     * @param fuelConsumption   fuel consumption
     * @param yearOfManufacture year of manufacture
     * @param isEuroCompliant   Euro-6 compliance
     * @return final rating after applying modifiers
     */
    private int applyRatingModifiers(int baseRating, String fuelType, double engineVolume,
                                     double fuelConsumption, int yearOfManufacture,
                                     boolean isEuroCompliant) {

        int currentYear = Year.now().getValue();
        double rating = baseRating;

        if (isEuroCompliant) rating += 10;
        int yearsOverThreshold = yearOfManufacture > EURO_STANDARD_YEAR_THRESHOLD
                ? 0
                :EURO_STANDARD_YEAR_THRESHOLD - yearOfManufacture;
        switch (fuelType.toLowerCase()) {
            case "petrol":
                rating = rating - (engineVolume * 5) - (fuelConsumption * 2) - yearsOverThreshold;
                break;
            case "diesel":
                rating = rating - (engineVolume * 5) - (fuelConsumption * 2) - yearsOverThreshold;
                break;
            case "hybrid":
                if (fuelConsumption < 5) rating += 15;
                rating = rating - (engineVolume * 5) - (fuelConsumption * 2) - yearsOverThreshold;
                break;
            case "electric":
                rating = rating - (fuelConsumption / 2) - yearsOverThreshold;
                break;
            default:
                return ERROR_CODE;
        }

        return (int) Math.round(rating);
    }


    private int clampRating(int rating) {
        if (rating > MAX_RATING) return MAX_RATING;
        if (rating < MIN_RATING) return MIN_RATING;
        return rating;
    }



    public static void main(String[] args) {
        CarEcoRating ecoRating = new CarEcoRating();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter fuel type :");
        String fuelType = scanner.nextLine();


        System.out.println("Enter engine volume:");
        double engineVolume = scanner.nextDouble();

        System.out.println("Enter fuel consumption:");
        double fuelConsumption = scanner.nextDouble();

        System.out.println("Enter year of manufacture:");
        int year = scanner.nextInt();

        System.out.println("Is the car Euro-6 compliant? (true/false):");
        boolean isEuro = scanner.nextBoolean();

        int rating = ecoRating.calculateEcoRating(fuelType, engineVolume, fuelConsumption, year, isEuro);

        if (rating == ERROR_CODE) {
            System.out.println("invalid input");
        } else {
            System.out.println("Eco Rating of the car: " + rating + " / " + MAX_RATING);
        }

        scanner.close();
    }
}
