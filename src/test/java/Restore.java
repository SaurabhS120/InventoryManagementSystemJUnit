import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restore {
    static void deleteAllData(){
        Connection connection=Inventory.connect();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate("DELETE FROM inventory");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static int numberOfItemsInData(){
        Connection connection=Inventory.connect();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT COUNT(*) from inventory");
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;

    }
    @Test
    public void restore(){
        deleteAllData();
        BackupRestore.restore();
        Assert.assertTrue(numberOfItemsInData()>0);
    }
}
