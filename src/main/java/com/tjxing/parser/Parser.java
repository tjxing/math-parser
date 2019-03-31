package com.tjxing.parser;

import com.tjxing.parser.result.Error;
import com.tjxing.parser.result.OK;
import com.tjxing.parser.result.Result;
import com.tjxing.parser.util.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Parser<T> {

    public abstract Result<T> parse(StringStream input);

    public <U> Parser<U> map(Function<T, U> f) {
        return new Parser<U>() {

            @Override
            public Result<U> parse(StringStream input) {
                Result<T> res = Parser.this.parse(input);
                if(res.isOK()) {
                    return new OK<>(f.apply(res.getOrNull()), res.getNext());
                }
                return new Error<>(res.getErrorOrNull(), input);
            }

        };
    }

    public Parser<List<T>> repeat(int n) {
        return new Parser<List<T>>() {

            @Override
            public Result<List<T>> parse(StringStream input) {
                List<T> result = new ArrayList<>();
                StringStream in = input;
                for(int i = 0; i < n; ++i) {
                    Result<T> res = Parser.this.parse(in);
                    if(res.isOK()) {
                        result.add(res.getOrNull());
                        in = res.getNext();
                    } else {
                        return new Error<>(res.getErrorOrNull(), input);
                    }
                }
                return new OK<>(result, in);
            }

        };
    }

    public Parser<List<T>> repeat() {
        return new Parser<List<T>>() {

            @Override
            public Result<List<T>> parse(StringStream input) {
                List<T> result = new ArrayList<>();
                StringStream in = input;
                while(in.hasNext()) {
                    Result<T> res = Parser.this.parse(in);
                    if(res.isOK()) {
                        result.add(res.getOrNull());
                        in = res.getNext();
                    } else {
                        break;
                    }
                }
                return new OK<>(result, in);
            }

        };
    }

    public <U> Parser<Tuple2<T, U>> next(Parser<U> nextParser) {
        return new Parser<Tuple2<T, U>>() {

            @Override
            public Result<Tuple2<T, U>> parse(StringStream input) {
                Result<T> result = Parser.this.parse(input);
                if(!result.isOK()) {
                    return new Error<>(result.getErrorOrNull(), input);
                }

                Result<U> nextResult = nextParser.parse(result.getNext());
                if(nextResult.isOK()) {
                    return new OK<>(
                            new Tuple2<>(result.getOrNull(), nextResult.getOrNull()),
                            nextResult.getNext());
                }
                return new Error<>(nextResult.getErrorOrNull(), input);
            }

        };
    }

    public Parser<T> or(Parser<? extends T> other) {

        return new Parser<T>() {
            @Override
            public Result<T> parse(StringStream input) {
                StringBuilder error = new StringBuilder();

                Result<T> result = Parser.this.parse(input);
                if(result.isOK()) {
                    return result;
                } else {
                    error.append(result.getErrorOrNull());
                }

                Result<? extends T> otherResult = other.parse(input);
                if(otherResult.isOK()) {
                    return new OK<>(otherResult.getOrNull(), otherResult.getNext());
                } else {
                    error.append(" | ");
                    error.append(result.getErrorOrNull());
                }

                return new Error<>(error.toString(), input);
            }
        };

    }

}
