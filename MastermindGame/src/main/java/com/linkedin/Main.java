package com.linkedin;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(
                """
                      
                      Welcome to Mastermind Game!
                      In this game, you have 10 attempts to guess the 4-number combination (digits 0-7).
                      The numbers are randomly generated, and duplicate numbers are allowed.
                      After each attempt, you will receive feedback on whether you guessed a number correctly,
                      and whether it is in the correct location.
                      Good luck!""");

        // assigning secret from RandomOrgSecretNumbers class
        int[] secret = RandomOrgSecretNumbers.generateSecretNumbers();
        if (secret == null) {
            System.out.println("Error generating numbers. Exiting game.");
            return;
        }

        Game(secret);

    }

    public static void Game(int[] secret) {
        boolean guessed = false;

        for (int attempt = 1; attempt <= 10; attempt++) {
            int[] guess = getUserGuess(attempt);
            int[] result = checkGuess(secret, guess);

            int correctPosition = result[0];
            int correctNumber = result[1];

            if (correctPosition == 4) {
                System.out.println("\"Congratulations! You guessed the secret code!\"");
                guessed = true;
                break;
            } else if (correctPosition == 0 && correctNumber == 0) {
                System.out.println("All incorrect");
            } else {
                String locText = correctPosition == 1 ? "1 correct location" : correctPosition + " correct locations";
                String numText = correctNumber == 1 ? "1 correct number" : correctNumber + " correct numbers";
                System.out.printf(numText + " and " + locText);
            }

        }
        if (!guessed) {
            System.out.println("\nGame over! The secret code was: ");
            for (int s : secret) {
                System.out.print(s + " ");
            }
        }
    }

        public static int[] getUserGuess ( int attempt){
            while (true) {
                System.out.println("\nAttempt " + attempt + " of 10. Enter 4 digits: Ex:0123");
                String guessInput = scanner.nextLine().trim();

                if (guessInput.length() == 4 && guessInput.matches("\\d+")) {
                    int[] guess = new int[4];
                    for (int i = 0; i < 4; i++) {
                        guess[i] = Character.getNumericValue(guessInput.charAt(i));
                    }
                    return guess;
                }
                System.out.println("Invalid input. Please enter exactly 4 digits. Ex:1234");
            }
        }

        public static int[] checkGuess(int[] secret, int[] guess){
            int correctPosition = 0;
            int correctNumber = 0;

            boolean[] secretUsed = new boolean[4];
            boolean[] guessUsed = new boolean[4];

            // correct position & correct number
            for (int i = 0; i < 4; i++) {
                if (guess[i] == secret[i]) {
                    correctPosition++;
                    correctNumber++;
                    secretUsed[i] = true;
                    guessUsed[i] = true;
                }
            }

            // correct number & wrong position
            for (int i = 0; i < 4; i++) {
                if (!guessUsed[i]) {
                    for (int j = 0; j < 4; j++) {
                        if (!secretUsed[j] && guess[i] == secret[j]) {
                            correctNumber++;
                            secretUsed[j] = true;
                            break;
                        }
                    }
                }
            }

            return new int[]{correctPosition, correctNumber};
        }

}