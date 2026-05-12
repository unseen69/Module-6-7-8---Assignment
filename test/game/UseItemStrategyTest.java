package game;

import game.strategy.UseItemStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UseItemStrategyTest {

    @Test
    public void testUsingKeyInLockedExitRoomWinsGame() {
        Player player = new Player("Adventurer");
        player.addItem(new Item("key", "A small key."));

        Room exitRoom = new Room("Exit Room", "A locked exit.");
        exitRoom.setLockedExitRoom(true);

        UseItemStrategy strategy = new UseItemStrategy();
        strategy.execute(player, exitRoom, "key");

        assertTrue(player.hasEscaped());
    }

    @Test
    public void testUsingKeyInNormalRoomDoesNotWinGame() {
        Player player = new Player("Adventurer");
        player.addItem(new Item("key", "A small key."));

        Room normalRoom = new Room("Dungeon", "A dark dungeon.");

        UseItemStrategy strategy = new UseItemStrategy();
        strategy.execute(player, normalRoom, "key");

        assertFalse(player.hasEscaped());
    }

    @Test
    public void testCannotUseItemPlayerDoesNotHave() {
        Player player = new Player("Adventurer");

        Room exitRoom = new Room("Exit Room", "A locked exit.");
        exitRoom.setLockedExitRoom(true);

        UseItemStrategy strategy = new UseItemStrategy();
        strategy.execute(player, exitRoom, "key");

        assertFalse(player.hasEscaped());
    }
}