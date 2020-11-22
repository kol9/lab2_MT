package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class  MissingColonException extends ParseException{
    public MissingColonException() {
        super("Missed colon in array declaration");
    }
}
