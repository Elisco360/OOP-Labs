import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        // Example usage
        guessingGame(6);
    }

    public static void guessingGame(int seed) {
        Random random = new Random(seed);
        int target = random.nextInt(100) + 1;
        int numberOfTries = 0;

        System.out.println("Welcome to the Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");

        Scanner scanner = new Scanner(System.in);
        int userEntry = scanner.nextInt();

        while (userEntry != target) {
            numberOfTries++;

            if (userEntry > target) {
                System.out.println("Good try, but that's too high. Try again");
            } else {
                System.out.println("Good try, but that's too low. Try again");
            }

            userEntry = scanner.nextInt();
        }

        numberOfTries++;

        if (numberOfTries == 1) {
            System.out.println("Yes! You guessed correctly after 1 try! Congratulations.");
        } else {
            System.out.println("Yes! You guessed correctly after " + numberOfTries + " tries! Congratulations.");
        }

        scanner.close();
    }
}
