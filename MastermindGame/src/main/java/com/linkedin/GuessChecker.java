package com.linkedin;

public class GuessChecker {
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
