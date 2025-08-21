package com.linkedin;

import java.util.Scanner;

public class InputHandler {
    public static int[] getUserGuess (Scanner scanner, int attempt){
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
}
