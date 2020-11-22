import Parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Nikolay Yarlychenko
 */
public class Tester {
    private final Parser parser;

    public Tester() {
        this.parser = new Parser();
    }


    void correctArrayTest(String arr) {
        InputStream is = new ByteArrayInputStream(arr.getBytes());
        try {
            parser.parse(is);
        } catch (Exception e) {
            Assertions.fail();
        }
    }


    void wrongArrayTest(String arr) {
        InputStream is = new ByteArrayInputStream(arr.getBytes());
        try {
            parser.parse(is);
            Assertions.fail();
        } catch (Exception e) {
            System.out.println("\t" + e.getMessage());
        }
    }

    @Test
    void correctArraySingleTypeTests() {
        correctArrayTest("var x: Array<Int>");
        correctArrayTest("var x: Array<Int>;");
        correctArrayTest("var       Abc: Array     <     Int    >     ");
        correctArrayTest("var Abc: Array<Int>;");
        correctArrayTest("var y    : Array<double>;");
        correctArrayTest("var y: Array      <        double>");
        correctArrayTest("var vaa: Array<     double>        ;");
    }


    @Test
    void correctArrayMultipleTypeTests() {
        correctArrayTest("var x: Array<Pair<Int, Int>>;");
        correctArrayTest("var x: Array<Set<Set<Int>>>;");
        correctArrayTest("var x: Array<Set<Set<Int>>>;");
        correctArrayTest("var x: Array<Array<Array<Int>>>;");
        correctArrayTest("var x: Array<Array<Array<Set<      Pair<Map<String   , Array<Int>>, Int>>>>>;");
        correctArrayTest("var x: Array<Pair<Pair<Int, Set<Double>>, Pair<Map<String, Array<Int>>, Int>>>;");
        correctArrayTest("var x: Array<VeryLongType<Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>>>");
    }


    @Test
    void missingVarStringTests() {
        System.out.println("Missed \"var\" word:");
        wrongArrayTest(" x : Array<Int>");
        wrongArrayTest("   x: Array<Set<Set<Int>>>;");
        wrongArrayTest("   Abc: Array<Int>;");
        wrongArrayTest("   vvar    : Array<double>;");
        wrongArrayTest("varr   Abc: Array<Int>;");
    }

    @Test
    void missingArrayNameTests() {
        System.out.println("Missed array name:");
        wrongArrayTest("var  : Array<Int>");
        wrongArrayTest("var : Array<Int>");
        wrongArrayTest(" var: Array<Set<Set<Int>>>;");
        wrongArrayTest("   var    : Array<Int>;");
        wrongArrayTest("   var    : Array<double>;");
    }


    @Test
    void missingColonTests() {
        System.out.println("Missed colon:");
        wrongArrayTest("var  x Array<Int>");
        wrongArrayTest("var x Array<Int>");
        wrongArrayTest(" var x Array<Set<Set<Int>>>;");
        wrongArrayTest("   var   x    Array<Int>;");
        wrongArrayTest("   var  x     Array<double>;");
    }


    @Test
    void missingArrayWordTests() {
        System.out.println("Missed \"Array\" word:");
        wrongArrayTest("var  x: <Int>");
        wrongArrayTest("var x : Arrayy<Int>");
        wrongArrayTest(" var x :  notArray<Set<Set<Int>>>;");
        wrongArrayTest("   var   x:    Smth<Int>;");
        wrongArrayTest("   var  x  :   Set<double>;");
    }


    @Test
    void extraSymbolsAfterArrayDeclarationTests() {
        System.out.println("Extra symbols after array declaration:");
        wrongArrayTest("var  x: Array<Int>;;");
        wrongArrayTest("var  x: Array<Int>;var  x: Array<Int>;");
        wrongArrayTest("var  x: Array<Int> var  x: Array<Int>;");
    }


    @Test
    void someMissingTokensTests() {
        System.out.println("Missed tokens:");
        wrongArrayTest("var x: Array<Pair<,>>;");
        wrongArrayTest("var x: Array<Pair Int, Int>>;");
        wrongArrayTest("var x: Array<Set<Set<>>>;");
        wrongArrayTest("var x: Array<Set<<Int>>>;");
        wrongArrayTest("var x: Array<Array<Array<>>>;");
        wrongArrayTest("var x: Array<Array<Array<Set<      Pair<Map<String    Array<Int>>, Int>>>>>;");
        wrongArrayTest("var x: Array<Pair<Pair<Int Set<Double>>, Pair<Map<String, Array<Int>>, Int>>>;");
        wrongArrayTest("var x: Array<VeryLongType< <Int,Int> Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>,Pair<Int,Int>>>");
    }


}
