
import java.util.*;

public class BasicUser extends User {

    public BasicUser(String name, String password, String userId) {
        super(name, password, userId);
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);

        if (userId.startsWith("C")) {

            Cart cart = new Cart();
            MenuItem menu = new MenuItem("", "", "", 0, false);

            System.out.println("\nWelcome Customer!");

            while (!menuChoiceCust.equalsIgnoreCase("exit")) {

                System.out.println("\n1.Display Menu\n2.Add to Cart\n3.Place Order\n4.Make Payment");

                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        menu.displayMenu();
                        break;
                    case 2:
                        cart.setCustomerId(userId);
                        cart.addToCart();
                        break;
                    case 3:
                        cart.takeOrder();
                        break;
                    case 4:
                        cart.processPayment();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

                System.out.println("Type 'continue' or 'exit'");
                menuChoiceCust = sc.nextLine();
            }

        } else if (userId.startsWith("A")) {

            MenuItem menu = new MenuItem("", "", "", 0, false);
            InventoryItem item = new InventoryItem(0, "", 0, "", 0);
            SalesReport report = new SalesReport(0, 0, "", null);

            System.out.println("\nWelcome Admin!");

            while (!menuChoiceAdm.equalsIgnoreCase("exit")) {

                System.out.println(
                        "\n1.Display Menu\n2.Update Menu\n3.Display Inventory\n4.Check Availability\n5.Add Stock\n6.Remove Stock\n7.Report");

                int option = sc.nextInt();
                sc.nextLine();

                switch (option) {
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
                        report.displayReport();
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

                System.out.println("Type 'continue' or 'exit'");
                menuChoiceAdm = sc.nextLine();
            }
        }
    }
}