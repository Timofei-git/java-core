package ru.mentee.power.methods;

/**
 * Утилитарный класс для работы со строками.
 */
public class StringUtils {

    /**
     * Подсчитывает количество вхождений символа в строке.
     *
     * @param str Исходная строка
     * @param target Искомый символ
     * @return Количество вхождений символа
     */
    public static int countChars(String str, char target) {
        // TODO: Реализуйте метод
        if (str==null)return 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * Обрезает строку до указанной максимальной длины.
     * Если строка длиннее maxLength, добавляет "..." в конце.
     *
     * @param str Исходная строка
     * @param maxLength Максимальная длина
     * @return Обрезанная строка
     */
    public static String truncate(String str, int maxLength) {
        if (str==null)return "";

        if (str.length() > maxLength){
            str = org.apache.commons.lang3.StringUtils.left(str, maxLength);
            return str + "...";
        }else{
            return str ;
        }
    }

    /**
     * Проверяет, является ли строка палиндромом.
     * Игнорирует регистр и не-буквенные символы.
     *
     * @param str Исходная строка
     * @return true, если строка является палиндромом, иначе false
     */
    public static boolean isPalindrome(String str) {
        // TODO: Реализуйте метод
        if (str==null)return false;
        String Str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String newStr = "";
        for (int i = Str.length() - 1;i >= 0;i--){
            newStr += Str.charAt(i);
        }
        return newStr.equals(Str);
    }

    /**
     * Заменяет все последовательности пробельных символов одним пробелом.
     * Удаляет пробелы в начале и конце строки.
     *
     * @param str Исходная строка
     * @return Нормализованная строка
     */
    public static String normalizeSpaces(String str) {
        // TODO: Реализуйте метод
        if (str==null)return "";
        return str.trim().replaceAll("\\s+", " ");
    }

    /**
     * Объединяет массив строк, используя указанный разделитель.
     *
     * @param strings Массив строк
     * @param delimiter Разделитель
     * @return Объединенная строка
     */
    public static String join(String[] strings, String delimiter) {
        // TODO: Реализуйте метод
        if (strings==null)return "";
        StringBuilder result = new StringBuilder();

        for (String s : strings) {
            if (s != null) {
                if (result.length() > 0) {
                    result.append(delimiter);
                }
                result.append(s);
            }
        }

        return result.toString();
    }
}