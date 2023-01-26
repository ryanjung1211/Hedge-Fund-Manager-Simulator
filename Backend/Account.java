package Backend;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Account {
    private double balance;
    private static double personalBalance;
    private static Account account;
    private HashMap<Stock, Integer> portfolio = new HashMap<Stock, Integer>();
    private int commission;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static HashMap<Stock, Double> purchasePrices = new HashMap<Stock, Double>();

    protected Account(int balance, int commission) {
        this.balance = balance;
        this.commission = commission;
    };

    public static Account makeAccount(int balance, int commission) throws AccountAlreadyExistsException {
        if (account == null) {
            account = new Account(balance, commission);
            return account;
        } else {
            throw new AccountAlreadyExistsException("Account Already Exists.");
        }
    }

    public int getCommission() {
        return this.commission;
    }

    public double getBalance() {
        return this.balance;
    }

    public void removeFromBalance(double amount) {
        this.balance = this.balance - amount;
    }

    public void addToBalance(double amount) {
        this.balance = this.balance + amount;
    }

    public double getValue() {
        double value = 0.0;
        for (Stock stock : portfolio.keySet()) {
            Integer quantity = portfolio.get(stock); 
            value += stock.getPrice() * quantity; 
        }
        return value;
    }

    public static void removeFromPersonalBalance(double amount) {
        personalBalance -= amount;
    }

    public static void addToPersonalBalance(double amount) {
        personalBalance += amount;
    }

    public static double getPersonalBalance() {
        return personalBalance;
    }

    public static void deleteAccount() {
        account = null;
    }

    public static Account getAccount() {
        return account;
    }

    public void Buy(Stock ticker, int amount) throws NotSufficientFundsException {
        if (ticker.getPrice() * amount > this.balance) {
            throw new NotSufficientFundsException("Not enough funds to make complete this transaction.");
        } else {
            this.removeFromBalance(amount * ticker.getPrice());
            if (portfolio.get(ticker) == null) {
                portfolio.put(ticker, amount);
            } else {
                portfolio.put(ticker, portfolio.get(ticker) + amount);
            }
            if (purchasePrices.get(ticker) == null)
            {
                purchasePrices.put(ticker, amount * ticker.getPrice());
            }
        }
    }

    public void Sell(Stock ticker, int amount) throws NotSufficientSharesException {
        if (portfolio.get(ticker) == null || portfolio.get(ticker) < amount) {
            throw new NotSufficientSharesException("Not enough shares to make complete this transaction.");
        } else {
            double profit = amount * ticker.getPrice();
            double commission_amount = Double.valueOf(this.commission) / 100 * profit;
            this.addToBalance(profit - commission_amount);
            addToPersonalBalance(commission_amount);
            portfolio.put(ticker, portfolio.get(ticker) - amount);
        }
    }

    public String[][] getPortfolioData() {
        String[][] data = new String[portfolio.size()][6];
        int counter = 0;
        for (Stock stock : portfolio.keySet()) {
            if (portfolio.get(stock) > 0) {
                data[counter][0] = stock.getTickerSymbol();
                data[counter][1] = "$" + Double.toString(stock.getPrice());
                data[counter][2] = Double.toString(StockMarket.getChanges().get(stock)) + "%";
                data[counter][3] = Integer.toString(portfolio.get(stock));
                data[counter][4] = "$"
                        + df.format((portfolio.get(stock) * stock.getPrice()) - purchasePrices.get(stock));
                data[counter][5] = "$" + df.format(stock.getPrice() * portfolio.get(stock));
                counter++;
            }
        }
        return data;
    }

}
