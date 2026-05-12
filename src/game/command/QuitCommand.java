package game.command;

import game.GameManager;

public class QuitCommand implements Command {

    private GameManager gameManager;

    public QuitCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        System.out.println("Thanks for playing.");
        gameManager.stopGame();
    }
}