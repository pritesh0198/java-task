import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        int maxAttempts = 5;
        int maxRounds = 3;
        int score = 0;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        for (int round = 1; round <= maxRounds; round++) {
            int attempts = 0;
            int numberToGuess = random.nextInt(100) + 1;
            System.out.println("Round " + round + ": Guess the number between 1 and 100");
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;
                if (guess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("The number is higher than your guess.");
                } else {
                    System.out.println("The number is lower than your guess.");
                }
            }
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you couldn't guess the number. The correct number was " + numberToGuess);
            }
        }
        System.out.println("Game over. Your final score is: " + score);
    }
}
