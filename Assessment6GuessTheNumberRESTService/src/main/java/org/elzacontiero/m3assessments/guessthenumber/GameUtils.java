package org.elzacontiero.m3assessments.guessthenumber;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * 1. Generate an array of random numbers
 * 2. Ensure uniqueness
 * 3. Compare this array to another array and check if both the same
 * 4. If not, check for E and P
 */
public class GameUtils {

    public static String generateRandomNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Integer r;
        for (int i=0; i<4; i++) {
            do {
                r = (int) ((Math.random()*9) +1);
            } while (numbers.contains(r));
            numbers.add(r);
        }

        return String.format("%d%d%d%d", numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3));
//        int[] a = new int[4];
//        for (int i =0;i<a.length;i++) {
//            a[i] = numbers.get(i);
//        }
//        return a;
    }

    public static boolean compareArrays(int[] one, int[] two) {
        if (Arrays.equals (one,two)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static char[] checkingForEAndP (int[] computer, int[] guess) {
        char[] result = new char[4];
        for (int i=0; i<guess.length; i++) {
            if (computer[i] == guess[i]){
                result[i] = 'E';
                continue;
            }
            else {
                for (int j=0;j<computer.length; j++) {
                    if ((i!=j) && (computer[j]==guess[i])) {
                        result[i] = 'P';
                    }
                }
                if (result[i]!= 'P') {
                    result [i]= '0';
                }
            }
        }
        return result;
    }

}
