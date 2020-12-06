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
        for (int i=0;i<TestData.ItemNames.length;i++){
            Inventory.addItem(TestData.ItemNames[i],TestData.Quantities[i]);
        }
    }
    @Test
    public void gainQuantity(){
        Enumeration<String> enumeration=Inventory.getUnavailableItems();
        while (enumeration.hasMoreElements()){
            String s=enumeration.nextElement();
            Assert.assertTrue(expected(s));

        }
    }
    @After
    public void removeItem(){
        for (String name:TestData.ItemNames){
            Inventory.deleteItem(name);
        }
    }

}
