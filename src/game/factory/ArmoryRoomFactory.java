package game.factory;

import game.Item;
import game.Room;

public class ArmoryRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        Room room = new Room(
                "Armory",
                "You are in an old armory. Broken weapons and rusty shields are scattered everywhere."
        );

        room.setItem(new Item("key", "A small iron key. It might unlock the exit."));

        return room;
    }
}