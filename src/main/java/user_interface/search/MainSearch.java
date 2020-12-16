package user_interface.search;


import database.BackupRestore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MainSearch extends JFrame {
    SearchDetails searchDetails;
    JFrame frame;
    JPanel panel;
    JTextField nameTextField;
    JButton searchButton;
    JButton addButton;
    JButton showAllRecordsButton;
    JButton showUnavailableRecordsButton;
    JButton backupButton;
    JButton restoreButton;
    public MainSearch(SearchDetails searchDetails){
        super("Search record");
        frame=this;
        setSize(400,300);
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(panel);
        panel.add(new JLabel("Product name"));
        nameTextField=new JTextField();
        nameTextField.setMaximumSize(new Dimension(800,30));
        panel.add(nameTextField);
        searchButton=new JButton("Search");
        searchButton.setMaximumSize(new Dimension(350,30));
        panel.add(searchButton);
        addButton=new JButton("Add");
        addButton.setMaximumSize(new Dimension(350,30));
        panel.add(addButton);
        showAllRecordsButton=new JButton("Show all records");
        showAllRecordsButton.setMaximumSize(new Dimension(350,30));
        panel.add(showAllRecordsButton);
        showUnavailableRecordsButton=new JButton("Show unavailable records");
        showUnavailableRecordsButton.setMaximumSize(new Dimension(350,30));
        panel.add(showUnavailableRecordsButton);
        backupButton=new JButton("Backup");
        backupButton.setMaximumSize(new Dimension(350,30));
        panel.add(backupButton);
        restoreButton=new JButton("Restore");
        restoreButton.setMaximumSize(new Dimension(350,30));
        panel.add(restoreButton);
        searchDetails.setState(SearchDetails.SEARCHING);
        searchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (searchDetails){
                    String name=nameTextField.getText();
                    if(name.length()>0) {
                        searchDetails.setName(name);
                        try {
                            searchDetails.notify();
                            searchDetails.wait();
                            if (searchDetails.getState() == SearchDetails.FOUND) {
                                JOptionPane.showMessageDialog(frame, "Record " + searchDetails.getName() + " is found", "Record found", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Record " + searchDetails.getName() + " not found", "Not found", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(frame,"Please enter name before searching items","Name not enterd",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                synchronized (searchDetails) {
                    if(searchDetails.getState()==SearchDetails.SEARCHING)
                        searchDetails.setState(SearchDetails.CLOSED);
                    searchDetails.notify();
                }
            }
        });
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (searchDetails){
                    searchDetails.setState(SearchDetails.ADD);
                    searchDetails.notify();
                }
            }
        });
        backupButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (searchDetails){
                    searchDetails.setState(SearchDetails.BACKUP);
                    searchDetails.notify();
                }
            }
        });
        restoreButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f=new File(BackupRestore.BACKUP_FILE_NAME);
                if(f.exists()) {
                    synchronized (searchDetails) {
                        searchDetails.setState(SearchDetails.RESTORE);
                        searchDetails.notify();
                    }
                }else {
                    JOptionPane.showMessageDialog(frame,"Backup file not found,\nPlease create backup before restore","Backup file not found",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        showAllRecordsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (searchDetails){
                    searchDetails.setState(SearchDetails.SHOW_ALL_RECORDS);
                    searchDetails.notify();
                }
            }
        });
        showUnavailableRecordsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (searchDetails){
                    searchDetails.setState(SearchDetails.SHOW_UNAVAILABLE_RECORDS);
                    searchDetails.notify();
                }
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
