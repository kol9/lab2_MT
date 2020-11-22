package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */



public class RightAngleExpectedException extends ParseException {
    public RightAngleExpectedException(int pos) {
        super("Right angle expected at pos=" + pos);
    }
}
