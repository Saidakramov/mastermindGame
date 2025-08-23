package com.linkedin;

import java.util.Scanner;

public class InputHandler {
    private int numDigits;
    private int minDigit;
    private int maxDigit;

    public InputHandler(int numDigits, int minDigit, int maxDigit) {
        this.numDigits = numDigits;
        this.minDigit = minDigit;
        this.maxDigit = maxDigit;
    }

    public  int[] getUserGuess (Scanner scanner, int attempt){
        while (true) {
            System.out.printf("\nAttempt %d of 10. Enter %d digits (digits %d-%d): %n",
                    attempt, numDigits, minDigit, maxDigit);
            String guessInput = scanner.nextLine().trim();

            if (guessInput.length() != numDigits || !guessInput.matches("\\d+")) {
                System.out.println("Invalid input. Please enter exactly " + numDigits + " digits.");
                continue;
            }

            int[] guess = new int[numDigits];
            boolean valid = true;

            for (int i = 0; i < numDigits; i++) {
                guess[i] = Character.getNumericValue(guessInput.charAt(i));

                if (guess[i] < minDigit || guess[i] > maxDigit) {
                    System.out.println("Invalid input. Each digit must be between " + minDigit + " and " +  maxDigit + ".");
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
