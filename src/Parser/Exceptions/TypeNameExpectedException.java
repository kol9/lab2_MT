package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class TypeNameExpectedException extends ParseException {
    public TypeNameExpectedException(int pos) {
        super("Type name expected at pos=" + pos);
    }
}
