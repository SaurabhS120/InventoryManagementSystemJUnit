import user_interface.ItemDetails;
public class ItemRecord extends ItemDetails{
    public ItemRecord(ItemDetails itemDetails) {
        super(itemDetails);
    }
    public void add(){
        Inventory.addItem(name,quantity);
    }
    public void remove(){
        Inventory.deleteItem(name);
    }
    public void updateQuantity(){
        quantity=Inventory.getQuantity(name);

    }
    public void gainQuantity(int q){
        Inventory.gainQuantity(name,q);
        quantity=quantity+q;
    }
    public void reduceQuantity(int q){
        Inventory.reduceQuantity(name,q);
        quantity=quantity-q;
    }
    public void rename(String n){
        Inventory.renameItem(name,n);
        name=n;
    }

}
