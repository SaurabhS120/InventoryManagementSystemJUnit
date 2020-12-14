package user_interface.basic_operations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddItemGui extends JFrame {
    JFrame frame;
    ItemDetails itemDetails;
    JPanel panel;
    JPanel buttonsPanel;
    JButton backButton;
    JButton addButton;
    JTextField nameTextField;
    JTextField quantityTextField;
    public AddItemGui(ItemDetails itemDetails){
        super("Add item");
        this.itemDetails=itemDetails;
        frame=this;
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.PAGE_AXIS));
        Dimension buttonDimension=new Dimension(800,30);
        nameTextField=new JTextField();
        nameTextField.setMaximumSize(buttonDimension);
        quantityTextField=new JTextField();
        quantityTextField.setMaximumSize(buttonDimension);
        backButton=new JButton("Beck");
        addButton=new JButton("Add item");
        addButton.setMaximumSize(buttonDimension);
        backButton.setMaximumSize(buttonDimension);
        buttonsPanel.add(new JLabel("Enter name : "));
        buttonsPanel.add(nameTextField);
        buttonsPanel.add(new JLabel("Enter quantity : "));
        buttonsPanel.add(quantityTextField);
        buttonsPanel.add(addButton);
        buttonsPanel.add(backButton);
        panel.add(buttonsPanel);
        add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setSize(400,300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        itemDetails.setOperation(ItemDetails.NONE);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemDetails.setOperation(ItemDetails.NONE);
                dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                synchronized (itemDetails){

                    itemDetails.notify();
                }
            }
        });
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(frame,"Want to add this item?","Confirm add",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION) {

                    synchronized (itemDetails) {
                        itemDetails.name = nameTextField.getText();
                        itemDetails.quantity = Integer.parseInt(quantityTextField.getText());
                        itemDetails.setOperation(ItemDetails.ADD);

                    }
                    dispose();
                }
            }
        });


    }

}
