import database.BackupRestore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class Backup {
    @Before
    public void addTestData(){
        TestData.addData();
    }
    @Test
    public void backup(){
        BackupRestore.backup();
        File backup_file=new File(BackupRestore.BACKUP_FILE_NAME);
        Assert.assertTrue(backup_file.exists());
        Assert.assertTrue(backup_file.length()>0);
    }
    @After
    public void removeTestData(){
        TestData.removeData();
    }
}
