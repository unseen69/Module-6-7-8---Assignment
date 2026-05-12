package game.factory;

import game.Room;

public class HallwayRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        return new Room(
                "Hallway",
                "You are in a long stone hallway. Torches flicker along the walls."
        );
    }
}