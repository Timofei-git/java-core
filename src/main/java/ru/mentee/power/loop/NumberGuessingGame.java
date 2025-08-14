package ru.mentee.power.loop;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private final Random random;
    private final Scanner scanner = new Scanner(System.in);

    // Статистика
    private int gamesPlayed = 0;
    private int minAttempts = Integer.MAX_VALUE;
    private int maxAttempts = 0;
    private int totalAttempts = 0;

    /**
     * Конструктор по умолчанию
     */
    public NumberGuessingGame() {
        this.random = createRandom();
    }

    /**
     * Создает объект Random для генерации случайных чисел
     * Метод выделен для возможности тестирования
     *
     * @return новый объект Random
     */
    protected Random createRandom() {
        return new Random();
    }

    /**
     * Запускает игру "Угадай число"
     */
    public void startGame() {
        // TODO: Реализовать метод запуска игры
        do {
            int attempts = playRound();
            updateStatistics(attempts);
        }while(askPlayAgain());
        showStatistics();
    }

    /**
     * Запускает один раунд игры
     *
     * @return количество попыток, потребовавшихся для угадывания
     */
    public int playRound() {
        // Загадываем число от 1 до 100
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean guessed = false;

        System.out.println("I guessed a number from 1 to 100. Try to guess it!");

        int number;
        // TODO: Реализовать игровой цикл с использованием do-while
        do{
            System.out.println("Enter a number: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter an integer");
                scanner.next(); // пропускаем некорректный ввод
                continue;
            }

            number = scanner.nextInt();

            if (number < 1 || number > 100) {
                System.out.println("Please enter a number between 1 and 100");
                continue;
            }
            attempts++;
            if (number == secretNumber){
                guessed= true;
                System.out.println("Congratulations! You guessed the number " + secretNumber + " in " + attempts + " attempts");
            }else{
                String result = (number > secretNumber)?"I guessed a number greater" : "I guessed a number less";
                System.out.println(result);
            }
        }while(guessed!=true);

        return attempts;
    }

    /**
     * Обновляет статистику игр
     *
     * @param attempts количество попыток в текущей игре
     */
    private void updateStatistics(int attempts) {
        // TODO: Реализовать обновление статистики
        // do{
        totalAttempts+=attempts;
        if (attempts < minAttempts){
            minAttempts = attempts;
        }
        if ( attempts > maxAttempts ){
            maxAttempts = attempts;
        }
        gamesPlayed++;
        //} while (askPlayAgain());
    }

    /**
     * Выводит текущую статистику игр
     */
    public void showStatistics() {
        // TODO: Реализовать вывод статистики
        System.out.println("Minimum number of attempts: " + minAttempts);
        System.out.println("Maximum number of attempts: " + maxAttempts);
        System.out.println("Average number of attempts: " + (double)totalAttempts/gamesPlayed);
        System.out.println("Games played: " + gamesPlayed);


    }

    /**
     * Спрашивает пользователя, хочет ли он сыграть еще раз
     *
     * @return true, если пользователь хочет продолжить, иначе false
     */
    private boolean askPlayAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        String answer = scanner.next().toLowerCase();
        return answer.equals("да") || answer.equals("yes") || answer.equals("y");
    }

    /**
     * Закрывает ресурсы
     */
    public void close() {
        scanner.close();
    }



    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();

        try {
            game.startGame();
        } finally {
            game.close();
        }
    }
}
