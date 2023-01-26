package Frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Backend.Account;
import Backend.NotSufficientFundsException;
import Backend.StockMarket;

import java.awt.GridBagLayout;
import java.awt.*;

public class BuyPage implements ActionListener{

    JFrame frame = new JFrame();
    JButton button;
    JComboBox<String> comboBox;
    JTextField textfield;

    public BuyPage()
    {
        frame.setSize(300, 200);
        frame.setTitle("Buy Page");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        String[] tickers = {"SELECT", "NEIL", "BAPS", "REC", "STOK", "SCHL", "GASS", "ALUM", "CNTE", "RES", "FUL"};
        comboBox = new JComboBox<String>(tickers);
        comboBox.addActionListener(this);

        textfield = new JTextField();
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setPreferredSize(new Dimension(75, 25));
        textfield.setText("Quantity");

        button = new JButton();
        button.setText("Buy");
        button.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(comboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(textfield, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(button, gbc);
        
        frame.add(mainPanel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button)
        {
            try {
                Account account = Account.getAccount();
                account.Buy(StockMarket.getLookup().get(comboBox.getSelectedItem()), Integer.parseInt(textfield.getText()));
                JOptionPane.showMessageDialog(null, "Purchase Successful! Porfolio will update after clicking \"Proceed to Next Week\" button.", null, JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
            } catch (NotSufficientFundsException except) {
                JOptionPane.showMessageDialog(null, except, null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
