package game.strategy;

import game.Player;
import game.Room;

public class InventoryStrategy implements ActionStrategy {

    @Override
    public void execute(Player player, Room room, String input) {
        player.showInventory();
    }
}