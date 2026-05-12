package game.command;

import game.Player;
import game.Room;
import game.strategy.TakeItemStrategy;

public class TakeCommand implements Command {

    private Player player;
    private Room room;
    private String itemName;

    public TakeCommand(Player player, Room room, String itemName) {
        this.player = player;
        this.room = room;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        new TakeItemStrategy().execute(player, room, itemName);
    }
}