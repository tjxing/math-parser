package com.tjxing.math.def;

public class IntNumber implements Number {

    final private Integer value;

    public IntNumber(Integer value) {
        this.value = value;
    }

    @Override
    public Integer caculate() {
        return value;
    }
}
