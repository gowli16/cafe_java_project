import java.util.ArrayList;

public class Order {
    int orderId;
    String orderStatus;
    ArrayList<OrderItem> items;
    double totalAmount;

    Payment payment;

    public Order(int orderId) {
        this.orderId = orderId;
        this.orderStatus = "Created";
        this.items = new ArrayList<>();
        this.totalAmount = 0;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        totalAmount += item.subtotal;
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (OrderItem item : items) {
            totalAmount += item.subtotal;
        }
    }

    public void updateStatus(String status) {
        this.orderStatus = status;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Status: " + orderStatus);

        for (OrderItem item : items) {
            item.displayItem();
        }

        System.out.println("Total: " + totalAmount);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}