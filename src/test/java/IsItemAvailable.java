import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsItemAvailable {
    @BeforeClass
    public static void addItem(){
        Inventory.addItem("test",500);
    }
    @Test
    public void isItemAvailable(){
        Assert.assertTrue(Inventory.isItemAvailable("test",5));
        Assert.assertFalse(Inventory.isItemAvailable("test",1000));
    }
    @AfterClass
    public static void deleteItem(){
        Inventory.deleteItem("test");
    }
}
