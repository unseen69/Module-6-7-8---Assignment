package game.strategy;

import game.Player;
import game.Room;

public class LookStrategy implements ActionStrategy {

    @Override
    public void execute(Player player, Room room, String input) {
        room.showRoomDetails();
        player.showInventory();
    }
}