package game;

import game.factory.ArmoryRoomFactory;
import game.factory.DungeonRoomFactory;
import game.factory.ExitRoomFactory;
import game.factory.HallwayRoomFactory;
import game.factory.LibraryRoomFactory;
import game.factory.TrapRoomFactory;
import game.factory.TreasureRoomFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryTest {

    @Test
    public void testDungeonFactoryCreatesDungeon() {
        Room room = new DungeonRoomFactory().createRoom();

        assertEquals("Dungeon", room.getName());
    }

    @Test
    public void testHallwayFactoryCreatesHallway() {
        Room room = new HallwayRoomFactory().createRoom();

        assertEquals("Hallway", room.getName());
    }

    @Test
    public void testArmoryFactoryCreatesRoomWithKey() {
        Room room = new ArmoryRoomFactory().createRoom();

        assertEquals("Armory", room.getName());
        assertTrue(room.hasItem("key"));
    }

    @Test
    public void testLibraryFactoryCreatesRoomWithSword() {
        Room room = new LibraryRoomFactory().createRoom();

        assertEquals("Library", room.getName());
        assertTrue(room.hasItem("sword"));
    }

    @Test
    public void testTreasureRoomFactoryCreatesRoomWithGold() {
        Room room = new TreasureRoomFactory().createRoom();

        assertEquals("Treasure Room", room.getName());
        assertTrue(room.hasItem("gold"));
    }

    @Test
    public void testTrapRoomFactoryCreatesRoomWithAxe() {
        Room room = new TrapRoomFactory().createRoom();

        assertEquals("Trap Room", room.getName());
        assertTrue(room.hasItem("axe"));
    }

    @Test
    public void testExitFactoryCreatesLockedExitRoom() {
        Room room = new ExitRoomFactory().createRoom();

        assertEquals("Exit Room", room.getName());
        assertTrue(room.isLockedExitRoom());
    }
}