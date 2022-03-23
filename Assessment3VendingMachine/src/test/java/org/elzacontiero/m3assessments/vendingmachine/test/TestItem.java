package org.elzacontiero.m3assessments.vendingmachine.test;

import org.elzacontiero.m3assessments.vendingmachine.dto.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestItem {

    @Test
    public void testGetters() {
        Item item = new Item("Some name", 123, 9);
        assertEquals("Some name", item.getName());
        assertEquals(123, item.getPrice());
        assertEquals(9, item.getQuantityAvailable());
    }

    @Test
    public void testSetters() {
        Item item = new Item("Some name", 123, 9);
        item.setQuantityAvailable(12);
        assertEquals(12, item.getQuantityAvailable());
    }
}
