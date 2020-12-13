package user_interface;

public class ItemDetails {
    public static final int NONE=0;
    public static final int GAIN_QUANTITY=1;
    public static final int REDUCE_QUANTITY=2;
    public static final int RENAME=3;
    public static final int REMOVE=4;
    public static final int BACK=5;
    int operation;
    public String name;
    public int quantity;
    String tempName;
    int tempQuantity;
    public ItemDetails(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ItemDetails(String name) {
        this.name = name;
    }
    public ItemDetails(ItemDetails itemDetails){
        name=itemDetails.name;
        quantity=itemDetails.quantity;
    }
    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public int getTempQuantity() {
        return tempQuantity;
    }

    public void setTempQuantity(int tempQuantity) {
        this.tempQuantity = tempQuantity;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
