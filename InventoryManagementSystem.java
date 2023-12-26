import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagementSystem {
    private Map<String, Item> inventory; // Declare inventory as a map that stores Strings as keys and Integers as values

    public InventoryManagementSystem() {
        inventory = new HashMap<>(); // Initialize the inventory as a new HashMap
    }
     
    //addItem adds an item to the inventory or increments its quantity
    public void addItem(String itemName, int quantity, double price) {
        if (inventory.containsKey(itemName)) { // Check if the item already exists in the inventory
            Item item = inventory.get(itemName); // Get the current quantity of the item
            item.setQuantity(item.getQuantity() +quantity); // Increase the quantity of the existing item
        } else {
            Item newItem = new Item(itemName, quantity,price);
            inventory.put(itemName, newItem); // Add a new item to the inventory
        }
        System.out.println(quantity + " " + itemName + "(s) added to the inventory.");
    }

    //removeItem method removes an item from the inventory or decrements its quantity
    public void removeItem(String itemName, int quantity) {
        if (inventory.containsKey(itemName)) { // Check if the item exists in the inventory
            Item item = inventory.get(itemName); // Get the current quantity of the item
            if (item.getQuantity() >= quantity) { // Check if there's enough quantity to remove
                item.setQuantity(item.getQuantity() - quantity);; // Decrease the quantity of the item
                System.out.println(quantity + " " + itemName + "(s) removed from the inventory.");
                if (item.getQuantity() == 0) { // Check if the quantity is zero after removal
                    inventory.remove(itemName); // Remove the item from the inventory if the quantity is zero
                }
            } else {
                System.out.println("Insufficient quantity of " + itemName + " in the inventory.");
            }
        } else {
            System.out.println("Item not found in the inventory.");
        }
    }

    //displayInventory method shows the current status of the inventory
    public void displayInventory() {
        System.out.println("Current Inventory Status:");
        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            Item item =entry.getValue();
            System.out.println(item.getName() + ": Quantity - " + item.getQuantity() + ", Price - $" + item.getPrice()); // Print each item and its quantity in the inventory
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem inventorySystem = new InventoryManagementSystem(); // Create an instance of the InventoryManagementSystem class
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Display inventory");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); // Read the user's choice from the console

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String addItem = scanner.next(); // Read the item name from the console
                    System.out.print("Enter quantity: ");
                    int addQuantity = scanner.nextInt(); // Read the quantity from the console
                    System.out.print("Enter price: $");
                    double addPrice = scanner.nextDouble();
                    inventorySystem.addItem(addItem, addQuantity, addPrice); // Call the addItem method to add the item to the inventory
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String removeItem = scanner.next(); // Read the item name from the console
                    System.out.print("Enter quantity: ");
                    int removeQuantity = scanner.nextInt(); // Read the quantity from the console
                    inventorySystem.removeItem(removeItem, removeQuantity); // Call the removeItem method to remove the item from the inventory
                    break;
                case 3:
                    inventorySystem.displayInventory(); // Call the displayInventory method to show the current inventory status
                    break;
                case 4:
                System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 4);

        scanner.close();
    }
}
class Item {
    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}