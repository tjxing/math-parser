package com.tjxing.math;

import com.tjxing.math.def.Expr;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ExpressionParserTest {

    @Test
    public void parseExpression() {
        StringStream input = new StringStream("10 + 3 * 5 ");

        ExpressionParser parser = new ExpressionParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), true);
        Assertions.assertEquals(result.getOrNull().caculate().intValue(), 25);
    }

    @Test
    public void parseNotEnd() {
        StringStream input = new StringStream("10 + 3 * 5 abc");

        ExpressionParser parser = new ExpressionParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), false);
    }

    @Test
    public void parsePartial() {
        StringStream input = new StringStream("10 +");

        ExpressionParser parser = new ExpressionParser();
        Result<Expr> result = parser.parse(input);

        Assertions.assertEquals(result.isOK(), false);
    }

}