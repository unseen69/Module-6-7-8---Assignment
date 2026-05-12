package game.strategy;

import game.Player;
import game.Room;

public class UseItemStrategy implements ActionStrategy {

    @Override
    public void execute(Player player, Room room, String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Use what?");
            return;
        }

        if (!player.hasItem(itemName)) {
            System.out.println("You do not have that item.");
            return;
        }

        if (itemName.equalsIgnoreCase("key") && room.isLockedExitRoom()) {
            System.out.println("You use the key and unlock the exit.");
            player.setEscaped(true);
        } else {
            System.out.println("You cannot use that here.");
        }
    }
}