package com.tjxing.math.parser;

import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.combinator.CharParser;
import com.tjxing.parser.result.Result;

public class IgnoreParser extends Parser<Void> {

    final Parser<Void> parser;

    public IgnoreParser() {
        this.parser = new CharParser(' ')
                .or(new CharParser('\t'))
                .or(new CharParser('\n'))
                .or(new CharParser('\r'))
                .repeat()
                .map(l -> null);
    }

    @Override
    public Result<Void> parse(StringStream input) {
        return parser.parse(input);
    }

}
