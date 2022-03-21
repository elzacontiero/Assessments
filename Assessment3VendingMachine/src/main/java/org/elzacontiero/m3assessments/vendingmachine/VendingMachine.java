package org.elzacontiero.m3assessments.vendingmachine;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
        f2Reader.close();
    }

    private void presentMenu() {
        // Display all of the items and their prices along with an option to exit the program.
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getQuantityAvailable() > 0) {
                System.out.println((i+1) + " - " + item.getName() + " €" + item.getPrice()/100.0);
            }
        }
    }

    private int askUserForMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter money: ");
        double doubleMoney = Double.parseDouble(scan.nextLine());
        int money = (int) (doubleMoney*100.0);
        return money;
    }

    private Item askUserForChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your selection: ");
        int choice = Integer.parseInt(scan.nextLine()) - 1;
        return inventory.get(choice);
    }

    private void saveInventoryToDisk() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(inventoryFileName));
        for (int i = 0; i<inventory.size(); i++) {
            Item item = inventory.get(i);
            writer.println(item.getName()+","+item.getPrice()+","+item.getQuantityAvailable());
        }
        writer.flush();
        writer.close();
    }

    public void run() {
        try {
            initialiseCollectionFromCsvFile();

            // present menu along with an option to exit the program
            presentMenu();
            int amount = askUserForMoney();

            Item item = askUserForChoice();
            System.out.println("Item selected: " + item.getName());

            while (item.getPrice() > amount) {
                System.out.println("Insufficient funds. Your amount is: " + amount +
                        ". Cost of item: " + item.getPrice() +
                        ". Insert more money, please.");
                amount += askUserForMoney();
            }

            amount -= item.getPrice();
            Change change = new Change(amount);
            System.out.println(change.toString());
            item.setQuantityAvailable(item.getQuantityAvailable() - 1);
            saveInventoryToDisk();
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
