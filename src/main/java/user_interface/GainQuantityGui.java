package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GainQuantityGui extends JFrame {

    ItemDetails itemDetails;
    Messege messege;
    JPanel panel;
    JLabel itemNameLabel;
    JLabel quantityLabel;
    JPanel buttonsPanel;
    JButton backButton;
    JButton gainQuantityButton;
    JTextField quantityTextField;
    public GainQuantityGui(ItemDetails itemDetails,Messege messege){
        this.messege=messege;
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
                synchronized (messege){
                    messege.notify();
                }
                dispose();
            }
        });
        gainQuantityButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (itemDetails){
                    itemDetails.setTempQuantity(Integer.parseInt(quantityTextField.getText()));
                    itemDetails.setOperation(ItemDetails.GAIN_QUANTITY);
                }
                dispose();
            }
        });


    }

}
