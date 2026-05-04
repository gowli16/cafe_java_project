
import java.util.*;
import java.io.*;

public class Order {

    int orderId;
    ArrayList<OrderItem> items = new ArrayList<>();
    double total = 0;

    public Order(int id) {
        this.orderId = id;
    }

    public void takeOrder() throws Exception {

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader("menu.csv"));

        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();

        br.readLine();

        String line;
        while ((line = br.readLine()) != null) {
            String[] d = line.split(",");
            if (Boolean.parseBoolean(d[4])) {
                ids.add(d[0]);
                names.add(d[1]);
                prices.add(Double.parseDouble(d[3]));
            }
        }
        br.close();

        System.out.print("Items count: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < names.size(); j++) {
                System.out.println((j + 1) + ". " + names.get(j) + " ₹" + prices.get(j));
            }

            int c = sc.nextInt();
            int q = sc.nextInt();

            OrderItem item = new OrderItem(ids.get(c - 1), names.get(c - 1), q, prices.get(c - 1));
            items.add(item);
            total += item.subtotal;
        }
    }

    public void displayOrder() {
        for (OrderItem i : items)
            i.displayItem();
        System.out.println("Total: ₹" + total);
    }
}