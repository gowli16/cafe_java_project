
import java.util.*;

public class BasicUser extends User {

    public BasicUser(String name, String password, String userId) {
        super(name, password, userId);
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);
        Order order = null;

        while (!menuChoiceCust.equalsIgnoreCase("exit")) {

            System.out.println("\n1.View Menu\n2.Place Order\n3.Make Payment");
            int op = sc.nextInt();

            switch (op) {

                case 1:
                    MenuItem.displayMenuFromCSV();
                    break;

                case 2:
                    order = new Order(1);
                    order.takeOrder();
                    order.displayOrder();
                    break;

                case 3:
                    if (order == null) {
                        System.out.println("Place order first");
                        break;
                    }
                    Payment p = new Payment(order);
                    p.takePayment();
                    p.processPayment();
                    p.generateReceipt();
                    break;

                default:
                    System.out.println("Invalid");
            }

            System.out.println("continue/exit?");
            menuChoiceCust = sc.next();
        }
    }
}