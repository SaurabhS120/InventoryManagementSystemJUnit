import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class AddItem {
    @Test
    public void addItem(){
        Assert.assertTrue(
                new Inventory().addItem("test",1));
    }
    @AfterClass
    public static void deleteItem(){
        new Inventory().deleteItem("test");
    }

}
