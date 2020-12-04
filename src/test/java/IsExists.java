import org.junit.Test;

public class IsExists {

    @Test
    public void isExistRec() {
        Inventory.addItem("test",5);
        Inventory.isExist("test");
        Inventory.deleteItem("test");
    }
}
