package game.factory;

import game.Item;
import game.Room;

public class LibraryRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        Room room = new Room(
                "Library",
                "You enter an old library filled with dusty books and broken shelves."
        );

        room.setItem(new Item("sword", "An old sword. It is rusty, but still sharp enough to be useful."));

        return room;
    }
}