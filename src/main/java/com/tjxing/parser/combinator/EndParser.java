package com.tjxing.parser.combinator;

import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Error;
import com.tjxing.parser.result.OK;
import com.tjxing.parser.result.Result;

public class EndParser extends Parser<Void> {

    @Override
    public Result<Void> parse(StringStream input) {
        if(input.hasNext()) {
            return new Error<>("", input);
        }
        return new OK<>(null, input);
    }

}
