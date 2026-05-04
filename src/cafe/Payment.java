
import java.util.*;

public class Payment {

    Order order;
    String method;

    public Payment(Order order) {
        this.order = order;
    }

    public void takePayment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Method: ");
        method = sc.next();
    }

    public void processPayment() {
        System.out.println("Payment done");
    }

    public void generateReceipt() {
        System.out.println("Receipt:");
        order.displayOrder();
        System.out.println("Method: " + method);
    }
}