package game.factory;

import game.Room;

public class ExitRoomFactory extends RoomFactory {

    @Override
    public Room createRoom() {
        Room room = new Room(
                "Exit Room",
                "You stand before a heavy wooden door. It appears to be locked."
        );

        room.setLockedExitRoom(true);

        return room;
    }
}