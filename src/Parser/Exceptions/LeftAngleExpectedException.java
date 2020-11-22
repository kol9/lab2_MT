package Parser.Exceptions;

/**
 * @author Nikolay Yarlychenko
 */
public class LeftAngleExpectedException extends ParseException {
    public LeftAngleExpectedException(int pos) {
        super("Left angle expected at pos=" + pos);
    }
}
