package com.linkedin;

import java.util.Scanner;

public class InputHandler {
    private int numDigits;
    private int minDigits;
    private int maxDigits;

    public InputHandler(int numDigits, int minDigits, int maxDigits) {
        this.numDigits = numDigits;
        this.minDigits = minDigits;
        this.maxDigits = maxDigits;
    }

    public  int[] getUserGuess (Scanner scanner, int attempt){
        while (true) {
            System.out.printf("\nAttempt %d of 10. Enter %d digits (digits %d-%d): %n",
                    attempt, numDigits, minDigits, maxDigits);
            String guessInput = scanner.nextLine().trim();

            if (guessInput.length() != numDigits || !guessInput.matches("\\d+")) {
                System.out.println("Invalid input. Please enter exactly " + numDigits + " digits.");
                continue;
            }

            int[] guess = new int[numDigits];
            boolean valid = true;

            for (int i = 0; i < numDigits; i++) {
                guess[i] = Character.getNumericValue(guessInput.charAt(i));

                if (guess[i] < minDigits || guess[i] > maxDigits) {
                    System.out.println("Invalid input. Each digit must be between " + minDigits + " and " +  maxDigits + ".");
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return guess;
            }
        }
    }
}
