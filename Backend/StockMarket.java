package Backend;

import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StockMarket {
    private static StockMarket stockMarket;
    public List<Stock> tickers = new ArrayList<Stock>();
    private static int currentWeek = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    Random r = new Random();
    public Stock oneill = new Stock("NEIL", r.nextInt(91) + 10);
    public Stock bapst = new Stock("BAPS", r.nextInt(91) + 10);
    public Stock connell = new Stock("REC", r.nextInt(91) + 10);
    public Stock stokes = new Stock("STOK", r.nextInt(91) + 10);
    public Stock schiller = new Stock("SCHL", r.nextInt(91) + 10);
    public Stock gasson = new Stock("GASS", r.nextInt(91) + 10);
    public Stock alumni = new Stock("ALUM", r.nextInt(91) + 10);
    public Stock conte = new Stock("CNTE", r.nextInt(91) + 10);
    public Stock reservoir = new Stock("RES", r.nextInt(91) + 10);
    public Stock fulton = new Stock("FUL", r.nextInt(91) + 10);
    private static HashMap<Stock, Double> changes = new HashMap<Stock, Double>();
    private static HashMap<String, Stock> lookup = new HashMap<String, Stock>();
    
    protected StockMarket() {
        this.tickers.add(oneill);
        StockMarket.lookup.put("NEIL", oneill);
        this.tickers.add(bapst);
        StockMarket.lookup.put("BAPS", bapst);
        this.tickers.add(connell);
        StockMarket.lookup.put("REC", connell);
        this.tickers.add(stokes);
        StockMarket.lookup.put("STOK", stokes);
        this.tickers.add(schiller);
        StockMarket.lookup.put("SCHL", schiller);
        this.tickers.add(gasson);
        StockMarket.lookup.put("GASS", gasson);
        this.tickers.add(alumni);
        StockMarket.lookup.put("ALUM", alumni);
        this.tickers.add(conte);
        StockMarket.lookup.put("CNTE", conte);
        this.tickers.add(reservoir);
        StockMarket.lookup.put("RES", reservoir);
        this.tickers.add(fulton);
        StockMarket.lookup.put("FUL", fulton);
    };

    public static StockMarket makeStockMarket() throws StockMarketAlreadyExistsException
    {
        if (stockMarket == null)
        {
            stockMarket = new StockMarket();
            return stockMarket;
        }
        else{
            throw new StockMarketAlreadyExistsException("Stock Market Already Exists.");
        }
    }

    public String[][] getStockMarketData()
    {
        String[][] data = new String[10][3];
        Iterator<Stock> iter = this.tickers.iterator();
        int counter = 0;
		while (iter.hasNext())
		{
            Stock curr = iter.next();
            double change = this.getChange();
            curr.changePrice(change);
            changes.put(curr, change);
            data[counter][0] = curr.getTickerSymbol();
            data[counter][1] = "$" + df.format(curr.getPrice());
            data[counter][2] = Double.toString(change) + "%";
            counter++;
		}
        currentWeek++;
        return data;
    }
    public static HashMap<String, Stock> getLookup()
    {
        return lookup;
    }
    public static HashMap<Stock, Double> getChanges()
    {
        return changes;
    }

    public double getChange()
    {
        Random r = new Random();
        return Math.round((r.nextGaussian() * 5) * 100.0) / 100.0;
    }   

    public final void nextWeek(String[][] data) {
        Iterator<Stock> iter = tickers.iterator();
		while (iter.hasNext())
		{
			iter.next().changePrice(this.getChange());;
		}
    }

    public static int getCurrentWeek()
    {
        return currentWeek;
    }

    public static StockMarket getStockMarket()
    {
        return stockMarket;
    }
}
