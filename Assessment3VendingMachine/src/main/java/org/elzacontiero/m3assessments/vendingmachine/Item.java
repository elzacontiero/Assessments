package org.elzacontiero.m3assessments.vendingmachine;

/**
 * The class item models items available in a vending machine.
 * Getters and setters are provided for accessing the properties and setting values.
 * It is not provided a setter for name and price because they are not expected to change
 * throughout the course of the execution of the program.
 * A constructor is provided for creating new items.
 */
public class Item {

    private String name;
    private int price;
    private int quantityAvailable;

    public Item(String name, int price, int itemsAvailable) {
        this.name = name;
        this.price = price;
        this.quantityAvailable = itemsAvailable;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String toString() {
        return "Item: name=" + name + " price=" + price + " qtyAvailable=" + quantityAvailable;
    }
}
