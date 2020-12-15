import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CreateConn.class,
        AddItem.class,
        DeleteItem.class,
        IsExists.class,
        IsItemAvailable.class,
        CheckQuantity.class,
        GainQuantity.class,
        ReduceQuantity.class,
        RenameItem.class,
        GetUnavailableItems.class
})

public class InventoryOperationsTestSuite {
}
