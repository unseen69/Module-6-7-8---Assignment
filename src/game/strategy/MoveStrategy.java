package game.strategy;

import game.GameManager;
import game.Player;
import game.Room;

public class MoveStrategy implements ActionStrategy {

    private GameManager gameManager;

    public MoveStrategy(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute(Player player, Room room, String direction) {
        if (direction == null || direction.isEmpty()) {
            System.out.println("Move where?");
            return;
        }

        Room nextRoom = room.getExit(direction);

        if (nextRoom == null) {
            System.out.println("You cannot move that way.");
            return;
        }

        gameManager.moveToRoom(nextRoom);
        System.out.println("You move " + direction + ".");
        nextRoom.showRoomDetails();
    }
}