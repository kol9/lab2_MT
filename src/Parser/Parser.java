package Parser;

import Parser.Exceptions.*;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author Nikolay Yarlychenko
 */

public class Parser {
    LexicalAnalyzer lex;

    public Tree parse(InputStream is) throws ParseException, IOException {
        lex = new LexicalAnalyzer(is);
        lex.nextToken();
        Tree res = S();
        if (lex.curToken() != Token.END) {
            throw new EndExpectedException();
        }
        return res;
    }


    Tree S() throws ParseException, IOException {
        if (lex.curToken() == Token.VAR) {
            lex.nextToken();
            Tree t = T();
            return new Tree("S", t);
        }
        throw new MissingVarWordException();
    }

    Tree T() throws ParseException, IOException {
        if (lex.curToken() == Token.NAME) {
            String name = lex.getName();
            lex.nextToken();
            if (lex.curToken() != Token.COLON) {
                throw new MissingColonException();
            }
            lex.nextToken();
            Tree at = AT();
            return new Tree("T", new Tree(name), new Tree(":"), at);
        }
        throw new MissingArrayNameException();
    }

    Tree AT() throws ParseException, IOException {
        if (lex.curToken() == Token.ARRAY) {
            lex.nextToken();
            Tree b0 = B0();
            lex.nextToken();
            Tree sc = SC();
            return new Tree("AT", new Tree("Array"), b0, sc);
        }
        throw new MissingArrayWordException();
    }


    Tree SC() throws IOException, ParseException {
        switch (lex.curToken()) {
            case SEMICOLON:
                lex.nextToken();
                return new Tree("SC", new Tree(";"));
            case END:
                return new Tree("SC_eps");
            default:
                throw new EndExpectedException();
        }
    }


    Tree B0() throws ParseException, IOException {
        if (lex.curToken() == Token.L_ANGLE) {
            lex.nextToken();
            if (lex.curToken() == Token.NAME || lex.curToken() == Token.ARRAY) {
                String name = lex.getName();
                lex.nextToken();
                Tree b = B();
                if (b.children != null) {
                    lex.nextToken();
                }

                if (lex.curToken() == Token.R_ANGLE) {
                    return new Tree("B0", new Tree("<"), new Tree(name), b, new Tree(">"));
                } else {
                    throw new RightAngleExpectedException(lex.curPos());
                }
            } else {
                throw new TypeNameExpectedException(lex.curPos());
            }
        }
        throw new LeftAngleExpectedException(lex.curPos());
    }


    Tree B() throws ParseException, IOException {
        switch (lex.curToken()) {
            case L_ANGLE:
                lex.nextToken();
                Tree i = I();
                if (lex.curToken() == Token.R_ANGLE) {
                    return new Tree("B", new Tree("<"), i, new Tree(">"));
                } else {
                    throw new RightAngleExpectedException(lex.curPos());
                }
            case R_ANGLE:
                return new Tree("B_eps");
            default:
                throw new UnexpectedTokenException(lex.curPos());
        }
    }


    Tree I() throws ParseException, IOException {
        switch (lex.curToken()) {
            case ARRAY:
            case NAME:
                String name = lex.getName();
                lex.nextToken();
                Tree b1 = B1();
                if (b1.children != null) {
                    lex.nextToken();
                }
                Tree i1 = I1();
                return new Tree("I", new Tree(name), b1, i1);
            default:
                throw new UnexpectedTokenException(lex.curPos());
        }
    }

    Tree B1() throws ParseException, IOException {
        switch (lex.curToken()) {
            case L_ANGLE:
                Tree b = B();
                return new Tree("B1", b);
            case R_ANGLE:
            case COMMA:
                return new Tree("B1_eps");
            default:
                throw new UnexpectedTokenException(lex.curPos());

        }
    }


    Tree I1() throws ParseException, IOException {
        switch (lex.curToken()) {
            case COMMA:
                lex.nextToken();
                Tree i = I();
                return new Tree("I1", new Tree(","), i);
            case R_ANGLE:
                return new Tree("I1_eps");
            default:
                throw new UnexpectedTokenException(lex.curPos());
        }
    }
}
