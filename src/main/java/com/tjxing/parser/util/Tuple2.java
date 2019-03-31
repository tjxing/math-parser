package com.tjxing.parser.util;

public class Tuple2<T, U> {

    final T _1;
    final U _2;

    public Tuple2(T _1, U _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public T _1() {
        return _1;
    }

    public U _2() {
        return _2;
    }

}
