package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

class Item{
    final static int ID=0;
    final static int NAME=1;
    final static int QUANTITY=2;

    public int id;
    public String name;
    public int quantity;

    public Item(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    public Item(String s){
        String[] values=s.split(",");
        id=Integer.parseInt(values[ID]);
        name=values[NAME];
        quantity=Integer.parseInt(values[QUANTITY]);

    }
    @Override
    public String toString() {
        return id+","+name+","+quantity+"\n";
    }

}
public class BackupRestore {
    public final static String BACKUP_FILE_NAME="Inventory_backup.csv";
    public static void backup(){
        Connection connection=Inventory.connect();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM inventory");
            Vector<Item> items=new Vector<>();
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                int quantity=resultSet.getInt(3);
                Item item=new Item(id,name,quantity);
                items.add(item);
            }
            Enumeration<Item> items_enum=items.elements();
            FileWriter fileWriter=new FileWriter(BACKUP_FILE_NAME);
            fileWriter.append("id,name,quantity\n");
            while (items_enum.hasMoreElements()){
                Item item=items_enum.nextElement();
                fileWriter.append(item.toString());
            }
            fileWriter.flush();
            fileWriter.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void restore(){
        File backup_file=new File(BACKUP_FILE_NAME);
        Vector<Item> items=new Vector<>();
        try {
            Scanner sc=new Scanner(backup_file);
            sc.nextLine();
            while (sc.hasNext()){
                Item item=new Item(sc.next());
                items.add(item);
            }
            Enumeration<Item> itemEnumeration=items.elements();
            Connection connection=Inventory.connect();
            while (itemEnumeration.hasMoreElements()){
                Item item=itemEnumeration.nextElement();
                PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM inventory WHERE id=?");
                preparedStatement.setInt(1,item.id);
                preparedStatement.execute();
                preparedStatement=connection.prepareStatement("INSERT INTO inventory values(?,?,?)");
                preparedStatement.setInt(1,item.id);
                preparedStatement.setString(2,item.name);
                preparedStatement.setInt(3,item.quantity);
                preparedStatement.execute();

            }
            connection.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
