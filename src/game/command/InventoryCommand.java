package game.command;

import game.Player;
import game.Room;
import game.strategy.InventoryStrategy;

public class InventoryCommand implements Command {

    private Player player;
    private Room room;

    public InventoryCommand(Player player, Room room) {
        this.player = player;
        this.room = room;
    }

    @Override
    public void execute() {
        new InventoryStrategy().execute(player, room, "");
    }
}