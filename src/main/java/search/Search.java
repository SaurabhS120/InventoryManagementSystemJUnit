package search;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Search extends JFrame {
    SearchDetails searchDetails;
    JFrame frame;
    JPanel panel;
    JTextField nameTextField;
    JButton searchButton;
    public Search(SearchDetails searchDetails){
        super("Search record");
        frame=this;
        setSize(400,300);
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        add(panel);
        panel.add(new JLabel("Product name"));
        nameTextField=new JTextField();
        nameTextField.setMaximumSize(new Dimension(800,30));
        panel.add(nameTextField);
        searchButton=new JButton("Search");
        searchButton.setMaximumSize(new Dimension(350,30));
        panel.add(searchButton);
        searchDetails.setState(SearchDetails.SEARCHING);
        searchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (searchDetails){
                    String name=nameTextField.getText();
                    searchDetails.setName(name);
                    try {
                        searchDetails.notify();
                        searchDetails.wait();
                        if(searchDetails.getState()==SearchDetails.FOUND){
                            JOptionPane.showMessageDialog(frame,"Record "+searchDetails.getName()+" is found","Record found",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }else {
                            JOptionPane.showMessageDialog(frame,"Record "+searchDetails.getName()+" not found","Not found",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                synchronized (searchDetails) {
                    if(searchDetails.getState()!=SearchDetails.FOUND)searchDetails.setState(SearchDetails.CLOSED);
                    searchDetails.notify();
                }
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}