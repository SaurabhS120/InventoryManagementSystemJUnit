import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GainQuantity {
    @BeforeClass
    public static void addItem(){
        new Inventory().addItem("test",500);
    }
    @Test
    public void gainQuantity(){
        Inventory inventory=new Inventory();
        inventory.gainQuantity("test",200);
        Assert.assertEquals(700,inventory.getQuantity("test"));
    }
    @AfterClass
    public static void removeItem(){
        new Inventory().deleteItem("test");
    }
}
