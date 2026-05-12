package game.factory;

import game.Item;
import game.Room;

public class TreasureRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        Room room = new Room(
                "Treasure Room",
                "You found a small treasure room. Gold coins are scattered across the floor."
        );

        room.setItem(new Item("gold", "A handful of gold coins. They may not help you escape, but they are valuable."));

        return room;
    }
}