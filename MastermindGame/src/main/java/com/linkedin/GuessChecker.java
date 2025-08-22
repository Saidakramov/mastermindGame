package com.linkedin;

public class GuessChecker {
    public static int[] checkGuess(int[] secret, int[] guess){
        int correctPosition = 0;
        int correctNumber = 0;

        boolean[] secretUsed = new boolean[secret.length];
        boolean[] guessUsed = new boolean[guess.length];

        // correct position & correct number
        for (int i = 0; i < secret.length; i++) {
            if (guess[i] == secret[i]) {
                correctPosition++;
                correctNumber++;
                secretUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        // correct number & wrong position
        for (int i = 0; i < guess.length; i++) {
            if (!guessUsed[i]) {
                for (int j = 0; j < secret.length; j++) {
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
