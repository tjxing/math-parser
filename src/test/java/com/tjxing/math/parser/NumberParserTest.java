package com.tjxing.math.parser;

import com.tjxing.math.def.IntNumber;
import com.tjxing.math.def.Number;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberParserTest {

    @Test
    public void parseInt() {
        StringStream input = new StringStream("1024");

        NumberParser parser = new NumberParser();
        Result<Number> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 1024);
    }

    @Test
    public void parseDouble() {
        StringStream input = new StringStream("3.14");

        NumberParser parser = new NumberParser();
        Result<Number> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().doubleValue(), 3.14, 0.00001);
    }


}