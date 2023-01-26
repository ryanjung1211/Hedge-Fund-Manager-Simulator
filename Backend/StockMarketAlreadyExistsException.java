package Backend;

public class StockMarketAlreadyExistsException extends Exception{
    public StockMarketAlreadyExistsException(String s)
    {
        super(s);
    }
}