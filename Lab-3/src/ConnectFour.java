import java.util.Arrays;
import java.util.Scanner;

/**
 * This class implements the game Connect Four.
 * Players take turns dropping colored disks into a seven-column, six-row vertically suspended grid.
 * The game ends when a player forms a horizontal, vertical, or diagonal line of four of their own disks.
 */
public class ConnectFour {
    public static void main(String[] args)
    {
        // declare variable for game state (winner found or not).
        boolean winner;

        // Create the game board as a 2D array.
        String[][] board = new String[6][7];

        // Initialize the game board with empty spaces.
        updateBoard(board, -1, "");
        String boardView = createBoard(board);
        System.out.println(boardView);

        // Setup input scanner for player moves.
        Scanner input = new Scanner(System.in);
        String player;
        int play;
        int count = 1;

        // Game loop continues until a winner is found.
        do
        {
            // Determine current player based on the count of turns.
            player = count % 2 == 1 ? "red" : "yellow";
            System.out.printf("Drop a %s disk at column (0-6): ", player);

            // Read the player's chosen column
            play = input.nextInt();

            // Update the board with the player's move.
            updateBoard(board, play, player);

            // Generate and display the current state of the game board.
            boardView = createBoard(board);

            // Check for a winning condition.
            winner = checkWinner(board, player);

            System.out.println(boardView);

            // Increment turn count.
            count++;
        }
        while (!winner);

        // Announce the game winner.
        System.out.printf("The %s player won", player);
    }

    /**
     * Updates the game board with the player's move.
     *
     * @param board  The current state of the game board.
     * @param play   The column where the disk is to be dropped.
     * @param player The current player ("red" or "yellow").
     */
    static void updateBoard(String[][] board, int play, String player)
    {
        char P = ' ';
        if (player.contains("r")) P = 'R';
        else if (player.contains("y")) P = 'Y';

        if (P != ' ')
        {
            // Drop the disk into the specified column at the lowest available row.
            for (int i = board.length - 1; i >= 0; i--)
            {
                if (board[i][play].equals(" "))
                {
                    board[i][play] = String.format("%s", P);
                    break;
                }
            }
        }
        else
        {
            // Initialize the board with empty spaces if play is -1 (beginning of the game).
            for (String[] strings : board)
                Arrays.fill(strings, " ");
        }
    }

    /**
     * Generates a string representation of the current state of the game board.
     *
     * @param board The current state of the game board.
     * @return A string visualizing the game board.
     */
    static String createBoard(String[][] board)
    {
        String boardView = "";
        for (String[] strings : board)
        {
            boardView += "|";
            for (String string : strings)
                boardView += String.format(" %s |", string);
            boardView += "\n";
        }
        boardView += "-----------------------------";
        return boardView;
    }

    /**
     * Checks if the current player has won the game.
     *
     * @param board  The current state of the game board.
     * @param player The current player ("red" or "yellow").
     * @return true if the player has won; false otherwise.
     */
    static boolean checkWinner(String[][] board, String player)
    {
        String P = player.contains("r") ? "R" : "Y";

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j].equals(P))
                    // Check various directions for a winning condition.
                    if (checkDirection(board, i, j, P)) return true;

            }
        }
        return false;
    }

    /**
     * Helper method to check for a win in all directions from a given position.
     *
     * @param board The game board.
     * @param row   The starting row position to check from.
     * @param col   The starting column position to check from.
     * @param P     The player's disk to check for (R or Y).
     * @return true if a winning condition is found; false otherwise.
     */
    private static boolean checkDirection(String[][] board, int row, int col, String P)
    {
        // Check down, left, right, bottom left, and bottom right for winning condition.
        return checkVertical(board, row, col, P) ||
                checkRight(board, row, col, P) ||
                checkLeft(board, row, col, P) ||
                checkDiagonalLeft(board, row, col, P) ||
                checkDiagonalRight(board, row, col, P);
    }

    /**
     * Checks for a win condition in a vertical line from the current position.
     *
     * @param board The game board.
     * @param row   The row position to start checking from.
     * @param col   The column position to start checking from.
     * @param P     The player's piece (R or Y).
     * @return true if a vertical win is found; false otherwise.
     */
    private static boolean checkVertical(String[][] board, int row, int col, String P)
    {
        int count = 0;
        // Check to the bottom
        for (int i = row; i < board.length && i < row + 4; i++)
        {
            if (board[i][col].equals(P))
            {
                count++;
                if (count == 4) return true;
            }
            else break;
        }
        return false;
    }

    /**
     * Checks for a win condition in a horizontal line from the current position.
     *
     * @param board The game board.
     * @param row   The row position to start checking from.
     * @param col   The column position to start checking from.
     * @param P     The player's piece (R or Y).
     * @return true if a horizontal win is found; false otherwise.
     */
    private static boolean checkRight(String[][] board, int row, int col, String P)
    {
        int count = 0;
        // Check to the right
        for (int j = col; j < board[0].length && j < col + 4; j++)
        {
            if (board[row][j].equals(P))
            {
                count++;
                if (count == 4) return true;
            }
            else break;
        }
        return false;
    }

    /**
     * Checks for a win condition in a horizontal line from the current position.
     *
     * @param board The game board.
     * @param row   The row position to start checking from.
     * @param col   The column position to start checking from.
     * @param P     The player's piece (R or Y).
     * @return true if a horizontal win is found; false otherwise.
     */
    private static boolean checkLeft(String[][] board, int row, int col, String P)
    {
        int count = 0;
        // Check to the left
        for (int j = col; j > 0 && j > col-4; j--)
        {
            if (board[row][j].equals(P))
            {
                count++;
                if (count == 4) return true;
            }
            else break;
        }
        return false;
    }

    /**
     * Checks for a win condition in a diagonal line going bottom-left from the current position.
     *
     * @param board The game board.
     * @param row   The row position to start checking from.
     * @param col   The column position to start checking from.
     * @param P     The player's piece (R or Y).
     * @return true if a diagonal bottom-left win is found; false otherwise.
     */
    private static boolean checkDiagonalLeft(String[][] board, int row, int col, String P)
    {
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            if (row + i < board.length && col - i >= 0 && board[row + i][col - i].equals(P))
            {
                count++;
                if (count == 4) return true;
            }
            else break;
        }
        return false;
    }

    /**
     * Checks for a win condition in a diagonal line going bottom-right from the current position.
     *
     * @param board The game board.
     * @param row   The row position to start checking from.
     * @param col   The column position to start checking from.
     * @param P     The player's piece (R or Y).
     * @return true if a diagonal bottom-right win is found; false otherwise.
     */
    private static boolean checkDiagonalRight(String[][] board, int row, int col, String P)
    {
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            if (row + i < board.length && col + i < board[0].length && board[row + i][col + i].equals(P))
            {
                count++;
                if (count == 4) return true;
            }
            else break;
        }
        return false;
    }
}

