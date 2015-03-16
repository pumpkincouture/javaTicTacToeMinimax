package Java_TTT;

import Java_TTT.boards.Board;
import Java_TTT.participants.GameParticipants;
import Java_TTT.participants.Human;
import Java_TTT.participants.SimpleAI;
import Java_TTT.ui.CommandLineInterface;
import Java_TTT.ui.UserInterface;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineInterfaceTest {
    private ByteArrayOutputStream printedToScreen = new ByteArrayOutputStream();
    private UserInterface ui;
    private Scanner input = new Scanner("3");
    private Board board;
    private PrintStream output = new PrintStream(printedToScreen);
    private MockUserInterface mockUi = new MockUserInterface(output, input);
    private GameParticipants player1 = new Human("X", mockUi);
    private GameParticipants player2 = new SimpleAI("O", board);


    private void fillBoard(String choice, String gamePiece) {
        board.placeMove(choice, gamePiece);
    }

    private String scannerInput(String mockInput) {
        Scanner input = new Scanner(mockInput);
        String choice = input.nextLine();
        return choice;
    }

    @Test
    public void printBoardChoicePrompt() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.promptForBoardSize();
        assertEquals("Please pick your board size (3, 4): ", printedToScreen.toString());
    }

    @Test
    public void printOptionsForPlayerConfiguration() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.chooseGameConfiguration();
        assertEquals("Welcome, please choose your desired player configuration.\n" +
                "1 : Human vs Human\n" +
                "2 : Human vs SimpleAI\n" +
                "3 : Human vs HardAI\n" +
                "4 : SimpleAI vs HardAI\n" +
                "Configuration choice: ", printedToScreen.toString());
    }

    @Test
    public void printWelcomeMessageTest() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printWelcomeMessage(3);
        assertEquals("Welcome to Tic Tac Toe! The first player to get 3 in a row wins!\n", printedToScreen.toString());
    }

    @Test
    public void printChooseStartingPlayerPrompt() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.chooseStartingPlayer("Computer", "Human");
        assertEquals("Please choose the starting player (1 for Computer, 2 for Human): ", printedToScreen.toString());
    }

    @Test
    public void printGamePieceAssignmentTest() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printGamePieceAssignment(player1, player2);
        assertEquals("Human will have the X piece and SimpleAI will have the O piece. X will start. Please indicate your chosen space with a number.\n", printedToScreen.toString());
    }

    @Test
    public void getChosenInputMove() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        scannerInput("4\n");
        ui.captureChoice();
        assertEquals(scannerInput("4\n"), "4");
    }

    @Test
    public void printMessageAfterUserChoseAMove() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printChoice(player1, "3");
        assertEquals("Human has chosen space 3.\n", printedToScreen.toString());
    }

    @Test
    public void printWinnerMessage() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printWinner("O");
        assertEquals("O wins!\n", printedToScreen.toString());
    }

    @Test
    public void printCatsGameMessage() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printCatsGame();
        assertEquals("Cat's game!\n", printedToScreen.toString());
    }

    @Test
    public void printErrorMessage() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printError("hhhhhh");
        assertEquals("hhhhhh is not available, please try again.\n", printedToScreen.toString());
    }

    @Test
    public void printEmpty3x3Board() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        ui.printBoard(board);
        assertEquals("\n" +
                     "------------\n" +
                     "| * | * | * |\n" +
                     "------------\n" +
                     "| * | * | * |\n" +
                     "------------\n" +
                     "| * | * | * |\n" +
                     "------------\n"
                     , printedToScreen.toString());
    }

    @Test
    public void printEmpty4x4Board() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(4);
        ui.printBoard(board);
        assertEquals("\n" +
                     "----------------\n" +
                     "| * | * | * | * |\n" +
                     "----------------\n" +
                     "| * | * | * | * |\n" +
                     "----------------\n" +
                     "| * | * | * | * |\n" +
                     "----------------\n" +
                     "| * | * | * | * |\n" +
                     "----------------\n"
                     , printedToScreen.toString());
    }

    @Test
    public void printBoardWithSomeInputs() {
        this.ui = new CommandLineInterface(output, input);
        this.board = new Board(3);
        fillBoard("1", "O");
        fillBoard("2", "X");
        fillBoard("3", "X");
        fillBoard("5", "O");
        fillBoard("6", "X");
        fillBoard("8", "O");
        fillBoard("9", "X");
        ui.printBoard(board);
        assertEquals("\n" +
                "------------\n" +
                "| O | X | X |\n" +
                "------------\n" +
                "| * | O | X |\n" +
                "------------\n" +
                "| * | O | X |\n" +
                "------------\n"
                , printedToScreen.toString());
    }
}