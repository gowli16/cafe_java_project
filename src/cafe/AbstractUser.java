package project;
import java.util.Scanner;

public abstract class AbstractUser {

    protected String userId;

    public AbstractUser(String userId) {
        this.userId = userId;
    }

    public abstract void displayMenu();
    public abstract void performOperation(int choice, Scanner sc);
}
