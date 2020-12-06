import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;

public class Inventory {
    public static void main(String[] args) {
        Inventory.addItem("test",5);
        System.out.println("is exist test"+Inventory.isExist("test"));
        System.out.println("is exist abc"+Inventory.isExist("abc"));

    }
    static Connection connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Inventory","root","");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
    public static boolean addItem(String itemName,int quantity){
        Connection con=connect();
        try {
            PreparedStatement preparedStatement=con.prepareStatement("Insert into inventory (name,quantity)values(?,?)");
            preparedStatement.setString(1,itemName);
            preparedStatement.setInt(2,quantity);
            boolean result= preparedStatement.executeUpdate()>0;
            con.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static int deleteItem(String name){

        Connection con=connect();
        try {
            PreparedStatement preparedStatement=con.prepareStatement("DELETE FROM inventory WHERE name=?");
            preparedStatement.setString(1,name);
            int result= preparedStatement.executeUpdate();
            con.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public static boolean isExist(String name){

        Connection con=connect();
        try {
            PreparedStatement preparedStatement=con.prepareStatement("select count(*) from inventory where name=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                boolean result= resultSet.getInt(1)>0;
                con.close();
                return result;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static boolean isItemAvailable(String name,int quantity){
        Connection con=connect();
        try {
            PreparedStatement preparedStatement=con.prepareStatement("select quantity from inventory where name=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                boolean result= resultSet.getInt(1)>quantity;
                con.close();
                return result;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static int getQuantity(String name){
        Connection connection=connect();
        PreparedStatement preparedStatement= null;
        int result=0;
        try {
            preparedStatement = connection.prepareStatement("select quantity from inventory where name=?");
            preparedStatement.setString(1,name);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                result= resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
    public static void gainQuantity(String itemName,int gain_quantity){

        Connection con=connect();
        int current_quantity=getQuantity(itemName);
        int new_quantity=current_quantity+gain_quantity;
        try {
            PreparedStatement preparedStatement=con.prepareStatement("UPDATE inventory SET quantity=? where name=?");
            preparedStatement.setInt(1,new_quantity);
            preparedStatement.setString(2,itemName);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void reduceQuantity(String itemName,int reduce_quantity){

        Connection con=connect();
        int current_quantity=getQuantity(itemName);
        int new_quantity=current_quantity-reduce_quantity;
        try {
            PreparedStatement preparedStatement=con.prepareStatement("UPDATE inventory SET quantity=? where name=?");
            preparedStatement.setInt(1,new_quantity);
            preparedStatement.setString(2,itemName);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void renameItem(String itemName,String NewName){

        Connection con=connect();
        try {
            PreparedStatement preparedStatement=con.prepareStatement("UPDATE inventory SET name=? where name=?");
            preparedStatement.setString(1,NewName);
            preparedStatement.setString(2,itemName);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Enumeration<String> getUnavailableItems(){
        Connection connection=connect();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT name from inventory WHERE quantity=0");
            Vector<String> vector=new Vector();
            while (resultSet.next()){
                vector.add(resultSet.getString(1));
            }
            Enumeration enumeration=vector.elements();
            return enumeration;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
