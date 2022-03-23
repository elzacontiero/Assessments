package org.elzacontiero.m3assessments.vendingmachine.dao;

import org.elzacontiero.m3assessments.vendingmachine.dto.Item;

import java.io.*;
import java.util.ArrayList;

public class InventoryDaoFileImpl implements InventoryDao {
    // The name of the CSV file containing initial inventory list.
    private final String inventoryFileName = "inventory.csv";
    private ArrayList<Item> inventory = new ArrayList<>();

    @Override
    public Item get(int i) {
        return inventory.get(i);
    }

    @Override
    public ArrayList<Item> getAll() {
        return inventory;
    }

    @Override
    public void load() throws IOException {
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

    @Override
    public void save() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(inventoryFileName));
        for (Item item : inventory) {
            writer.println(item.getName()+","+item.getPrice()+","+item.getQuantityAvailable());
        }
        writer.flush();
        writer.close();
    }

    @Override
    public int size() {
        return inventory.size();
    }
}
