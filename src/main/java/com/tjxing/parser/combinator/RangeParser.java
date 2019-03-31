package com.tjxing.parser.combinator;

import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;

import java.util.List;

public class RangeParser extends Parser<Character> {

    final Parser<Character> parser;

    public RangeParser(int x, int y) {
        if(x <0 || x >9 || y <0 || y >9) {
            throw new IllegalArgumentException();
        }
        this.parser = initialize(Character.forDigit(x, 10), Character.forDigit(y, 10));
    }

    public RangeParser(char x, char y) {
        this.parser = initialize(x, y);
    }

    private Parser<Character> initialize(char x, char y) {
        if(x > y) {
            char temp = x;
            x = y; y = temp;
        }
        Parser<Character> p = new CharParser(x);
        for(int i = x + 1; i < y; ++i) {
            p = p.or(new CharParser((char) i));
        }
        return p;
    }

    @Override
    public Result<Character> parse(StringStream input) {
        return parser.parse(input);
    }

}
