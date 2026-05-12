package game.strategy;

import game.Item;
import game.Player;
import game.Room;

public class TakeItemStrategy implements ActionStrategy {

    @Override
    public void execute(Player player, Room room, String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("Take what?");
            return;
        }

        if (room.hasItem(itemName)) {
            Item item = room.removeItem();
            player.addItem(item);
            System.out.println("You picked up the " + item.getName() + ".");
        } else {
            System.out.println("That item is not here.");
        }
    }
}