import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                CreateConn.class,
                AddItem.class,
                DeleteItem.class,
                IsExists.class,
                IsItemAvailable.class,
                CheckQuantity.class,
                GainQuantity.class,
                ReduceQuantity.class,
                RenameItem.class
        );
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Is test successful? : "+result.wasSuccessful());
    }
}
