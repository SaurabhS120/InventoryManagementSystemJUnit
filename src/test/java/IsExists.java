import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsExists {

    @Test
    public void isExistRec() {
        Inventory inventory=new Inventory();
        inventory.addItem("test",5);
        new Inventory().isExist("test");
        inventory.deleteItem("test");
    }
}
