package project;
import java.util.*;
import java.io.*;

public class User {
	
	static String menuChoiceCust= "continue";
	static String menuChoiceAdm= "continue";
    Scanner sc = new Scanner(System.in);

    protected String name, password, userId;

    String path = "D:\\ANJANA\\CLASS\\Presentation & Projects\\oop\\mockpasswordlist.csv";

    public User(String name, String password, String userId) {
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

    
    public ArrayList<User> loadData() {

        ArrayList<User> loginList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            reader.readLine(); 

            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");

                if (fields.length >= 3) {
                    loginList.add(new User(
                            fields[0].trim(),
                            fields[1].trim(),
                            fields[2].trim()
                    ));
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return loginList;
    }

   
    public void saveNewRegister(String n, String p) {

        ArrayList<User> loginList = loadData();

        String userIdAddition;

        if (loginList.isEmpty()) {
            userIdAddition = "C001";
        } else {
            User lastItem = loginList.get(loginList.size() - 1);

            int lastId = Integer.parseInt(lastItem.userId.substring(1));

            userIdAddition = "C" + String.format("%03d", lastId + 1);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {

            bw.newLine();
            bw.write(n + "," + p + "," + userIdAddition);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

   
    public String login() {

        ArrayList<User> loginList = loadData();

        System.out.print("\nEnter username: ");
        String n = sc.next();

        System.out.print("Enter password: ");
        String p = sc.next();

        for (User item : loginList) {

            if (item.name.equals(n)) {

                if (item.password.equals(p)) {

                    System.out.println("Login successful!!");
                    System.out.println("User ID: " + item.userId);

                    return item.userId;
                }
            }
        }

        System.out.println("Login failed.");
        return null;
    }

    
    public String register() {

        ArrayList<User> loginList = loadData();

        System.out.print("\nEnter username: ");
        String n = sc.next();

        for (User item : loginList) {
            if (item.name.equals(n)) {
                System.out.println("Username already exists.");
                return null;
            }
        }

        System.out.print("Enter password: ");
        String p = sc.next();

        saveNewRegister(n, p);

        System.out.println("Registration successful!");
        System.out.println("Now login...");

        return login();
    }

    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        User user = new User("", "", "");

        System.out.println("====== CAFE SYSTEM ======");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();
        String userId = null;

        if (choice == 1) {
            userId = user.login();
        } else if (choice == 2) {
        	userId = user.register();
        } else {
            System.out.println("Invalid choice.");
            return;
        }

       
        if (userId == null) {
            System.out.println("Exiting...");
            return;
        }

        
        AbstractUser role = null;

        if (userId.startsWith("C")) {
            role = new Customer(userId);
            System.out.println("\nWelcome Customer!");
        }
        else if (userId.startsWith("A")) {
            role = new Admin(userId);
            System.out.println("\nWelcome Admin!");
        }
        else {
            System.out.println("Unknown user type.");
            return;
        }

        String loopChoice = "continue";

        while (!loopChoice.equalsIgnoreCase("exit")) {

            role.displayMenu();

            int option = sc.nextInt();
            sc.nextLine(); 
            role.performOperation(option, sc);

            System.out.println("Type 'continue' to continue or 'exit' to stop:");
            loopChoice = sc.nextLine();
        }}}
