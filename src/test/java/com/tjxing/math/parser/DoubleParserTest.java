package com.tjxing.math.parser;

import com.tjxing.math.def.DoubleNumber;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleParserTest {

    @Test
    public void positiveDouble() {
        StringStream input = new StringStream("12.345");

        DoubleParser parser = new DoubleParser();
        Result<DoubleNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().doubleValue(), 12.345, 0.00001);
    }

    @Test
    public void negativeDouble() {
        StringStream input = new StringStream("-0.123");

        DoubleParser parser = new DoubleParser();
        Result<DoubleNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().doubleValue(), -0.123, 0.00001);
    }

    @Test
    public void doubleNoInt() {
        StringStream input = new StringStream(".888");

        DoubleParser parser = new DoubleParser();
        Result<DoubleNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().doubleValue(), 0.888, 0.00001);
    }

    @Test
    public void notADouble() {
        StringStream input = new StringStream("abc");
        DoubleParser parser = new DoubleParser();
        Result<DoubleNumber> result = parser.parse(input);
        Assertions.assertEquals(result.isOK(), false);

        input = new StringStream("123");
        parser = new DoubleParser();
        Assertions.assertEquals(result.isOK(), false);
    }

    @Test
    public void doubleWithIgnore() {
        StringStream input = new StringStream(" \t\r12.345  \r\n ");

        DoubleParser parser = new DoubleParser();
        Result<DoubleNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().doubleValue(), 12.345, 0.00001);
    }

}