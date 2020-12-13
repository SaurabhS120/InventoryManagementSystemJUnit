import search.Search;
import search.SearchDetails;
import user_interface.ItemDetails;
import user_interface.ItemOperations;
import user_interface.UpdateMsg;

public class GuiMain {
    public static boolean start() {
        SearchDetails searchDetails=new SearchDetails();
        Search search=new Search(searchDetails);
        boolean found=false;
        synchronized (searchDetails) {
            while (!found) {
                try {
                    searchDetails.wait();
                    if (searchDetails.getState() == SearchDetails.CLOSED) break;
                    found = Inventory.isExist(searchDetails.getName());
                    if (found) searchDetails.setState(SearchDetails.FOUND);
                    searchDetails.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if(found){
                try {
                    searchDetails.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("GUI Main : "+searchDetails.getName()+"is found");
                String name=searchDetails.getName();
                showDetails(name);
                return true;

            }
        }
        return false;
    }
    public static void showDetails(String name){

        int quantity=Inventory.getQuantity(name);
        ItemDetails itemDetails=new ItemDetails(name,quantity);
        UpdateMsg updateMsg=new UpdateMsg();
        new ItemOperations(itemDetails,updateMsg);
        ItemRecord itemRecord=new ItemRecord(itemDetails);
        synchronized (itemDetails){
            while (itemDetails.getOperation()!=ItemDetails.BACK){
                try {
                    itemDetails.wait();
                    switch (itemDetails.getOperation()){
                        case ItemDetails.GAIN_QUANTITY:
                            itemRecord.gainQuantity(itemDetails.getTempQuantity());
                            synchronized (updateMsg){
                                updateMsg.q=Inventory.getQuantity(name);
                                updateMsg.notify();
                            }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        while (start());
    }
}
