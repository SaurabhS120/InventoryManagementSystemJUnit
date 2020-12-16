import database.Inventory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;

public class GetUnavailableItems {
    public boolean expected(String s){
        for(String i:TestData.UnavailableItems){
            if(i.equals(s)){
                return true;
            }
        }
        return false;
    }
    @Before
    public void addItems(){
        TestData.addData();
    }
    @Test
    public void gainQuantity(){
        Enumeration<String> enumeration= Inventory.getUnavailableItems().elements();
        while (enumeration.hasMoreElements()){
            String s=enumeration.nextElement();
            Assert.assertTrue(expected(s));

        }
    }
    @After
    public void removeItem(){
        TestData.removeData();
    }

}
