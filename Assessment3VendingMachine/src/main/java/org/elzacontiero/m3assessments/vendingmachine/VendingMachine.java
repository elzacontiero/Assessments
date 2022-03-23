package org.elzacontiero.m3assessments.vendingmachine;

import org.elzacontiero.m3assessments.vendingmachine.dao.InventoryDao;
import org.elzacontiero.m3assessments.vendingmachine.dao.InventoryDaoFileImpl;
import org.elzacontiero.m3assessments.vendingmachine.dto.Item;
import java.io.*;
import java.util.Scanner;

public class VendingMachine {

    /**
     * Create an inventory.
     */
    InventoryDao inventory = new InventoryDaoFileImpl();

    /**
     * Display all of the items and their prices along with an option to exit the program.
     */
    private void presentMenu() {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getQuantityAvailable() > 0) {
                System.out.println((i+1) + " - " + item.getName() + " €" + item.getPrice()/100.0);
            }
        }
    }

    /**
     * Ask the user for money.
     * @return Amount of money entered, in pence.
     */
    private int askUserForMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter money: ");
        double doubleMoney = Double.parseDouble(scan.nextLine());
        return (int) (doubleMoney*100.0);
    }

    /**
     * Ask user for a option to purchase. Handles the input and select choice from inventory.
     * @return object type Item from user's choice.
     * @throws NoItemInventoryException
     */
    private Item askUserForChoice() throws NoItemInventoryException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your selection: ");
        int choice = Integer.parseInt(scan.nextLine()) - 1;
        if (choice<0 || choice>=inventory.size()) {
            throw new NoItemInventoryException();
        }
        Item item = inventory.get(choice);
        if (item.getQuantityAvailable() == 0) {
            throw new NoItemInventoryException();
        }
        return item;
    }

    /**
     * Main loop of the program.
     */
    public void run() {
        try {
            // Instruct the inventory to load the csv file from disk.
            inventory.load();

            // Present menu along with an option to exit the program.
            presentMenu();

            int amount = askUserForMoney();

            try {
                Item item = askUserForChoice();
                System.out.println("Item selected: " + item.getName());

                /*
                The specification says:
                "If the user selects an item that costs more than the amount the user put into the vending machine,
                the program should display a message indicating insufficient funds and then redisplay the amount
                the user had put into the machine."
                So I've implemented a loop that keeps asking for more money if the selected item costs more than
                the available funds. The tiny loop rules out the need for any exceptions in it.
                 */
                while (item.getPrice() > amount) {
                    System.out.println("Insufficient funds. Your amount is: " + amount +
                            ". Cost of item: " + item.getPrice() +
                            ". Insert more money, please.");
                    amount += askUserForMoney();
                }

                // Decreases the item's price from the amount of money in.
                amount -= item.getPrice();
                // Calculates the change.
                Change change = new Change(amount);
                // Prints the change to the user.
                System.out.println(change.toString());
                // Decrease the inventory for that item.
                item.setQuantityAvailable(item.getQuantityAvailable() - 1);
                // Saves to inventory.csv file
                inventory.save();
            }
            catch (NoItemInventoryException e) {
                System.out.println("Selected item is not available in the inventory.");
            }
        }
        catch (IOException e) {
            // This exception is thrown by the IO classes.
            System.out.println("Error in program. Error: " + e.getMessage());
        }
    }

}
