import search.Search;
import search.SearchDetails;
import user_interface.ItemDetails;
import user_interface.ItemOperations;

public class GuiMain {
    public static void start() {
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
                int quantity=Inventory.getQuantity(name);
                ItemDetails itemDetails=new ItemDetails(name,quantity);
                new ItemOperations(itemDetails);
                synchronized (itemDetails){
                    while (itemDetails.getOperation()!=ItemDetails.BACK){
                        try {
                            itemDetails.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (itemDetails.getOperation()==ItemDetails.BACK){
                        start();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
