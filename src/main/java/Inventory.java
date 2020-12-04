import javax.xml.transform.Result;
import java.sql.*;
public class Inventory {
    public static void main(String[] args) {
        Inventory inventory=new Inventory();
        inventory.addItem("test",5);
        System.out.println("is exist test"+inventory.isExist("test"));
        System.out.println("is exist abc"+inventory.isExist("abc"));

    }
    Connection connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Inventory","root","");
            return con;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
    public void createTableIfNotExists(){
        Connection connection=connect();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS inventory(id INT AUTO_INCREMENT,name VARCHAR(20),quantity INT,PRIMARY KEY(id))");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public boolean addItem(String itemName,int quantity){
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
    public int deleteItem(String name){

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
    public boolean isExist(String name){

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
    public boolean isItemAvailable(String name,int quantity){
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
    public int getQuantity(String name){
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
    public void gainQuantity(String itemName,int gain_quantity){

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

    public void reduceQuantity(String itemName,int reduce_quantity){

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

}
