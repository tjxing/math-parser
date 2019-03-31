package com.tjxing.math.def;

public class DoubleNumber implements Number {

    final private Double value;

    public DoubleNumber(Double value) {
        this.value = value;
    }

    @Override
    public Double caculate() {
        return value;
    }

}
