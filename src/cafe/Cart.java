package project;
import java.util.*;
import java.io.*;

public class Cart {

    Scanner sc = new Scanner(System.in);
    private String customerId;
    private double totalAmount;
    String choice = "continue";

    ArrayList<MenuItem> cartItems = new ArrayList<MenuItem>();
    String path = "cart.csv";

    
    public void addToCart() {
        MenuItem menu = new MenuItem("", "", "", 0, false);
        ArrayList<MenuItem> menuList = menu.loadMenu();
        menu.displayMenu();

        while (!choice.equalsIgnoreCase("exit")) {
            System.out.println("Enter item to cart: ");
            String selectedItem = sc.nextLine();
            boolean found = false; 

            for (MenuItem item : menuList) {
                if (item.getItemName().equalsIgnoreCase(selectedItem)) {
                    cartItems.add(item);
                    System.out.println("Item added successfully!!");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item not found.");
            }
            System.out.println("Type 'exit' to stop adding items else type 'continue'");
            choice = sc.nextLine();
        }viewCart();      
        totalAmount();
        System.out.println("Do you want to remove item? (yes/no)");
        String ans = sc.nextLine();

        if (ans.equalsIgnoreCase("yes")) {
            removeItem();
            viewCart();
            totalAmount();
        }
        saveCartToCSV();
    }

    public void removeItem() {
        System.out.println("Enter item to be removed: ");
        String itemRemove = sc.nextLine();

        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getItemName().equalsIgnoreCase(itemRemove)) {
                cartItems.remove(i);
                System.out.println("Item has been removed from cart");
                return; 
            }
        }
        System.out.println("Item not found in cart.");
    }

    public void viewCart() {

        System.out.printf("%-30s %-10s%n", "Cart Item", "Price");

        for (MenuItem item : cartItems) {
            System.out.printf("%-30s %-10.2f%n",
                    item.getItemName(),
                    item.getPrice());
        }
    }

    public double totalAmount() {

        double sum = 0;

        for (MenuItem item : cartItems) {
            sum += item.getPrice();
        }

        totalAmount = sum;
        System.out.println("Total amount: " + totalAmount);
        return totalAmount;
    }

    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void saveCartToCSV() {

        try {

            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            boolean isEmpty = file.length() == 0;
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            if (isEmpty) {
                writer.write("CustomerID,ItemName,Price");
                writer.newLine();
            }

            for (MenuItem item : cartItems) {
                writer.write(
                        customerId + "," +
                        item.getItemName() + "," +
                        item.getPrice()
                );

                writer.newLine();
            }

            writer.close();

            System.out.println("Cart saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }
/*    // testing main method
    public static void main(String[] args) {

        Cart cart = new Cart();

        // temporary customerId (later get this from Customer class)
        cart.setCustomerId("C001");

        cart.addToCart();     
           
        System.out.println("Do you want to remove item? (yes/no)");
        Scanner sc = new Scanner(System.in);
        String ans = sc.nextLine();

        if (ans.equalsIgnoreCase("yes")) {
            cart.removeItem();
            cart.viewCart();
            cart.totalAmount();
        }

        cart.saveCartToCSV();
    } */
}
