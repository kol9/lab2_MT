package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class ParseIOException extends ParseException {
    public ParseIOException(String msg, int at) {
        super(msg + " at index: " + at);
    }
}
