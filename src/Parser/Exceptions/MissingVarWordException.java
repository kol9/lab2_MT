package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class MissingVarWordException extends ParseException {

    public MissingVarWordException() {
        super("Missed var word at pos in array declaration");
    }
}
