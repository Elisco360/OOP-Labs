import java.util.Random;
import java.util.Scanner;

/**
 * Hangman game implementation in Java.
 * This program allows users to play the classic hangman game where they guess letters to uncover a hidden word.
 */
public class Hangman
{
    public static void main(String[] args)
    {
        // Default words for the game, used if no command-line arguments are provided.
        String[] words = {"great", "is", "thy", "faithfulness"};

        // Replace default words with command-line arguments if any are provided.
        if (args.length != 0)
        {
            words = new String[args.length];
            System.arraycopy(args, 0, words, 0, args.length);
        }

        // Scanner to read user's choice to replay the game.
        Scanner replay = new Scanner(System.in);
        char userChoice = 'y';

        do
        {
            // Play a round of the game.
            playGame(words);

            // Ask the user if they want to play another round.
            System.out.print("Do you want to guess another word? Enter y or n > ");
            userChoice = replay.next().charAt(0);
        }
        while (userChoice == 'y');
    }

    /**
     * Plays a single game of hangman using the provided array of words.
     * The method selects a random word from the array and prompts the user to guess letters.
     *
     * @param words An array of strings from which a word is randomly selected for the game.
     */
    static void playGame(String[] words)
    {
        // Select a random word for the game.
        Random random = new Random();
        String word = words[random.nextInt(words.length)];


        Scanner input = new Scanner(System.in);

        // Initialize the user's guess with asterisks representing each letter of the word.
        String userGuess = new String(new char[word.length()]).replace('\0', '*');

        // Variables for the game logic.
        char guess; // The user's current guess.
        String positions; // Positions of the guessed letter in the word.
        String temp; // Temporary string for updating the user's guess.
        int misses = 0; // Count of incorrect guesses.

        // Game loop continues until the user guesses the word.
        while (!userGuess.equals(word))
        {
            // Prompt the user to guess a letter.
            System.out.printf("(Guess) Enter a letter in word %s > ", userGuess);
            guess = input.next().charAt(0);

            // Get positions of the guessed letter in the word.
            positions = getAllPositions(word, guess);

            // If the guess is correct, update the user's guess to reveal the guessed letters.
            if (!positions.contains("-1"))
            {
                temp = userGuess;
                userGuess = "";

                if (temp.indexOf(guess) != -1)
                {
                    System.out.println(guess + " is already in the word");
                    userGuess = temp;
                    continue;
                }

                for (int i = 0; i < temp.length(); i++)
                {
                    if (positions.contains(Integer.toString(i))) userGuess += word.charAt(i);
                    else userGuess += temp.charAt(i);
                }
            }
            else
            {
                // If the guess is incorrect, inform the user and or increment the miss count.
                System.out.println(guess + " is not in the word");
                misses++;
            }
        }

        // Display the outcome of the game.
        if (misses == 1) System.out.printf("The word is %s. You missed %d time", word, misses);
        else System.out.printf("The word is %s. You missed %d times", word, misses);
        System.out.println();
    }

    /**
     * Returns a string containing the positions of a guessed letter in the word.
     * If the letter is not in the word, returns "-1".
     *
     * @param word  The word being guessed.
     * @param guess The letter guessed by the user.
     * @return A string with indices of the guessed letter in the word, or "-1" if the guess is incorrect.
     */
    static String getAllPositions(String word, char guess)
    {
        String ids = "";
        for (int i = 0; i < word.length(); i++)
        {
            if (word.charAt(i) == guess) ids += Integer.toString(i);
        }

        if (ids.isEmpty()) ids = "-1";

        return ids;
    }

    /**
     * Gets the next position of a guessed letter in the user's guess that has not been checked.
     * This method seems to be unused and could be part of future enhancements or debugging purposes.
     *
     * @param userGuess The current state of the user's guess.
     * @param guess     The letter guessed by the user.
     * @return The next index to check for the guessed letter in the user's guess.
     */
    static int getCheckPoint(String userGuess, char guess)
    {
        int idx = userGuess.indexOf(guess);
        int temp = 0;
        while (idx != -1)
        {
            idx = userGuess.indexOf(guess, temp++);
        }
        return temp - 1;
    }
}
