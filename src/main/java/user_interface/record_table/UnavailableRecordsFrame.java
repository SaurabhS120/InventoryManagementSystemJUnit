package user_interface.record_table;

import database.Inventory;
import user_interface.search.SearchDetails;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Vector;

public class UnavailableRecordsFrame extends JFrame {
    SearchDetails searchDetails;
    JTable table;
    public UnavailableRecordsFrame(SearchDetails searchDetails){
        super("Unavailable records");
        this.searchDetails=searchDetails;
        String[] col_name={"name"};
        Vector<String> dataVector=Inventory.getUnavailableItems();
        int size=dataVector.size();
        String data[][]=new String[size][1];
        Enumeration<String> enumeration=dataVector.elements();
        for (int i=0;enumeration.hasMoreElements();i++){
            data[i][0]=enumeration.nextElement();
        }
        table=new JTable(data,col_name);
        add(table);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                synchronized (searchDetails) {
                    searchDetails.setState(SearchDetails.SEARCHING);
                    searchDetails.notify();
                }
            }
        });
        setSize(300,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
