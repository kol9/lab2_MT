import Parser.Exceptions.ParseException;
import Parser.Parser;
import Parser.Tree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Nikolay Yarlychenko
 */
public class Main {

    public static void main(String[] args) throws IOException, ParseException, ParseException {

        String s = "var x: Array<Pair<Int, Int>>;";
        InputStream is = new ByteArrayInputStream(s.getBytes());


//        Parser.LexicalAnalyzer la = new Parser.LexicalAnalyzer(is);
//
//        while(la.curToken() != Parser.Token.END) {
//            System.out.println(la.curToken());
////            if(la.curToken() == Parser.Token.NAME) {
////                System.out.println("NAME=" + la.getName());
////            }
//            la.nextToken();
//        }

/*
VAR
NAME
NAME=x
COLON
ARRAY
L_ANGLE
ARRAY
L_ANGLE
NAME
NAME=Int
R_ANGLE
R_ANGLE
SEMICOLON
*/
        Parser parser = new Parser();
        Tree t = parser.parse(is);
        System.out.println(t.toGraph());
    }

}

