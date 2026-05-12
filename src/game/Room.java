package game;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private String name;
    private String description;
    private Map<String, Room> exits;
    private Item item;
    private boolean lockedExitRoom;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.lockedExitRoom = false;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void showRoomDetails() {
        System.out.println(name);
        System.out.println(description);

        if (item != null) {
            System.out.println("You see a " + item.getName() + ".");
        }

        System.out.println("Exits: " + exits.keySet());
    }

    public boolean hasItem(String itemName) {
        return item != null && item.getName().equalsIgnoreCase(itemName);
    }

    public Item removeItem() {
        Item foundItem = item;
        item = null;
        return foundItem;
    }

    public String getAvailableItemName() {
        if (item == null) {
            return "none";
        }

        return item.getName();
    }

    public String getName() {
        return name;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isLockedExitRoom() {
        return lockedExitRoom;
    }

    public void setLockedExitRoom(boolean lockedExitRoom) {
        this.lockedExitRoom = lockedExitRoom;
    }
}