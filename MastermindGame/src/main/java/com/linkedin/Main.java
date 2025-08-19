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

        boolean guessed = false;
        int attempts = 10;

        //Allow up to 10 attempts
        for (int attempt = 1; attempt <= attempts; attempt++) {
            System.out.println("Attempt " + attempt + " of 10. Enter your guess: (4 digits. Ex:0123)");
            String guessInput = scanner.nextLine().trim();

            if (guessInput.length() != 4 || !guessInput.matches("\\d+")) {
                System.out.println("Invalid input: Please enter exactly 4 digits.");
                attempt--; // don't count wrong attempt
                continue;
            }

            int[] guess = new int[4];
            for (int i = 0; i < 4; i++) {
                guess[i] = Character.getNumericValue(guessInput.charAt(i));
            }

            int correctNumber = 0;
            int correctPosition = 0;
            boolean[] secretUsed = new boolean[4];
            boolean[] guessUsed =new boolean[4];

            //Exact matches
            for (int i = 0; i < 4; i++) {
                if (guess[i] == secret[i]){
                    correctPosition++;
                    secretUsed[i] = true;
                    guessUsed[i] = true;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (!guessUsed[i]) {
                    for (int j = 0; j <4; j++){
                        if (!secretUsed[j] && guess[i] == secret[j]) {
                            correctNumber++;
                            secretUsed[j] = true;
                            break;
                        }
                    }
                }
            }
            if (correctPosition == 4) {
                System.out.println("Congratulations! You guessed the secret code!");
                guessed = true;
                break;
            } else if (correctPosition == 0 && correctNumber ==0) {
                System.out.println("All incorrect");
            } else {
                String numText = correctNumber == 1 ? "1 correct number" : correctNumber + " correct numbers";
                String posText = correctPosition == 1 ? "1 correct location" : correctPosition + " correct locations";
                System.out.println(numText + " and " + posText);
            }
        }
        if (!guessed) {
            System.out.println("Game over! The secret code was: ");
            for (int n : secret) {
                System.out.println(n + " ");

            }
        }
    }
}