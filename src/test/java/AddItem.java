import database.Inventory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class AddItem {
    @Test
    public void addItem(){
        Assert.assertTrue(
                Inventory.addItem("test",1));
    }
    @AfterClass
    public static void deleteItem(){
        Inventory.deleteItem("test");
    }

}
