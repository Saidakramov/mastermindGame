package com.linkedin;

import java.util.Scanner;

public class GameRunner {
    public static Scanner scanner = new Scanner(System.in);

    public static void startGame() {
        String answer;
        do {
            int choice = 0;
            while (choice < 1 || choice > 3) {
                System.out.println("""
                Select difficulty level:
                (1) Easy (4 digits to guess from numbers 0-7)
                (2) Medium (4 digits to guess from numbers 0-9)
                (3) Hard (5 digits to guess from numbers 0-9)
                """);

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice < 1 || choice > 3) {
                        System.out.println("Enter 1,2 or 3.");
                    }
                } else {
                    System.out.println("Invalid input: Enter 1, 2, or 3.");
                    scanner.next();
                }
            }

            int numDigits = 0;
            int minDigit = 0;
            int maxDigit = 0;

            switch (choice) {
                case 1 -> {numDigits = 4; minDigit = 0; maxDigit = 7 ;} //easy
                case 2 -> {numDigits = 4; minDigit = 0; maxDigit = 9 ;} //medium
                case 3 -> {numDigits = 5; minDigit = 0; maxDigit = 9 ;}//hard
            }

            MastermindGame mastermindGame = new MastermindGame(numDigits, minDigit, maxDigit);
            mastermindGame.start();
            while (true) {
                System.out.println("\nWould you like to play another game? (Y/N)");
                answer = scanner.nextLine().trim().toUpperCase();

                if (answer.equals("Y") || answer.equals("N")){
                    break;
                }
                System.out.println("Invalid input. Please enter 'Y' for yes or 'N' for no. ");
            }
        } while (answer.equals("Y"));
        System.out.println("\nThanks for playing!");
        scanner.close();
    }
}
