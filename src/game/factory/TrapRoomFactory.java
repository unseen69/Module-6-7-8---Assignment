package game.factory;

import game.Item;
import game.Room;

public class TrapRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        Room room = new Room(
                "Trap Room",
                "You step into a dead-end room. The floor is cracked and the walls are covered in claw marks."
        );

        room.setItem(new Item("axe", "A heavy battle axe. It looks old, but powerful."));

        return room;
    }
}