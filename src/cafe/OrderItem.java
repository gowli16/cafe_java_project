
public class OrderItem {
    int orderItemId;
    String itemName;
    int quantity;
    double price;
    double subtotal;

    public OrderItem(int orderItemId, String itemName, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = calculateSubtotal();
    }

    public double calculateSubtotal() {
        return quantity * price;
    }

    public void displayItem() {
        System.out.println(itemName + " x " + quantity + " = " + subtotal);
    }
}