package game.command;

import game.Player;
import game.Room;
import game.strategy.LookStrategy;

public class LookCommand implements Command {

    private Player player;
    private Room room;

    public LookCommand(Player player, Room room) {
        this.player = player;
        this.room = room;
    }

    @Override
    public void execute() {
        new LookStrategy().execute(player, room, "");
    }
}