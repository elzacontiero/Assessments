package org.elzacontiero.m3assessments.vendingmachinespring.dao;

import org.elzacontiero.m3assessments.vendingmachinespring.dto.Item;

import java.io.IOException;
import java.util.ArrayList;

public interface InventoryDao {

    Item get(int index);
    ArrayList<Item> getAll();
    void load() throws IOException;
    void save() throws IOException;
    int size();
}
