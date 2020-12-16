package user_interface.basic_operations;

import user_interface.basic_operations.exceptions.BlankQuantityFieldException;
import user_interface.basic_operations.exceptions.NegativeValueException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GainQuantityGui extends JFrame {
    JFrame frame;
    ItemDetails itemDetails;
    JPanel panel;
    JLabel itemNameLabel;
    JLabel quantityLabel;
    JPanel buttonsPanel;
    JButton backButton;
    JButton gainQuantityButton;
    JTextField quantityTextField;
    public GainQuantityGui(ItemDetails itemDetails){
        super("Gain Quantity");
        frame=this;
        this.itemDetails=itemDetails;
        itemNameLabel=new JLabel(this.itemDetails.name);
        quantityLabel=new JLabel(String.valueOf(this.itemDetails.quantity));
        panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.PAGE_AXIS));
        Dimension buttonDimension=new Dimension(800,30);
        quantityTextField=new JTextField();
        quantityTextField.setMaximumSize(buttonDimension);
        backButton=new JButton("Beck");
        gainQuantityButton=new JButton("Gain quantity");
        gainQuantityButton.setMaximumSize(buttonDimension);
        backButton.setMaximumSize(buttonDimension);
        panel.add(new JLabel("Produce name : "));
        panel.add(itemNameLabel);
        panel.add(new JLabel("Quantity : "));
        panel.add(quantityLabel);
        buttonsPanel.add(new JLabel("Enter quantity to gain : "));
        buttonsPanel.add(quantityTextField);
        buttonsPanel.add(gainQuantityButton);
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
        gainQuantityButton.addActionListener(new AbstractAction() {
            String quantityText;
            int quantity;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    quantityText = quantityTextField.getText();
                    if (quantityText.length() == 0) throw new BlankQuantityFieldException();
                    quantity = Integer.parseInt(quantityText);
                    if (quantity < 0) throw new NegativeValueException();
                    synchronized (itemDetails) {
                        itemDetails.setTempQuantity(quantity);
                        itemDetails.setOperation(ItemDetails.GAIN_QUANTITY);
                    }
                    dispose();
                }
                catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(frame,"Please enter gain quantity in number","Invalid gain quantity",JOptionPane.ERROR_MESSAGE);
                }
                catch (BlankQuantityFieldException blankQuantityFieldException){
                    JOptionPane.showMessageDialog(frame,"Please enter quantity to gain","Blank gain quantity",JOptionPane.ERROR_MESSAGE);
                }
                catch (NegativeValueException negativeValueException){
                    JOptionPane.showMessageDialog(frame,"Please enter positive item quantity","Negative quantity",JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

}
