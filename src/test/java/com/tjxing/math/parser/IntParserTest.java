package com.tjxing.math.parser;

import com.tjxing.math.def.IntNumber;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntParserTest {

    @Test
    public void positiveInteger() {
        StringStream input = new StringStream("1024");

        IntParser parser = new IntParser();
        Result<IntNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 1024);
    }

    @Test
    public void negativeInteger() {
        StringStream input = new StringStream("-258");

        IntParser parser = new IntParser();
        Result<IntNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), -258);
    }

    @Test
    public void singleNumber() {
        StringStream input = new StringStream("1");

        IntParser parser = new IntParser();
        Result<IntNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 1);

        input = new StringStream("0");

        result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 0);
    }

    @Test
    public void notANumber() {
        StringStream input = new StringStream("abc");

        IntParser parser = new IntParser();
        Result<IntNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), false);
    }

    @Test
    public void numberWithIgnore() {
        StringStream input = new StringStream("\t \r 123\n \t");

        IntParser parser = new IntParser();
        Result<IntNumber> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 123);
    }

}