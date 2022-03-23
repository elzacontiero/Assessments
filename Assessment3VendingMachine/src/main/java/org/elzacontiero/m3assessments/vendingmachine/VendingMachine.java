package org.elzacontiero.m3assessments.vendingmachine;

import org.elzacontiero.m3assessments.vendingmachine.dao.InventoryDao;
import org.elzacontiero.m3assessments.vendingmachine.dao.InventoryDaoFileImpl;
import org.elzacontiero.m3assessments.vendingmachine.dto.Item;

import java.io.*;

import java.util.Scanner;

public class VendingMachine {

    InventoryDao inventory = new InventoryDaoFileImpl();

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
        return (int) (doubleMoney*100.0);
    }

    private Item askUserForChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your selection: ");
        int choice = Integer.parseInt(scan.nextLine()) - 1;
        return inventory.get(choice);
    }

    public void run() {
        try {
            inventory.load();

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
            inventory.save();
        }
        catch (IOException e) {
            System.out.println("Error in program. Error: " + e.getMessage());
        }
    }

}
