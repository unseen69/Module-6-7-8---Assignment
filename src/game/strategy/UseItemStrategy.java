package game.strategy;

import game.Player;
import game.Room;

public class UseItemStrategy implements ActionStrategy {

    @Override
    public void execute(Player player, Room room, String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            System.out.println("Use what?");
            return;
        }

        if (!player.hasItem(itemName)) {
            System.out.println("You do not have a " + itemName + ".");
            return;
        }

        if (room.isLockedExitRoom() && itemName.equalsIgnoreCase("key")) {
            System.out.println("You unlock the exit door with the key.");
            player.setEscaped(true);
        } else {
            System.out.println("You cannot use that here.");
        }
    }
}