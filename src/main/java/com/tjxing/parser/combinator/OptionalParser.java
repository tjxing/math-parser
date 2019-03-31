package com.tjxing.parser.combinator;

import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.OK;
import com.tjxing.parser.result.Result;

import java.util.Optional;

public class OptionalParser<T> extends Parser<Optional<T>> {

    final private Parser<T> parser;

    public OptionalParser(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public Result<Optional<T>> parse(StringStream input) {
        Result<T> result = parser.parse(input);
        if(result.isOK()) {
            return new OK<>(result.get(), result.getNext());
        }
        return new OK<>(Optional.empty(), input);
    }

}
