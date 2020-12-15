import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        Backup.class,
        Restore.class
})

public class BackupRestoreTestSuite {
}
