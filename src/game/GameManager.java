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
import game.factory.HallwayRoomFactory;
import game.factory.LibraryRoomFactory;
import game.factory.TrapRoomFactory;
import game.factory.TreasureRoomFactory;

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
        Room hallway = new HallwayRoomFactory().createRoom();
        Room library = new LibraryRoomFactory().createRoom();
        Room armory = new ArmoryRoomFactory().createRoom();
        Room treasureRoom = new TreasureRoomFactory().createRoom();
        Room trapRoom = new TrapRoomFactory().createRoom();
        Room exit = new ExitRoomFactory().createRoom();

        // Main correct path:
        // Dungeon -> Hallway -> Armory -> Exit Room
        dungeon.setExit("north", hallway);

        hallway.setExit("south", dungeon);
        hallway.setExit("north", armory);

        armory.setExit("south", hallway);
        armory.setExit("east", exit);

        exit.setExit("west", armory);

        // Dead-end side path from the hallway.
        // Player can return to the correct path.
        hallway.setExit("west", library);
        library.setExit("east", hallway);

        // Dead-end side path from the armory.
        // Treasure Room leads to Trap Room, but both have a way back.
        armory.setExit("west", treasureRoom);
        treasureRoom.setExit("east", armory);
        treasureRoom.setExit("south", trapRoom);
        trapRoom.setExit("north", treasureRoom);

        rooms.put("dungeon", dungeon);
        rooms.put("hallway", hallway);
        rooms.put("library", library);
        rooms.put("armory", armory);
        rooms.put("treasure", treasureRoom);
        rooms.put("trap", trapRoom);
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
                "L/look - Look around",
                "I/inventory - Show items",
                "Q/quit - Quit game");

        System.out.printf("%-28s %-28s %-28s%n",
                "M north - Move north",
                "M south - Move south",
                "M east - Move east");

        System.out.printf("%-28s %-28s %-28s%n",
                "M west - Move west",
                "T/take " + roomItem,
                "U/use " + inventoryItems);

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