package game;

import game.strategy.TakeItemStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TakeItemStrategyTest {

    @Test
    public void testPlayerCanTakeItemFromRoom() {
        Player player = new Player("Adventurer");
        Room room = new Room("Armory", "An old armory.");
        room.setItem(new Item("key", "A small key."));

        TakeItemStrategy strategy = new TakeItemStrategy();
        strategy.execute(player, room, "key");

        assertTrue(player.hasItem("key"));
        assertFalse(room.hasItem("key"));
    }

    @Test
    public void testPlayerCannotTakeMissingItem() {
        Player player = new Player("Adventurer");
        Room room = new Room("Armory", "An old armory.");

        TakeItemStrategy strategy = new TakeItemStrategy();
        strategy.execute(player, room, "key");

        assertFalse(player.hasItem("key"));
    }
}