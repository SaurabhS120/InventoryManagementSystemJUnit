import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsItemAvailable {
    @BeforeClass
    public static void addItem(){
        new Inventory().addItem("test",500);
    }
    @Test
    public void isItemAvailable(){
        Assert.assertTrue(new Inventory().isItemAvailable("test",5));
        Assert.assertFalse(new Inventory().isItemAvailable("test",1000));
    }
    @AfterClass
    public static void deleteItem(){
        new Inventory().deleteItem("test");
    }
}
