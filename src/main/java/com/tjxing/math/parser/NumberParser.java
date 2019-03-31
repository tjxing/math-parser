package com.tjxing.math.parser;

import com.tjxing.math.def.Number;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;

public class NumberParser extends Parser<Number> {

    final Parser<Number> parser;

    public NumberParser() {
        this.parser = new DoubleParser().map(n -> (Number) n).or(new IntParser());
    }

    @Override
    public Result<Number> parse(StringStream input) {
        return parser.parse(input);
    }

}
