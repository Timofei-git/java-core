package ru.mentee.power.methods.overloading;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.DecimalFormatSymbols;

/**
 * Класс для форматирования различных типов данных в строку
 */
public class DataFormatter {

    /**
     * Форматирует целое число, разделяя его по разрядам
     *
     * @param value Целое число
     * @return Отформатированное представление числа
     */
    public static String format(int value) {
        if (value == 0) {
            return "0";
        }

        String stringNumber = Integer.toString(value);

        StringBuilder formatted = new StringBuilder();
        int count = 0;


        for (int i = stringNumber.length() - 1; i >= 0; i--) {
            formatted.insert(0, stringNumber.charAt(i));
            count++;

            if (count % 3 == 0 && i != 0) {
                formatted.insert(0, ' ');
            }
        }

        return formatted.toString();
    }

    /**
     * Форматирует целое число с указанием префикса и суффикса
     *
     * @param value Целое число
     * @param prefix Префикс (например, символ валюты)
     * @param suffix Суффикс (например, код валюты)
     * @return Отформатированное представление числа с префиксом и суффиксом
     */
    public static String format(int value, String prefix, String suffix) {
        return prefix + format(value) + " " + suffix;
    }

    /**
     * Форматирует число с плавающей точкой, используя два десятичных знака
     *
     * @param value Число с плавающей точкой
     * @return Отформатированное представление числа
     */
    public static String format(double value) {
        // TODO: Реализуйте метод
        return format(value, 2);
    }

    /**
     * Форматирует число с плавающей точкой с указанным количеством десятичных знаков
     *
     * @param value Число с плавающей точкой
     * @param decimalPlaces Количество знаков после запятой
     * @return Отформатированное представление числа
     */
    public static String format(double value, int decimalPlaces) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');

        StringBuilder pattern = new StringBuilder("#,##0");
        if (decimalPlaces > 0) {
            pattern.append(".");
            for (int i = 0; i < decimalPlaces; i++) {
                pattern.append("0");
            }
        }

        DecimalFormat df = new DecimalFormat(pattern.toString(), symbols);
        return df.format(value);

    }

    /**
     * Форматирует дату в формате "дд.мм.гггг"
     *
     * @param date Дата
     * @return Отформатированное представление даты
     */
    public static String format(Date date) {
        // TODO: Реализуйте метод
       if (date == null) {
           return "";
       }

       return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    /**
     * Форматирует дату согласно указанному шаблону
     *
     * @param date Дата
     * @param pattern Шаблон форматирования (как в SimpleDateFormat)
     * @return Отформатированное представление даты
     */
    public static String format(Date date, String pattern) {
        if (date == null || pattern == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Форматирует список строк, объединяя их через запятую
     *
     * @param items Список строковых элементов
     * @return Объединенная строка
     */
    public static String format(List<String> items) {
        // TODO: Реализуйте метод
        if (items == null) return "";
        String result = String.join(", ", items);
        return result;
    }

    /**
     * Форматирует список строк, объединяя их через указанный разделитель
     *
     * @param items Список строковых элементов
     * @param delimiter Разделитель
     * @return Объединенная строка
     */
    public static String format(List<String> items, String delimiter) {
        // TODO: Реализуйте метод
        String result = String.join(delimiter, items);
        return result;
    }

}