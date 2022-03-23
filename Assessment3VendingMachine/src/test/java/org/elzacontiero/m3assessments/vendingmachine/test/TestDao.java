package org.elzacontiero.m3assessments.vendingmachine.test;

import org.elzacontiero.m3assessments.vendingmachine.dao.InventoryDao;
import org.elzacontiero.m3assessments.vendingmachine.dao.InventoryDaoFileImpl;
import org.elzacontiero.m3assessments.vendingmachine.dto.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDao {

    @Test
    void testDaoCreation() throws IOException  {
        InventoryDao inventory = new InventoryDaoFileImpl("test.csv");
        inventory.load();
        assertEquals(6, inventory.size());
        // Get a known Item: Peanuts at index 3
        Item item = inventory.get(3);
        assertEquals("Peanuts", item.getName());
        assertEquals(5, item.getQuantityAvailable());
    }

    @Test
    void testDaoSaveQuantityAvailable() throws IOException {
        // Load an inventory instance, change one quantity available and save to disk.
        InventoryDao inventory = new InventoryDaoFileImpl("test.csv");
        inventory.load();
        Item item = inventory.get(3);
        item.setQuantityAvailable(4);
        inventory.save();

        // Read again from disk ensure it is now 4 quantities for Peanuts
        InventoryDao inventory2 = new InventoryDaoFileImpl("test.csv");
        inventory2.load();
        Item item2 = inventory2.get(3);
        assertEquals(4, item2.getQuantityAvailable());

        // Put back original value 5, otherwise other tests will fail.
        item.setQuantityAvailable(5);
        inventory.save();
    }
}
