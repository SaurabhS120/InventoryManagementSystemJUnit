import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RenameItem {
    @Before
    public void addItem(){
        Inventory.addItem("test",50);
    }
    @Test
    public void RenameItem(){
        Inventory.renameItem("test","abc");
        Assert.assertTrue(Inventory.isExist("abc"));
    }
    @After
    public void removeItem(){
        Inventory.deleteItem("abc");
    }
}
