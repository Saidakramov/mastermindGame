package com.linkedin;


import java.util.Scanner;
import java.util.WeakHashMap;


public class Main {
static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

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

            MastermindGame mastermindGame = new MastermindGame(choice);
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