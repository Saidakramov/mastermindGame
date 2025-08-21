package com.linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.linkedin.GuessChecker.checkGuess;
import static com.linkedin.InputHandler.getUserGuess;
import static com.linkedin.Utils.arrayToString;

public class MastermindGame {
    private static Scanner scanner = new Scanner(System.in);
    private  int[] secret;
    private List<String> history = new ArrayList<>();
    private boolean guessed = false;

    public MastermindGame() {
        this.secret = RandomOrgSecretNumbers.generateSecretNumbers();
        if (this.secret == null) {
            throw  new RuntimeException("Error generating numbers.");
        }
    }

    public void start() {
        printIntro();

        for (int attempt = 1; attempt <= 10; attempt++) {
            int[] guess = getUserGuess(scanner, attempt);
            int[] result = checkGuess(secret, guess);

            int correctPosition = result[0];
            int correctNumber = result[1];

            String feedback = "";
            if (correctPosition == 4) {
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
            history.add(attempt + ") Guess: " + arrayToString(guess) + " Feedback: " + feedback);

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
        System.out.println(
                """
                      
                      Welcome to Mastermind Game!
                      In this game, you have 10 attempts to guess the 4-number combination (digits 0-7).
                      The numbers are randomly generated, and duplicate numbers are allowed.
                      After each attempt, you will receive feedback on whether you guessed a number correctly,
                      and whether it is in the correct location.
                      Good luck!""");
    }
}
