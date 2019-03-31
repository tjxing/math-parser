package com.tjxing.parser.util;

public class Tuple3<T, U, V> {

    final private T _1;
    final private U _2;
    final private V _3;

    public Tuple3(T _1, U _2, V _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }

    public T _1() {
        return _1;
    }

    public U _2() {
        return _2;
    }

    public V _3() {
        return _3;
    }

}
