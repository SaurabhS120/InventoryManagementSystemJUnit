import search.Search;
import search.SearchDetails;

public class GuiMain {
    public static void main(String[] args) {
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
            }
        }

    }
}
