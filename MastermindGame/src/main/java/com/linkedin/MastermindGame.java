package com.linkedin;

import java.util.*;

import static com.linkedin.GuessChecker.checkGuess;
import static com.linkedin.Utils.arrayToString;

public class MastermindGame {
    private static Scanner scanner = new Scanner(System.in);
    private  int[] secret;
    private List<String> history = new ArrayList<>();
    private boolean guessed = false;
    private InputHandler inputHandler;
    private int numDigits;
    private int minDigit;
    private int maxDigit;
    private List<Integer> hintIndicesRemaining; // for unique hints

    private static final List<Integer> HINT_ATTEMPTS = Arrays.asList(5,7,9);


    public MastermindGame(int numDigits, int minDigit, int maxDigit) {
        this.numDigits = numDigits;
        this.minDigit = minDigit;
        this.maxDigit = maxDigit;

        this.secret = RandomOrgSecretNumbers.generateSecretNumbers(numDigits, minDigit, maxDigit);
        if (this.secret == null) {
            throw  new RuntimeException("Error generating numbers.");
        }

        this.inputHandler = new InputHandler(numDigits, minDigit, maxDigit);

        //initialize indices for hints
        hintIndicesRemaining = new ArrayList<>();
        for (int i = 0; i < secret.length; i++) {
            hintIndicesRemaining.add(i);
        }
    }

    public void start() {
        printIntro();


        for (int attempt = 1; attempt <= 10; attempt++) {
            playAttempt(attempt);

            if (guessed) {
                break;
            }

            // offer hint
            if (HINT_ATTEMPTS.contains(attempt)) {
                askForHint();
            }

        }
        if (!guessed) {
            System.out.println("\nGame over! The secret code was: ");
            for (int s : secret) {
                System.out.print(s + " ");
            }
        }
    }

    private void playAttempt(int attempt) {
        int[] guess = inputHandler.getUserGuess(scanner, attempt);
        int[] result = checkGuess(secret, guess);

        int correctPosition = result[0];
        int correctNumber = result[1];

        String feedback = formatFeedback(correctPosition,correctNumber);

        // Save history
        history.add(attempt + ") Guess: " + arrayToString(guess) + ". Feedback: " + feedback);

        // Print current feedback
        System.out.println(feedback);
        printHistory();

        // Check for win
        if (correctPosition == numDigits) {
            System.out.println("Congratulations! You guessed the secret code!");
            guessed = true;
        }


    }

    private void askForHint() {
        System.out.println("Would you like a hint? (Y/N)");
        String hintOption = scanner.nextLine().trim().toUpperCase();
        while (!hintOption.equals("Y") && !hintOption.equals("N")) {
            System.out.println("Invalid input. Enter 'Y' or 'N':");
            hintOption= scanner.nextLine().trim().toUpperCase();
        }

        if (hintOption.equals("Y")) {
            String hint = giveHint();
            System.out.println(hint);
            history.add(("Hint: " + hint));
        }
    }

    private String formatFeedback(int correctPosition, int correctNumber) {

        if (correctPosition ==0 && correctNumber ==0) {
            return "All incorrect";
        }

        String locText = correctPosition == 1 ? "1 correct location" : correctPosition + " correct locations";
        String numText = correctNumber == 1 ? "1 correct number" : correctNumber + " correct numbers";
        return   numText + " and " + locText;
    }

    private void printHistory() {
        // Print full history
        System.out.println("\nHistory so far:");
        for (String h : history) {
            System.out.println(h);
        }
    }

    private void printIntro() {
        System.out.printf("""
            Welcome to Mastermind Game!
            In this game, you have 10 attempts to guess the %d-number combination (digits %d-%d).
            The numbers are randomly generated, and duplicates are allowed.
            After each attempt, you will receive feedback on whether you guessed a number correctly,
            and whether it is in the correct location.
            Good luck!
            """, numDigits, minDigit, maxDigit);
    }

    private String giveHint() {
        Random random = new Random();
        int index = random.nextInt(hintIndicesRemaining.size()); // random position
        int secretIndex = hintIndicesRemaining.remove(index);
       return "Hint: One of the digits is: " + secret[secretIndex];
    }
}
