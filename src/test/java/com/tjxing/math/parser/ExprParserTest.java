package com.tjxing.math.parser;

import com.tjxing.math.def.DoubleNumber;
import com.tjxing.math.def.Expr;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExprParserTest {

    @Test
    public void parseDouble() {
        StringStream input = new StringStream(".888");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().doubleValue(), 0.888, 0.00001);
    }

    @Test
    public void parseInt() {
        StringStream input = new StringStream("1024");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 1024);
    }

    @Test
    public void parsePlusMinus() {
        StringStream input = new StringStream("1000 + 100 -200");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 900);
    }

    @Test
    public void parseMinusMinus() {
        StringStream input = new StringStream("1000 - 100 -200");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 700);
    }

    @Test
    public void parseTimesDiv() {
        StringStream input = new StringStream("10 * 20 / 4");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 50);
    }

    @Test
    public void parsePlusTimes() {
        StringStream input = new StringStream("10 + 3 * 5");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 25);
    }

    @Test
    public void parseBracket() {
        StringStream input = new StringStream("10 * (2 + 3)");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 50);
    }

    @Test
    public void parseNotEnd() {
        StringStream input = new StringStream("10 + 3 * 5 abc");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 25);
    }

    @Test
    public void parsePartial() {
        StringStream input = new StringStream("10 +");

        ExprParser parser = new ExprParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 10);
    }

}