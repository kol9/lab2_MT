package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class EndExpectedException extends ParseException {

    public EndExpectedException() {
        super("Extra characters were encountered at the end of the array declaration");
    }
}
