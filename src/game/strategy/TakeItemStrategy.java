package game.strategy;

import game.Item;
import game.Player;
import game.Room;

public class TakeItemStrategy implements ActionStrategy {

    @Override
    public void execute(Player player, Room room, String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            System.out.println("Take what?");
            return;
        }

        if (!room.hasItem(itemName)) {
            System.out.println("There is no " + itemName + " here.");
            return;
        }

        Item item = room.removeItem();
        player.addItem(item);

        System.out.println("You picked up the " + item.getName() + ".");
    }
}