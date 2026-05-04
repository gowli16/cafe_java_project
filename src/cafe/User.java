

import java.util.*;
import java.io.*;

public abstract class User {

    static String menuChoiceCust = "continue";
    Scanner sc = new Scanner(System.in);

    protected String name, password, userId;
    String path = "mockpasswordlist.csv";

    public User(String name, String password, String userId) {
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

    public ArrayList<User> loadData() {
        ArrayList<User> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] f = line.split(",");
                list.add(new BasicUser(f[0], f[1], f[2]));
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public String login() {
        ArrayList<User> list = loadData();

        System.out.print("Username: ");
        String n = sc.next();

        System.out.print("Password: ");
        String p = sc.next();

        for (User u : list) {
            if (u.name.equals(n) && u.password.equals(p)) {
                System.out.println("Login successful");
                return u.userId;
            }
        }
        System.out.println("Login failed");
        return null;
    }

    public String register() {
        System.out.print("Username: ");
        String n = sc.next();

        System.out.print("Password: ");
        String p = sc.next();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.newLine();
            bw.write(n + "," + p + ",C001");
        } catch (Exception e) {
        }

        return login();
    }

    public abstract void showMenu();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        User user = new BasicUser("", "", "");

        System.out.println("1.Login\n2.Register");
        int ch = sc.nextInt();

        String id = (ch == 1) ? user.login() : user.register();

        if (id == null)
            return;

        user.userId = id;
        user.showMenu();
    }
}