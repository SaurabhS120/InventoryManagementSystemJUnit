import database.Inventory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GainQuantity {
    @BeforeClass
    public static void addItem(){
        Inventory.addItem("test",500);
    }
    @Test
    public void gainQuantity(){
        Inventory.gainQuantity("test",200);
        Assert.assertEquals(700,Inventory.getQuantity("test"));
    }
    @AfterClass
    public static void removeItem(){
        Inventory.deleteItem("test");
    }
}
