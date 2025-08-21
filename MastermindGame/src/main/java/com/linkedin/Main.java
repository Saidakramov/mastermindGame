package com.linkedin;


import java.util.Scanner;

public class Main {
static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String answer;
        do {
            MastermindGame mastermindGame = new MastermindGame();
            mastermindGame.start();
            while (true) {
                System.out.println("Would you like to play another game? (Y/N)");
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