
public class OrderItem {

    String id;
    String name;
    int qty;
    double price;
    double subtotal;

    public OrderItem(String id, String name, int qty, double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.subtotal = qty * price;
    }

    public void displayItem() {
        System.out.println(id + " " + name + " x" + qty + " = " + subtotal);
    }
}