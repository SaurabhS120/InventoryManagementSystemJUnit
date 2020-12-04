import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReduceQuantity {
    @BeforeClass
    public static void addItem(){
        new Inventory().addItem("test",500);
    }
    @Test
    public void gainQuantity(){
        Inventory inventory=new Inventory();
        inventory.reduceQuantity("test",200);
        Assert.assertEquals(300,inventory.getQuantity("test"));
    }
    @AfterClass
    public static void removeItem(){
        new Inventory().deleteItem("test");
    }
}
