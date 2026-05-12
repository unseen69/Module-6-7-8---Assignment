package game.factory;

import game.Room;

public class DungeonRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        return new Room(
                "Dungeon",
                "You are in a cold stone dungeon. The air is damp and the walls are covered in moss."
        );
    }
}