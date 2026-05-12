package game;

import game.command.Command;
import game.command.InventoryCommand;
import game.command.LookCommand;
import game.command.MoveCommand;
import game.command.QuitCommand;
import game.command.TakeCommand;
import game.command.UseCommand;
import game.factory.ArmoryRoomFactory;
import game.factory.DungeonRoomFactory;
import game.factory.ExitRoomFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameManager {

    private static GameManager instance;

    private Player player;
    private Room currentRoom;
    private boolean running;
    private Map<String, Room> rooms;

    private GameManager() {
        rooms = new HashMap<>();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public void startGame() {
        setupGame();

        Scanner scanner = new Scanner(System.in);
        running = true;

        System.out.println("Welcome to Dungeon Escape!");
        System.out.println("Find the key and escape the dungeon.");
        showCommands();

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            Command command = parseCommand(input);
            command.execute();

            if (player.hasEscaped()) {
                System.out.println("You escaped the dungeon. You win!");
                running = false;
            }

            if (running) {
                showCommands();
            }
        }

        scanner.close();
    }

    private void setupGame() {
        player = new Player("Adventurer");

        Room dungeon = new DungeonRoomFactory().createRoom();
        Room armory = new ArmoryRoomFactory().createRoom();
        Room exit = new ExitRoomFactory().createRoom();

        dungeon.setExit("north", armory);
        armory.setExit("south", dungeon);
        armory.setExit("east", exit);
        exit.setExit("west", armory);

        rooms.put("dungeon", dungeon);
        rooms.put("armory", armory);
        rooms.put("exit", exit);

        currentRoom = dungeon;
    }

    private Command parseCommand(String input) {
        if (input == null || input.isEmpty()) {
            return new Command() {
                @Override
                public void execute() {
                    System.out.println("Please enter a command.");
                }
            };
        }

        String[] parts = input.split(" ");

        String action = parts[0];

        String target = "";
        if (parts.length > 1) {
            target = parts[1];
        }

        if (action.equals("look") || action.equals("l")) {
            return new LookCommand(player, currentRoom);
        } else if (action.equals("inventory") || action.equals("i")) {
            return new InventoryCommand(player, currentRoom);
        } else if (action.equals("move") || action.equals("m")) {
            return new MoveCommand(this, player, currentRoom, target);
        } else if (action.equals("take") || action.equals("t")) {
            return new TakeCommand(player, currentRoom, target);
        } else if (action.equals("use") || action.equals("u")) {
            return new UseCommand(player, currentRoom, target);
        } else if (action.equals("quit") || action.equals("q")) {
            return new QuitCommand(this);
        } else {
            return new Command() {
                @Override
                public void execute() {
                    System.out.println("Unknown command.");
                }
            };
        }
    }

    private void showCommands() {
        String roomItem = currentRoom.getAvailableItemName();
        String inventoryItems = player.getInventoryItemNames();

        System.out.println();
        System.out.println("Commands:");

        System.out.printf("%-28s %-28s %-28s%n",
                "L - Look - Look around",
                "I - Inventory - Show items",
                "Q - Quit - Quit game");

        System.out.printf("%-28s %-28s %-28s%n",
                "M - North - Move north",
                "M - South - Move south",
                "M - East - Move east");

        System.out.printf("%-28s %-28s %-28s%n",
                "M west - Move west",
                "T - Take item " + roomItem,
                "U - Use item " + inventoryItems);

        System.out.println();
    }



    public void moveToRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public void stopGame() {
        running = false;
    }
}