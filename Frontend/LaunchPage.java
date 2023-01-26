package Frontend;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.border.TitledBorder;
import Backend.StockMarket;
import Backend.StockMarketAlreadyExistsException;
import Backend.Account;
import Backend.AccountAlreadyExistsException;


public class LaunchPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton buyButton;
    JButton sellButton;
    JButton nextWeekButton;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public LaunchPage() {
        frame.setSize(600, 450);
        frame.setLocation(1920, 0);
        frame.setTitle("Hedge Fund Manager Simulator");
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panel1 = new JPanel();
        panel1.setBorder(new TitledBorder("Stock Market"));
        panel1.setLayout(new BorderLayout());

        StockMarket market;

        try {
            market = StockMarket.makeStockMarket();
        } catch (StockMarketAlreadyExistsException e) {
            market = StockMarket.getStockMarket();
        }

        String data[][] = market.getStockMarketData();
        String column[] = {"TICKER", "PRICE", "CHANGE"};
        JTable stockMarket = new JTable(data, column);
        panel1.add(stockMarket, BorderLayout.CENTER);
        panel1.add(stockMarket.getTableHeader(), BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(panel1, gbc);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());

        JPanel panel2a = new JPanel();
        panel2a.setBorder(new TitledBorder("Account Information"));
        panel2a.setLayout(new BorderLayout());

        Account account;
        try {
            account = Account.makeAccount(10000,3);
        } catch (AccountAlreadyExistsException e) {
            account = Account.getAccount();
        }
       
        ArrayList<String> ar = new ArrayList<String>();
        ar.add("Week: " + StockMarket.getCurrentWeek());
        ar.add("Firm Portfolio Value: $" + df.format(account.getValue()));
        ar.add("Firm Funds: $" + df.format(account.getBalance()));
        ar.add("Personal Balance: $" + df.format(Account.getPersonalBalance()));
        ar.add("Commission: " + account.getCommission() + "%");
        Object[] info = ar.toArray();
        JList<Object> list = new JList<>(info);

        panel2a.add(list, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        
        panel2.add(panel2a, gbc);

        buyButton = new JButton();
        buyButton.setText("Buy Stocks");
        buyButton.addActionListener(this);
        buyButton.setVisible(true);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        panel2.add(buyButton, gbc);

        sellButton = new JButton();
        sellButton.setText("Sell Stocks");
        sellButton.addActionListener(this);
        sellButton.setVisible(true);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        panel2.add(sellButton, gbc);

        nextWeekButton = new JButton();
        nextWeekButton.setText("Proceed to Next Week");
        nextWeekButton.addActionListener(this);
        nextWeekButton.setVisible(true);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;

        panel2.add(nextWeekButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mainPanel.add(panel2, gbc);

        frame.add(mainPanel);
        
        JPanel panel3 = new JPanel();
        panel3.setBorder(new TitledBorder("Firm Portfolio"));
        panel3.setLayout(new BorderLayout());

        String portfolioData[][] = account.getPortfolioData();
        String portfolioColumn[] = {"TICKER", "PRICE", "CHANGE", "QUANTITY", "GAIN/LOSS", "VALUE"};
        JTable portfolio = new JTable(portfolioData, portfolioColumn);
        panel3.add(portfolio, BorderLayout.CENTER);
        panel3.add(portfolio.getTableHeader(), BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        mainPanel.add(panel3, gbc);
        
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyButton)
        {
            new BuyPage();
        }
        else if (e.getSource() == sellButton)
        {
            new SellPage();
        }
        else if (e.getSource() == nextWeekButton)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}