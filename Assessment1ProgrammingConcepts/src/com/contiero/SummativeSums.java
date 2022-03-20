package com.contiero;

public class SummativeSums {

    /**
     * Perform the sum calculation of every element of input array.
     *
     * @param input An array of integers
     * @return The sum of all elements of input.
     */
    static int adder(int[] input) {
        // initialise the result with zero
        int result = 0;
        // Loop over every element of the input and add to result
        for (int i = 0; i< input.length; i++) {
            result = result + input[i];
        }
        return result;
    }


    /**
     * Entry point for the application.
     * Three arrays of integers have their sum calculated and presented to the user.
     * @param args
     */
    public static void main(String[] args) {

        int arrOne [] = {1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int arrTwo [] = {999, -60, -77, 14, 160, 301 };
        int arrThree [] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };


        int sumArray1 = adder(arrOne);
        int sumArray2 = adder(arrTwo);
        int sumArray3 = adder(arrThree);

        System.out.println("#1 Array sum: " + sumArray1);
        System.out.println("#2 Array sum: " + sumArray2);
        System.out.println("#3 Array sum: " + sumArray3);

    }
}
