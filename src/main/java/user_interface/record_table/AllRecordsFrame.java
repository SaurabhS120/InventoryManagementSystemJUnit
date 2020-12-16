package user_interface.record_table;

import database.Inventory;
import user_interface.basic_operations.ItemDetails;
import user_interface.search.SearchDetails;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Vector;

public class AllRecordsFrame extends JFrame {
    SearchDetails searchDetails;
    JTable table;
    JPanel panel;
    public AllRecordsFrame(SearchDetails searchDetails){
        super("All records");
        this.searchDetails=searchDetails;
        String[] col_name={"name","quantity"};
        Vector<ItemDetails> dataVector=Inventory.getAllItems();
        int size=dataVector.size();
        String data[][]=new String[size][2];
        Enumeration<ItemDetails> enumeration=dataVector.elements();
        ItemDetails itemDetails;
        for (int i=0;enumeration.hasMoreElements();i++){
            itemDetails=enumeration.nextElement();
            data[i][0]=itemDetails.name;
            data[i][1]=String.valueOf(itemDetails.quantity);

        }
        panel=new JPanel();
        table=new JTable(data,col_name);
        panel.add(new JScrollPane(table));
        add(panel);
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
