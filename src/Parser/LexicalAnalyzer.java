package Parser;

import Parser.Exceptions.ParseException;
import Parser.Exceptions.ParseIOException;
import Parser.Exceptions.UnrecognizableTokenException;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author Nikolay Yarlychenko
 */

public class LexicalAnalyzer {

    private static final int READ_LIMIT = 1_000_000_000;
    private final InputStream is;
    private int curPos;
    private int curChar;
    private Token curToken;
    private String curName;


    LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseIOException(e.getMessage(), curPos);
        }
    }

    private void skipWhitespaces() throws ParseException {
        while (Character.isWhitespace(curChar)) {
            nextChar();
        }
    }

    private boolean expectString(String expected) throws ParseException {
        int i = 0;
        while (i < expected.length() && !Character.isWhitespace(curChar)) {
            if (curChar != expected.charAt(i)) {
                return false;
            }
            nextChar();
            i++;
        }
        if (!isGoodForName(curChar)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isGoodForName(int c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private String readName() throws ParseException {
        StringBuilder sb = new StringBuilder();
        if (Character.isLetter(curChar) || curChar == '_') {
            sb.append((char) curChar);
        } else {
            return "";
        }
        nextChar();
        while (isGoodForName(curChar)) {
            sb.append((char) curChar);
            nextChar();
        }
        return sb.toString();
    }

    public void nextToken() throws ParseException, IOException {
        skipWhitespaces();
        switch (curChar) {
            case -1:
                curToken = Token.END;
                break;
            case ':':
                nextChar();
                curToken = Token.COLON;
                break;
            case ';':
                nextChar();
                curToken = Token.SEMICOLON;
                break;
            case '<':
                nextChar();
                curToken = Token.L_ANGLE;
                break;
            case '>':
                nextChar();
                curToken = Token.R_ANGLE;
                break;
            case ',':
                nextChar();
                curToken = Token.COMMA;
                break;
            case 'v':
                is.mark(READ_LIMIT);
                int tmp1 = curChar;
                if (expectString("var")) {
                    curToken = Token.VAR;
                } else {
                    is.reset();
                    curChar = tmp1;
                    String name = readName();
                    if (name.isEmpty()) {
                        throw new UnrecognizableTokenException("Unrecognizable token at ", curPos);
                    }
                    curToken = Token.NAME;
                    curName = name;
                }
                break;
            case 'A':
                is.mark(READ_LIMIT);
                int tmp = curChar;
                if (expectString("Array")) {
                    curToken = Token.ARRAY;
                    curName = "Array";
                } else {
                    is.reset();
                    curChar = tmp;
                    String name = readName();
                    if (name.isEmpty()) {
                        throw new UnrecognizableTokenException("Unrecognizable token at", curPos);
                    }
                    curToken = Token.NAME;
                    curName = name;
                }
                break;
            default:
                is.mark(READ_LIMIT);
                String name = readName();
                if (name.isEmpty()) {
                    throw new UnrecognizableTokenException("Unrecognizable token at", curPos);
                }
                curToken = Token.NAME;
                curName = name;
        }
    }

    public String getName() {
        return curName;
    }

    public Token curToken() {
        return curToken;
    }

    public int curPos() {
        return curPos;
    }

}
