import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReduceQuantity {
    @BeforeClass
    public static void addItem(){
        Inventory.addItem("test",500);
    }
    @Test
    public void gainQuantity(){
        Inventory.reduceQuantity("test",200);
        Assert.assertEquals(300,Inventory.getQuantity("test"));
    }
    @AfterClass
    public static void removeItem(){
        Inventory.deleteItem("test");
    }
}
