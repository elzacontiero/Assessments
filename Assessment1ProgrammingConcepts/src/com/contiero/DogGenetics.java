package com.contiero;

import java.util.Scanner;
import java.util.Random;

/**
 * Fake DNA generator for dogs.
 */
public class DogGenetics {
    public static void main(String[] args) {
        // Input dog's name for the report:
        System.out.print("What is your dog's name? ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        // output a friendly message
        System.out.println("Well then, I have this highly reliable report on " + name + "'s prestigious background right here.");
        // Create random object
        Random rand = new Random();

        // generate fake genes for each breed.
        // the % of each breed will be calculated with respect to the total number of genes.
        int stBernard = rand.nextInt(10000); //
        int chihuaua = rand.nextInt(10000);
        int pug = rand.nextInt(10000);
        int cur = rand.nextInt(10000);
        int doberman = rand.nextInt(10000);;

        // This dog has this amount of genes:
        int totalGenes = stBernard+chihuaua+pug+cur+doberman;

        // Now calculate percentages.
        stBernard = 100*stBernard / totalGenes;
        chihuaua = 100*chihuaua / totalGenes;
        pug = 100*pug / totalGenes;
        cur = 100*cur / totalGenes;
        // To make exact 100%, make 100- all other percentages.
        doberman = 100 - (stBernard+chihuaua+pug+cur);

        // Print out the report to the user:
        System.out.println(stBernard + "% St. Bernard");
        System.out.println(chihuaua + "% Chihuahua");
        System.out.println(pug + "% Dramatic RedNosed Asian Pug");
        System.out.println(cur + "% Common Cur");
        System.out.println(doberman + "% King Doberman");
        System.out.println("Wow, that's QUITE the dog!");
    }
}

