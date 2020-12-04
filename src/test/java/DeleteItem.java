import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteItem {
    @BeforeClass
    public static void addItem(){
        Inventory.addItem("test",5);
    }
    @Test
    public void deleteItem(){
        Assert.assertTrue(Inventory.deleteItem("test")>0);
    }
}
