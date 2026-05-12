package game.command;

import game.Player;
import game.Room;
import game.strategy.UseItemStrategy;

public class UseCommand implements Command {

    private Player player;
    private Room room;
    private String itemName;

    public UseCommand(Player player, Room room, String itemName) {
        this.player = player;
        this.room = room;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        new UseItemStrategy().execute(player, room, itemName);
    }
}