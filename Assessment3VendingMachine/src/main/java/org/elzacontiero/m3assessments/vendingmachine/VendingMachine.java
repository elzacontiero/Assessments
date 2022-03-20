package org.elzacontiero.m3assessments.vendingmachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VendingMachine {

    // The name of the CSV file containing initial inventory list.
    private final String inventoryFileName = "inventory.csv";
    private ArrayList<Item> inventory = new ArrayList<>();

    private void initialiseCollectionFromCsvFile() throws IOException {
        // Read the CSV file with the inventory
        // loop over the rows, parse them;
        // create one Item object for each row;
        // store the item into a Collection with all items.


        FileReader fReader = new FileReader(inventoryFileName); // can throw a FileNotFoundException
        BufferedReader f2Reader = new BufferedReader(fReader);
        for (String line = f2Reader.readLine();
             line != null;
             line= f2Reader.readLine()) {
            // contents of line (Beamish Irish Stout, 200, 12)
            String[] fields = line.split(",");
            // fields = {"Beamish Irish Stout", "200", "12" }
            String name = fields[0];
            int price = Integer.parseInt(fields[1]);
            int itemsAvailable = Integer.parseInt(fields[2]);
            Item item = new Item(name,price,itemsAvailable);
            inventory.add(item);
        }
    }

    private void presentMenu() {
        // TODO
    }

    public void run() {
        try {
            initialiseCollectionFromCsvFile();

            // present menu along with an option to exit the program
            presentMenu();
        }
        catch (IOException e) {
            System.out.println("Error in program. Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        VendingMachine app = new VendingMachine();
        app.run();
    }
}
