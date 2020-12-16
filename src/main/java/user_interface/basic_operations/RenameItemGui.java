package user_interface.basic_operations;

import user_interface.basic_operations.exceptions.BlankNameFieldException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RenameItemGui extends JFrame {
    JFrame frame;
    ItemDetails itemDetails;
    JPanel panel;
    JLabel itemNameLabel;
    JLabel quantityLabel;
    JPanel buttonsPanel;
    JButton backButton;
    JButton renameButton;
    JTextField nameTextField;
    public RenameItemGui(ItemDetails itemDetails){
        super("Rename item");
        this.itemDetails=itemDetails;
        frame=this;
        itemNameLabel=new JLabel(this.itemDetails.name);
        quantityLabel=new JLabel(String.valueOf(this.itemDetails.quantity));
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.PAGE_AXIS));
        Dimension buttonDimension=new Dimension(800,30);
        nameTextField=new JTextField();
        nameTextField.setMaximumSize(buttonDimension);
        backButton=new JButton("Beck");
        renameButton=new JButton("Rename item");
        renameButton.setMaximumSize(buttonDimension);
        backButton.setMaximumSize(buttonDimension);
        panel.add(new JLabel("Produce name : "));
        panel.add(itemNameLabel);
        panel.add(new JLabel("Quantity : "));
        panel.add(quantityLabel);
        buttonsPanel.add(new JLabel("Enter new name : "));
        buttonsPanel.add(nameTextField);
        buttonsPanel.add(renameButton);
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
        renameButton.addActionListener(new AbstractAction() {
            String name;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name = nameTextField.getText();
                    if (name.length() == 0) throw new BlankNameFieldException();

                    synchronized (itemDetails) {
                        if (JOptionPane.showConfirmDialog(frame, "Do you want to rename this item?", "Rename Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                            itemDetails.setTempName(name);
                            itemDetails.setOperation(ItemDetails.RENAME);
                            dispose();
                        }
                    }
                }catch (BlankNameFieldException blankNameFieldException){
                    JOptionPane.showMessageDialog(frame,"Please enter item name to rename","Blank item name",JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

}
