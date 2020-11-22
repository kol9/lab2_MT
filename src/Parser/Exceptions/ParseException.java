package Parser.Exceptions;

import Parser.Parser;

/**
 * @author Nikolay Yarlychenko
 */
public abstract class ParseException extends Exception {
    public ParseException() {
        super();
    }

    public ParseException(String msg) {
        super(msg);
    }
}
