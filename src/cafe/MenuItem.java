package project;
import java.util.*;
import java.io.*;

public class MenuItem {

    String choice = null;
    Scanner sc = new Scanner(System.in);

    String path = "menu.csv";

    private String itemId, itemName, category;
    private double price;
    boolean availabilityStatus;

    public MenuItem(String itemId, String itemName, String category,
                    double price, boolean availabilityStatus) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.availabilityStatus = availabilityStatus;
    }

    
    
    public ArrayList<MenuItem> loadMenu() {
        ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                MenuItem item = new MenuItem(
                        fields[0],
                        fields[1],
                        fields[2],
                        Double.parseDouble(fields[3]),
                        Boolean.parseBoolean(fields[4])
                );

                menuList.add(item);
            }

            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return menuList;
    }

    
    
    public void displayMenuAdmin() {
        ArrayList<MenuItem> menuList = loadMenu();

        System.out.printf("%-5s %-30s %-15s %-10s %-10s%n",
                "ID", "Name", "Category", "Price", "Available");

        for (MenuItem item : menuList) {
            System.out.printf("%-5s %-30s %-15s %-10.2f %-10s%n",
                    item.itemId,
                    item.itemName,
                    item.category,
                    item.price,
                    item.availabilityStatus);
        }
    }
    
    
    public void displayMenu() {
        ArrayList<MenuItem> menuList = loadMenu();

        System.out.printf("%-30s %-15s %-10s %-10s%n",
                "Name", "Category", "Price", "Available");

        for (MenuItem item : menuList) {
            System.out.printf("%-30s %-15s %-10.2f %-10s%n",
                    item.itemName,
                    item.category,
                    item.price,
                    item.availabilityStatus);
        }
    }
    
    
    public void updateItem() {
        ArrayList<MenuItem> menuList = loadMenu();

        System.out.println("\n\tMenu");
        System.out.println("1. Change Price");
        System.out.println("2. Change Availability");
        System.out.println("3. Add Item");
        System.out.println("4. Remove Item");
        System.out.print("Enter your choice: ");
        choice = sc.next();
        sc.nextLine();
        switch (choice) {

            case "1":
                System.out.print("Enter id of item to be modified: ");
                String priceChangeId = sc.next();

                for (MenuItem item : menuList) {
                    if (item.itemId.equals(priceChangeId)) {
                        System.out.print("Enter updated price: ");
                        double priceChange = sc.nextDouble();
                        item.price = priceChange;
                     }
                }
                System.out.println("Change made successfully!!");
                break;

            case "2":
                System.out.print("Enter id of item to be modified: ");
                String availabilityChangeId = sc.next();

                for (MenuItem item : menuList) {
                    if (item.itemId.equals(availabilityChangeId)) {
                        item.availabilityStatus = !item.availabilityStatus;
                        System.out.println("Change made successfully!!");
                    }
                }
                break;

            case "3":
                MenuItem lastItem = menuList.get(menuList.size() - 1);
                int lastId = Integer.parseInt(lastItem.itemId.substring(1));
                String itemIdAddition = "M" + String.format("%03d", lastId + 1);
                System.out.print("Enter Item name: ");
                String nameAddition = sc.nextLine();

                System.out.print("Enter Item category: ");
                String categoryAddition = sc.next();

                System.out.print("Enter Item price: ");
                double priceAddition = sc.nextDouble();

                System.out.print("Enter Item availability (true/false): ");
                boolean availabilityStatusAddition = sc.nextBoolean();

                MenuItem newItem = new MenuItem(
                        itemIdAddition,
                        nameAddition,
                        categoryAddition,
                        priceAddition,
                        availabilityStatusAddition
                );

                menuList.add(newItem);

                System.out.println("Change made successfully!!");
                break;

            case "4":
                System.out.print("Enter id of item to be removed: ");
                String removeId = sc.next();

                for (int i = 0; i < menuList.size(); i++) {
                    if (menuList.get(i).itemId.equals(removeId)) {             
                        
                    	menuList.remove(i);
                        System.out.println("Change made successfully!!");
                        break;
                    }
                }
                break;

            default:
                System.out.println("Invalid choice made");
        }
        write(menuList);
    }

    
    
    public void write(ArrayList<MenuItem> menuList) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("ItemID,ItemName,Category,Price,AvailabilityStatus");
            writer.newLine();

            for (MenuItem item : menuList) {
                writer.write(
                        item.itemId + "," +
                        item.itemName + "," +
                        item.category + "," +
                        item.price + "," +
                        item.availabilityStatus
                );
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    public String getItemName() {
    	return itemName;
    }
    
    public String getItemId() {
    	return itemId;
    }
    
    public double getPrice() {
    	return price;
    }
    
    
    /*public static void main(String[] args) {
        MenuItem m = new MenuItem("", "", "", 0, false);

        m.displayMenu();
        m.updateItem();
        m.displayMenu();
    } */
}
