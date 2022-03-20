package com.contiero;

import java.util.Scanner;

/**
 * Calculates the healthy heart rate for an individual given its age.
 * It provides the maximum heart rate and the healthy range for that age.
 */
public class HealthyHearts {

    public static void main(String[] args) {
        // declare age variable
        int age;
        // Create scanner for later input data from user
        Scanner scan = new Scanner(System.in);

        System.out.println("What is your age?: ");
        // collect the age
        age = scan.nextInt();
        // calculate the max heart rate with given formula
        int maxHeartRate = 220 - age;
        // the healthy range is 50% and 85% of max heart rate:
        int minTargetHeartRate = (int) (0.5*maxHeartRate);
        int maxTargetHeartRate = (int) (0.85*maxHeartRate);
        // prints out to user the values
        System.out.println("Your maximum heart rate should be: " + maxHeartRate + " beats per minute");
        System.out.println("Your target HR zone is: " +  minTargetHeartRate + "-"+ maxTargetHeartRate + " beats per minute");
    }
}
