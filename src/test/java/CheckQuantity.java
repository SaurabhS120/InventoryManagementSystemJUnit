import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckQuantity {
    @BeforeClass
    public static void addItem(){
        Inventory.addItem("test",200);
    }
    @Test
    public void checkQuantity(){
        int quantity=200;
        Assert.assertEquals(quantity,Inventory.getQuantity("test"));
        Assert.assertNotEquals(500,Inventory.getQuantity("test"));
    }
    @AfterClass
    public static void deleteItem(){
        Inventory.deleteItem("test");
    }
}
