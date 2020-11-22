package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class UnexpectedTokenException extends ParseException {
    public UnexpectedTokenException(int pos) {
        super("Unexpected token at pos=" + pos);
    }
}
