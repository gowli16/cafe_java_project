package cafe;
import java.util.*;
import java.io.*;

public class InventoryItem {

    Scanner sc = new Scanner(System.in);

    private int inventoryId;
    private String ingredientName;
    private int quantityAvailable;
    private String unit;
    private int reorderLevel;

    public InventoryItem(int inventoryId, String ingredientName, int quantityAvailable, String unit, int reorderLevel) {
        this.inventoryId = inventoryId;
        this.ingredientName = ingredientName;
        this.quantityAvailable = quantityAvailable;
        this.unit = unit;
        this.reorderLevel = reorderLevel;
    }

    public ArrayList<InventoryItem> readInventory() {
        ArrayList<InventoryItem> inventoryList = new ArrayList<InventoryItem>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/meenakshinm/Desktop/inventory.csv"));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                InventoryItem item = new InventoryItem(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        Integer.parseInt(data[4])
                );

                inventoryList.add(item);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error reading inventory file");
        }

        return inventoryList;
    }

    public void writeInventory(ArrayList<InventoryItem> inventoryList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/meenakshinm/Desktop/inventory.csv"));
            bw.write("inventoryId,ingredientName,quantityAvailable,unit,reorderLevel");
            bw.newLine();

            for (int i = 0; i < inventoryList.size(); i++) {
                InventoryItem item = inventoryList.get(i);
                bw.write(item.inventoryId + "," + item.ingredientName + "," + item.quantityAvailable + "," + item.unit + "," + item.reorderLevel);
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing inventory file");
        }
    }

    public void displayInventory() {
        ArrayList<InventoryItem> inventoryList = readInventory();

        System.out.println("Inventory Details");
        System.out.println("-------------------------");

        for (int i = 0; i < inventoryList.size(); i++) {
            InventoryItem item = inventoryList.get(i);
            System.out.println("Inventory ID: " + item.inventoryId);
            System.out.println("Ingredient Name: " + item.ingredientName);
            System.out.println("Quantity Available: " + item.quantityAvailable);
            System.out.println("Unit: " + item.unit);
            System.out.println("Reorder Level: " + item.reorderLevel);
            System.out.println();
        }
    }

    public void addStock() {
        ArrayList<InventoryItem> inventoryList = readInventory();

        System.out.println("Enter inventory id:");
        int id = sc.nextInt();

        System.out.println("Enter quantity to add:");
        int qty = sc.nextInt();

        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).inventoryId == id) {
                inventoryList.get(i).quantityAvailable = inventoryList.get(i).quantityAvailable + qty;
                writeInventory(inventoryList);
                System.out.println("Stock added successfully");
                return;
            }
        }

        System.out.println("Inventory item not found");
    }

    public void reduceStock() {
        ArrayList<InventoryItem> inventoryList = readInventory();

        System.out.println("Enter inventory id:");
        int id = sc.nextInt();

        System.out.println("Enter quantity to reduce:");
        int qty = sc.nextInt();

        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).inventoryId == id) {
                if (inventoryList.get(i).quantityAvailable >= qty) {
                    inventoryList.get(i).quantityAvailable = inventoryList.get(i).quantityAvailable - qty;
                    writeInventory(inventoryList);
                    System.out.println("Stock reduced successfully");
                } else {
                    System.out.println("Not enough stock available");
                }
                return;
            }
        }

        System.out.println("Inventory item not found");
    }

    public void checkAvailability() {
        ArrayList<InventoryItem> inventoryList = readInventory();

        System.out.println("Enter inventory id:");
        int id = sc.nextInt();

        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).inventoryId == id) {
                if (inventoryList.get(i).quantityAvailable <= inventoryList.get(i).reorderLevel) {
                    System.out.println("Stock is low");
                } else {
                    System.out.println("Stock is available");
                }
                return;
            }
        }

        System.out.println("Inventory item not found");
    }

    public static void main(String[] args) {
        InventoryItem item = new InventoryItem(0, "", 0, "", 0);
        item.displayInventory();
        item.checkAvailability();
        item.addStock();
        item.reduceStock();
    }
}