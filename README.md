Cafe-Management System

Members : Anjana R , Gowri Menon , Meenakshi N M
Anjana worked on the Cart MenuItem User classes. Gowri worked on the Order ,OrderItem and Payment classes. Meenakshi worked on the InventoryItem and Sales report classes along with the UML diagram. 

Problem Description:
Many cafés still rely on manual processes to manage orders, menus, and payments. This often leads to issues such as incorrect billing, longer waiting times, poor order tracking, and difficulty in managing inventory. Staff must handle multiple tasks simultaneously, increasing the chances of human error and reducing overall service efficiency.

There is a need for a simple digital system that can automate these operations. The system should allow customers to view available menu items, place orders, and make payments efficiently. At the same time, it should help the café manage data such as orders and transactions in an organized way.

The proposed Café Management System addresses these problems by providing a structured and automated solution. It improves accuracy, reduces delays, and enhances the overall customer experience while simplifying management tasks.

After having all the files in the same folder you just need to run the User class as this is our main class.

Sample Input :

Enter choice: 1
Enter username: gowri
Enter password: 123

Enter option: 2
Enter number of items: 2

Choose item: 1
Enter quantity: 2

Choose item: 3
Enter quantity: 1

Enter option: 3
Enter payment method: UPI

Sample Output:

====== CAFE SYSTEM ======
1.Login
2.Register
Enter choice: 1

Username: gowri
Password: 123
Login successful

Welcome Customer!

1.View Menu
2.Place Order
3.Make Payment

Menu:
1. Espresso ₹90
2. Americano ₹110
3. Cappuccino ₹140

Items count: 1

1. Espresso ₹90
2. Americano ₹110
3. Cappuccino ₹140
Choose item: 1
Enter quantity: 2

Order:
M001 Espresso x2 = 180
Total: ₹180

Method: UPI
Payment done

Receipt:
M001 Espresso x2 = 180
Total: ₹180
Method: UPI

Tools and Technologies Used

Programming Language : Java

Concepts Used
- Object-Oriented Programming (OOP)
- Classes and Objects
- Inheritance
- Abstraction (abstract User class)
- Method Overriding

Libraries Used
- java.util → Scanner, ArrayList
- java.io → FileReader, BufferedReader, FileWriter

Data Storage
- CSV files:
- menu.csv → stores menu items
- mockpasswordlist.csv → stores user credentials

 Development Tools
- VS Code and Eclipse

