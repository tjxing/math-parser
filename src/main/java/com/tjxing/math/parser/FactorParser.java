package com.tjxing.math.parser;

import com.tjxing.math.def.Expr;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;

public class FactorParser extends Parser<Expr> {

    final Parser<Expr> parser;

    public FactorParser() {
        this.parser = new NumberParser().map(n -> (Expr) n)
                .or(new BracketParser());
    }

    @Override
    public Result<Expr> parse(StringStream input) {
        return parser.parse(input);
    }

}
