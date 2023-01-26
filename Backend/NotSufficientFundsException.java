package Backend;

public class NotSufficientFundsException extends Exception{
    public NotSufficientFundsException(String s)
    {
        super(s);
    }
}
