package com.tjxing.parser.combinator;

import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.result.Result;
import com.tjxing.parser.util.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class GroupParser<T> extends Parser<List<T>> {

    final private Parser<List<T>> parser;

    public GroupParser(Parser<T>... parsers) {
        if(parsers.length < 2) {
            throw new IllegalArgumentException();
        }

        Parser<List<T>> p = parsers[0].next(parsers[1]).map((Tuple2<T, T> t) -> {
            List<T> result = new ArrayList<>();
            result.add(t._1());
            result.add(t._2());
            return result;
        });
        for(int i = 2; i < parsers.length; ++i) {
            p = p.next(parsers[i]).map((Tuple2<List<T>, T> t) -> {
               t._1().add(t._2());
               return t._1();
            });
        }

        this.parser = p;
    }

    @Override
    public Result<List<T>> parse(StringStream input) {
        return parser.parse(input);
    }

}
