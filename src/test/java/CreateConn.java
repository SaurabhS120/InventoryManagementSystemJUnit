import database.Inventory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import com.mysql.jdbc.JDBC4Connection;
import java.sql.SQLException;

public class CreateConn {
    @Test
    public void connectDB(){
        Connection connection= Inventory.connect();
        try {
        Assert.assertEquals(connection.getClass(),JDBC4Connection.class);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
