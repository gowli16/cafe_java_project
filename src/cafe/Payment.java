package project;
import java.util.Scanner;

public class Payment {

    String paymentId;
    double amount;
    String paymentMethod;
    String paymentStatus;
    Order order;

    public Payment(Order order) {
        this.order = order;
        this.amount = order.totalAmount;
        this.paymentStatus = "Pending";
    }

   
    public void takePayment(Scanner sc) {

        System.out.print("\nEnter Payment ID: ");
        paymentId = sc.next();
        sc.nextLine(); 

        System.out.print("Enter Payment Method: ");
        paymentMethod = sc.nextLine();
    }

   
    public void processPayment(Scanner sc) {

        takePayment(sc);

        paymentStatus = "Completed";

        System.out.println("Payment successful for Order ID: " + order.orderId);

        generateReceipt();
    }

    public void generateReceipt() {

        System.out.println("\n----- RECEIPT -----");
        System.out.println("Order ID: " + order.orderId);
        System.out.println("Amount Paid: " + amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Status: " + paymentStatus);
    }
}
