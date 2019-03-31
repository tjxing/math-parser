package com.tjxing.parser.result;


import com.tjxing.parser.StringStream;

import java.util.Optional;

public class OK<T> implements Result<T> {

    final private T value;
    final private StringStream next;

    public OK(T value, StringStream next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public boolean isOK() {
        return true;
    }

    @Override
    public Optional<T> get() {
        return Optional.ofNullable(value);
    }

    @Override
    public Optional<String> getError() {
        return Optional.empty();
    }

    @Override
    public StringStream getNext() {
        return next;
    }

}
