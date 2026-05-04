package project;

import java.util.Scanner;

public class Customer extends AbstractUser {

    public Customer(String userId) {
        super(userId);
    }

    @Override
    public void displayMenu() {
        System.out.println("\nCustomer Amenities");
        System.out.println("1.Display Menu");
        System.out.println("2.Add to Cart");
        System.out.println("3.Place Order & Payment");
    }

    @Override
    public void performOperation(int choice, Scanner sc) {

        Cart cart = new Cart();
        MenuItem menu = new MenuItem("", "", "", 0, false);

        switch (choice) {

            case 1:
                menu.displayMenu();
                break;

            case 2:
                cart.setCustomerId(userId);
                cart.addToCart();
                break;

            case 3:
                System.out.print("Enter Order ID: ");
                String orderId = sc.next();
                sc.nextLine(); // IMPORTANT

                Order order = new Order(orderId);

                try {
                    order.takeOrder();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                Payment payment = new Payment(order);
                order.setPayment(payment);
                payment.processPayment(sc);
                break;

            default:
                System.out.println("Invalid choice");
        }
    }
}