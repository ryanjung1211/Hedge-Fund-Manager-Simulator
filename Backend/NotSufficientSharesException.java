package Backend;

public class NotSufficientSharesException extends Exception{
    public NotSufficientSharesException(String s)
    {
        super(s);
    }
}
