package project;
import java.util.*;
import java.io.*;

public class User {
    Scanner sc = new Scanner(System.in);
    HashMap<String, String> employeeMap = new HashMap<>();
    protected String name;
    protected int userId;

    public User() {
        userId = 000;
        name=employeeMap.keySet();
    }

    public void read() {
        employeeMap.clear();
        try (BufferedReader br = new BufferedReader(
                new FileReader("mockpasswordlist.csv"))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 3) {
                    employeeMap.put(
                        columns[0].trim(),
                        columns[1].trim() + "," + columns[2].trim()
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNewRegister(String n, String p) {
        int newId = employeeMap.size() + 1;
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("D:/ANJANA/CLASS/Presentation & Projects/oop/mockpasswordlist.csv", true))) {
            bw.newLine();
            bw.write(n + "," + p + "," + String.format("%03d", newId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        read();
        System.out.print("\nEnter username: ");
        String n = sc.next();
        System.out.print("Enter password: ");
        String p = sc.next();
        if (employeeMap.containsKey(n)) {
            String data = employeeMap.get(n);
            String[] parts = data.split(",");
            String storedPassword = parts[0];
            String storedUserId = parts[1];
            if (storedPassword.equals(p)) {
                System.out.println("Login successful!!");
                System.out.println("User ID: " + storedUserId);
                return;
            }
        }
        System.out.println("Login failed. Have you registered?(Y/N)");
        char answer = sc.next().charAt(0);
        if (answer == 'N' || answer == 'n') {
            System.out.println("Redirecting to Registration...");
            register();
        } else {
            System.out.println("Cannot log you in. Please try again later.");
        }
    }

    public void register() {
        read();
        System.out.print("\nEnter username: ");
        String n = sc.next();
        if (employeeMap.containsKey(n)) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String p = sc.next();
        saveNewRegister(n, p);
        System.out.println("Registration successful!!!");
        System.out.println("Taking you back to Login...");
        login();
    }

    public static void main(String[] args) {
        User u = new User();
        u.login();
    }
}
