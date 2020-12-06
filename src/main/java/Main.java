import java.util.Enumeration;
import java.util.Scanner;

public class Main {
    final static String operations =
            "1.Add Item\n" +
                    "2.Remove Item\n" +
                    "3.Check item exists in rec\n" +
                    "4.Check item quantity\n" +
                    "5.Gain item quantity\n" +
                    "6.Reduce item quantity\n" +
                    "7.Check item quantity available or not\n" +
                    "8.Rename item\n" +
                    "9.Get unavailable items\n" +
                    "10.Exit\n";

    final static int ADD_ITEM = 1;
    final static int REMOVE_ITEM = 2;
    final static int IF_EXISTS = 3;
    final static int CHECK_QUANTITY = 4;
    final static int GAIN_QUANTITY = 5;
    final static int REDUCE_QUANTITY = 6;
    final static int IS_ITEM_AVAILABLE = 7;
    final static int RENAME_ITEM = 8;
    final static int GET_UNAVAILABLE_ITEMS=9;
    final static int EXIT = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(operations);
        int op = sc.nextInt();
        while (op != EXIT) {
            String item_name;
            String newName;
            int quantity;
            switch (op) {
                case ADD_ITEM:
                    System.out.println("Adding item");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println("Enter quantity : ");
                    quantity = sc.nextInt();
                    System.out.println(Inventory.addItem(item_name, quantity) ? "Item added" : "Item not added");
                    break;
                case REMOVE_ITEM:
                    System.out.println("Removing item");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println(Inventory.deleteItem(item_name) + " items removed");
                    break;
                case IF_EXISTS:
                    System.out.println("Checking item exists or not");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println(Inventory.isExist(item_name) ? "Item exists" : "Item does not exists");
                    break;
                case CHECK_QUANTITY:
                    System.out.println("Checking item quantity");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println("Item available in " + Inventory.getQuantity(item_name) + " quantity");
                    break;
                case GAIN_QUANTITY:
                    System.out.println("Gaining item quantity");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println("Enter quantity : ");
                    quantity = sc.nextInt();
                    Inventory.gainQuantity(item_name, quantity);
                    System.out.println("Quantity gained");
                    break;
                case REDUCE_QUANTITY:
                    System.out.println("Reducing item quantity");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println("Enter quantity : ");
                    quantity = sc.nextInt();
                    Inventory.reduceQuantity(item_name, quantity);
                    System.out.println("Quantity reduced");
                    break;
                case IS_ITEM_AVAILABLE:
                    System.out.println("Checking item quantity available or not");
                    System.out.println("Enter item name : ");
                    item_name = sc.next();
                    System.out.println("Enter quantity : ");
                    quantity = sc.nextInt();
                    System.out.println(Inventory.isItemAvailable(item_name, quantity) ?
                            "Item is available in quantity " + quantity : "Item is not available in quantity " + quantity);
                    break;
                case RENAME_ITEM:
                    System.out.println("Renaming item");
                    System.out.println("Enter item name : ");
                    item_name=sc.next();
                    System.out.println("Enter item new name : ");
                    newName=sc.next();
                    Inventory.renameItem(item_name,newName);
                    break;
                case GET_UNAVAILABLE_ITEMS:
                    System.out.println("Getting unavailable items : ");
                    Enumeration enumeration=Inventory.getUnavailableItems();
                    System.out.println("Items");
                    while (enumeration.hasMoreElements()){
                        System.out.println(enumeration.nextElement());
                    }

            }
            System.out.println(operations);
            op = sc.nextInt();
        }
        System.out.println("Exiting...");
    }
}
