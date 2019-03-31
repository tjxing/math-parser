package com.tjxing.math.parser;

import com.tjxing.math.def.IntNumber;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.combinator.CharParser;
import com.tjxing.parser.combinator.OptionalParser;
import com.tjxing.parser.combinator.RangeParser;
import com.tjxing.parser.result.Result;

public class IntParser extends Parser<IntNumber> {

    final Parser<IntNumber> parser;

    public IntParser() {
        Parser<IntNumber> p = new OptionalParser<Character>(new CharParser('-'))
                .next(new RangeParser(0, 9))
                .next(new RangeParser(0, 9).repeat())
                .map(t -> {
                    int num = Character.digit(t._1()._2(), 10);
                    for(Character c : t._2()) {
                        num = 10 * num + Character.digit(c, 10);
                    }
                    final int N = num;
                    return new IntNumber(t._1()._1().map(c -> -N).orElse(N));
                });
        this.parser = new IgnoreParser().next(p).next(new IgnoreParser())
                .map(t -> t._1()._2());
    }

    @Override
    public Result<IntNumber> parse(StringStream input) {
        return parser.parse(input);
    }

}
