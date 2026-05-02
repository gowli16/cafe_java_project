
public class Payment {
    int paymentId;
    double amount;
    String paymentMethod;
    String paymentStatus;
    Order order;

    public Payment(int paymentId, double amount, String paymentMethod, Order order) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.order = order;
        this.paymentStatus = "Pending";
    }

    public void processPayment() {
        paymentStatus = "Completed";
        System.out.println("Payment successful for Order ID: " + order.orderId);
    }

    public void generateReceipt() {
        System.out.println("----- RECEIPT -----");
        System.out.println("Order ID: " + order.orderId);
        System.out.println("Amount Paid: " + amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Status: " + paymentStatus);
    }

    public void displayPayment() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + paymentStatus);
    }
}