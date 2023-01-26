package Backend;

import java.text.DecimalFormat;

public class Stock {
    private String tickerSymbol;
    private double price;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Stock(String tickerSymbol, int price) {
        this.tickerSymbol = tickerSymbol;
        this.price = price;
    }

    public final void setPrice(double price) {
        this.price = price;
    }
    
    public final void changePrice(double change) {
        this.price = Double.parseDouble(df.format(this.price * (1 + (change / 100))));
    }

    public double getPrice() {
        return this.price;
    }

    public String getTickerSymbol() {
        return this.tickerSymbol;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.tickerSymbol;
    }
}
