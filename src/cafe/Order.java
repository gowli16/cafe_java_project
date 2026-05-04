import java.util.*;
import java.io.*;

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

    public void takeOrder() throws Exception {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("menu.csv"));
        String line;

        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            String id = data[0];
            String name = data[1];
            double price = Double.parseDouble(data[3]);
            boolean available = Boolean.parseBoolean(data[4]);

            if (available) {
                ids.add(id);
                names.add(name);
                prices.add(price);
            }
        }

        br.close();

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("\nMenu:");
            for (int j = 0; j < names.size(); j++) {
                System.out.println((j + 1) + ". " + names.get(j) + " - ₹" + prices.get(j));
            }

            System.out.print("Choose item (number): ");
            int choice = sc.nextInt();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            String itemId = ids.get(choice - 1);
            String name = names.get(choice - 1);
            double price = prices.get(choice - 1);

            OrderItem item = new OrderItem(itemId, name, qty, price);
            addItem(item);
        }

        updateStatus("Confirmed");
    }

    public void addItem(OrderItem item) {
        items.add(item);
        totalAmount += item.subtotal;
    }

    public void updateStatus(String status) {
        this.orderStatus = status;
    }

    public void displayOrder() {
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Status: " + orderStatus);

        for (OrderItem item : items) {
            item.displayItem();
        }

        System.out.println("Total: ₹" + totalAmount);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}