package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Item> inventory;
    private boolean escaped;

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.escaped = false;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }

        return false;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

        System.out.println("Inventory:");

        for (Item item : inventory) {
            System.out.println("- " + item.getName());
        }
    }

    public String getInventoryItemNames() {
        if (inventory.isEmpty()) {
            return "none";
        }

        StringBuilder itemNames = new StringBuilder();

        for (Item item : inventory) {
            if (itemNames.length() > 0) {
                itemNames.append(", ");
            }

            itemNames.append(item.getName());
        }

        return itemNames.toString();
    }

    public String getName() {
        return name;
    }

    public boolean hasEscaped() {
        return escaped;
    }

    public void setEscaped(boolean escaped) {
        this.escaped = escaped;
    }
}