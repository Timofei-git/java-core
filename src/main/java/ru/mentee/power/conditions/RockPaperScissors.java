package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    // TODO: Define constants for moves and game results
    public static final String ROCK = "rock";
    public static final String PAPER = "paper";
    public static final String SCISSORS = "scissors";

    public static final String WIN = "Player wins";
    public static final String LOSE = "Computer wins";
    public static final String DRAW = "Draw";
    public static final String ERROR = "Error";

    // TODO: Create a list of valid moves
    private static final List<String> VALID_MOVES = Arrays.asList("rock", "paper", "scissors");

    private Random random = new Random(); // Random number generator

    Scanner scanner = new Scanner(System.in);
    /**
     * Determines the game outcome based on the player's and computer's moves
     *
     * @param playerMove    the player's move
     * @param computerMove  the computer's move
     * @return the game result (WIN, LOSE, DRAW, or ERROR)
     */
    public String determineWinner(String playerMove, String computerMove) {
        // TODO: Implement winner determination
        // 1. Check the validity of moves using validateMove
        // 2. Check for draw conditions
        // 3. Use nested conditions or switch to determine the winner
        // Rules:
        // - Rock beats scissors
        // - Scissors beat paper
        // - Paper beats rock
        if (validateMove(playerMove.toLowerCase()) && validateMove(computerMove.toLowerCase())){
            if(playerMove.toLowerCase().equals(computerMove.toLowerCase()))return DRAW;
            switch (playerMove.toLowerCase()){
                case ROCK:
                    if (computerMove.toLowerCase().equals(SCISSORS)) {return WIN;}else return LOSE;
                    //break;
                case PAPER:
                    if (computerMove.toLowerCase().equals(SCISSORS)) {return LOSE;}else return WIN;
                    //break;
                case SCISSORS:
                    if (computerMove.toLowerCase().equals(PAPER)) {return WIN;}else return LOSE;
                    //break;
                default:
                    return ERROR;
            }
        }
        return ERROR;
    }

    /**
     * Checks whether a move is valid
     *
     * @param move the move to check
     * @return true if the move is valid, otherwise false
     */
    private boolean validateMove(String move) {
        if (VALID_MOVES.contains(move.toLowerCase())){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Generates a random computer move
     *
     * @return a random move from the list of valid moves
     */
    public String generateComputerMove() {
        // TODO: Implement random computer move generation
        // 1. Create a Random object
        // 2. Generate a random index within the range of VALID_MOVES size
        // 3. Return the list element at that index

        int index = random.nextInt(VALID_MOVES.size());
        return VALID_MOVES.get(index); // Temporary stub - replace with correct implementation
    }

    /**
     * Runs one game session
     */
    public String playOneGame() {
        System.out.print("Enter your choice (rock, paper, scissors): ");
        String playerChoice = scanner.nextLine().trim().toLowerCase();

        if (!validateMove(playerChoice)) {
            System.out.println("Error");
            return ERROR;
        }

        String compMove = generateComputerMove();
        System.out.println("Computer chose: " + compMove);

        String result = determineWinner(playerChoice, compMove);
        System.out.println("The result is: " + result);

        return String.format("Computer chose: %s. The result is: %s", compMove, result);
    }


    /**
     * Starts the game loop
     */
    public void startGameLoop() {
        System.out.println("Welcome to the RockPaperScissors game.");
        System.out.println("Rules: paper beats rock, rock beats scissors, scissors beats paper.");
        System.out.println("Good luck!");

        int playerWins = 0, computerWins = 0, draws = 0;

        while (true) {
            String result = playOneGame();

            if (result.equals(WIN)) playerWins++;
            else if (result.equals(LOSE)) computerWins++;
            else if (result.equals(DRAW)) draws++;

            System.out.print("Do you want to play more? (yes/no): ");
            String answer = scanner.nextLine().toLowerCase();

            if (!answer.equals("yes")) {
                System.out.println("\nGame over! Statistics:");
                System.out.println("Player wins: " + playerWins);
                System.out.println("Computer wins: " + computerWins);
                System.out.println("Draws: " + draws);
                break;
            }
        }
    }


    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.startGameLoop();


    }
}
