package com.tjxing.math.parser;

import com.tjxing.math.def.Expr;
import com.tjxing.math.def.Operation;
import com.tjxing.parser.Parser;
import com.tjxing.parser.StringStream;
import com.tjxing.parser.combinator.CharParser;
import com.tjxing.parser.result.Result;
import com.tjxing.parser.util.Tuple2;

public class TermParser extends Parser<Expr> {

    final Parser<Expr> parser;

    public TermParser() {
        Parser<Tuple2<Operation.Oper, Expr>> p = new IgnoreParser()
                .next(new CharParser('*').or(new CharParser('/')))
                .next(new IgnoreParser())
                .next(new FactorParser())
                .map(t -> {
                    Expr expr = t._2();
                    Character c = t._1()._1()._2();
                    Operation.Oper op = c == '*' ? Operation.Oper.TIMES : Operation.Oper.DIV;
                    return new Tuple2(op, expr);
                });
        this.parser = new FactorParser().next(p.repeat())
                .map(t -> {
                    Expr expr = t._1();
                    for(Tuple2<Operation.Oper, Expr> op : t._2()) {
                        expr = new Operation(expr, op._1(), op._2());
                    }
                    return expr;
                });
    }

    @Override
    public Result<Expr> parse(StringStream input) {
        return parser.parse(input);
    }

}
