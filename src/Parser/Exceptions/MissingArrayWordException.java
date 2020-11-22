package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class MissingArrayWordException extends ParseException{
    public MissingArrayWordException() {
        super("Missed Array word in array declaration");
    }
}
