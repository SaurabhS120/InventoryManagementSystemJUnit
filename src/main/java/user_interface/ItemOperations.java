package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ItemOperations extends JFrame {
    ItemDetails itemDetails;
    JPanel panel;
    JLabel itemNameLabel;
    JLabel quantityLabel;
    JPanel buttonsPanel;
    JButton gainQuantityButton;
    JButton reduceQuantityButton;
    JButton renameButton;
    JButton removeButton;
    JButton backButton;
    public ItemOperations(ItemDetails itemDetails){
        this.itemDetails=itemDetails;
        itemNameLabel=new JLabel(itemDetails.name);
        quantityLabel=new JLabel(String.valueOf(itemDetails.quantity));
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
                synchronized (itemDetails){
                    itemDetails.setOperation(ItemDetails.BACK);
                    itemDetails.notify();
                    dispose();
                }
            }
        });

    }

}
