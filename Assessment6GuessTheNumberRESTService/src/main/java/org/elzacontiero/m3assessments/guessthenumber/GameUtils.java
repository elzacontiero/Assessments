package org.elzacontiero.m3assessments.guessthenumber;

import java.util.ArrayList;

/**
 * Game Utils is a class with two core functions to the game.
 */
public class GameUtils {

    /**
     * Generates four random digits in a String.
     * The digits can't be repeated.
     * @return Four unique random numbers that don't repeat.
     */
    public static String generateRandomNumbers() {
        // Initialise an empty array for the numbers
        ArrayList<Integer> numbers = new ArrayList<>();
        Integer r;
        // Loop over 4 ditigs
        for (int i=0; i<4; i++) {
            // The trick: keep repeating the random method until the loop finds
            // a number that is not in the list, that is, a number that was not
            // previously found.
            do {
                r = (int) ((Math.random()*9) +1);
            } while (numbers.contains(r));
            // Here 'r' is unique. Just add to the list.
            numbers.add(r);
        }
        // Format the numbers as String.
        return String.format("%d%d%d%d", numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3));
    }

    /**
     * Calculates the result for a guess. The rules are:
     *  E: the number is correct and is at the right position.
     *  P: the number is present in the answer but is in the wrong position.
     *  0: the number is not in the answer.
     *
     * @param computer The answer that computer holds.
     * @param guess The guess from the player.
     * @return A String in the format "EP00" with the result.
     */
    public static char[] checkingForEAndP (char[] computer, char[] guess) {
        // Initialise the result with 4 chars.
        char[] result = new char[4];
        // Loop over each index at 'guess'. It should be lenght of 4.
        for (int i=0; i<guess.length; i++) {
            // If the guess at position i is identical to the position i in answer,
            // the result at that position is E:
            if (computer[i] == guess[i]){
                result[i] = 'E';
                // Continue to the next number.
                continue;
            }
            else {
                // Here need to first check if the number at guess position i is
                // present in other locations.
                // So, loop over the four digits
                for (int j=0;j<computer.length; j++) {
                    // and if the position is NOT the same, but IS present...
                    if ((i!=j) && (computer[j]==guess[i])) {
                        // ... set as P, partial match.
                        result[i] = 'P';
                    }
                }
                // If the algorithm didn't find a partial, then the number is not
                // in the answer.
                if (result[i]!= 'P') {
                    // set to 0
                    result [i]= '0';
                }
            }
        }
        return result;
    }

}
