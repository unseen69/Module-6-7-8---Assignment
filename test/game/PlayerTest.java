package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerCanAddAndFindItem() {
        Player player = new Player("Adventurer");
        Item key = new Item("key", "A small iron key.");

        player.addItem(key);

        assertTrue(player.hasItem("key"));
    }

    @Test
    public void testPlayerDoesNotHaveMissingItem() {
        Player player = new Player("Adventurer");

        assertFalse(player.hasItem("sword"));
    }

    @Test
    public void testInventoryItemNamesReturnsNoneWhenEmpty() {
        Player player = new Player("Adventurer");

        assertEquals("none", player.getInventoryItemNames());
    }

    @Test
    public void testInventoryItemNamesReturnsItems() {
        Player player = new Player("Adventurer");

        player.addItem(new Item("key", "A small key."));
        player.addItem(new Item("sword", "A rusty sword."));

        assertEquals("key, sword", player.getInventoryItemNames());
    }

    @Test
    public void testPlayerEscapedCanBeSet() {
        Player player = new Player("Adventurer");

        player.setEscaped(true);

        assertTrue(player.hasEscaped());
    }
}