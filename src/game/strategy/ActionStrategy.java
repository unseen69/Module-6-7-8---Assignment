package game.strategy;

import game.Player;
import game.Room;

public interface ActionStrategy {
    void execute(Player player, Room room, String input);
}