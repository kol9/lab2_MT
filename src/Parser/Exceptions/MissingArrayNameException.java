package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class MissingArrayNameException extends ParseException {

    public MissingArrayNameException() {
        super("Missed array name in array declaration");
    }
}
