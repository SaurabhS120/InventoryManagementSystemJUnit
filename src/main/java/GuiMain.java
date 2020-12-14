import user_interface.basic_operations.AddItemGui;
import database.Inventory;
import database.ItemRecord;
import user_interface.search.MainSearch;
import user_interface.search.SearchDetails;
import user_interface.basic_operations.ItemDetails;
import user_interface.basic_operations.ItemOperations;
import user_interface.basic_operations.UpdateMsg;
import database.*;

import javax.swing.*;

public class GuiMain {
    public static boolean start() {
        SearchDetails searchDetails=new SearchDetails();
        MainSearch search=new MainSearch(searchDetails);
        searchDetails.setState(SearchDetails.SEARCHING);
        boolean found=false;
        synchronized (searchDetails) {
            while (!found) {
                try {
                    searchDetails.wait();
                    if(searchDetails.getState()==SearchDetails.ADD){
                        search.setEnabled(false);
                        ItemDetails itemDetails=new ItemDetails();
                        new AddItemGui(itemDetails);
                        synchronized (itemDetails){
                            itemDetails.wait();
                            if(itemDetails.getOperation()==ItemDetails.ADD){
                                new ItemRecord(itemDetails).add();
                            }
                            searchDetails.setState(SearchDetails.SEARCHING);
                        }
                        search.setEnabled(true);
                        search.setVisible(true);
                    }
                    if(searchDetails.getState()==SearchDetails.BACKUP){
                        BackupRestore.backup();
                        JOptionPane.showMessageDialog(search,"Backup Successful","Backup",JOptionPane.INFORMATION_MESSAGE);
                        searchDetails.setState(SearchDetails.SEARCHING);
                    }
                    if(searchDetails.getState()==SearchDetails.RESTORE){
                        try {
                            BackupRestore.restore();
                            JOptionPane.showMessageDialog(search, "Restore Successful", "Restore", JOptionPane.INFORMATION_MESSAGE);
                            searchDetails.setState(SearchDetails.SEARCHING);
                        }catch (Exception exception){
                            exception.printStackTrace();
                        }
                    }
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
                        case ItemDetails.NONE:
                            synchronized (updateMsg){
                                updateMsg.notify();
                            }
                            break;
                        case ItemDetails.GAIN_QUANTITY:
                            itemRecord.gainQuantity(itemDetails.getTempQuantity());
                            synchronized (updateMsg){
                                updateMsg.quantity=Inventory.getQuantity(name);
                                updateMsg.isQuantityUpdated=true;
                                updateMsg.notify();
                            }
                            break;
                        case ItemDetails.REDUCE_QUANTITY:
                            itemRecord.reduceQuantity(itemDetails.getTempQuantity());
                            synchronized (updateMsg){
                                updateMsg.quantity=Inventory.getQuantity(name);
                                updateMsg.isQuantityUpdated=true;
                                updateMsg.notify();
                            }
                            break;
                            case ItemDetails.RENAME:
                                itemRecord.rename(itemDetails.getTempName());
                                name=itemRecord.name;
                                synchronized (updateMsg){
                                    updateMsg.name=name;
                                    updateMsg.isNameUpdated=true;
                                    updateMsg.notify();
                                }
                                break;
                        case ItemDetails.REMOVE:
                            itemRecord.remove();
                            return;

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
