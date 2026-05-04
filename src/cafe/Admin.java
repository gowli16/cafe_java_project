package project;
import java.util.Scanner;

public class Admin extends AbstractUser {

    public Admin(String userId) {
        super(userId);
    }

    @Override
    public void displayMenu() {
        System.out.println("\nAdmin Functions");
        System.out.println("1.Display Menu");
        System.out.println("2.Update Menu Item");
        System.out.println("3.Display Inventory");
        System.out.println("4.Check Inventory Availability");
        System.out.println("5.Add Stock");
        System.out.println("6.Reduce Stock");
        System.out.println("7.Generate Report");
    }

    @Override
    public void performOperation(int choice, Scanner sc) {

        MenuItem menu = new MenuItem("", "", "", 0, false);
        InventoryItem item = new InventoryItem(0, "", 0, "", 0);
        SalesReport report = new SalesReport(0, 0, "", null);

        switch (choice) {

            case 1:
                menu.displayMenuAdmin();
                break;

            case 2:
                menu.updateItem();
                break;

            case 3:
                item.displayInventory();
                break;

            case 4:
                item.checkAvailability();
                break;

            case 5:
                item.addStock();
                break;

            case 6:
                item.reduceStock();
                break;

            case 7:
                report.generateDailyReport(); 
                break;

            default:
                System.out.println("Invalid choice");
        }
    }
}
