package user_interface.basic_operations;

import user_interface.basic_operations.exceptions.BlankQuantityFieldException;
import user_interface.basic_operations.exceptions.NegativeValueException;
import user_interface.basic_operations.exceptions.QuantityNotAvailableException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReduceQuantityGui extends JFrame {
    JFrame frame;
    ItemDetails itemDetails;
    JPanel panel;
    JLabel itemNameLabel;
    JLabel quantityLabel;
    JPanel buttonsPanel;
    JButton backButton;
    JButton reduceQuantityButton;
    JTextField quantityTextField;
    public ReduceQuantityGui(ItemDetails itemDetails){
        super("Reduce Quantity");
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
        reduceQuantityButton=new JButton("Reduce quantity");
        reduceQuantityButton.setMaximumSize(buttonDimension);
        backButton.setMaximumSize(buttonDimension);
        panel.add(new JLabel("Produce name : "));
        panel.add(itemNameLabel);
        panel.add(new JLabel("Quantity : "));
        panel.add(quantityLabel);
        buttonsPanel.add(new JLabel("Enter quantity to reduce : "));
        buttonsPanel.add(quantityTextField);
        buttonsPanel.add(reduceQuantityButton);
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
        reduceQuantityButton.addActionListener(new AbstractAction() {
            String quantityText;
            int quantity;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    quantityText=quantityTextField.getText();
                    if(quantityText.length()==0)throw new BlankQuantityFieldException();
                    quantity=Integer.parseInt(quantityText);
                    if(quantity<0)throw new NegativeValueException();
                    if(quantity>itemDetails.quantity)throw new QuantityNotAvailableException();
                    synchronized (itemDetails) {
                        itemDetails.setTempQuantity(quantity);
                        itemDetails.setOperation(ItemDetails.REDUCE_QUANTITY);
                    }
                    dispose();
                }
                catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(frame,"Please enter reduce quantity in number","Invelid reduce quantity",JOptionPane.ERROR_MESSAGE);
                }
                catch (BlankQuantityFieldException blankQuantityFieldException){
                    JOptionPane.showMessageDialog(frame,"Please enter quantity to reduce","Blank reduce quantity",JOptionPane.ERROR_MESSAGE);
                }
                catch (NegativeValueException negativeValueException){
                    JOptionPane.showMessageDialog(frame,"Please enter positive item quantity","Negative quantity",JOptionPane.ERROR_MESSAGE);
                }
                catch (QuantityNotAvailableException quantityNotAvailableException){
                    JOptionPane.showMessageDialog(frame,"Reduce quantity is more than available quantity","Quantity not available",JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

}
