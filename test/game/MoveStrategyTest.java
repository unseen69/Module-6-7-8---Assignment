package game;

import game.strategy.MoveStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveStrategyTest {

    @Test
    public void testPlayerCanMoveToValidRoom() {
        GameManager gameManager = GameManager.getInstance();

        Room dungeon = new Room("Dungeon", "A dark dungeon.");
        Room hallway = new Room("Hallway", "A long hallway.");

        dungeon.setExit("north", hallway);
        gameManager.moveToRoom(dungeon);

        Player player = new Player("Adventurer");

        MoveStrategy strategy = new MoveStrategy(gameManager);
        strategy.execute(player, dungeon, "north");

        assertEquals(hallway, gameManager.getCurrentRoom());
    }

    @Test
    public void testPlayerCannotMoveThroughInvalidExit() {
        GameManager gameManager = GameManager.getInstance();

        Room dungeon = new Room("Dungeon", "A dark dungeon.");
        gameManager.moveToRoom(dungeon);

        Player player = new Player("Adventurer");

        MoveStrategy strategy = new MoveStrategy(gameManager);
        strategy.execute(player, dungeon, "south");

        assertEquals(dungeon, gameManager.getCurrentRoom());
    }

    @Test
    public void testPlayerCannotEnterLockedExitWithoutKey() {
        GameManager gameManager = GameManager.getInstance();

        Room armory = new Room("Armory", "An old armory.");
        Room exitRoom = new Room("Exit Room", "A locked exit.");
        exitRoom.setLockedExitRoom(true);

        armory.setExit("east", exitRoom);
        gameManager.moveToRoom(armory);

        Player player = new Player("Adventurer");

        MoveStrategy strategy = new MoveStrategy(gameManager);
        strategy.execute(player, armory, "east");

        assertEquals(armory, gameManager.getCurrentRoom());
    }

    @Test
    public void testPlayerCanEnterLockedExitWithKey() {
        GameManager gameManager = GameManager.getInstance();

        Room armory = new Room("Armory", "An old armory.");
        Room exitRoom = new Room("Exit Room", "A locked exit.");
        exitRoom.setLockedExitRoom(true);

        armory.setExit("east", exitRoom);
        gameManager.moveToRoom(armory);

        Player player = new Player("Adventurer");
        player.addItem(new Item("key", "A small key."));

        MoveStrategy strategy = new MoveStrategy(gameManager);
        strategy.execute(player, armory, "east");

        assertEquals(exitRoom, gameManager.getCurrentRoom());
    }
}