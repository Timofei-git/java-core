package ru.mentee.power.conditions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RockPaperScissorsTest {

    private RockPaperScissors game;

    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private static final String SCISSORS = "scissors";
    private static final String WIN = "Player wins";
    private static final String LOSE = "Computer wins";
    private static final String DRAW = "Draw";
    private static final String ERROR = "Error";

    @BeforeEach
    void setUp() {
        game = new RockPaperScissors();
    }

    @Test
    @DisplayName("Rock beats Scissors")
    void rockBeatsScissors() {
        // Arrange
        String playerChoice = ROCK;
        String computerChoice = SCISSORS;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Fix the expected result!
        // Rock beats Scissors, so the player should win
        assertThat(result).isEqualTo(WIN); // Error here!
    }

    @Test
    @DisplayName("Scissors beats Paper")
    void scissorsBeatsPaper() {
        // Arrange
        String playerChoice = SCISSORS;
        String computerChoice = PAPER;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(WIN);
    }

    @Test
    @DisplayName("Paper beats Rock")
    void paperBeatsRock() {
        // Arrange
        // TODO: Fix the computer's choice!
        // To test that Paper beats Rock, the computer should choose Rock
        String playerChoice = PAPER;
        String computerChoice = ROCK; // Error here!

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(WIN);
    }

    @Test
    @DisplayName("Draw when both choose Rock")
    void drawWhenSameChoiceRock() {
        // Arrange
        String playerChoice = ROCK;
        String computerChoice = ROCK;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Fix the expected result!
        // When both choose the same, it should be a draw
        assertThat(result).isEqualTo(DRAW); // Error here!
    }

    @Test
    @DisplayName("Draw when both choose Paper")
    void drawWhenSameChoicePaper() {
        // Arrange
        String playerChoice = PAPER;
        String computerChoice = PAPER;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Draw when both choose Scissors")
    void drawWhenSameChoiceScissors() {
        // Arrange
        String playerChoice = SCISSORS;
        String computerChoice = SCISSORS;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("Handle invalid player choice")
    void handleInvalidPlayerChoice() {
        // Arrange
        String playerChoice = "Well"; // Invalid choice
        String computerChoice = ROCK;

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Fix the expected result!
        // Invalid choice should return an error
        assertThat(result).isEqualTo(ERROR); // Error here!
    }

    @Test
    @DisplayName("Handle invalid computer choice")
    void handleInvalidComputerChoice() {
        // Arrange
        String playerChoice = ROCK;
        String computerChoice = "Fire"; // Invalid choice

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        // TODO: Fix the expected result!
        // Invalid choice should return an error
        assertThat(result).isEqualTo(ERROR); // Error here!
    }

    @Test
    @DisplayName("Handle invalid choices for both")
    void handleInvalidBothChoices() {
        // Arrange
        String playerChoice = "Water";
        String computerChoice = "Air";

        // Act
        String result = game.determineWinner(playerChoice, computerChoice);

        // Assert
        assertThat(result).isEqualTo(ERROR);
    }

    @RepeatedTest(10) // Repeat 10 times for better randomness check
    @DisplayName("Computer generates a valid move")
    void generateComputerChoiceReturnsValidOption() {
        // Act
        String choice = game.generateComputerMove();

        // Assert
        // TODO: Fix the check for valid options!
        // The method should return one of: Rock, Scissors, or Paper
        assertThat(choice).isIn(ROCK, SCISSORS, PAPER); // Error here!
    }

    @ParameterizedTest
    @CsvSource({ // playerChoice, computerChoice, expectedResult
            "rock, scissors, Player wins",
            "scissors, rock, Computer wins",
            "paper, paper, Draw",
            "scissors, paper, Player wins",
            // TODO: Fix expected results!
            "rock, paper, Computer wins",  // Error here! Paper beats Rock → Computer wins
            "paper, scissors, Computer wins"   // Error here! Scissors beats Paper → Computer wins
    })
    @DisplayName("Various choice combinations for determineWinner")
    void testVariousChoiceCombinationsDetermineWinner(String playerChoice, String computerChoice, String expectedResult) {
        String result = game.determineWinner(playerChoice, computerChoice);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Test playGame method (no mocks)")
    void testPlayGame_ValidChoice() {
        // Arrange
        game.scanner = new java.util.Scanner("rock\n");

        // Act
        String result = game.playOneGame();

        // Assert
        // TODO: Fix the result check!
        // The result should contain info about the computer's choice
        assertThat(result).contains("Computer chose: ");  // Error here!
        //   assertThat(result).isIn(WIN, LOSE, DRAW, ERROR);

        // assertThat(result).containsAnyOf(WIN, LOSE, DRAW, ERROR);

        // Check that computer's choice was valid
        if (result.contains(ROCK)) assertThat(game.determineWinner(ROCK, ROCK)).isEqualTo(DRAW);
        if (result.contains(PAPER)) assertThat(game.determineWinner(ROCK, PAPER)).isEqualTo(LOSE);
        if (result.contains(SCISSORS)) assertThat(game.determineWinner(ROCK, SCISSORS)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("Test playGame method with invalid choice")
    void testPlayGame_InvalidChoice() {
        // Arrange
        game.scanner = new java.util.Scanner("Lizard\n");

        // Act
        String result = game.playOneGame();

        // Assert
        // TODO: Fix expected message!
        // Invalid player choice should output an error message
        assertThat(result).isEqualTo(ERROR); // Error here!
    }

    // Bonus: Use mocks to replace generateComputerChoice
    // To do this, add Mockito dependency in build.gradle:
    // testImplementation 'org.mockito:mockito-core:5.+'
    // testImplementation 'org.mockito:mockito-junit-jupiter:5.+'
    // And use @ExtendWith(MockitoExtension.class) above the test class

  /*  @Test
    @DisplayName("Test playGame method with mock for generateComputerChoice")
    void testPlayGame_WithMock() {
        // Arrange
        RockPaperScissors mockedGame = Mockito.spy(new RockPaperScissors()); // Create spy
        String playerChoice = ROCK;
        String forcedComputerChoice = PAPER; // Force computer to choose Paper
        Mockito.doReturn(forcedComputerChoice).when(mockedGame).generateComputerMove();

        // Act
        String result = mockedGame.playOneGame();

        // Assert
        assertThat(result).isEqualTo(String.format("Computer chose: %s. Result: %s",
                                     forcedComputerChoice, LOSE));
        Mockito.verify(mockedGame, Mockito.times(1)).generateComputerMove(); // Ensure method was called
    }*/
}
