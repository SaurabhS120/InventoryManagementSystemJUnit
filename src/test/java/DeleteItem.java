import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteItem {
    @BeforeClass
    public static void addItem(){
        new Inventory().addItem("test",5);
    }
    @Test
    public void deleteItem(){
        Assert.assertTrue(new Inventory().deleteItem("test")>0);
    }
}
