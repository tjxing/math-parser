package com.tjxing.math.parser;

import com.tjxing.math.def.DoubleNumber;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.combinator.CharParser;
import com.tjxing.parser.combinator.OptionalParser;
import com.tjxing.parser.combinator.RangeParser;
import com.tjxing.parser.result.Result;

public class DoubleParser extends Parser<DoubleNumber> {

    final Parser<DoubleNumber> parser;

    public DoubleParser() {
        Parser<DoubleNumber> p = new OptionalParser<Character>(new CharParser('-'))
                .next(new RangeParser(0, 9).repeat())
                .next(new CharParser('.'))
                .next(new RangeParser(0, 9).repeat())
                .map(t -> {
                    double doubleValue = 0.0;
                    double base = 0.1;
                    for(char c : t._2()) {
                        doubleValue += Character.digit(c, 10) * base;
                        base /= 10;
                    }
                    int intValue = 0;
                    for(char c : t._1()._1()._2()) {
                        intValue = intValue * 10 + Character.digit(c, 10);
                    }
                    final double result = intValue + doubleValue;
                    return new DoubleNumber(t._1()._1()._1().map(c -> -result).orElse(result));
                });
        this.parser = new IgnoreParser().next(p).next(new IgnoreParser())
                .map(t -> t._1()._2());
    }

    @Override
    public Result<DoubleNumber> parse(StringStream input) {
        return parser.parse(input);
    }

}
