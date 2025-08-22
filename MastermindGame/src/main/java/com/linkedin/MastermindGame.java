package com.linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.linkedin.GuessChecker.checkGuess;
import static com.linkedin.Utils.arrayToString;

public class MastermindGame {
    private static Scanner scanner = new Scanner(System.in);
    private  int[] secret;
    private List<String> history = new ArrayList<>();
    private boolean guessed = false;
    private InputHandler inputHandler;
    private int numDigits;
    private int minDigits;
    private int maxDigits;


    public MastermindGame(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1 -> {numDigits = 4; minDigits = 0; maxDigits = 7 ;} //easy
            case 2 -> {numDigits = 4; minDigits = 0; maxDigits = 9 ;}
            case 3 -> {numDigits = 5; minDigits = 0; maxDigits = 9 ;}//medium
        }

        this.secret = RandomOrgSecretNumbers.generateSecretNumbers(numDigits, minDigits, maxDigits);
        if (this.secret == null) {
            throw  new RuntimeException("Error generating numbers.");
        }

        this.inputHandler = new InputHandler(numDigits, minDigits, maxDigits);
    }

    public void start() {
        printIntro();


        for (int attempt = 1; attempt <= 10; attempt++) {
            int[] guess = inputHandler.getUserGuess(scanner, attempt);
            int[] result = checkGuess(secret, guess);

            int correctPosition = result[0];
            int correctNumber = result[1];

            String feedback = "";
            if (correctPosition == numDigits) {
                feedback = "\"Congratulations! You guessed the secret code!\"";
                history.add(attempt + ") Guess: "  + arrayToString(guess) + ". Win!");
                System.out.println(feedback);
                guessed = true;
                break;
            } else if (correctPosition == 0 && correctNumber == 0) {
                feedback = "All incorrect";
            } else {
                String locText = correctPosition == 1 ? "1 correct location" : correctPosition + " correct locations";
                String numText = correctNumber == 1 ? "1 correct number" : correctNumber + " correct numbers";
                feedback = numText + " and " + locText;
            }

            // Save history
            history.add(attempt + ") Guess: " + arrayToString(guess) + ". Feedback: " + feedback);

            // Print current feedback
            System.out.println(feedback);

            // Print full history
            System.out.println("\nHistory so far:");
            for (String h : history) {
                System.out.println(h);
            }

        }
        if (!guessed) {
            System.out.println("\nGame over! The secret code was: ");
            for (int s : secret) {
                System.out.print(s + " ");
            }
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
            """, numDigits, minDigits, maxDigits);
    }
}
