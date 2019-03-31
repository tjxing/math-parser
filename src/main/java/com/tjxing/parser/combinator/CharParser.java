package com.tjxing.parser.combinator;

import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Error;
import com.tjxing.parser.result.OK;
import com.tjxing.parser.result.Result;

public class CharParser extends Parser<Character> {

    final private char target;

    public CharParser(char target) {
        this.target = target;
    }

    @Override
    public Result<Character> parse(StringStream input) {
        if(input.hasNext() && target == input.head()) {
            return new OK<>(target, input.next());
        }
        return new Error<>("Not match " + target, input);
    }

}
