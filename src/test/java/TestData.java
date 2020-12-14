import database.Inventory;

public class TestData {
    public static final String ItemNames[]={"abc","pqr","qnd","akv","pfa","pas"};
    public static final int Quantities[]={12,0,56,0,98,0};
    public static final String UnavailableItems[]={"pqr","akv","pas"};

    public static void addData(){
        for (int i=0;i<TestData.ItemNames.length;i++){
            Inventory.addItem(TestData.ItemNames[i],TestData.Quantities[i]);
        }
    }

    public static void removeData(){
        for (String name:TestData.ItemNames){
            Inventory.deleteItem(name);
        }
    }
}
