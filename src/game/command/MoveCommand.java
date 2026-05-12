package game.command;

import game.GameManager;
import game.Player;
import game.Room;
import game.strategy.MoveStrategy;

public class MoveCommand implements Command {

    private GameManager gameManager;
    private Player player;
    private Room room;
    private String direction;

    public MoveCommand(GameManager gameManager, Player player, Room room, String direction) {
        this.gameManager = gameManager;
        this.player = player;
        this.room = room;
        this.direction = direction;
    }

    @Override
    public void execute() {
        new MoveStrategy(gameManager).execute(player, room, direction);
    }
}