
public class Order {
    int orderId;
    String orderStatus;
    OrderItem[] items;
    int itemCount;
    double totalAmount;

    Payment payment;

    public Order(int orderId) {
        this.orderId = orderId;
        this.orderStatus = "Created";
        this.items = new OrderItem[10];
        this.itemCount = 0;
        this.totalAmount = 0;
    }

    public void addItem(OrderItem item) {
        items[itemCount] = item;
        itemCount++;
        totalAmount += item.subtotal;
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (int i = 0; i < itemCount; i++) {
            totalAmount += items[i].subtotal;
        }
    }

    public void updateStatus(String status) {
        this.orderStatus = status;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Status: " + orderStatus);

        for (int i = 0; i < itemCount; i++) {
            items[i].displayItem();
        }

        System.out.println("Total: " + totalAmount);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}