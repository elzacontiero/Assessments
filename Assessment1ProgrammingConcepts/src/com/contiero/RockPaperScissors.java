package com.contiero;

import java.util.Random;
import java.util.Scanner;

/**
 * Console game for playing Rock Paper and Scissors against the computer.
 */
public class RockPaperScissors {

    // Declare some constants to better visualise the choices
    static final int ROCK = 1;
    static final int PAPER = 2;
    static final int SCISSORS = 3;

    /**
     * Prompt the user for another round of the game
     * @param scan Scanner to receive user's input
     * @return true if user wants to play again
     */
    static boolean askIfUserWantsToPlayAgain(Scanner scan) {
        System.out.print("Do you want to play again (Y/N)? ");
        // Because only integers were collected from scan, there was a newline left in the buffer ('\n') inside scan.
        // this means a call to scan.nextLine() will return empty.
        String choice;
        do {
           choice = scan.nextLine();
        } while(choice.isEmpty());
        return choice.equalsIgnoreCase("y");
    }

    /**
     * Compare scores from user and computer and display winner.
     * @param user
     * @param comp
     */
    static void declareWinner(int user, int comp) {
        if (user>comp) {
            System.out.println("USER WINS!!!");
        }
        else if (user<comp) {
            System.out.println("COMPUTER WINS!!!");
        } else {
            System.out.println("TIE!!!");
        }
    }

    /**
     * Display what the computer has chosen to the user.
     * @param compChoice
     */
    static void displayComputerChoice(int compChoice) {
        switch (compChoice) {
            case ROCK:
                System.out.println("Computer chose ROCK");
                break;
            case SCISSORS:
                System.out.println("Computer chose SCISSORS");
                break;
            case PAPER:
                System.out.println("Computer chose PAPER");
                break;
        }
    }

    /**
     * Entry point for the application.
     * @param args
     */
    public static void main(String[] args) {
        // Initialise some variables:

        // A boolean here to keep track whether the user wants to repeat the whole game.
        boolean wantsToPlay = true;
        // Scanner to input data from the user
        Scanner scan = new Scanner(System.in);
        // A random generator to produce the computer's choice.
        Random randomizer = new Random();

        // Main loop: continue if the user wants to play.
        while (wantsToPlay) {
            // Print banner of the game.
            System.out.println("---- Rock Paper Scissors ----");
            // input for the number of rounds, limited to 10
            System.out.print("How many rounds (1 to 10)? ");
            int rounds = scan.nextInt();
            // validate the rounds
            if (rounds >= 1 && rounds <= 10) {

                // Initialise counts for each player
                int user=0, comp=0, tie=0;

                // Play 'round' times the game
                for (int roundsPlayed = 0; roundsPlayed < rounds; roundsPlayed++) {
                    // Input user's move
                    System.out.print("What is your choice (1 = Rock, 2 = Paper, 3 = Scissors)? ");
                    int userChoice = scan.nextInt();
                    // Generate computer's move
                    int compChoice = randomizer.nextInt(3) + 1;
                    // display computer's move to user.
                    displayComputerChoice(compChoice);

                    // Now compare all the combinations for the game
                    if (userChoice==compChoice) {
                        System.out.println("Tie!");
                        tie++;
                    }
                    else if (userChoice==ROCK && compChoice==PAPER) {
                        System.out.println("1 point to computer.");
                        comp++;
                    }
                    else if (userChoice==ROCK && compChoice==SCISSORS) {
                        System.out.println("1 point to user.");
                        user++;
                    }
                    else if (userChoice==PAPER && compChoice==ROCK) {
                        System.out.println("1 point to user.");
                        user++;
                    }
                    else if (userChoice==PAPER && compChoice==SCISSORS) {
                        System.out.println("1 point to computer.");
                        comp++;
                    }
                    else if (userChoice==SCISSORS && compChoice==ROCK) {
                        System.out.println("1 point to computer.");
                        comp++;
                    }
                    else if (userChoice==SCISSORS && compChoice==PAPER) {
                        System.out.println("1 point to user.");
                        user++;
                    }
                    else {
                        // If we're here, it means the user has entered a wrong choice, something different than 1,2,3.
                        System.out.println("Invalid choice!");
                    }
                }

                // Here the game has finished playing 'round' times, so declare who's the winner.
                declareWinner(user, comp);
                // Then ask the user if she wants to play again
                wantsToPlay = askIfUserWantsToPlayAgain(scan);
            } else {
                // User entered the wrong number of rounds.
                System.out.println("Wrong number of rounds. Bye!");
                // In which case we have to return and exit the program.
                return;
            }
        }
        System.out.println("Thanks for playing. Bye! :-) ");
    }
}
