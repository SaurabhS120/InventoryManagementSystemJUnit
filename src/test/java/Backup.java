import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Backup {

    @Test
    public void backup(){
        BackupRestore.backup();
        File backup_file=new File(BackupRestore.BACKUP_FILE_NAME);
        Assert.assertTrue(backup_file.exists());
        Assert.assertTrue(backup_file.length()>0);
    }
}
