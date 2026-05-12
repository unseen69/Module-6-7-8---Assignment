package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void testRoomCanStoreExit() {
        Room dungeon = new Room("Dungeon", "A dark dungeon.");
        Room hallway = new Room("Hallway", "A long hallway.");

        dungeon.setExit("north", hallway);

        assertEquals(hallway, dungeon.getExit("north"));
    }

    @Test
    public void testRoomReturnsNullForInvalidExit() {
        Room dungeon = new Room("Dungeon", "A dark dungeon.");

        assertNull(dungeon.getExit("south"));
    }

    @Test
    public void testRoomCanHoldItem() {
        Room armory = new Room("Armory", "An old armory.");
        Item key = new Item("key", "A small key.");

        armory.setItem(key);

        assertTrue(armory.hasItem("key"));
        assertEquals("key", armory.getAvailableItemName());
    }

    @Test
    public void testRemoveItemFromRoom() {
        Room armory = new Room("Armory", "An old armory.");
        Item key = new Item("key", "A small key.");

        armory.setItem(key);

        Item removedItem = armory.removeItem();

        assertEquals("key", removedItem.getName());
        assertFalse(armory.hasItem("key"));
        assertEquals("none", armory.getAvailableItemName());
    }

    @Test
    public void testLockedExitRoomFlag() {
        Room exitRoom = new Room("Exit Room", "A locked exit.");

        exitRoom.setLockedExitRoom(true);

        assertTrue(exitRoom.isLockedExitRoom());
    }
}