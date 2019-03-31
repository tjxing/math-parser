package com.tjxing.math;

import com.tjxing.math.def.Expr;
import com.tjxing.math.parser.ExprParser;
import com.tjxing.math.parser.IgnoreParser;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.combinator.EndParser;
import com.tjxing.parser.result.Result;

public class ExpressionParser extends Parser<Expr> {

    final Parser<Expr> parser;

    public ExpressionParser() {
        this.parser = new IgnoreParser()
                .next(new ExprParser())
                .next(new IgnoreParser())
                .next(new EndParser())
                .map(t -> t._1()._1()._2());
    }

    @Override
    public Result<Expr> parse(StringStream input) {
        return parser.parse(input);
    }

}
