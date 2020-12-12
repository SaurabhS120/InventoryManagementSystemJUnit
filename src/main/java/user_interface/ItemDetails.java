package user_interface;

public class ItemDetails {
    protected String name;
    protected int quantity;

    public ItemDetails(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ItemDetails(String name) {
        this.name = name;
    }
    public ItemDetails(ItemDetails itemDetails){
        name= itemDetails.name;
        quantity= itemDetails.quantity;
    }
}
