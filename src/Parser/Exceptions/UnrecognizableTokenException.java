package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class UnrecognizableTokenException extends ParseException{
    public UnrecognizableTokenException(String msg, int at) {
        super(msg + " at index: " + at);
    }
}
