package user_interface.basic_operations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ItemOperations extends JFrame {
    ItemOperations frame;
    ItemDetails itemDetails;
    UpdateMsg updateMsg;
    JPanel panel;
    JLabel itemNameLabel;
    JLabel quantityLabel;
    JPanel buttonsPanel;
    JButton gainQuantityButton;
    JButton reduceQuantityButton;
    JButton renameButton;
    JButton removeButton;
    JButton backButton;

    public ItemOperations(ItemDetails itemDetails,UpdateMsg updateMsg){
        frame=this;
        this.itemDetails=itemDetails;
        this.updateMsg=updateMsg;
        itemNameLabel=new JLabel(this.itemDetails.name);
        quantityLabel=new JLabel(String.valueOf(this.itemDetails.quantity));
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.PAGE_AXIS));
        Dimension buttonDimension=new Dimension(800,30);
        gainQuantityButton=new JButton("Gain Quantity");
        reduceQuantityButton=new JButton("Reduce Quantity");
        renameButton=new JButton("Rename");
        removeButton=new JButton("Remove");
        backButton=new JButton("Beck");
        gainQuantityButton.setMaximumSize(buttonDimension);
        reduceQuantityButton.setMaximumSize(buttonDimension);
        renameButton.setMaximumSize(buttonDimension);
        removeButton.setMaximumSize(buttonDimension);
        backButton.setMaximumSize(buttonDimension);
        panel.add(new JLabel("Produce name : "));
        panel.add(itemNameLabel);
        panel.add(new JLabel("Quantity : "));
        panel.add(quantityLabel);
        buttonsPanel.add(gainQuantityButton);
        buttonsPanel.add(reduceQuantityButton);
        buttonsPanel.add(renameButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(backButton);
        panel.add(buttonsPanel);
        add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);

                synchronized (itemDetails){
                    itemDetails.setOperation(ItemDetails.BACK);
                    itemDetails.notify();
                    dispose();
                }
            }
        });
        gainQuantityButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GainQuantityGui(itemDetails);
                frame.setEnabled(false);
                new UpdateThread(updateMsg,frame).start();
            }
        });
        reduceQuantityButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReduceQuantityGui(itemDetails);
                frame.setEnabled(false);
                new UpdateThread(updateMsg,frame).start();
            }
        });
        renameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RenameItemGui(itemDetails);
                frame.setEnabled(false);
                new UpdateThread(updateMsg,frame).start();
            }
        });
        removeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(frame,"Do you want to remove this item?")==JOptionPane.OK_OPTION) {
                    synchronized (itemDetails) {
                        itemDetails.setOperation(ItemDetails.REMOVE);
                        itemDetails.notify();
                        dispose();
                    }
                }
            }
        });


    }

}
