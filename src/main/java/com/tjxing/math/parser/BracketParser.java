package com.tjxing.math.parser;

import com.tjxing.math.def.Expr;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.combinator.CharParser;
import com.tjxing.parser.result.Result;

import java.util.Optional;

public class BracketParser extends Parser<Expr> {

    static Optional<Parser<Expr>> parser = Optional.empty();

    @Override
    public Result<Expr> parse(StringStream input) {
        Parser<Expr> p = null;
        synchronized (parser) {
            p = parser.orElseGet(() -> {
                Parser<Void> begin = new IgnoreParser().next(new CharParser('(')).next(new IgnoreParser())
                        .map(t -> null);
                Parser<Void> end = new IgnoreParser().next(new CharParser(')')).next(new IgnoreParser())
                        .map(t -> null);
                this.parser = Optional.of(begin.next(new ExprParser()).next(end).map(t -> t._1()._2()));
                return this.parser.get();
            });
        }
        return p.parse(input);
    }

}
