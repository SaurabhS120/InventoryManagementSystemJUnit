import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;

public class GetUnavailableItems {
    String ItemNames[]={"abc","pqr","qnd","akv","pfa","pas"};
    int Quantities[]={12,0,56,0,98,0};
    String UnavailableItems[]={"pqr","akv","pas"};
    public boolean expected(String s){
        for(String i:UnavailableItems){
            if(i.equals(s)){
                return true;
            }
        }
        return false;
    }
    @Before
    public void addItems(){
        for (int i=0;i<ItemNames.length;i++){
            Inventory.addItem(ItemNames[i],Quantities[i]);
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
        for (String name:ItemNames){
            Inventory.deleteItem(name);
        }
    }

}
