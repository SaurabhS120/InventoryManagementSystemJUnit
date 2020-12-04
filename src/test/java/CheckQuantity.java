import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckQuantity {
    @BeforeClass
    public static void addItem(){
        new Inventory().addItem("test",200);
    }
    @Test
    public void checkQuantity(){
        int quantity=200;
        Assert.assertEquals(quantity,new Inventory().getQuantity("test"));
        Assert.assertNotEquals(500,new Inventory().getQuantity("test"));
    }
    @AfterClass
    public static void deleteItem(){
        new Inventory().deleteItem("test");
    }
}
